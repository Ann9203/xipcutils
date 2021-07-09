package com.xue.data.xipcutils.cor;

import android.text.TextUtils;

import com.xue.data.xipcutils.bean.RequestBean;
import com.xue.data.xipcutils.bean.RequestParameter;
import com.xue.data.xipcutils.utils.TypeUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ProjectName: AndroidStudyDemoJ
 * @Package: com.xue.data.xipcutils.cor
 * @ClassName: TypeCenter
 * @Description: 请求参数  类 中转站
 * @Author: 李雪
 * @CreateDate: 2021-07-08 17:16
 * @UpdateUser:
 * @UpdateDate: 2021-07-08 17:16
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public class TypeCenter {
    private static TypeCenter instance;
    private final ConcurrentHashMap<String, Class<?>> clazzMap = new ConcurrentHashMap<>();

    /**
     * 减少反射 存储本地
     */
    private final ConcurrentHashMap<Class<?>, ConcurrentHashMap<String, Method>> rawMethodsMap = new ConcurrentHashMap<>();

    public static TypeCenter getInstance() {
        if (instance == null) {
            synchronized (TypeCenter.class) {
                instance = new TypeCenter();
            }
        }
        return instance;
    }

    private TypeCenter() {
    }

    /**
     * 注册
     * 1.注册类
     * 2.注册类\方法
     * @param clazz
     */
    public void register(Class<?> clazz){
        registerClass(clazz);
        registerMethods(clazz);
    }

    /**
     * 注册类名
     * @param clazz
     */
    private void registerClass(Class<?> clazz) {
        String name = clazz.getName();
        clazzMap.putIfAbsent(name, clazz);
    }

    /**
     * 注册方法
     * @param clazz
     */
    private void registerMethods(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        for (Method method:methods){
            rawMethodsMap.putIfAbsent(clazz, new ConcurrentHashMap<>());
            ConcurrentHashMap<String, Method> tempMethodMap = new ConcurrentHashMap<>();
            String methodId = TypeUtils.getMethodId(method);
            tempMethodMap.putIfAbsent(methodId, method);
        }
    }

    /**
     * 根据类名 获取 返回类的 类型
     * @param name
     * @return
     */
    public Class<?> getClassType(String name){
        if (TextUtils.isEmpty(name)){
            return null;
        }
        Class<?> clazz = clazzMap.get(name);
        if (clazz== null){
            try {
                clazz = Class.forName(name);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return clazz;
    }

    public Method getMethod(Class<?> aClass, RequestBean requestBean){
        String methodName = requestBean.getMethodName();
        if (methodName != null){
            rawMethodsMap.putIfAbsent(aClass, new ConcurrentHashMap<>());
            ConcurrentHashMap<String, Method> conMethod = rawMethodsMap.get(aClass);
            Method method = conMethod.get(methodName);
            if (method != null){
                return method;
            }
            int post = methodName.indexOf("(");
            Class[] params = null;
            RequestParameter[] requestParameters = requestBean.getRequestParameters();
            if (requestParameters != null && requestParameters.length > 0){
                params = new Class[requestParameters.length];
                for (int i =0; i < requestParameters.length; i++){
                    params[i] = getClassType(requestParameters[i].getParameterClassName());
                }
            } else {
                params = new Class[0];
            }
            method = TypeUtils.getMethod(aClass, methodName.substring(0, post), params);
            conMethod.put(methodName,method);
            return method;
        }

        return null;

    }



}

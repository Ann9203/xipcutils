package com.xue.data.xipcutils.cor;

import android.content.Context;

import com.google.gson.Gson;
import com.xue.data.xipcutils.aidl.Request;
import com.xue.data.xipcutils.aidl.Response;
import com.xue.data.xipcutils.annotation.ClassId;
import com.xue.data.xipcutils.bean.RequestBean;
import com.xue.data.xipcutils.bean.RequestParameter;
import com.xue.data.xipcutils.utils.TypeUtils;

import org.json.JSONObject;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ProjectName: AndroidStudyDemoJ
 * @Package: com.xue.data.xipcutils.cor
 * @ClassName: XIPC
 * @Description: 单例入口
 * @Author: 李雪
 * @CreateDate: 2021-07-08 17:15
 * @UpdateUser:
 * @UpdateDate: 2021-07-08 17:15
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public class XIPC {
    private static  XIPC instance;
    private XIPCServiceConnectionManager xipcServiceConnectionManager;
    private TypeCenter typeCenter;
    private Context context;
    private final static Gson gson = new Gson();

    public static final int TYPE_NEW= 0;//对象
    public static final int TYPE_GET = 1;//单例


    public static  XIPC getDefault(){
        if (instance == null){
            synchronized (XIPC.class){
                instance = new XIPC();
            }
        }
        return instance;
    }

    private  XIPC() {
        xipcServiceConnectionManager = XIPCServiceConnectionManager.getInstance();
        typeCenter = TypeCenter.getInstance();
    }
    public void init(Context context){
        this.context = context.getApplicationContext();
    }

    /**
     * 服务端注册 单例到binder中
     * @param clazz
     */
    public void register(Class<?> clazz){
        typeCenter.register(clazz);
    }

    /**
     * 链接服务
     */
    public void connect(Context context, Class<? extends XIPCService> xipcService){
        connectApp(context, null, xipcService);
    }

    /**
     * 链接服务
     * @param context
     * @param packageName
     * @param xipcService
     */
    public void connect(Context context, String packageName,Class<? extends XIPCService> xipcService ){
        connectApp(context, packageName, xipcService);
    }

    /**
     * 绑定服务
     * @param context
     * @param packageName
     * @param xipcService
     */
    private void connectApp(Context context, String packageName, Class<? extends XIPCService> xipcService) {
        init(context);
        xipcServiceConnectionManager.bind(context.getApplicationContext(),packageName, xipcService);
    }

    /**
     * 获取服务端的单例方法
     * @param clazz
     * @param params
     * @param <T>
     * @return
     */
    public <T> T getInstance(Class<T> clazz, Object ... params){
        sendRequest(XIPCService.class, clazz, null, params);
        return getProxy(XIPCService.class, clazz);
    }

    private <T> T getProxy(Class<XIPCService> xipcServiceClass, Class<T> clazz) {
        ClassLoader classLoader = xipcServiceClass.getClassLoader();
        T proxy = (T) Proxy.newProxyInstance(classLoader, new Class[]{clazz},new XIPCInvocationHandler(clazz, xipcServiceClass));
        return proxy;
    }

    /**
     * 发送请求
     * @param xipcServiceClass
     * @param clazz
     * @param method
     * @param params
     * @param <T>
     */
    private <T> Response  sendRequest(Class<XIPCService> xipcServiceClass, Class<T> clazz, Method method, Object[] params) {
        RequestBean requestBean = new RequestBean();
        if (clazz.getAnnotation(ClassId.class) == null){
            requestBean .setClassName(clazz.getName());
            requestBean.setResultClassName(clazz.getName());
        } else {
            requestBean.setClassName(clazz.getAnnotation(ClassId.class).value());
            requestBean.setResultClassName(clazz.getAnnotation(ClassId.class).value());
        }
        if (method != null){
            //获取methodId
            requestBean.setMethodName(TypeUtils.getMethodId(method));
        }
        RequestParameter[] requestParameters = null;
        if (params!= null &&params.length > 0){
            requestParameters = new RequestParameter[params.length];
            for (int i =0; i < params.length;i++){
                Object param = params[i];
                String parameValue =gson.toJson(param) ;
                RequestParameter requestParameter = new RequestParameter(param.getClass().getName(),parameValue );
                requestParameters[i] = requestParameter;
            }
        }
        if (requestParameters != null){
            requestBean.setRequestParameters(requestParameters);
        }
        Request request = new Request(gson.toJson(requestBean),TYPE_GET);
        return xipcServiceConnectionManager.request(xipcServiceClass,request);
    }


    public<T> Response sendObjectRequest(Class xIPCService, Class<T> clazz, Method method, Object[] args){
        RequestBean requestBean = new RequestBean();
        if (clazz.getAnnotation(ClassId.class) == null){
            requestBean .setClassName(clazz.getName());
            requestBean.setResultClassName(clazz.getName());
        } else {
            requestBean.setClassName(clazz.getAnnotation(ClassId.class).value());
            requestBean.setResultClassName(clazz.getAnnotation(ClassId.class).value());
        }
        if (method != null){
            //获取methodId
            requestBean.setMethodName(TypeUtils.getMethodId(method));
        }
        RequestParameter[] requestParameters = null;
        if (args!= null &&args.length > 0){
            requestParameters = new RequestParameter[args.length];
            for (int i =0; i < args.length;i++){
                Object param = args[i];

                String parameValue =gson.toJson(param) ;
                RequestParameter requestParameter = new RequestParameter(param.getClass().getName(),parameValue );
                requestParameters[i] = requestParameter;
            }
        }
        if (requestParameters != null){
            requestBean.setRequestParameters(requestParameters);
        }
        Request request = new Request(gson.toJson(requestBean),TYPE_NEW);
        return xipcServiceConnectionManager.request(xIPCService,request);
    }
}

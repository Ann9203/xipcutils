package com.xue.data.xipcutils.cor;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.xue.data.xipcutils.aidl.Response;
import com.xue.data.xipcutils.bean.ResponseBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ProjectName: AndroidStudyDemoJ
 * @Package: com.xue.data.xipcutils.cor
 * @ClassName: XIPCInvocationHandler
 * @Description: 动态代理
 * @Author: 李雪
 * @CreateDate: 2021-07-08 17:16
 * @UpdateUser:
 * @UpdateDate: 2021-07-08 17:16
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public class XIPCInvocationHandler implements InvocationHandler {
    private Class aClazz;
    private Class xIPCService;
    private Gson gson = new Gson();
    public XIPCInvocationHandler(Class aClazz, Class xIPCService) {
        this.aClazz = aClazz;
        this.xIPCService = xIPCService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Response response = XIPC.getDefault().sendObjectRequest(xIPCService, aClazz,method, args);
        if (response != null  && !TextUtils.isEmpty(response.getData())){
            ResponseBean responseBean =gson.fromJson(response.getData(),ResponseBean.class);
            if (responseBean.getData() != null){
                Object responseObj = responseBean.getData();
                if (responseObj !=null){
                    String data  = gson.toJson(responseObj);
                    Class returnType = method.getReturnType();
                    Object obj = gson.fromJson(data, returnType);
                    return obj;
                }

                return null;
            }
        }
        return null;
    }
}

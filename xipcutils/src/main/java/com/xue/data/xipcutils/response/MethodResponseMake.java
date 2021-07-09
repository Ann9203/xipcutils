package com.xue.data.xipcutils.response;

import com.xue.data.xipcutils.bean.RequestBean;
import com.xue.data.xipcutils.cor.TypeCenter;
import com.xue.data.xipcutils.utils.TypeUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: lixue
 * @time : 2021/7/8
 * @email : lixue326@163.com
 * @detail:
 */
public class MethodResponseMake extends ResponseMake{
    private Method method;
    private Object object;
    @Override
    protected Object invokeMethod() {
        try {
           return method.invoke(object,params);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void setMethod(RequestBean requestBean) {
        object = OBJECT_CENTER.getObject(resultClass.getName());
        method =TypeCenter.getInstance().getMethod(object.getClass(), requestBean);
    }
}

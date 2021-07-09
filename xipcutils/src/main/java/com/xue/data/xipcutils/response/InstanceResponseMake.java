package com.xue.data.xipcutils.response;

import android.util.Log;

import com.xue.data.xipcutils.bean.RequestBean;
import com.xue.data.xipcutils.bean.RequestParameter;
import com.xue.data.xipcutils.cor.TypeCenter;
import com.xue.data.xipcutils.utils.TypeUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @ProjectName: AndroidStudyDemoJ
 * @Package: com.xue.data.xipcutils.response
 * @ClassName: InstanceResponseMake
 * @Description:
 * @Author: 李雪
 * @CreateDate: 2021-07-08 19:41
 * @UpdateUser:
 * @UpdateDate: 2021-07-08 19:41
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public class InstanceResponseMake extends ResponseMake{
    private Method method;
    @Override
    protected Object invokeMethod() {
        Object o = null;
        try {
            o = method.invoke(null,params);
            Log.e("TAG", "invokeMethod: "+o.toString());
            //保存对象
            OBJECT_CENTER.putObj(o.getClass().getName(), o);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void setMethod(RequestBean requestBean) {
        RequestParameter[] requestParameters = requestBean.getRequestParameters();
        Class<?>[] params = null;
        if (requestParameters!=null && requestParameters.length > 0) {
            params = new Class[requestParameters.length];
            for (int i = 0; i < requestParameters.length; i++) {
                params[i] = TypeCenter.getInstance().getClassType(requestParameters[i].getParameterClassName());
            }
        }
        String methodName = requestBean.getMethodName();
        Method tempMethod = TypeUtils.getMethodGetInstance(resultClass, methodName, params);
        method = tempMethod;

    }
}

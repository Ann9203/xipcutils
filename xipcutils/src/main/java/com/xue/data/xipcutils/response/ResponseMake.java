package com.xue.data.xipcutils.response;

import com.google.gson.Gson;
import com.xue.data.xipcutils.aidl.Request;
import com.xue.data.xipcutils.aidl.Response;
import com.xue.data.xipcutils.bean.RequestBean;
import com.xue.data.xipcutils.bean.RequestParameter;
import com.xue.data.xipcutils.bean.ResponseBean;
import com.xue.data.xipcutils.cor.ObjectCenter;
import com.xue.data.xipcutils.cor.TypeCenter;

/**
 * @ProjectName: AndroidStudyDemoJ
 * @Package: com.xue.data.xipcutils.response
 * @ClassName: ResponseMake
 * @Description:
 * @Author: 李雪
 * @CreateDate: 2021-07-08 19:26
 * @UpdateUser:
 * @UpdateDate: 2021-07-08 19:26
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public abstract class ResponseMake {
    //返回的 请求单例类
    protected Class<?> resultClass;
    //单例 getInstance 需要的参数
    protected Object[]  params;

    Gson gson = new Gson();
    protected TypeCenter typeCenter = TypeCenter.getInstance();

    protected  static final ObjectCenter OBJECT_CENTER = ObjectCenter.getInstance();

    protected  abstract  Object invokeMethod();
    protected  abstract  void setMethod(RequestBean requestBean);

    /**
     * 调用函数 返回 结果
     * @param request
     * @return
     */
    public Response makeResponse(Request request){
        RequestBean requestBean = gson.fromJson(request.getData(), RequestBean.class);
        resultClass = typeCenter.getClassType(requestBean.getResultClassName());
        //参数还原 object[]

        RequestParameter[] parameters = requestBean.getRequestParameters();
        if (parameters != null && parameters .length >0){
            params = new Object[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                RequestParameter param=parameters[i];
                Class<?> pClazz = typeCenter.getClassType(param.getParameterClassName());
                params[i] = gson.fromJson(param.getParameterValue(), pClazz);
            }
        } else {
            params = new Object[0];
        }
        setMethod(requestBean);
        Object resultObj = invokeMethod();
        //返回
        ResponseBean responseBean = new ResponseBean(resultObj);
        //把结果  徐丽华字符串
        String data = gson.toJson(responseBean);
        Response response = new Response(data);
        return response;

    }
}

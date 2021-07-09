package com.xue.data.xipcutils.bean;

/**
 * @ProjectName: AndroidStudyDemoJ
 * @Package: com.xue.data.xipcutils.bean
 * @ClassName: RequestBean
 * @Description: 请求内容
 * @Author: 李雪
 * @CreateDate: 2021-07-08 17:12
 * @UpdateUser:
 * @UpdateDate: 2021-07-08 17:12
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public class RequestBean {
    private String className;//请求单例的类全名
    private String resultClassName;//类名
    private String methodName;//返回方法名;
    private RequestParameter[]  requestParameters;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getResultClassName() {
        return resultClassName;
    }

    public void setResultClassName(String resultClassName) {
        this.resultClassName = resultClassName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public RequestParameter[] getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(RequestParameter[] requestParameters) {
        this.requestParameters = requestParameters;
    }
}

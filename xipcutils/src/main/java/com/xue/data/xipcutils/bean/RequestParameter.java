package com.xue.data.xipcutils.bean;

/**
 * @ProjectName: AndroidStudyDemoJ
 * @Package: com.xue.data.xipcutils.bean
 * @ClassName: RequestParamsName
 * @Description:
 * @Author: 李雪
 * @CreateDate: 2021-07-08 17:13
 * @UpdateUser:
 * @UpdateDate: 2021-07-08 17:13
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public class RequestParameter {
    private String parameterClassName;
    private String parameterValue;

    public RequestParameter(String parameterClassName, String parameterValue) {
        this.parameterClassName = parameterClassName;
        this.parameterValue = parameterValue;
    }

    public String getParameterClassName() {
        return parameterClassName;
    }

    public void setParameterClassName(String parameterClassName) {
        this.parameterClassName = parameterClassName;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }
}

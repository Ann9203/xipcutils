package com.xue.data.xipcutils.bean;

/**
 * @ProjectName: AndroidStudyDemoJ
 * @Package: com.xue.data.xipcutils.bean
 * @ClassName: ResponseBean
 * @Description:
 * @Author: 李雪
 * @CreateDate: 2021-07-08 19:21
 * @UpdateUser:
 * @UpdateDate: 2021-07-08 19:21
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public class ResponseBean {
    private Object data;

    public ResponseBean(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

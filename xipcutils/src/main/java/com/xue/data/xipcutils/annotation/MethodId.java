package com.xue.data.xipcutils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName: AndroidStudyDemoJ
 * @Package: com.xue.data.xipcutils.annotation
 * @ClassName: MethodId
 * @Description:
 * @Author: 李雪
 * @CreateDate: 2021-07-08 17:11
 * @UpdateUser:
 * @UpdateDate: 2021-07-08 17:11
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodId {
    String value();
}

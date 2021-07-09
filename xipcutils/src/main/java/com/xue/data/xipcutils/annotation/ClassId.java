package com.xue.data.xipcutils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName: AndroidStudyDemoJ
 * @Package: com.xue.data.xipcutils.annotation
 * @ClassName: ClassId
 * @Description:
 * @Author: 李雪
 * @CreateDate: 2021-07-08 17:10
 * @UpdateUser:
 * @UpdateDate: 2021-07-08 17:10
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ClassId {
    String value();

}

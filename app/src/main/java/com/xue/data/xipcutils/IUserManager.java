package com.xue.data.xipcutils;


import com.xue.data.xipcutils.annotation.ClassId;
import com.xue.data.xipcutils.annotation.MethodId;

/**
 * @ProjectName: AndroidStudyDemoJ
 * @Package: com.xue.data.androidstudydemoj
 * @ClassName: IUserManager
 * @Description:
 * @Author: 李雪
 * @CreateDate: 2021-07-09 9:31
 * @UpdateUser:
 * @UpdateDate: 2021-07-09 9:31
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
@ClassId(value = "com.xue.data.xipcutils.UserManager")
public interface IUserManager {

    @MethodId(value = "getPeopleInfo")
    PeopleInfo getPeopleInfo();

    @MethodId(value = "getPeopleName")
    String getPeopleName();

    @MethodId(value = "setPeopleInfo")
    void setPeopleInfo(PeopleInfo temp);


}

package com.xue.data.xipcutils;

import com.xue.data.xipcutils.annotation.ClassId;
import com.xue.data.xipcutils.annotation.MethodId;

/**
 * @ProjectName: AndroidStudyDemoJ
 * @Package: com.xue.data.androidstudydemoj.mbus
 * @ClassName: UserManager
 * @Description:
 * @Author: 李雪
 * @CreateDate: 2021-07-09 9:15
 * @UpdateUser:
 * @UpdateDate: 2021-07-09 9:15
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
@ClassId(value ="com.xue.data.xipcutils.UserManager" )
public class UserManager {
    private static UserManager instance;
    private PeopleInfo peopleInfo;

    @MethodId(value = "getInstance")
    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    @MethodId(value = "getPeopleInfo")
    public PeopleInfo getPeopleInfo() {

        return peopleInfo;
    }

    @MethodId(value = "getPeopleName")
    public  String getPeopleName(){
        return peopleInfo != null ? peopleInfo.getName() : "没有人类";
    }

    @MethodId(value = "setPeopleInfo")
    public void setPeopleInfo( PeopleInfo temp) {
        if (peopleInfo == null) {
            peopleInfo = new PeopleInfo();
        }
        peopleInfo.setAddress(temp.getAddress());
        peopleInfo.setAge(temp.getAge());
        peopleInfo.setSex(temp.getSex());
        peopleInfo.setName(temp.getName());
    }

}

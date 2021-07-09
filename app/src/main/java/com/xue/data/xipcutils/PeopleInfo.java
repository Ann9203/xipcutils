package com.xue.data.xipcutils;

/**
 * @ProjectName: AndroidStudyDemoJ
 * @Package: com.xue.data.androidstudydemoj
 * @ClassName: PeopleInfo
 * @Description:
 * @Author: 李雪
 * @CreateDate: 2021-07-09 9:16
 * @UpdateUser:
 * @UpdateDate: 2021-07-09 9:16
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public class PeopleInfo {
    private String name;
    private String sex;
    private String address;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "PeopleInfo{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}

package com.xue.data.xipcutils.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @ProjectName: AndroidStudyDemoJ
 * @Package: com.xue.data.xipcutils
 * @ClassName: Request
 * @Description:
 * @Author: 李雪
 * @CreateDate: 2021-07-08 17:02
 * @UpdateUser:
 * @UpdateDate: 2021-07-08 17:02
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public class Request  implements Parcelable {
    private String data; //请求对象. RequestBean对应的是JSON字符串
    private int Type;//请求对象类型

    public Request(String data, int type) {
        this.data = data;
        Type = type;
    }

    protected Request(Parcel in) {
        data = in.readString();
        Type = in.readInt();
    }

    public static final Creator<Request> CREATOR = new Creator<Request>() {
        @Override
        public Request createFromParcel(Parcel in) {
            return new Request(in);
        }

        @Override
        public Request[] newArray(int size) {
            return new Request[size];
        }
    };

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(data);
        dest.writeInt(Type);
    }
}

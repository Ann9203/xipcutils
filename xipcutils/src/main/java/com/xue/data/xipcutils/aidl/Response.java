package com.xue.data.xipcutils.aidl;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @ProjectName: AndroidStudyDemoJ
 * @Package: com.xue.data.xipcutils
 * @ClassName: Response
 * @Description:
 * @Author: 李雪
 * @CreateDate: 2021-07-08 17:02
 * @UpdateUser:
 * @UpdateDate: 2021-07-08 17:02
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public class Response implements Parcelable {
    private String data;

    public Response(String data) {
        this.data = data;
    }

    protected Response(Parcel in) {
        data = in.readString();
    }

    public static final Creator<Response> CREATOR = new Creator<Response>() {
        @Override
        public Response createFromParcel(Parcel in) {
            return new Response(in);
        }

        @Override
        public Response[] newArray(int size) {
            return new Response[size];
        }
    };

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(data);
    }
}

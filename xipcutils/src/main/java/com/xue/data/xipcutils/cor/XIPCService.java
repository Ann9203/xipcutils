package com.xue.data.xipcutils.cor;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.xue.data.xipcutils.aidl.IXIPCAidlInterface;
import com.xue.data.xipcutils.aidl.Request;
import com.xue.data.xipcutils.aidl.Response;
import com.xue.data.xipcutils.response.InstanceResponseMake;
import com.xue.data.xipcutils.response.MethodResponseMake;
import com.xue.data.xipcutils.response.ResponseMake;

/**
 * @ProjectName: AndroidStudyDemoJ
 * @Package: com.xue.data.xipcutils.cor
 * @ClassName: XIPCService
 * @Description: 注册一个service服务
 * @Author: 李雪
 * @CreateDate: 2021-07-08 17:15
 * @UpdateUser:
 * @UpdateDate: 2021-07-08 17:15
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public class XIPCService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    /**
     *注册binder
     */
    private IXIPCAidlInterface.Stub binder = new IXIPCAidlInterface.Stub() {
        @Override
        public Response send(Request request) throws RemoteException {
            int type = request.getType();
            ResponseMake responseMake = null;
            switch (type){
                case XIPC.TYPE_GET:
                    responseMake= new InstanceResponseMake();
                    break;
                case XIPC.TYPE_NEW:
                    responseMake = new MethodResponseMake();
                    break;
            }

            return responseMake.makeResponse(request);
        }
    };
}

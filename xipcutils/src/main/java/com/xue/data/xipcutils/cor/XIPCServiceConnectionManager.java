package com.xue.data.xipcutils.cor;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;

import com.xue.data.xipcutils.aidl.IXIPCAidlInterface;
import com.xue.data.xipcutils.aidl.Request;
import com.xue.data.xipcutils.aidl.Response;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @ProjectName: AndroidStudyDemoJ
 * @Package: com.xue.data.xipcutils.cor
 * @ClassName: XIPCServiceManager
 * @Description: 服务管理
 * @Author: 李雪
 * @CreateDate: 2021-07-08 17:15
 * @UpdateUser:
 * @UpdateDate: 2021-07-08 17:15
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public class XIPCServiceConnectionManager {
    private static  XIPCServiceConnectionManager instance;
    private static final ConcurrentHashMap<Class<? extends XIPCService> , IXIPCAidlInterface> xIpcServiceMap = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Class<? extends XIPCService>, XIPCServiceConnection > xIpcServiceConnectMap = new ConcurrentHashMap<>();
    public static  XIPCServiceConnectionManager getInstance(){
        if (instance == null){
            synchronized (XIPCServiceConnectionManager.class){
                instance = new XIPCServiceConnectionManager();
            }
        }
        return instance;
    }

    /**
     * 绑定服务
     * @param applicationContext
     * @param packageName
     * @param xipcService
     */
    public void bind(Context applicationContext, String packageName, Class<? extends XIPCService> xipcService) {
        XIPCServiceConnection xIpcServiceConnection = new XIPCServiceConnection(xipcService);
        xIpcServiceConnectMap.put(xipcService, xIpcServiceConnection);
        Intent intent ;
        if (TextUtils.isEmpty(packageName)){
            intent = new Intent(applicationContext, xipcService);
        } else {
            intent = new Intent();
            intent.setClassName(packageName, xipcService.getName());
        }
        applicationContext.bindService(intent,xIpcServiceConnection,Context.BIND_AUTO_CREATE);
    }

    /**
     * 进程通信 客户端发送请求, 服务端返回
     * @param xipcServiceClass
     * @param request
     * @return
     */
    public Response request(Class<XIPCService> xipcServiceClass, Request request) {
        IXIPCAidlInterface ixIpcAidlInterface = xIpcServiceMap.get(xipcServiceClass);
        if (ixIpcAidlInterface != null){
            try {
                Response response =   ixIpcAidlInterface.send(request);
                return response;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    private class XIPCServiceConnection implements ServiceConnection{
        private Class<? extends XIPCService> clazz;

        public XIPCServiceConnection(Class<? extends XIPCService> clazz) {
            this.clazz = clazz;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IXIPCAidlInterface ixipcAidlInterface = IXIPCAidlInterface.Stub.asInterface(service);
            xIpcServiceMap.put(clazz, ixipcAidlInterface);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            xIpcServiceMap.remove(clazz);
        }
    }
}

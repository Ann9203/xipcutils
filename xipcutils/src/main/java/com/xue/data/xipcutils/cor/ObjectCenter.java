package com.xue.data.xipcutils.cor;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @ProjectName: AndroidStudyDemoJ
 * @Package: com.xue.data.xipcutils.cor
 * @ClassName: ObjectCenter
 * @Description:
 * @Author: 李雪
 * @CreateDate: 2021-07-08 19:28
 * @UpdateUser:
 * @UpdateDate: 2021-07-08 19:28
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public class ObjectCenter {

    private static final String TAG = "ObjectCenter";
    private static volatile  ObjectCenter instance = null;
    private final ConcurrentHashMap<String, Object> objectMap ;

    public ObjectCenter() {
        objectMap =new ConcurrentHashMap<>();
    }
    public static ObjectCenter getInstance(){
        if (instance == null){
            synchronized (ObjectCenter.class){
                instance = new ObjectCenter();
            }
        }
        return instance;
    }

    public Object getObject(String name){
        return objectMap.get(name);
    }
    public void  putObj(String name, Object o){
        objectMap.put(name, o);
    }
}

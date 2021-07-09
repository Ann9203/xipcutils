package com.xue.data.xipcutils;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xue.data.xipcutils.cor.XIPC;
import com.xue.data.xipcutils.cor.XIPCService;

/**
 * @ProjectName: AndroidStudyDemoJ
 * @Package: com.xue.data.androidstudydemoj
 * @ClassName: SecondTwoThread
 * @Description:
 * @Author: 李雪
 * @CreateDate: 2021-07-06 11:43
 * @UpdateUser:
 * @UpdateDate: 2021-07-06 11:43
 * @UpdateRemark: 更新说明
 * @Version: 1.0.0
 */
public class SecondActivity extends AppCompatActivity {

    private TextView tvText;
    private IUserManager userManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tvText = this.findViewById(R.id.tv_text);
        XIPC.getDefault().connect(this, XIPCService.class);

    }

    public void send(View view) {
        userManager = XIPC.getDefault().getInstance(IUserManager.class);
        tvText.setText("姓名: " + userManager.getPeopleName() + "\n信息: " + userManager.getPeopleInfo().toString());
    }


    public void write(View view) {
        if (userManager != null) {
            PeopleInfo peopleInfo = new PeopleInfo();
            peopleInfo.setName("闪电侠");
            peopleInfo.setSex("男");
            peopleInfo.setAge("10");
            peopleInfo.setAddress("北京市糖豆区闪电侠");
            userManager.setPeopleInfo(peopleInfo);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            setResult(20);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }
}

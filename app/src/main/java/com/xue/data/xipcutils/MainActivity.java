package com.xue.data.xipcutils;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.xue.data.xipcutils.cor.XIPC;

public class MainActivity extends AppCompatActivity {

    private TextView tvText;
    private TextView tvNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvText = findViewById(R.id.tv_one);
        tvNew = this.findViewById(R.id.tv_new);

        PeopleInfo peopleInfo = new PeopleInfo();
        peopleInfo.setName("娃哈哈");
        peopleInfo.setSex("女");
        peopleInfo.setAge("16");
        peopleInfo.setAddress("北京市糖豆区闪电侠");
        UserManager.getInstance().setPeopleInfo(peopleInfo);
        tvText.setText("当前用户信息: \n"+UserManager.getInstance().getPeopleInfo());
        XIPC.getDefault().init(this);
        XIPC.getDefault().register(UserManager.class);

    }



    public void gotoSecond(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100){
            if (resultCode == 20) {
                tvNew.setText("子线程重新写入了用户信息\n 更新内容: \n" + UserManager.getInstance().getPeopleInfo().toString());
            }
        }
    }
}
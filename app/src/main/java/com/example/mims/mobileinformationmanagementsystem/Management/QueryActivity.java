package com.example.mims.mobileinformationmanagementsystem.Management;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mims.mobileinformationmanagementsystem.Database.MobileDataAdapter;
import com.example.mims.mobileinformationmanagementsystem.Mobile.Mobile;
import com.example.mims.mobileinformationmanagementsystem.R;

import java.util.Iterator;
import java.util.List;

public class QueryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        final TextView tv_name = findViewById(R.id.tv_query_name);
        final TextView tv_time = findViewById(R.id.tv_query_time);
        final TextView tv_country = findViewById(R.id.tv_query_country);
        final TextView tv_ceo = findViewById(R.id.tv_query_ceo);
        final EditText et_query = findViewById(R.id.et_query_query);
        final EditText et_introduce = findViewById(R.id.et_query_introduce);
        findViewById(R.id.btn_query_query).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query_information = et_query.getText().toString().trim();
                MobileDataAdapter adapter = new MobileDataAdapter(getApplicationContext());
                List<Mobile> list = adapter.query(query_information);
                Iterator<Mobile> iterator = list.iterator();
                if (TextUtils.isEmpty(query_information)){
                    Toast.makeText(getApplicationContext(),"文本框不可以留空，请重新检查,认真填写！",Toast.LENGTH_SHORT).show();
                }else {
                    while (iterator.hasNext()){
                        Mobile mobile = iterator.next();
                        tv_name.setText("品牌名称：" + mobile.getName());
                        tv_time.setText("建立时间：" + mobile.getTime());
                        tv_country.setText("所属国家：" + mobile.getCountry());
                        tv_ceo.setText("总裁：" + mobile.getCeo());
                        et_introduce.setText("品牌简介：" + mobile.getIntroduce());
                    }
                }
            }
        });
    }
}

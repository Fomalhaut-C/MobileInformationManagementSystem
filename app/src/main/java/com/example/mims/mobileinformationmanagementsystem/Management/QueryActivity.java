package com.example.mims.mobileinformationmanagementsystem.Management;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
        final TextView tv_introduce = findViewById(R.id.tv_query_introduce);
        final EditText et_query = findViewById(R.id.et_query_query);
        findViewById(R.id.btn_query_query).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query_information = et_query.getText().toString().trim();
                MobileDataAdapter adapter = new MobileDataAdapter(getApplicationContext());
                List<Mobile> list = adapter.query(query_information);
                Iterator<Mobile> iterator = list.iterator();
                while (iterator.hasNext()){
                    Mobile mobile = iterator.next();
                    tv_name.append("名称：" + mobile.getName());
                    tv_time.append("建立时间：" + mobile.getTime());
                    tv_country.append("所属国家：" + mobile.getCountry());
                    tv_ceo.append("总裁：" + mobile.getCeo());
                    tv_introduce.append("简介：" + mobile.getIntroduce());
                }
            }
        });
    }
}

package com.example.mims.mobileinformationmanagementsystem.Management;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mims.mobileinformationmanagementsystem.Database.MobileDataAdapter;
import com.example.mims.mobileinformationmanagementsystem.Mobile.Mobile;
import com.example.mims.mobileinformationmanagementsystem.R;


public class UpdateActivity extends AppCompatActivity {
    private EditText et_name,et_time,et_country,et_ceo,et_introduce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        et_name = findViewById(R.id.et_update_name);
        et_time = findViewById(R.id.et_update_time);
        et_country = findViewById(R.id.et_update_country);
        et_ceo = findViewById(R.id.et_update_ceo);
        et_introduce = findViewById(R.id.et_update_introduce);

        findViewById(R.id.btn_update_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et_name.getText().toString().trim();
                String time = et_time.getText().toString().trim();
                String country = et_country.getText().toString().trim();
                String ceo = et_ceo.getText().toString().trim();
                String introduce = et_introduce.getText().toString().trim();
                MobileDataAdapter adapter = new MobileDataAdapter(getApplicationContext());
                Mobile mobile = new Mobile();
                mobile.setName(name);
                mobile.setTime(time);
                mobile.setCountry(country);
                mobile.setCeo(ceo);
                mobile.setIntroduce(introduce);
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(time) || TextUtils.isEmpty(country) || TextUtils.isEmpty(ceo) || TextUtils.isEmpty(introduce)){
                    Toast.makeText(getApplicationContext(),"文本框不可以留空，请重新检查,认真填写！",Toast.LENGTH_SHORT).show();
                }else if (adapter.update(mobile) > 0){
                    Toast.makeText(getApplicationContext(),"修改成功",Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"修改失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

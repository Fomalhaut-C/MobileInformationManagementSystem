package com.example.mims.mobileinformationmanagementsystem.Management;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mims.mobileinformationmanagementsystem.Database.MobileDataAdapter;
import com.example.mims.mobileinformationmanagementsystem.Mobile.Mobile;
import com.example.mims.mobileinformationmanagementsystem.Mobile.MobileActivity;
import com.example.mims.mobileinformationmanagementsystem.R;

public class PlusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus);
        final EditText et_name = findViewById(R.id.et_plus_name);
        final EditText et_time = findViewById(R.id.et_plus_time);
        final EditText et_country = findViewById(R.id.et_plus_country);
        final EditText et_ceo = findViewById(R.id.et_plus_ceo);
        final EditText et_introduce = findViewById(R.id.et_plus_introduce);
        findViewById(R.id.btn_plus_plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mobile mobile = new Mobile();
                mobile.setName(et_name.getText().toString().trim());
                mobile.setTime(et_time.getText().toString().trim());
                mobile.setCountry(et_country.getText().toString().trim());
                mobile.setCeo(et_ceo.getText().toString().trim());
                mobile.setIntroduce(et_introduce.getText().toString().trim());
                MobileDataAdapter adapter = new MobileDataAdapter(getApplicationContext());
                if (adapter.insert(mobile) != -1){
                    Toast.makeText(getApplicationContext(),"添加成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MobileActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"添加失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

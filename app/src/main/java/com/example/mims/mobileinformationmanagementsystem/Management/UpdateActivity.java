package com.example.mims.mobileinformationmanagementsystem.Management;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mims.mobileinformationmanagementsystem.Database.MobileDataAdapter;
import com.example.mims.mobileinformationmanagementsystem.Mobile.Mobile;
import com.example.mims.mobileinformationmanagementsystem.R;


public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        final EditText et_name = findViewById(R.id.et_update_name);
        final EditText et_time = findViewById(R.id.et_update_time);
        final EditText et_country = findViewById(R.id.et_update_country);
        final EditText et_ceo = findViewById(R.id.et_update_ceo);
        final EditText et_introduce = findViewById(R.id.et_update_introduce);

        findViewById(R.id.btn_update_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobileDataAdapter adapter = new MobileDataAdapter(getApplicationContext());
                Mobile mobile = new Mobile();
                mobile.setName(et_name.getText().toString().trim());
                mobile.setTime(et_time.getText().toString().trim());
                mobile.setCountry(et_country.getText().toString().trim());
                mobile.setCeo(et_ceo.getText().toString().trim());
                mobile.setIntroduce(et_introduce.getText().toString().trim());
                if (adapter.update(mobile) > 0){
                    Toast.makeText(getApplicationContext(),"修改成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"修改失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

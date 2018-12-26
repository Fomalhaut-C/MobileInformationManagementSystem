package com.example.mims.mobileinformationmanagementsystem.Register;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mims.mobileinformationmanagementsystem.Login.LoginActivity;
import com.example.mims.mobileinformationmanagementsystem.R;


public class RegisterActivity extends AppCompatActivity {
    private EditText et_phoneNum,et_user,et_mail,et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
//        Long phoneNum = Long.valueOf(et_phoneNum.getText().toString().trim());
//        String user = et_user.getText().toString().trim();
//        String mail = et_mail.getText().toString().trim();
//        System.out.println(et_user.getText().toString());
//        System.out.println(mail);
//        final String password = et_password.getText().toString().trim();
//          final String rePassword = et_rePassword.getText().toString().trim();
        findViewById(R.id.btn_register_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (user.equals("") || mail.equals("") || password.equals("") || rePassword.equals("")){
                    Toast.makeText(getApplicationContext(),"文本框不可以留空，请重新检查,认真填写！",Toast.LENGTH_SHORT).show();
                }else if (!rePassword.equals(password)){
                    Toast.makeText(getApplicationContext(),"两次输入的密码不一致，请重新输入！",Toast.LENGTH_SHORT).show();
                }else {
                    Register();
                    Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_SHORT).show();
                }*/
                Register();
                Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void Register(){
        SharedPreferences.Editor editor = getSharedPreferences("AccountInfo", MODE_PRIVATE).edit();
        editor.putLong("phoneNum", Long.parseLong(et_phoneNum.getText().toString()));
        editor.putString("user", et_user.getText().toString());
        editor.putString("mail", et_mail.getText().toString());
        editor.putString("password", et_password.getText().toString());
        editor.apply();
        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void initView(){
        et_phoneNum = findViewById(R.id.et_register_phoneNum);
        et_user = findViewById(R.id.et_register_user);
        et_mail = findViewById(R.id.et_register_mail);
        et_password = findViewById(R.id.et_register_password);
//        et_rePassword = findViewById(R.id.et_register_rePassword);
    }
}
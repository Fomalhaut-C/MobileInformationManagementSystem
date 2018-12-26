package com.example.mims.mobileinformationmanagementsystem.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mims.mobileinformationmanagementsystem.MobileActivity;
import com.example.mims.mobileinformationmanagementsystem.R;

import com.example.mims.mobileinformationmanagementsystem.Register.RegisterActivity;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText et_account,et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_account = findViewById(R.id.et_login_account);
        et_password = findViewById(R.id.et_login_password);
        findViewById(R.id.tv_login_register).setOnClickListener(this);
        findViewById(R.id.btn_login_login).setOnClickListener(this);
        findViewById(R.id.rbtn_login_remember_pass).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_login_register:
                Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_login_login:
                Login();
                break;
            case R.id.rbtn_login_remember_pass:
                /*String account = sharedPreferences.getString("account","");
                String password = sharedPreferences.getString("password","");
                et_account.setText(account);
                et_password.setText(password);*/
                break;
        }
    }

    public void Login(){
        SharedPreferences register_sp;
        register_sp = getSharedPreferences("AccountInfo",MODE_PRIVATE);
        Long phoneNum = register_sp.getLong("phoneNum",1);
        String user = register_sp.getString("user","");
        String mail = register_sp.getString("mail","");
        String password = register_sp.getString("password","");
        String account = et_account.getText().toString().trim();
        String password1 = et_password.getText().toString().trim();
        if (account.equals(String.valueOf(phoneNum)) || account.equals(user) || account.equals(mail) && password1.equals(password)){
            Intent intent = new Intent(getApplicationContext(),MobileActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(getApplicationContext(),"帐号或者密码错误，请重新输入！",Toast.LENGTH_SHORT).show();
        }
    }
}

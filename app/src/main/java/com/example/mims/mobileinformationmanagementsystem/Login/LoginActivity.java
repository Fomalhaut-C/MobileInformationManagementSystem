package com.example.mims.mobileinformationmanagementsystem.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mims.mobileinformationmanagementsystem.MobileActivity;
import com.example.mims.mobileinformationmanagementsystem.R;

import com.example.mims.mobileinformationmanagementsystem.Register.RegisterActivity;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText et_account,et_password;
    private CheckBox login_remember;
    private SharedPreferences remember_sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_account = findViewById(R.id.et_login_account);
        et_password = findViewById(R.id.et_login_password);
        findViewById(R.id.tv_login_register).setOnClickListener(this);
        findViewById(R.id.btn_login_login).setOnClickListener(this);
        login_remember = findViewById(R.id.cbox_login_remember_password);
        login_remember.setOnClickListener(this);
        remember_sp = getSharedPreferences("RememberInfo",MODE_PRIVATE);
        Boolean isRemember = remember_sp.getBoolean("checkbox",false);
        if (isRemember){
            Long phoneNum = remember_sp.getLong("phoneNum",1);
            String user = remember_sp.getString("user","");
            String mail = remember_sp.getString("mail","");
            String password = remember_sp.getString("password","");
            if (phoneNum != 1){
                et_account.setText(String.valueOf(phoneNum));
            }else if (!TextUtils.isEmpty(user)){
                et_account.setText(user);
            }else if (!TextUtils.isEmpty(mail)){
                et_account.setText(mail);
            }
            et_password.setText(password);
            login_remember.setChecked(true);
        }else {
            login_remember.setChecked(false);
        }
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
                if (login_remember.isChecked()){
                    Remember();
                }else {
                    SharedPreferences.Editor editor = getSharedPreferences("RememberInfo", MODE_PRIVATE).edit();
                    editor.clear();
                    editor.apply();
                }
                Login();
                break;
        }
    }

    public void Login(){
        SharedPreferences register_sp = getSharedPreferences("AccountInfo",MODE_PRIVATE);
        Long phoneNum = register_sp.getLong("phoneNum",1);
        String user = register_sp.getString("user","");
        String mail = register_sp.getString("mail","");
        String register_password = register_sp.getString("password","");
        String account = et_account.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        if (Long.valueOf(account).equals(phoneNum) || account.equals(user) || account.equals(mail) && password.equals(register_password)){
            Intent intent = new Intent(getApplicationContext(),MobileActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(getApplicationContext(),"帐号或者密码错误，请重新输入！",Toast.LENGTH_SHORT).show();
        }
    }

    public void Remember(){
        SharedPreferences.Editor editor = getSharedPreferences("RememberInfo", MODE_PRIVATE).edit();
        SharedPreferences register_sp = getSharedPreferences("AccountInfo",MODE_PRIVATE);
        remember_sp = getSharedPreferences("RememberInfo",MODE_PRIVATE);
            Long phoneNum = register_sp.getLong("phoneNum",1);
            String user = register_sp.getString("user","");
            String mail = register_sp.getString("mail","");
            String account = et_account.getText().toString().trim();
            if (account.equals(String.valueOf(phoneNum))){
                editor.putLong("phoneNum", Long.parseLong(account));
            }else if(account.equals(user)){
                editor.putString("user", user);
            }else if (account.equals(mail)){
                editor.putString("mail", mail);
            }
            editor.putString("password", et_password.getText().toString());
            editor.putBoolean("checkbox",true);
            editor.apply();
    }
}

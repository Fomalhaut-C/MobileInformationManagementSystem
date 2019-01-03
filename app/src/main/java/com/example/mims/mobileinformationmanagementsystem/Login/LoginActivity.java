package com.example.mims.mobileinformationmanagementsystem.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mims.mobileinformationmanagementsystem.Database.MyDatabaseHelper;
import com.example.mims.mobileinformationmanagementsystem.Mobile.MobileActivity;
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
        //实现记住密码功能
        remember_sp = getSharedPreferences("Remember",MODE_PRIVATE);
        Boolean isRemember = remember_sp.getBoolean("checkbox",false);
        if (isRemember){
            et_account.setText(remember_sp.getString("account",""));
            et_password.setText(remember_sp.getString("password",""));
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
                //判断是否有点击记住密码
                if (login_remember.isChecked()){
                    Remember();
                }else {
                    SharedPreferences.Editor editor = getSharedPreferences("Remember", MODE_PRIVATE).edit();
                    editor.clear();
                    editor.apply();
                }
                Login();
                break;
        }
    }

    //登录
    public void Login(){
        String account = et_account.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        if (QueryAccount(account,password) > 0){
            Intent intent = new Intent(getApplicationContext(),MobileActivity.class);
            intent.putExtra("account",account);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(getApplicationContext(),"帐号或者密码,请重新输入!",Toast.LENGTH_SHORT).show();
        }
    }

    //存储需要记住的密码到文件中
    public void Remember(){
        SharedPreferences.Editor editor = getSharedPreferences("Remember", MODE_PRIVATE).edit();
        remember_sp = getSharedPreferences("Remember",MODE_PRIVATE);
        String account = et_account.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(),"帐号和密码不可以为空，请认真检查！",Toast.LENGTH_SHORT).show();
        }else {
            editor.putString("account",account);
            editor.putString("password",password);
            editor.putBoolean("checkbox",true);
            editor.apply();
        }
    }

    //查询帐号和密码是否匹配已经注册的帐号
    public int QueryAccount(String account,String password){
        int result = 0;
        MyDatabaseHelper databaseHelp = new MyDatabaseHelper(getApplicationContext(), "Account.db", null, 1);
        SQLiteDatabase database = databaseHelp.getWritableDatabase();
        Cursor cursor_phoneNum = database.rawQuery("select * from information where phoneNum = ? and password = ?",new String[]{account,password});
        Cursor cursor_user = database.rawQuery("select * from information where user = ? and password = ?",new String[]{account,password});
        Cursor cursor_mail = database.rawQuery("select * from information where mail = ? and password = ?",new String[]{account,password});
        if (cursor_phoneNum.getCount() > 0){
            result = 1;
        }else if (cursor_user.getCount() > 0){
            result = 2;
        }else if (cursor_mail.getCount() > 0){
            result = 3;
        }
        cursor_phoneNum.close();
        cursor_user.close();
        cursor_mail.close();
        database.close();
        return result;
    }
}

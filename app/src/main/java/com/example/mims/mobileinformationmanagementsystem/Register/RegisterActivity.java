package com.example.mims.mobileinformationmanagementsystem.Register;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mims.mobileinformationmanagementsystem.Database.MyDatabaseHelper;
import com.example.mims.mobileinformationmanagementsystem.Login.LoginActivity;
import com.example.mims.mobileinformationmanagementsystem.R;



public class RegisterActivity extends AppCompatActivity {
    private EditText et_phoneNum,et_user,et_mail,et_password,et_rePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        findViewById(R.id.btn_register_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = et_user.getText().toString().trim();
                String mail = et_mail.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String rePassword = et_rePassword.getText().toString().trim();
                if (TextUtils.isEmpty(et_phoneNum.getText()) || user.equals("") || mail.equals("") || password.equals("") || rePassword.equals("")){
                    Toast.makeText(getApplicationContext(),"文本框不可以留空，请重新检查,认真填写！",Toast.LENGTH_SHORT).show();
                }else if (!rePassword.equals(password)){
                    Toast.makeText(getApplicationContext(),"两次输入的密码不一致，请重新输入！",Toast.LENGTH_SHORT).show();
                }else {
                    Register();
                }
            }
        });
    }

    //实现注册功能
    public void Register() {
        Long phoneNum = Long.valueOf(et_phoneNum.getText().toString().trim());
        String user = et_user.getText().toString().trim();
        String mail = et_mail.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        MyDatabaseHelper databaseHelp = new MyDatabaseHelper(getApplicationContext(), "Account.db", null, 1);
        SQLiteDatabase database = databaseHelp.getWritableDatabase();
        try{
            database.execSQL("insert into information (phoneNum,user,mail,password) values(?,?,?,?)",new Object[]{String.valueOf(phoneNum),user,mail,password});
        }catch (SQLException e){
            Toast.makeText(getApplicationContext(), "注册信息已存在，请重新输入！", Toast.LENGTH_SHORT).show();
            return;
        }
        database.close();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
        Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
    }

    //初始化控件
    public void initView(){
        et_phoneNum = findViewById(R.id.et_register_phoneNum);
        et_user = findViewById(R.id.et_register_user);
        et_mail = findViewById(R.id.et_register_mail);
        et_password = findViewById(R.id.et_register_password);
        et_rePassword = findViewById(R.id.et_register_rePassword);
    }
}

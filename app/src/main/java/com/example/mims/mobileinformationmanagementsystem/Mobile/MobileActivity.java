package com.example.mims.mobileinformationmanagementsystem.Mobile;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.mims.mobileinformationmanagementsystem.Database.MyDatabaseHelper;
import com.example.mims.mobileinformationmanagementsystem.R;

public class MobileActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Long phoneNum;
    private String user,mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);
        String login_account = getIntent().getStringExtra("account");
        Query(login_account);
        initView();
        navigationView.setCheckedItem(R.id.nav_change_head);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }

    public void initView(){
        Toolbar toolbar = findViewById(R.id.toolbar_mobile);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        drawerLayout = findViewById(R.id.drawer_layout_mobile);
        navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView tv_header_username = headerView.findViewById(R.id.tv_nav_header_user);
        TextView tv_header_phoneNum = headerView.findViewById(R.id.tv_nav_header_phoneNum);
        TextView tv_header_mail = headerView.findViewById(R.id.tv_nav_header_mail);
        tv_header_phoneNum.setText(String.valueOf(phoneNum));
        tv_header_username.setText(user);
        tv_header_mail.setText(mail);
    }

    public void Query(String login_account){
        MyDatabaseHelper databaseHelp = new MyDatabaseHelper(getApplicationContext(), "Account.db", null, 1);
        SQLiteDatabase database = databaseHelp.getWritableDatabase();
        Cursor cursor = database.query("information",new String[]{"phoneNum","user","mail"} ,"phoneNum = ? or user = ? or mail = ?",new String[]{login_account},null,null,null);
        if (cursor.getCount() > 0){
            if (cursor.moveToFirst()){
                do {
                    phoneNum = cursor.getLong(cursor.getColumnIndex("phoneNum"));
                    user = cursor.getString(cursor.getColumnIndex("user"));
                    mail = cursor.getString(cursor.getColumnIndex("mail"));
                }while (cursor.moveToNext());
                cursor.close();
            }
        }
    }
}


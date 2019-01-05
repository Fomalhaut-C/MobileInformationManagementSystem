package com.example.mims.mobileinformationmanagementsystem.Mobile;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.mims.mobileinformationmanagementsystem.Database.MobileDataAdapter;
import com.example.mims.mobileinformationmanagementsystem.Database.MyDatabaseHelper;
import com.example.mims.mobileinformationmanagementsystem.Login.LoginActivity;
import com.example.mims.mobileinformationmanagementsystem.Management.DeleteActivity;
import com.example.mims.mobileinformationmanagementsystem.Management.PlusActivity;
import com.example.mims.mobileinformationmanagementsystem.Management.QueryActivity;
import com.example.mims.mobileinformationmanagementsystem.Management.UpdateActivity;
import com.example.mims.mobileinformationmanagementsystem.R;

import java.util.List;


public class MobileActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Long phoneNum;
    private String user,mail;
    private MobileDataAdapter data_adapter;
    private MobileAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;
    private List<Mobile> mobileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);
        String login_account = getIntent().getStringExtra("account");
        QueryAccount(login_account);
        initView();
        initNavigationView();
        //调用RecyclerView
        data_adapter = new MobileDataAdapter(getApplicationContext());
        mobileList = data_adapter.queryMobile();
        RecyclerView recyclerView = findViewById(R.id.recycler_view_mobile);
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MobileAdapter(mobileList);
        recyclerView.setAdapter(adapter);
        //设置下拉刷新监听器
        swipeRefresh = findViewById(R.id.swipe_refresh_mobile);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshMobile();
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    //添加
                    case R.id.nav_brand_plus:
                        Intent intent_plus = new Intent(getApplicationContext(),PlusActivity.class);
                        startActivity(intent_plus);
                        break;
                    //查询
                    case R.id.nav_brand_query:
                        Intent intent_query = new Intent(getApplicationContext(),QueryActivity.class);
                        startActivity(intent_query);
                    break;
                    //修改
                    case R.id.nav_brand_update:
                        Intent intent_update = new Intent(getApplicationContext(),UpdateActivity.class);
                        startActivity(intent_update);
                        break;
                    //删除
                    case R.id.nav_brand_delete:
                        Intent intent_delete = new Intent(getApplicationContext(),DeleteActivity.class);
                        startActivity(intent_delete);
                        break;
                    //切换用户
                    case R.id.nav_change_user:
                        Intent intent_change = new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(intent_change);
                        finish();
                        break;
                    default:
                }
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

    //实现下拉刷新
    private void refreshMobile(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mobileList.clear();
                        mobileList.addAll(data_adapter.queryMobile());
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
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
    }
    public void initNavigationView(){
        View headerView = navigationView.getHeaderView(0);
        TextView tv_header_username = headerView.findViewById(R.id.tv_nav_header_user);
        TextView tv_header_phoneNum = headerView.findViewById(R.id.tv_nav_header_phoneNum);
        TextView tv_header_mail = headerView.findViewById(R.id.tv_nav_header_mail);
        tv_header_phoneNum.setText(String.valueOf(phoneNum));
        tv_header_username.setText(user);
        tv_header_mail.setText(mail);
    }

    public void QueryAccount(String login_account){
        MyDatabaseHelper databaseHelp = new MyDatabaseHelper(getApplicationContext(), "Account.db", null, 1);
        SQLiteDatabase database = databaseHelp.getWritableDatabase();
        Cursor cursor = database.query("information",new String[]{"phoneNum","user","mail"} ,"phoneNum = ? or user = ? or mail = ?",new String[]{login_account,login_account,login_account},null,null,null);
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

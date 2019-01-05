package com.example.mims.mobileinformationmanagementsystem.Mobile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mims.mobileinformationmanagementsystem.Database.MobileDataAdapter;
import com.example.mims.mobileinformationmanagementsystem.R;

import java.util.Iterator;
import java.util.List;

public class MobileInforActivity extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_infor);
        Intent intent = getIntent();
        String mobile_Name = intent.getStringExtra("mobile_name");
        String mobile_Image = intent.getStringExtra("mobile_image");
        Toolbar toolbar = findViewById(R.id.toolbar_mobileinfor);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.mobileinfor_collapsing_toolbar);
        ImageView Mobileinfor_Image_View =  findViewById(R.id.mobileinfor_image_view);
        TextView Mobile_Information = findViewById(R.id.mobile_information);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(mobile_Name);
        Uri uri = Uri.parse(mobile_Image);
        Glide.with(this).load(uri).into(Mobileinfor_Image_View);
        MobileDataAdapter adapter = new MobileDataAdapter(getApplicationContext());
        List<Mobile> list = adapter.query(mobile_Name);
        Iterator<Mobile> iterator = list.iterator();
        while (iterator.hasNext()){
            Mobile mobile = iterator.next();
            Mobile_Information.setText(
                    "品牌名称：" + mobile.getName()+ "\n"+
                    "建立时间：" + mobile.getTime()+ "\n" +
                    "所属国家：" + mobile.getCountry() + "\n" +
                    "现任总裁：" + mobile.getCeo() + "\n" +
                    "品牌简介：" + mobile.getIntroduce());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

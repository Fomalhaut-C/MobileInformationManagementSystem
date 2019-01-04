package com.example.mims.mobileinformationmanagementsystem.Management;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mims.mobileinformationmanagementsystem.Database.MobileDataAdapter;
import com.example.mims.mobileinformationmanagementsystem.R;

public class DeleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        final EditText et_delete = findViewById(R.id.et_delete_delete);
        findViewById(R.id.btn_delete_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String delete_information = et_delete.getText().toString().trim();
                MobileDataAdapter adapter = new MobileDataAdapter(getApplicationContext());
                if (adapter.delete(delete_information) > 0){
                    Toast.makeText(getApplicationContext(),"删除成功",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"删除失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

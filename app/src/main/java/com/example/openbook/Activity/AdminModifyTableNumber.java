package com.example.openbook.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.openbook.Data.AdminTableList;
import com.example.openbook.R;

import java.util.ArrayList;

public class AdminModifyTableNumber extends Activity {

    String TAG = "AdminModifyTable_Activity";
    String get_id;
    Button plus;
    Button minus;
    Button submit;
    TextView tableNumber;
    TextView cancel;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    ArrayList<AdminTableList> adminTableList;

    int table;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.admin_modify_table_number);

        get_id = getIntent().getStringExtra("get_id");
        adminTableList = (ArrayList<AdminTableList>) getIntent().getSerializableExtra("adminTableList");

        plus = findViewById(R.id.admin_modify_table_plus_btn);
        minus = findViewById(R.id.admin_modify_table_minus_btn);
        submit = findViewById(R.id.admin_modify_table_submit);
        tableNumber = findViewById(R.id.admin_modify_table_text);
        cancel = findViewById(R.id.admin_modify_table_cancel);

        /**
         * adminTableList가 없으면 s.p에서 가져와서 넣어주기
         */
        pref = getSharedPreferences("TableNumber", MODE_PRIVATE);

        editor = pref.edit();

        if(adminTableList == null){
            table = pref.getInt("tableNumber", 20);
            Log.d(TAG, "adminTableList null :" + table);
        }else{
            table = adminTableList.size();
            Log.d(TAG, "adminTableList size : " + adminTableList.get(table-1).getAdminTableNumber());

        }

        tableNumber.setText(String.valueOf(table));

    }


    @Override
    protected void onResume() {
        super.onResume();


        plus.setOnClickListener(view -> {
            table = table+1;
            adminTableList.add(new AdminTableList("table"+String.valueOf(table),
                    null, null, null, null));
            Log.d(TAG, "adminTableList add : " + adminTableList.get(table-1).getAdminTableNumber());
            tableNumber.setText(String.valueOf(table));
        });

        minus.setOnClickListener(view -> {
            if (table != 0) {
                table = table-1;
                adminTableList.remove(table);
                Log.d(TAG, "adminTableList remove :" + adminTableList.get(table-1).getAdminTableNumber());
                tableNumber.setText(String.valueOf(table));
            } else if(table ==0){
                Toast.makeText(AdminModifyTableNumber.this, "테이블 개수는 0개 이상이어야 합니다.", Toast.LENGTH_LONG).show();
            }
        });

        submit.setOnClickListener(view -> {
            // 비밀번호 한 번 더 치고, 서버에 저장해서 껐다가 켜도 자동적으로 테이블 번호 받아오도록
            // 그러면 admin 관리 db가 있어야겟구먼유

            editor.putInt("tableNumber", table);
            editor.commit();

            Intent intent = new Intent(AdminModifyTableNumber.this, Admin.class);
            intent.putExtra("get_id", get_id);
            intent.putExtra("adminTableList", adminTableList);
            startActivity(intent);
        });

        cancel.setOnClickListener(view ->{
            Intent intent = new Intent(AdminModifyTableNumber.this, Admin.class);
            intent.putExtra("get_id", get_id);
            intent.putExtra("adminTableList", adminTableList);
            startActivity(intent);
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥 레이어 클릭해도 안닫히게 하기
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
}
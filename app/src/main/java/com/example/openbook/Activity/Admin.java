package com.example.openbook.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.openbook.Adapter.AdminTableAdapter;
import com.example.openbook.Data.AdminTableList;
import com.example.openbook.Data.TableList;
import com.example.openbook.FCM.FCM;
import com.example.openbook.R;

import java.util.ArrayList;

import okhttp3.OkHttpClient;

public class Admin extends AppCompatActivity {

    String TAG = "AdminTAG";

    ArrayList<AdminTableList> adminTableList;
    AdminTableAdapter adapter;

    TextView appbar_admin_sales;
    TextView appbar_admin_addMenu;
    TextView appbar_admin_modifyTable;

    String get_id;

    String gender;
    String guestNumber;
    String tableName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity);


        adminTableList = (ArrayList<AdminTableList>) getIntent().getSerializableExtra("adminTableList");
        get_id = getIntent().getStringExtra("get_id");

        /**
         * 로그인을 성공하면 id, token을 firebase realtime db에 저장
         */
        Intent fcm = new Intent(Admin.this, FCM.class);
        fcm.putExtra("get_id", get_id);
        startService(fcm);

        TextView appbar_admin_id = findViewById(R.id.appbar_admin_id);
        appbar_admin_id.setText(get_id);

        appbar_admin_sales = findViewById(R.id.appbar_admin_sales);

        appbar_admin_addMenu = findViewById(R.id.appbar_admin_addMenu);

        appbar_admin_modifyTable = findViewById(R.id.appbar_admin_modifyTable);

        RecyclerView tableGrid = findViewById(R.id.admin_grid);
        adapter = new AdminTableAdapter();

        //그리드 레이아웃 설정
        tableGrid.setLayoutManager(new GridLayoutManager(this, 3));

        //어댑터 연결
        tableGrid.setAdapter(adapter);

        adapter.setAdapterItem(adminTableList);

        OkHttpClient okHttpClient = new OkHttpClient();

    }


    @Override
    protected void onStart() {
        super.onStart();

        gender = getIntent().getStringExtra("gender");
        guestNumber = getIntent().getStringExtra("guestNumber");
        tableName = getIntent().getStringExtra("tableName");



    }

    @Override
    protected void onResume() {
        super.onResume();


        if(tableName != null){
            Log.d(TAG, "tableName not null: " );
            int tableNameInt = Integer.parseInt(tableName);
            adminTableList.get(tableNameInt).setAdminTableGender(gender);
            adminTableList.get(tableNameInt).setAdminTableGuestNumber(guestNumber);
        }



        appbar_admin_sales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //매출 액티비티가 나온다
                startActivityClass(AdminSales.class);
            }
        });

        appbar_admin_addMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 메뉴 이름, 가격, 이미지를 등록하면 서버로 들어가서 menuList Db에 등록된다
                startActivityClass(AdminModifyMenu.class);

            }
        });

        appbar_admin_modifyTable.setOnClickListener(view ->{
            startActivityClass(AdminModifyTableNumber.class);


        });

        Dialog dialog = new Dialog(Admin.this);
        dialog.setContentView(R.layout.admin_receipt_dialog);


        adapter.setOnItemClickListener(new AdminTableAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                dialog.show();
            }
        });

    }

    public void startActivityClass(Class activity){
        Intent intent = new Intent(Admin.this, activity);
        intent.putExtra("get_id", get_id);
        intent.putExtra("adminTableList", adminTableList);
        startActivity(intent);
    }
}

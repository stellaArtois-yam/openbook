package com.example.openbook;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.openbook.FCM.SendNotification;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SaveOrderDeleteData {

    String TAG = "OrderSaveTAG";

    boolean success;

    public boolean orderSave(String json) throws InterruptedException {

        Log.d(TAG, "orderSave json: " + json);

        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("json", json)
                .build();

        Request request = new Request.Builder()
                .url("http://3.36.255.141/saveOrder.php")
                .post(formBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d(TAG, "onFailure: " + e);
                success = false;
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String responseBody = response.body().string();
                Log.d(TAG, "onResponse: " + responseBody);

                if(responseBody.equals("주문완료")){
                    success = true;
                }else{
                    success = false;
                }
            }
        });

        Thread.sleep(250);
        Log.d(TAG, "orderSave: " + success);

        return success;
    }


    public void deleteServerData(String tableName){
        //해당 테이블의 tableInfo를 지운다
        OkHttpClient okHttpClient = new OkHttpClient();
        Log.d(TAG, "deleteServerData: 1");

        RequestBody formBody = new FormBody.Builder()
                .add("tableName", tableName)
                .build();

        Request request = new Request.Builder()
                .url("http://3.36.255.141/DeleteTableInfo.php")
                .post(formBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d(TAG, "Delete onFailure: " + e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String responseBody = response.body().string();
                Log.d(TAG, "Delete onResponse: " + responseBody);

                if(responseBody.equals("")){

                }

                SendNotification sendNotification = new SendNotification();
                sendNotification.saveChatting(tableName);


            }
        });


    }
}

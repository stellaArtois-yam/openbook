package com.example.openbook;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TableQuantity {
    String TAG = "TableQuantityTAG";

    OkHttpClient okHttpClient;
    RequestBody formBody;
    Request request;

    int tableQuantity;

    public int getTableQuantity()  {

        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(9999, TimeUnit.SECONDS).build();

        formBody = new FormBody.Builder()
                .add("request", "getTableQuantity")
                .build();

        request = new Request.Builder()
                .url("http://3.36.255.141/TableQuantity.php")
                .post(formBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d(TAG, "onFailure: " + e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String body = response.body().string();
                Log.d(TAG, "onResponse: " + body);

                if (body.equals("fail")) {
                    Log.d(TAG, "onResponse: " + body);

                } else {
                    tableQuantity = Integer.parseInt(body);
                    Log.d(TAG, "onResponse table : " + tableQuantity);
                }
            }
        });


        if(tableQuantity == 0){
            try {
                Log.d(TAG, "0: ");
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "sleep 1200: " + tableQuantity);
            return tableQuantity;
        }
        Log.d(TAG, "return tableQuantity: " + tableQuantity);
        return tableQuantity;

    }


    public void setTableQuantity(int tableQuantity) {

        okHttpClient = new OkHttpClient();

        formBody = new FormBody.Builder()
                .add("request", "setTableQuantity")
                .add("tableQuantity", String.valueOf(tableQuantity))
                .build();

        request = new Request.Builder()
                .url("http://3.36.255.141/TableQuantity.php")
                .post(formBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d(TAG, "onFailure: " + e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String body = response.body().string();

                Log.d(TAG, "onResponse: " + body);

            }
        });

    }
}

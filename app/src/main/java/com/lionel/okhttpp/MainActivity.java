package com.lionel.okhttpp;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.lionel.okhttpp.databinding.ActivityMainBinding;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class MainActivity extends AppCompatActivity {

    private static final String GET_URL = "https://jsonplaceholder.typicode.com/posts/1";
    private static final String POST_URL = "https://jsonplaceholder.typicode.com/posts";


    private OkHttpClient client;
    private ActivityMainBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        client = new OkHttpClient().newBuilder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build();

    }

    public void onGetBtnClicked(View view) {
        initOKHttpGet();
    }

    private void initOKHttpGet() {
        final Request request = new Request.Builder()
                .url(GET_URL)
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.body() != null) {
                    dataBinding.setData(response.body().string());
                }
            }
        });
    }

    public void onPostBtnClicked(View view) {
        initOKHttpPost();
    }

    private void initOKHttpPost() {
        FormBody requestBody = new FormBody.Builder()
                .add("userId", "3")
                .add("id", "1")
                .add("title" , "PSOOOS")
                .build();

        Request request = new Request.Builder()
                .url(POST_URL)
                .post(requestBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.body()!=null) {
                    dataBinding.setData(response.body().string());
                }
            }
        });
    }
}

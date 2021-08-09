package com.example.a20_androidretrofitdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.a20_androidretrofitdemo.adapter.JsonResultListAdapter;
import com.example.a20_androidretrofitdemo.domain.JsonResult;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView mRecyclerList;
    private JsonResultListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mRecyclerList = findViewById(R.id.result_list);
        mRecyclerList.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.top = 5;
                outRect.bottom = 5;
            }
        });
        mAdapter = new JsonResultListAdapter();
        mRecyclerList.setAdapter(mAdapter);
    }

    public void getRequest(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:9102")
                .build();
        API api = retrofit.create(API.class);
        Call<ResponseBody> task = api.getJson();
        task.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, "onResponse -> " + response.code());
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    try {
//                        Log.d(TAG, "json -> " + response.body().string());
                        String result = response.body().string();
                        Gson gson = new Gson();
                        JsonResult jsonResult = gson.fromJson(result, JsonResult.class);
                        updateList(jsonResult);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure -> " + t.toString());
            }
        });
    }

    private void updateList(JsonResult jsonResult) {
        mAdapter.setData(jsonResult);
    }
}
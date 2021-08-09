package com.example.a19_androidnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.example.a19_androidnetwork.utils.IOUtils;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpActivity extends AppCompatActivity {

    private static final String TAG = "OkHttpActivity";
    private static final String BASE_URL = "http://10.0.2.2:9102";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
    }

    public void getRequest(View view) {
        // 要有客户端 类似于我们要有一个浏览器
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .build();
        // 创建请求内容
        Request request = new Request.Builder()
                .get()
                .url(BASE_URL + "/get/text")
                .build();
        // 用client去创建请求任务
        Call task = okHttpClient.newCall(request);
        // 异步请求
        task.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, "onFailure -> " + e.toString());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                int code = response.code();
                Log.d(TAG, "code -> " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    ResponseBody body = response.body();
                    Log.d(TAG, "body -> " + body.string());
                }
            }
        });
    }

//    public void postComment(View view) {
//        OkHttpClient client = new OkHttpClient.Builder()
//                .connectTimeout(10000, TimeUnit.MILLISECONDS)
//                .build();
//        // 要提交的内容
//        CommmentItem commmentItem = new CommentItem("12345", "nice");
//        Gson gson = new Gson();
//        String jsonStr = gson.toJson(commmentItem);
//        MediaType mediaType = MediaType.parse("application/json");
//        RequestBody requestBody = RequestBody.create(jsonStr, mediaType);
//        Request request = new Request.Builder()
//                .post(requestBody)
//                .url(BASE_URL + "/post/comment")
//                .build();
//        Call task = client.newCall(request);
//        task.enqueue(new Callback() {
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                Log.d(TAG, "onFailure -> " + e.toString());
//            }
//
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                int code = response.code();
//                Log.d(TAG, "code -> " + code);
//                if (code == HttpURLConnection.HTTP_OK) {
//                    ResponseBody body = response.body();
//                    Log.d(TAG, "body -> " + body.string());
//                }
//            }
//        });
//    }

    public void postFile(View view) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .build();
        File file = new File("...文件路径...");
        MediaType fileType = MediaType.parse("image/png");
        RequestBody fileBody = RequestBody.create(file, fileType);
        RequestBody requestBody = new MultipartBody.Builder()
                .addFormDataPart("file", file.getName(), fileBody)
                .build();
        Request request = new Request.Builder()
                .url(BASE_URL + "/file/upload")
                .post(requestBody)
                .build();
        Call task = client.newCall(request);
        task.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, "onFailure -> " + e.toString());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                int code = response.code();
                Log.d(TAG, "code -> " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    ResponseBody body = response.body();
                    Log.d(TAG, "body -> " + body.string());
                }
            }
        });
    }

    public void postFiles(View view) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .build();
        File file1 = new File("...文件路径...");
        File file2 = new File("...文件路径...");
        File file3 = new File("...文件路径...");
        File file4 = new File("...文件路径...");
        MediaType fileType = MediaType.parse("image/png");
        RequestBody fileBody1 = RequestBody.create(file1, fileType);
        RequestBody fileBody2 = RequestBody.create(file2, fileType);
        RequestBody fileBody3 = RequestBody.create(file3, fileType);
        RequestBody fileBody4 = RequestBody.create(file4, fileType);
        RequestBody requestBody = new MultipartBody.Builder()
                .addFormDataPart("files", file1.getName(), fileBody1)
                .addFormDataPart("files", file2.getName(), fileBody2)
                .addFormDataPart("files", file3.getName(), fileBody3)
                .addFormDataPart("files", file4.getName(), fileBody4)
                .build();
        Request request = new Request.Builder()
                .url(BASE_URL + "/files/upload")
                .post(requestBody)
                .build();
        Call task = client.newCall(request);
        task.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d(TAG, "onFailure -> " + e.toString());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                int code = response.code();
                Log.d(TAG, "code -> " + code);
                if (code == HttpURLConnection.HTTP_OK) {
                    ResponseBody body = response.body();
                    Log.d(TAG, "body -> " + body.string());
                }
            }
        });
    }

    public void downloadFiles(View view) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .build();
        Request request = new Request.Builder()
                .url(BASE_URL + "/download/15")
                .get()
                .build();
        Call call = client.newCall(request);
        new Thread(new Runnable() {
            @Override
            public void run() {
                FileOutputStream fos = null;
                InputStream inputStream = null;
                try {
                    Response execute = call.execute();
                    int code = execute.code();
                    if (code != HttpURLConnection.HTTP_OK) {
                        return;
                    }
                    Headers headers = execute.headers();
                    for (int i = 0; i < headers.size(); i++) {
                        String key = headers.name(i);
                        String value = headers.value(i);
                        Log.d(TAG, key + " === " + value);
                    }
                    String contentType = headers.get("Content-disposition");
                    String fileName = contentType.replace("attachment; filename=", "");
                    File outFile = new File(OkHttpActivity.this.getExternalFilesDir(Environment.DIRECTORY_PICTURES) + File.separator + fileName);
                    if (!outFile.getParentFile().exists()) {
                        outFile.mkdirs();
                    }
                    if (!outFile.exists()) {
                        outFile.createNewFile();
                    }
                    fos = new FileOutputStream(outFile);
                    if (execute.body() != null) {
                        inputStream = execute.body().byteStream();
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
                            fos.write(buffer, 0, len);
                        }
                        fos.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    IOUtils.ioClose(fos);
                    IOUtils.ioClose(inputStream);
                }
            }
        }).start();
    }
}
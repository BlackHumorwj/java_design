package com.example.sf_demo.frame.okhttp;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @time 2020/1/10 14:52
 * @desc
 */
public class OkHttpUtil {


    private final OkHttpClient mOkHttpClient;

    public OkHttpUtil() {
        //构建OKHttpClient对象
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)//
                .readTimeout(100, TimeUnit.SECONDS)//
                .build();
    }

    String httpGet(String url) throws IOException {
        //构建 Request
        final Request request = new Request.Builder()//
                .url(url)//
                .get()//
                .build();
        //构建一个Call对象
        final Call call = mOkHttpClient.newCall(request);
        //执行这个call 同步返回  Response 对象
        final Response response = call.execute();
        //状态是否成功
        if (response.isSuccessful()) {
            //获取 ResponseBody 信息
            final ResponseBody responseBody = response.body();
            if (responseBody != null) {
                final String string = responseBody.string();
                Log.e("xxx",string);
                return string;
            } else {
                return "";
            }

        } else {
            throw new IOException("Unexpected code" + response);
        }
    }


    //使用post 方法提交 Json类型 RequestBody
    String httpPostJson(String url, String json) throws IOException {

        MediaType JSON = MediaType.parse("application/json;charset=utf-8");
        //构建 MediaType 为Json类型的 请求体
        RequestBody requestBody = RequestBody.create(JSON, json);

        //构建 Request
        final Request request = new Request.Builder()//
                .url(url)//
                .post(requestBody)//
                .build();

        final Call call = mOkHttpClient.newCall(request);
        final Response response = call.execute();

        if (response.isSuccessful()) {
            //获取 ResponseBody 信息
            final ResponseBody responseBody = response.body();
            if (responseBody != null) {
                final String string = responseBody.string();
                Log.e("xxx",string);
                return string;
            } else {
                return "";
            }
        } else {
            throw new IOException("Unexpected code" + response);
        }

    }

    //使用post 方法提交 键值对 RequestBody
    String httpPostKeyValue(String url) throws IOException{

        //构建键值对类型 FormBody
        RequestBody requestBody = new FormBody.Builder()
                .add("search", "Jurassic Park")
                .build();

        Request  request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        final Call call = mOkHttpClient.newCall(request);
        final Response response = call.execute();

        if (response.isSuccessful()) {
            //获取 ResponseBody 信息
            final ResponseBody responseBody = response.body();
            if (responseBody != null) {
                final String string = responseBody.string();
                Log.e("xxx",string);
                return string;
            } else {
                return "";
            }
        } else {
            throw new IOException("Unexpected code" + response);
        }

    }


}

package com.example.sf_demo.frame.okhttp;

import android.os.Handler;
import android.util.Log;

import com.example.sf_demo.helper.DemoCallBack;
import com.example.sf_demo.helper.SpannableUtils;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.Route;
import okio.BufferedSink;

/**
 * @time 2020/1/10 14:52
 * @desc
 */
public class OkHttpUtil {


    /**
     * <a href =" https://blog.csdn.net/mynameishuangshuai/article/details/51303446" > 参考手书</a>
     */

    private final OkHttpClient mOkHttpClient;
    private final Handler mHandler;
    private final SpannableUtils.Builder mSpBuilder;


    public OkHttpUtil() {
        mHandler = new Handler();
        mSpBuilder = SpannableUtils.getBuilder("");
        //构建OKHttpClient对象
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)//
                .readTimeout(10, TimeUnit.SECONDS)//
                .writeTimeout(10, TimeUnit.SECONDS)//
                .build();
    }

    String httpGet(String url, DemoCallBack callBack) throws IOException {
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
                Log.e("xxx", string);
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
                Log.e("xxx", string);
                return string;
            } else {
                return "";
            }
        } else {
            throw new IOException("Unexpected code" + response);
        }

    }

    //使用post 方法提交 键值对 RequestBody
    String httpPostKeyValue(String url) throws IOException {

        //构建键值对类型 FormBody
        RequestBody requestBody = new FormBody.Builder()
                .add("search", "Jurassic Park")
                .build();

        Request request = new Request.Builder()
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
                Log.e("xxx", string);
                return string;
            } else {
                return "";
            }
        } else {
            throw new IOException("Unexpected code" + response);
        }

    }


    //<editor-fold desc="官方 Recipes">

    //同步get方法
    void synchronousGet(String url, final DemoCallBack<CharSequence> callBack) throws IOException {
        Request request = new Request.Builder().get().url(url).build();


        final Headers requestHead = request.headers();
        for (int i = 0; i < requestHead.size(); i++) {
            final String name = requestHead.name(i);
            final String value = requestHead.value(i);
            mSpBuilder.append("requestHead:").append(name + ":" + value);
        }

        final Call call = mOkHttpClient.newCall(request);
        final Response response = call.execute();

        //状态是否成功
        if (response.isSuccessful()) {
            //遍历响应头信息
            final Headers headers = response.headers();
            for (int i = 0; i < headers.size(); i++) {
                final String name = headers.name(i);
                final String value = headers.value(i);
                mSpBuilder.append("\nresponseHeader:").append(name + ":" + value);
            }
            //获取 ResponseBody 信息
            final ResponseBody responseBody = response.body();

            if (responseBody != null) {
                final String string = responseBody.string();
                mSpBuilder.append("\nresponse:").append(string);
            } else {

            }

        } else {
            throw new IOException("Unexpected code" + response);
        }
        if (callBack != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    callBack.callBack(mSpBuilder.create());
                }
            });
        }
    }
    //异步get方法
    void asynchronousGet(String url, final DemoCallBack<CharSequence> callBack) throws IOException {
        Request request = new Request.Builder().get().url(url).build();
        final Call call = mOkHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //状态是否成功
                if (!response.isSuccessful())throw new IOException("Unexpected code" + response);

                //获取 ResponseBody 信息
                final ResponseBody responseBody = response.body();
                final String string = responseBody.string();
                mSpBuilder.append("\nresponse:").append(string);

                if (callBack != null) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.callBack(mSpBuilder.create());
                        }
                    });
                }
            }
        });
    }

    //提取响应头
    void httpHead(String url, final DemoCallBack<CharSequence> callBack) throws IOException {

        Request request = new Request.Builder().get().url(url)
                .header("User-Agent", "OkHttp Headers.java")//key相同则会覆盖，只能存在一个
                .addHeader("Accept", "application/json; q=0.5")//可以添加多个相同key
                .addHeader("Accept", "application/vnd.github.v3+json")//可以添加多个相同key
                .build();
        final Headers requestHead = request.headers();
        for (int i = 0; i < requestHead.size(); i++) {
            final String name = requestHead.name(i);
            final String value = requestHead.value(i);
            mSpBuilder.append("requestHead:").append(name + ":" + value);
        }
        Response response = mOkHttpClient.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);

        //提取响应头信息
        mSpBuilder.append("\nServer:").append(response.header("Server"));
        mSpBuilder.append("\nDate:").append(response.header("Date"));
        mSpBuilder.append("\nContent-Type:").append(response.header("Content-Type"));
        mSpBuilder.append("\nVary:").append(response.header("Vary"));

        if (callBack != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    callBack.callBack(mSpBuilder.create());
                }
            });
        }

    }

    //post 方法提交String信息
    void postString(String url, final DemoCallBack<CharSequence> callBack) throws IOException {
        //415	Unsupported Media Type	服务器无法处理请求附带的媒体格式
        MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
        String postBody = ""
                + "Releases\n"
                + "--------\n"
                + "\n"
                + " * _1.0_ May 6, 2013\n"
                + " * _1.1_ June 15, 2013\n"
                + " * _1.2_ August 11, 2013\n";
        Request request = new Request.Builder().url(url)
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
                .build();

        final Response response = mOkHttpClient.newCall(request).execute();

        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);

        if (callBack != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        callBack.callBack(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    private MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

    //post方法提交 Stream
    void postStreaming(String url, final DemoCallBack<CharSequence> callBack) throws IOException {
        RequestBody requestBody = new RequestBody() {
            @Override
            public MediaType contentType() {
                return MEDIA_TYPE_MARKDOWN;
            }
            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.writeUtf8("Numbers\n");
                sink.writeUtf8("-------\n");
                for (int i = 2; i <= 997; i++) {
                    sink.writeUtf8(String.format(" * %s = %s\n", i, factor(i)));
                }
            }
            private String factor(int n) {
                for (int i = 2; i < n; i++) {
                    int x = n / i;
                    if (x * i == n)
                        return factor(x) + " × " + i;
                }
                return Integer.toString(n);
            }
        };

        Request request = new Request.Builder().url(url).post(requestBody).build();
        final Response response = mOkHttpClient.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);
        if (callBack != null) {
            final String string = response.body().string();
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    callBack.callBack(string);
                }
            });
        }
    }


    //post上传文件
    void postFile(String url, final DemoCallBack<CharSequence> callBack) throws IOException {

        final File file = new File("README.md");
        final RequestBody requestBody = RequestBody.create(MEDIA_TYPE_MARKDOWN, file);
        Request request = new Request.Builder().post(requestBody).url(url).build();
        final Response response = mOkHttpClient.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);

        if (callBack != null) {
            final String string = response.body().string();
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    callBack.callBack(string);
                }
            });
        }
    }


    //提交表单请求
    void postFormParameters(String url, final DemoCallBack<CharSequence> callBack) throws IOException {
        RequestBody requestBody = new FormBody.Builder().add("search", "Jurassic Park").build();
        Request request = new Request.Builder().post(requestBody).url(url).build();

        final Response response = mOkHttpClient.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);
        if (callBack != null) {
            final String string = response.body().string();
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    callBack.callBack(string);
                }
            });
        }
    }

    //post提交分块请求
    void postMultipartRequest(String url, final DemoCallBack<CharSequence> callBack) throws IOException {
        MediaType mediaType = MediaType.parse("image/png");
        String IMGUR_CLIENT_ID = "...";
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title", "logo")
                .addFormDataPart("image", "logo-sequare.png", RequestBody.create(mediaType, new File("")))
                .build();

        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + IMGUR_CLIENT_ID)
                .post(requestBody).url("https://api.imgur.com/3/image").build();

        final Response response = mOkHttpClient.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);
        if (callBack != null) {
            final String string = response.body().string();
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    callBack.callBack(string);
                }
            });
        }
    }


    //使用Gson解析Json数据
    void pasreJsonWithGson() throws IOException {
        Gson gson = new Gson();
        Request request = new Request.Builder()
                .url("https://api.github.com/gists/c2a7c39532239ff261be")
                .build();

        final Response response = mOkHttpClient.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);

        final Gist gist = gson.fromJson(response.body().charStream(), Gist.class);

        for (Map.Entry<String, GistFile> entry : gist.files.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue().content);
        }
    }


    static class Gist {
        Map<String, GistFile> files;
    }

    static class GistFile {
        String content;
    }


    void responseCache(File cacheDirectory) throws IOException {

        int cacheMaxSize = 10 * 1024 * 1024;
        final Cache cache = new Cache(cacheDirectory, cacheMaxSize);

        //浅度拷贝 一个OkHttpClient
        final OkHttpClient okHttpClient = mOkHttpClient.newBuilder().cache(cache).build();
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();

        final Response response1 = okHttpClient.newCall(request).execute();

        if (!response1.isSuccessful())
            throw new IOException("Unexpected code " + response1);

        String response1Body = response1.body().string();
        System.out.println("Response 1 response:          " + response1);
        System.out.println("Response 1 cache response:    " + response1.cacheResponse());
        System.out.println("Response 1 network response:  " + response1.networkResponse());

        Response response2 = okHttpClient.newCall(request).execute();
        if (!response2.isSuccessful())
            throw new IOException("Unexpected code " + response2);

        String response2Body = response2.body().string();
        System.out.println("Response 2 response:          " + response2);
        System.out.println("Response 2 cache response:    " + response2.cacheResponse());
        System.out.println("Response 2 network response:  " + response2.networkResponse());
        System.out.println("Response 2 equals Response 1? " + response1Body.equals(response2Body));
    }

    //验证处理
    void handlingAuthentication() throws IOException {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()//
                .authenticator(new Authenticator() {
                    @Override
                    public Request authenticate(Route route, Response response) throws IOException {
                        System.out.println("Authenticating for response: " + response);
                        System.out.println("Challenges: " + response.challenges());
                        String credential = Credentials.basic("jesse", "password1");

                        if (credential.equals(response.request().header("Authorization"))) {
                            return null;
                        }
                        if (responseCount(response) >= 3) {
                            return null;
                        }
                        return response.request().newBuilder().header("Authorization", credential).build();
                    }
                    private int responseCount(Response response) {
                        int result = 1;
                        while ((response = response.priorResponse()) != null) {
                            result++;
                        }
                        return result;
                    }
                }).build();

        Request request = new Request.Builder()
                .url("http://publicobject.com/secrets/hellosecret.txt")
                .build();
        Response response = okHttpClient.newCall(request).execute();
        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);
        System.out.println(response.body().string());
    }


    //</editor-fold>

}

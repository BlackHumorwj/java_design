package com.example.sf_demo.frame.okhttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @time 2020/1/14 13:46
 * @desc
 */
public class OkhttpSource {


    void sync() throws IOException {

        OkHttpClient client = new OkHttpClient.Builder().build();

        Request request = new Request.Builder().build();

        final Call call = client.newCall(request);

        final Response response = call.execute();
    }

    /*
    ####  一、同步的请求

        1.1、构建OkHttpClient

             m1 OkHttpClient#Builder()
            ```
            public Builder() {
              dispatcher = new Dispatcher();
              protocols = DEFAULT_PROTOCOLS;
              connectionSpecs = DEFAULT_CONNECTION_SPECS;
              eventListenerFactory = EventListener.factory(EventListener.NONE);
              proxySelector = ProxySelector.getDefault();
              cookieJar = CookieJar.NO_COOKIES;
              socketFactory = SocketFactory.getDefault();
              hostnameVerifier = OkHostnameVerifier.INSTANCE;
              certificatePinner = CertificatePinner.DEFAULT;
              proxyAuthenticator = Authenticator.NONE;
              authenticator = Authenticator.NONE;
              connectionPool = new ConnectionPool();
              dns = Dns.SYSTEM;
              followSslRedirects = true;
              followRedirects = true;
              retryOnConnectionFailure = true;
              connectTimeout = 10_000;
              readTimeout = 10_000;
              writeTimeout = 10_000;
              pingInterval = 0;
            }
            ```
            Dispatcher 网络请求的分发器，在异步请求中很重要，负责网络请求的调度和执行，内部维护一个线程池，用于执行请求
            ConnectionPool 链接池，对于相同的请求可以进行复用


        1.2、构建Request

            ```
             public Builder() {
              this.method = "GET";
              this.headers = new Headers.Builder();
            }

            Builder(Request request) {
              this.url = request.url;
              this.method = request.method;
              this.body = request.body;
              this.tag = request.tag;
              this.headers = request.headers.newBuilder();
            }

            ```
        1.3、通过OkHttpClient#newCall() 返回一个RealCall

            `m1  OkHttpClient# newCall(Request request) : RealCall`
            ```
            @Override public Call newCall(Request request) {
                return RealCall.newRealCall(this, request, false);
            }
            ```

            `m1.1 RealCall # newRealCall(): RealCall`
            ```
            static RealCall newRealCall(OkHttpClient client, Request originalRequest, boolean forWebSocket) {
                // Safely publish the Call instance to the EventListener.
                RealCall call = new RealCall(client, originalRequest, forWebSocket);
                call.eventListener = client.eventListenerFactory().create(call);
                return call;
              }

            ```

            `m1.1.1 RealCall # RealCall()`
            ```
              private RealCall(OkHttpClient client, Request originalRequest, boolean forWebSocket) {
                this.client = client;
                this.originalRequest = originalRequest;
                this.forWebSocket = forWebSocket;
                this.retryAndFollowUpInterceptor = new RetryAndFollowUpInterceptor(client, forWebSocket);
              }

            ```

        1.4、执行 RealCall 返回 Response

            `m1 RealCall # execute()`
            ```
             @Override public Response execute() throws IOException {
                //一个RealCall只能被执行一次
                synchronized (this) {
                  if (executed) throw new IllegalStateException("Already Executed");
                  executed = true;
                }
                //日志抓取
                captureCallStackTrace();
                eventListener.callStart(this);
                try {
                // 将 RealCall 添加到  Dispatcher # runningSyncCalls 同步队列中
                  client.dispatcher().executed(this);
                 //通过拦截器链处理Request 和 Response 并返回Response
                  Response result = getResponseWithInterceptorChain();
                  if (result == null) throw new IOException("Canceled");
                  return result;
                } catch (IOException e) {
                  eventListener.callFailed(this, e);
                  throw e;
                } finally {
                //从 runningSyncCalls 队列中移除 RealCall
                  client.dispatcher().finished(this);
                }
              }

            ```
     */


    void async() {

        OkHttpClient client = new OkHttpClient.Builder().build();

        Request request = new Request.Builder().build();

        final Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
            }
        });
    }


    //<editor-fold desc="二、异步的请求">
    /*

      #### 二、异步的请求

      2.4、执行RealCall#enqueue()

    ```
     m1 RealCall # enqueue()
     @Override public void enqueue(Callback responseCallback) {
    synchronized (this) {
      if (executed) throw new IllegalStateException("Already Executed");
      executed = true;
    }
    captureCallStackTrace();
    eventListener.callStart(this);
    //m1
    client.dispatcher().enqueue(new AsyncCall(responseCallback));
  }
    ```

    Dispatcher

     //等待执行的异步请求队列
     private final Deque<RealCall.AsyncCall> readyAsyncCalls = new ArrayDeque<>();
     /正在执行的异步请求队列
     private final Deque<RealCall.AsyncCall> runningAsyncCalls = new ArrayDeque<>();

     ```
     m1.1 Dispatcher # enqueue()
    synchronized void enqueue(AsyncCall call) {
    //正在执行的请求数符合要求 则添加到队列 并开启线程池执行
    if (runningAsyncCalls.size() < maxRequests && runningCallsForHost(call) < maxRequestsPerHost) {
      runningAsyncCalls.add(call);
       //m1 m2
      executorService().execute(call);
    } else {
    //否则添加到等待队列中
      readyAsyncCalls.add(call);
    }
  }
    ```

    ```
    初始化线程池
   m1.1.1 Dispatcher # executorService() : ExecutorService
    public synchronized ExecutorService executorService() {
    if (executorService == null) {
      executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS,
          new SynchronousQueue<Runnable>(), Util.threadFactory("OkHttp Dispatcher", false));
    }
    return executorService;
  }

    ```

    ```
    m1.1.2  AsyncCall # run() =>  AsyncCall # execute()

     @Override protected void execute() {
      boolean signalledCallback = false;
      try {
       //网络请求核心
        Response response = getResponseWithInterceptorChain();
        if (retryAndFollowUpInterceptor.isCanceled()) {
          signalledCallback = true;
          responseCallback.onFailure(RealCall.this, new IOException("Canceled"));
        } else {
          signalledCallback = true;
          responseCallback.onResponse(RealCall.this, response);
        }
      } catch (IOException e) {
        if (signalledCallback) {
          // Do not signal the callback twice!
          Platform.get().log(INFO, "Callback failure for " + toLoggableString(), e);
        } else {
          eventListener.callFailed(RealCall.this, e);
          responseCallback.onFailure(RealCall.this, e);
        }
      } finally {
        client.dispatcher().finished(this);
      }
    }
  }


    ```


     */
    //</editor-fold>


}

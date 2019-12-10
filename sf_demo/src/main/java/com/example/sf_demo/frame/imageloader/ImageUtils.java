package com.example.sf_demo.frame.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * @time 2019/11/21 15:17
 * @desc
 */
public class ImageUtils {




    public static void loadImage(Context context, String imgUrl, ImageView imageView) {

        //时序图 https://www.processon.com/diagraming/5ddb3264e4b09e8b0b725dd4


        final RequestManager requestManager = Glide.with(context);
        /*
           Glide # with(Context context)

           方法返回一个 RequestManager 对象，主要功能是给 context 绑定生命周期，生命周期结束时取消加载。
           生命周期分两种情况，context 是 ApplicationContext 则生命周期与应用的生命周期一致
           context 是 Fragment 和 Activity 。

           如何绑定Context生命周期

            当Context是Fragment 或者是Activity时， 会创建一个不可见的Fragment 添加到当前的Activity中，通过监听不可见的Fragment生命周期来
            间接监听 Fragment 和 Activity的生命周期。这个设计有点6

         */




        //RequestManagerRetriever
        final DrawableTypeRequest<String> drawableTypeRequest = requestManager.load(imgUrl);
        /*
       2 RequestManager # load(String string)

            、、、

             RequestManager # load(String string)

              public DrawableTypeRequest<String> load(String string) {
                return (DrawableTypeRequest<String>) fromString().load(string);
            }

            、、、

           2.1 RequestManager # fromString()

            方法返回一个DrawableTypeRequest<String> 对象，load()方法内部调用fromString()，fromString()方法接着调用
            loadGeneric(Class<T> modelClass)，它返回DrawableTypeRequest<String>对象 ,该方法内部主要创建streamModelLoader
            和 fileDescriptorModelLoader 对象，
            ModelLoader主要用来加载图片。根据不同的modelClass 入参返回不同 ModelLoader 对象，此处入参为String 最终返回
            StreamStringLoader 对象，它是实现了 ModelLoader接口。

            最后将这些参数传入 DrawableTypeRequest 构造中 返回一个 DrawableTypeRequest<String> 对象。

            、、、

               RequestManager#loadGeneric(Class<T> modelClass)

                 private <T> DrawableTypeRequest<T> loadGeneric(Class<T> modelClass) {

                        //根据不同的modelClass 入参返回不同 ModelLoader 对象
                     ModelLoader<T, InputStream> streamModelLoader = Glide.buildStreamModelLoader(modelClass, context);

                     ModelLoader<T, ParcelFileDescriptor> fileDescriptorModelLoader =Glide.buildFileDescriptorModelLoader(modelClass, context);

            	   if (modelClass != null && streamModelLoader == null && fileDescriptorModelLoader == null) {
                         throw new IllegalArgumentException("Unknown type " + modelClass + ". You must provide a Model of a type for"
                                 + " which there is a registered ModelLoader, if you are using a custom model, you must first call"
                                 + " Glide#register with a ModelLoaderFactory for your custom model class");
                     }

                     return optionsApplier.apply(new DrawableTypeRequest<T>(modelClass, streamModelLoader, fileDescriptorModelLoader, context,glide, requestTracker, lifecycle, optionsApplier));
                 }

           、、、


           DrawableTypeRequest 源码

           DrawableTypeRequest # 构造方法
             、、、
            DrawableTypeRequest(Class<ModelType> modelClass, ModelLoader<ModelType, InputStream> streamModelLoader,
                         ModelLoader<ModelType, ParcelFileDescriptor> fileDescriptorModelLoader, Context context, Glide glide,
                         RequestTracker requestTracker, Lifecycle lifecycle, RequestManager.OptionsApplier optionsApplier) {
               super(context,
                     modelClass,
                     //下文有用
                     buildProvider(glide, streamModelLoader, fileDescriptorModelLoader, GifBitmapWrapper.class,
                      GlideDrawable.class, null),
                       glide, requestTracker, lifecycle);
               this.streamModelLoader = streamModelLoader;
               this.fileDescriptorModelLoader = fileDescriptorModelLoader;
               this.optionsApplier = optionsApplier;
            }


            buildProvider()方法 返回 FixedLoadProvider

            private static <A, Z, R> FixedLoadProvider<A, ImageVideoWrapper, Z, R> buildProvider(Glide glide,
                    ModelLoader<A, InputStream> streamModelLoader,
                    ModelLoader<A, ParcelFileDescriptor> fileDescriptorModelLoader, Class<Z> resourceClass,
                    Class<R> transcodedClass,
                    ResourceTranscoder<Z, R> transcoder) {
                if (streamModelLoader == null && fileDescriptorModelLoader == null) {
                    return null;
                }

                //返回 ResourceTranscoder 主要用来对图片进行转码 实际会构建出一个 GifBitmapWrapperDrawableTranscoder 对象
                if (transcoder == null) {
                    transcoder = glide.buildTranscoder(resourceClass, transcodedClass);
                }

               // 返回对象用于对图片进行 编解码，实际会返回一个 ImageVideoGifDrawableLoadProvider 对象
                DataLoadProvider<ImageVideoWrapper, Z> dataLoadProvider = glide.buildDataProvider(ImageVideoWrapper.class,
                        resourceClass);

                //将 ImageVideoGifDrawableLoadProvider 和 GifBitmapWrapperDrawableTranscoder 传入 ImageVideoModelLoader 构造中 创建对象
                ImageVideoModelLoader<A> modelLoader = new ImageVideoModelLoader<A>(streamModelLoader,
                        fileDescriptorModelLoader);

                return new FixedLoadProvider<A, ImageVideoWrapper, Z, R>(modelLoader, transcoder, dataLoadProvider);
            }


             、、、

           DrawableTypeRequest #  asBitmap() ，返回 BitmapTypeRequest<String> 对象
           DrawableTypeRequest #  asGif()() ，返回 GifTypeRequest<String> 对象


           2.2 DrawableTypeRequest<String> # load(String model)

                load(String model)方法返回一个 DrawableTypeRequest 对象，此方法是 DrawableTypeRequest 父类 DrawableRequestBuilder 中的方法。

                DrawableRequestBuilder 继承自 GenericRequestBuilder，内部有很多方法，这些方法绝大部分就是Glide的API

         */

        drawableTypeRequest.into(imageView);
        /*
         3 DrawableTypeRequest # into(ImageView view)

          该方法定义在 DrawableRequestBuilder 中，具体实现在 GenericRequestBuilder#into()方法中

           、、、
           3.1  GenericRequestBuilder#into()

             public Target<TranscodeType> into(ImageView view) {
                Util.assertMainThread();
                if (view == null) {
                    throw new IllegalArgumentException("You must pass in a non null View");
                }

                if (!isTransformationSet && view.getScaleType() != null) {
                    switch (view.getScaleType()) {
                        case CENTER_CROP:
                            applyCenterCrop();
                            break;
                        case FIT_CENTER:
                        case FIT_START:
                        case FIT_END:
                            applyFitCenter();
                            break;
                        //$CASES-OMITTED$
                        default:
                            // Do nothing.
                    }
                }

                return into(glide.buildImageViewTarget(view, transcodeClass));
            }
           、、、

            最后一行中 glide.buildImageViewTarget(view, transcodeClass) 会返回一个Target对象，这个Target对象则是用来最终

            展示图片用的。

           3.1.1 Glide # buildImageViewTarget(ImageView imageView, Class<R> transcodedClass) 返回 Target<R>，它内部又调用

           3.1.2 ImageViewTargetFactory#buildTarget(ImageView view, Class<Z> clazz) 返回 Target<R>
            、、、
            ImageViewTargetFactory#buildTarget()

             public <Z> Target<Z> buildTarget(ImageView view, Class<Z> clazz) {
                if (GlideDrawable.class.isAssignableFrom(clazz)) {
                    return (Target<Z>) new GlideDrawableImageViewTarget(view);
                } else if (Bitmap.class.equals(clazz)) {
                    return (Target<Z>) new BitmapImageViewTarget(view);
                } else if (Drawable.class.isAssignableFrom(clazz)) {
                    return (Target<Z>) new DrawableImageViewTarget(view);
                } else {
                    throw new IllegalArgumentException("Unhandled class: " + clazz
                            + ", try .as*(Class).transcode(ResourceTranscoder)");
                }
            }

            、、、

            这个方法主要通过 clazz 构建不同的Target对象。这个clazz 参数基本只有两种情况，当我们调用了asBitmap()方法
            就会构建出 BitmapImageViewTarget 对象，否则构建的都是 GlideDrawableImageViewTarget 对象，最后的基本用不上。

            所有最后返回一个 GlideDrawableImageViewTarget 对象，接下来看 GenericRequestBuilder # into(Y target)


          3.2  GenericRequestBuilder# into(Y target)

            、、、

               public <Y extends Target<TranscodeType>> Y into(Y target) {
                    Util.assertMainThread();
                    if (target == null) {
                        throw new IllegalArgumentException("You must pass in a non null Target");
                    }
                    if (!isModelSet) {
                        throw new IllegalArgumentException("You must first set a model (try #load())");
                    }

                    Request previous = target.getRequest();

                    if (previous != null) {
                        previous.clear();
                        requestTracker.removeRequest(previous);
                        previous.recycle();
                    }

                    ☆重点关注区域☆

                    //构建一个Request
                    Request request = buildRequest(target);
                    target.setRequest(request);
                    lifecycle.addListener(target);

                    //执行这个Request
                    requestTracker.runRequest(request);

                    return target;
                }

            、、、

            3.3 GenericRequestBuilder # buildRequest(Target<TranscodeType> target)

                构建Request对象，它内部调用了GenericRequestBuilder#buildRequestRecursive()方法，
                方法内部只看 obtainRequest()方法，该方法接着调用 GenericRequest.obtain() 方法构建Request对象。

                GenericRequest.obtain()方法内会构建一个  GenericRequest 对象，并通过 GenericRequest#init()方法
                初始化入参信息


            3.4 执行Request   RequestTracker#runRequest(Request request)

                、、、
                 public void runRequest(Request request) {
                    requests.add(request);
                    //不是暂停状态
                    if (!isPaused) {
                        request.begin();
                    } else {
                      //添加到待执行的集合中
                        pendingRequests.add(request);
                    }
                }
                、、、

                判断当前Glide是不是暂停状态，如果不是就调用begin()开始执行
                否则添加到待执行的队列里面，等暂停状态接触之后再执行。重点关注执行流程



            3.4.1 Request#begin() [GenericRequest#begin()]

                开始执行请求

                、、、
                GenericRequest#begin()

                 public void begin() {
                    startTime = LogTime.getLogTime();
                    if (model == null) {
                        onException(null);
                        return;
                    }

                    status = Status.WAITING_FOR_SIZE;
                    if (Util.isValidDimensions(overrideWidth, overrideHeight)) {

                       //重点
                        onSizeReady(overrideWidth, overrideHeight);
                    } else {
                        target.getSize(this);
                    }

                    if (!isComplete() && !isFailed() && canNotifyStatusChanged()) {
                        target.onLoadStarted(getPlaceholderDrawable());
                    }
                    if (Log.isLoggable(TAG, Log.VERBOSE)) {
                        logV("finished run method in " + LogTime.getElapsedMillis(startTime));
                    }
                }
                、、、


            3.4.1.1     GenericRequest#onSizeReady(int width, int height)
                、、、
                ☆☆☆☆☆
                public void onSizeReady(int width, int height) {
                    if (Log.isLoggable(TAG, Log.VERBOSE)) {
                        logV("Got onSizeReady in " + LogTime.getElapsedMillis(startTime));
                    }
                    if (status != Status.WAITING_FOR_SIZE) {
                        return;
                    }
                    status = Status.RUNNING;

                    width = Math.round(sizeMultiplier * width);
                    height = Math.round(sizeMultiplier * height);

                    //重点 loadProvider 就是  FixedLoadProvider ,返回 ImageVideoModelLoader
                    ModelLoader<A, T> modelLoader = loadProvider.getModelLoader();

                    //返回  ImageVideoFetcher
                    final DataFetcher<T> dataFetcher = modelLoader.getResourceFetcher(model, width, height);

                    if (dataFetcher == null) {
                        onException(new Exception("Failed to load model: \'" + model + "\'"));
                        return;
                    }

                    //返回 GifBitmapWrapperDrawableTranscoder 对象
                    ResourceTranscoder<Z, R> transcoder = loadProvider.getTranscoder();

                    if (Log.isLoggable(TAG, Log.VERBOSE)) {
                        logV("finished setup for calling load in " + LogTime.getElapsedMillis(startTime));
                    }
                    loadedFromMemoryCache = true;

                    //Engine#load()
                    loadStatus = engine.load(signature, width, height, dataFetcher, loadProvider, transformation, transcoder,
                            priority, isMemoryCacheable, diskCacheStrategy, this);


                    loadedFromMemoryCache = resource != null;

                    if (Log.isLoggable(TAG, Log.VERBOSE)) {
                        logV("finished onSizeReady in " + LogTime.getElapsedMillis(startTime));
                    }
                }

                、、、

                接下来关注 ImageVideoModelLoader#getResourceFetcher()：ImageVideoFetcher

                、、、
                    public DataFetcher<ImageVideoWrapper> getResourceFetcher(A model, int width, int height) {
                        DataFetcher<InputStream> streamFetcher = null;
                        if (streamLoader != null) {
                            streamFetcher = streamLoader.getResourceFetcher(model, width, height);
                        }
                        DataFetcher<ParcelFileDescriptor> fileDescriptorFetcher = null;
                        if (fileDescriptorLoader != null) {
                            fileDescriptorFetcher = fileDescriptorLoader.getResourceFetcher(model, width, height);
                        }

                        if (streamFetcher != null || fileDescriptorFetcher != null) {

                            //返回 ImageVideoFetcher对象
                            return new ImageVideoFetcher(streamFetcher, fileDescriptorFetcher);
                        } else {
                            return null;
                        }
                    }
                、、、

                Engine#load()方法 大部分主要在处理缓存
                、、、
                 public <T, Z, R> LoadStatus load(Key signature, int width, int height, DataFetcher<T> fetcher,
                        DataLoadProvider<T, Z> loadProvider, Transformation<Z> transformation, ResourceTranscoder<Z, R> transcoder,
                        Priority priority, boolean isMemoryCacheable, DiskCacheStrategy diskCacheStrategy, ResourceCallback cb) {
                    ......

                    //EngineJob 主要用来开启线程的，为后面加载图片做准备
                    EngineJob engineJob = engineJobFactory.build(key, isMemoryCacheable);

                    //对图片进行解码
                    DecodeJob<T, Z, R> decodeJob = new DecodeJob<T, Z, R>(key, width, height, fetcher, loadProvider, transformation,
                            transcoder, diskCacheProvider, diskCacheStrategy, priority);

                    EngineRunnable runnable = new EngineRunnable(engineJob, decodeJob, priority);

                    jobs.put(key, engineJob);

                    engineJob.addCallback(cb);


                    // 让 EngineRunnable run()方法在子线程中运行
                    engineJob.start(runnable);

                    if (Log.isLoggable(TAG, Log.VERBOSE)) {
                        logWithTimeAndKey("Started new load", startTime, key);
                    }
                    return new LoadStatus(cb, engineJob);
                }

                、、、

                EngineRunnable#run()方法内部主要调用 EngineRunnable#decode()方法
                、、、
                 EngineRunnable#decode()
                 private Resource<?> decode() throws Exception {
                    if (isDecodingFromCache()) {
                        //缓存
                        return decodeFromCache();
                    } else {
                       //网络
                        return decodeFromSource();
                    }
                }
                、、、

                EngineRunnable#decodeFromSource()方法调用了 DecodeJob#decodeFromSource()
                DecodeJob内部任务十分繁重

                class DecodeJob
                、、、
                  public Resource<Z> decodeFromSource() throws Exception {
                    //返回一个Resource对象
                    Resource<T> decoded = decodeSource();
                    return transformEncodeAndTranscode(decoded);
                }

                 private Resource<T> decodeSource() throws Exception {
                    Resource<T> decoded = null;
                    try {
                        long startTime = LogTime.getLogTime();

                        //ImageVideoFetcher#loadData()：ImageVideoWrapper
                        final A data = fetcher.loadData(priority);

                        if (Log.isLoggable(TAG, Log.VERBOSE)) {
                            logWithTimeAndKey("Fetched data", startTime);
                        }
                        if (isCancelled) {
                            return null;
                        }

                        //对数据进行解码 DecodeJob#decodeFromSourceData()
                        decoded = decodeFromSourceData(data);
                    } finally {
                        fetcher.cleanup();
                    }
                    return decoded;
                }


                ImageVideoFetcher#loadData()：ImageVideoWrapper

                  public ImageVideoWrapper loadData(Priority priority) throws Exception {
                    InputStream is = null;
                    if (streamFetcher != null) {
                        try {
                           // HttpUrlFetcher#loadData()
                            is = streamFetcher.loadData(priority);

                        } catch (Exception e) {
                            if (Log.isLoggable(TAG, Log.VERBOSE)) {
                                Log.v(TAG, "Exception fetching input stream, trying ParcelFileDescriptor", e);
                            }
                            if (fileDescriptorFetcher == null) {
                                throw e;
                            }
                        }
                    }
                    ParcelFileDescriptor fileDescriptor = null;
                    if (fileDescriptorFetcher != null) {
                        try {
                            fileDescriptor = fileDescriptorFetcher.loadData(priority);
                        } catch (Exception e) {
                            if (Log.isLoggable(TAG, Log.VERBOSE)) {
                                Log.v(TAG, "Exception fetching ParcelFileDescriptor", e);
                            }
                            if (is == null) {
                                throw e;
                            }
                        }
                    }

                    //返回一个 ImageVideoWrapper对象
                    return new ImageVideoWrapper(is, fileDescriptor);
                }


                HttpUrlFetcher#loadData():InputStream

                    public InputStream loadData(Priority priority) throws Exception {
                        return loadDataWithRedirects(glideUrl.toURL(), 0 , null , glideUrl.getHeaders());
                    }

                    private InputStream loadDataWithRedirects(URL url, int redirects, URL lastUrl, Map<String, String> headers)
                            throws IOException {
                        if (redirects >= MAXIMUM_REDIRECTS) {
                            throw new IOException("Too many (> " + MAXIMUM_REDIRECTS + ") redirects!");
                        } else {
                            // Comparing the URLs using .equals performs additional network I/O and is generally broken.
                            // See http://michaelscharf.blogspot.com/2006/11/javaneturlequals-and-hashcode-make.html.
                            try {
                                if (lastUrl != null && url.toURI().equals(lastUrl.toURI())) {
                                    throw new IOException("In re-direct loop");
                                }
                            } catch (URISyntaxException e) {
                                // Do nothing, this is best effort.
                            }
                        }
                        urlConnection = connectionFactory.build(url);
                        for (Map.Entry<String, String> headerEntry : headers.entrySet()) {
                            urlConnection.addRequestProperty(headerEntry.getKey(), headerEntry.getValue());
                        }
                        urlConnection.setConnectTimeout(2500);
                        urlConnection.setReadTimeout(2500);
                        urlConnection.setUseCaches(false);
                        urlConnection.setDoInput(true);

                        // Connect explicitly to avoid errors in decoders if connection fails.
                        urlConnection.connect();
                        if (isCancelled) {
                            return null;
                        }
                        final int statusCode = urlConnection.getResponseCode();
                        if (statusCode / 100 == 2) {
                            return getStreamForSuccessfulRequest(urlConnection);
                        } else if (statusCode / 100 == 3) {
                            String redirectUrlString = urlConnection.getHeaderField("Location");
                            if (TextUtils.isEmpty(redirectUrlString)) {
                                throw new IOException("Received empty or null redirect url");
                            }
                            URL redirectUrl = new URL(url, redirectUrlString);
                            return loadDataWithRedirects(redirectUrl, redirects + 1, url, headers);
                        } else {
                            if (statusCode == -1) {
                                throw new IOException("Unable to retrieve response code from HttpUrlConnection.");
                            }
                            throw new IOException("Request failed " + statusCode + ": " + urlConnection.getResponseMessage());
                        }
                    }

                     private InputStream getStreamForSuccessfulRequest(HttpURLConnection urlConnection)
                            throws IOException {
                        if (TextUtils.isEmpty(urlConnection.getContentEncoding())) {
                            int contentLength = urlConnection.getContentLength();
                            stream = ContentLengthInputStream.obtain(urlConnection.getInputStream(), contentLength);
                        } else {
                            if (Log.isLoggable(TAG, Log.DEBUG)) {
                                Log.d(TAG, "Got non empty content encoding: " + urlConnection.getContentEncoding());
                            }
                            stream = urlConnection.getInputStream();
                        }
                        return stream;
                    }

                、、、

                以上就是获取数据行进行封装的过程，最后返回一个 ImageVideoWrapper 对象。接下来对这个对象进行解码

                DecodeJob#decodeFromSourceData(ImageVideoWrapper)：Resource
                、、、
                 private Resource<T> decodeFromSourceData(A data) throws IOException {
                    final Resource<T> decoded;
                    if (diskCacheStrategy.cacheSource()) {
                        decoded = cacheAndDecodeSourceData(data);

                    } else {
                        long startTime = LogTime.getLogTime();

                        //解码操作 FixedLoadProvider.getgetSourceDecoder()返回 GifBitmapWrapperResourceDecoder
                        //实际调用 GifBitmapWrapperResourceDecoder.decode()：Resource进行解码
                        decoded = loadProvider.getSourceDecoder().decode(data, width, height);

                        if (Log.isLoggable(TAG, Log.VERBOSE)) {
                            logWithTimeAndKey("Decoded from source", startTime);
                        }
                    }
                    return decoded;
                }

                GifBitmapWrapperResourceDecoder#decode()：Resource 解码操作

                decode()-> decode()->decodeStream(ImageVideoWrapper source, int width, int height, byte[] bytes)

                 public Resource<GifBitmapWrapper> decode(ImageVideoWrapper source, int width, int height) throws IOException {
                        ByteArrayPool pool = ByteArrayPool.get();
                        byte[] tempBytes = pool.getBytes();

                        GifBitmapWrapper wrapper = null;
                        try {
                            wrapper = decode(source, width, height, tempBytes);
                        } finally {
                            pool.releaseBytes(tempBytes);
                        }
                        return wrapper != null ? new GifBitmapWrapperResource(wrapper) : null;
                 }

                 private GifBitmapWrapper decode(ImageVideoWrapper source, int width, int height, byte[] bytes) throws IOException {
                        final GifBitmapWrapper result;
                        if (source.getStream() != null) {
                            result = decodeStream(source, width, height, bytes);
                        } else {
                          //解码并返回包装类
                            result = decodeBitmapWrapper(source, width, height);
                        }
                        return result;
                    }

                  private GifBitmapWrapper decodeStream(ImageVideoWrapper source, int width, int height, byte[] bytes)
                            throws IOException {
                        InputStream bis = streamFactory.build(source.getStream(), bytes);
                        //流中读取2个字节的数据，来判断这张图是GIF图还是普通的静图
                        bis.mark(MARK_LIMIT_BYTES);
                        ImageHeaderParser.ImageType type = parser.parse(bis);
                        bis.reset();

                        GifBitmapWrapper result = null;
                        if (type == ImageHeaderParser.ImageType.GIF) {
                            result = decodeGifWrapper(bis, width, height);
                        }
                        // Decoding the gif may fail even if the type matches.
                        if (result == null) {
                            // We can only reset the buffered InputStream, so to start from the beginning of the stream, we need to
                            // pass in a new source containing the buffered stream rather than the original stream.

                            ImageVideoWrapper forBitmapDecoder = new ImageVideoWrapper(bis, source.getFileDescriptor());
                           //解码普通格式图片
                            result = decodeBitmapWrapper(forBitmapDecoder, width, height);
                        }
                        return result;
                    }

                   private GifBitmapWrapper decodeBitmapWrapper(ImageVideoWrapper toDecode, int width, int height) throws IOException {
                         GifBitmapWrapper result = null;

                          //ImageVideoBitmapDecoder#decode():Resource<Bitmap>
                         Resource<Bitmap> bitmapResource = bitmapDecoder.decode(toDecode, width, height);
                         if (bitmapResource != null) {
                         result = new GifBitmapWrapper(bitmapResource, null);
                         }

                     return result;
                  }


                ImageVideoBitmapDecoder#decode():Resource<Bitmap>

                public Resource<Bitmap> decode(ImageVideoWrapper source, int width, int height) throws IOException {
                    Resource<Bitmap> result = null;
                    //获取服务器端返回的数据流
                    InputStream is = source.getStream();
                    if (is != null) {
                        try {
                        //调用 StreamBitmapDecoder#decode()进行解码操作
                            result = streamDecoder.decode(is, width, height);

                        } catch (IOException e) {
                            if (Log.isLoggable(TAG, Log.VERBOSE)) {
                                Log.v(TAG, "Failed to load image from stream, trying FileDescriptor", e);
                            }
                        }
                    }

                    if (result == null) {
                        ParcelFileDescriptor fileDescriptor = source.getFileDescriptor();
                        if (fileDescriptor != null) {
                            result = fileDescriptorDecoder.decode(fileDescriptor, width, height);
                        }
                    }
                    return result;
                }


                StreamBitmapDecoder#decode()

                public Resource<Bitmap> decode(InputStream source, int width, int height) {
                    Bitmap bitmap = downsampler.decode(source, bitmapPool, width, height, decodeFormat);
                    return BitmapResource.obtain(bitmap, bitmapPool);
                }

                Downsampler#decode():Bitmap

                服务器端返回的数据获取，对图片的加载，图片压缩 旋转 圆角等逻辑处理，最后返回一个Bitmap对象


                BitmapResource#obtain():BitmapResource 对Bitmap进行封装

                、、、


         */



    }


    public static void loadDemo(Context context, String imgUrl, ImageView imageView){


        Glide
                .with(context)
                .load(imgUrl)
                .asBitmap()
                .override(100,100)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .preload();


    }



}

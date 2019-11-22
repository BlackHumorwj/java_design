package com.example.sf_demo.imageloader;

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

        final RequestManager requestManager = Glide.with(context);
        //with() 绑定生命周期，生命周期结束时取消加载
        //生命周期分两种情况，context 是 ApplicationContext 则生命周期与应用的生命周期一致
        //context 是 Fragment 和 Activity 则 会创建一个不可见的Fragment 添加到布局中，通过监听不可见的Fragment生命周期来
        //间接监听 Fragment 和 Activity的生命周期

        //RequestManagerRetriever


        final DrawableTypeRequest<String> drawableTypeRequest = requestManager.load(imgUrl);

        drawableTypeRequest.into(imageView);

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

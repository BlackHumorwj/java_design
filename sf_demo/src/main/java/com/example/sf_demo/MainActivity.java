package com.example.sf_demo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.target.Target;
import com.sonnyjack.widget.dragview.SonnyJackDragView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }


    private static final String IMG_URL = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
    private ImageView mImageView;
    private ImageView mImageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_click).setOnClickListener(this);
        mImageView = findViewById(R.id.image);
        mImageView1 = findViewById(R.id.image_1);


        getAppMaxMemory();

        Log.e("lifeCycle", "onCreate");
        initDragView();
    }


    private void initDragView() {
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.ic_launcher_round);
        SonnyJackDragView sonnyJackDragView = new SonnyJackDragView.Builder()
                .setActivity(this)//当前Activity，不可为空
                .setDefaultLeft(130)//初始位置左边距
                .setDefaultTop(130)//初始位置上边距
                .setNeedNearEdge(false)//拖动停止后，是否移到边沿
                .setSize(100)//DragView大小
                .setView(imageView)//设置自定义的DragView，切记不可为空
                .build();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("lifeCycle", "onResume");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e("lifeCycle", "onNewIntent");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("lifeCycle", "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("lifeCycle", "onDestroy");
    }


    private void viewPostSource() {

        mImageView.post(new Runnable() {
            @Override
            public void run() {

            }
        });
    }


    /**
     * 获取应用能使用的最大内存
     *
     * @return
     */
    private int getAppMaxMemory() {

        ImageView imageView = mImageView1;


        imageView = null;


        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        Log.d("TAG", "Max memory is " + maxMemory + "KB----" + maxMemory / 1024 + "MB" + "-----" + imageView + "---" + mImageView1);
        return maxMemory;
    }


    @Override
    public void onClick(View v) {

        final int id = v.getId();

        if (id == R.id.btn_click) {

            startActivity(MainActivity.newInstance(this));
            return;
        }

        final RequestManager requestManager = Glide.with(this);//with 绑定生命周期，生命周期结束时取消加载

        final DrawableTypeRequest<String> drawableTypeRequest = requestManager.load(IMG_URL);

        final BitmapTypeRequest<String> bitmapTypeRequest = drawableTypeRequest.asBitmap();

        final BitmapRequestBuilder<String, Bitmap> bitmapBitmapRequestBuilder = bitmapTypeRequest.override(200, 200);

        final Target<Bitmap> target = bitmapBitmapRequestBuilder.into(mImageView);


        Glide
                .with(this)//with 绑定生命周期，生命周期结束时取消加载
                .load(IMG_URL)
                .asBitmap()
                .override(1000, 1000)//指定加载多少像素的照片
                .into(mImageView1);
    }
}

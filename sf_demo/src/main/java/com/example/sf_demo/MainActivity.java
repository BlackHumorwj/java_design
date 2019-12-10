package com.example.sf_demo;

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

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String IMG_URL= "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
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



    }


    private void viewPostSource(){

        mImageView.post(new Runnable() {
            @Override
            public void run() {

            }
        });
    }


    /**
     * 获取应用能使用的最大内存
     * @return
     */
    private int getAppMaxMemory (){

        ImageView imageView = mImageView1;


        imageView =null;




        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        Log.d("TAG", "Max memory is " + maxMemory + "KB----"+maxMemory/1024 +"MB"+"-----"+imageView+"---"+mImageView1);
        return  maxMemory;
    }


    @Override
    public void onClick(View v) {


        final RequestManager requestManager = Glide.with(this);//with 绑定生命周期，生命周期结束时取消加载

        final DrawableTypeRequest<String> drawableTypeRequest = requestManager.load(IMG_URL);

        final BitmapTypeRequest<String> bitmapTypeRequest = drawableTypeRequest.asBitmap();

        final BitmapRequestBuilder<String, Bitmap> bitmapBitmapRequestBuilder = bitmapTypeRequest.override(200, 200);

        final Target<Bitmap> target = bitmapBitmapRequestBuilder.into(mImageView);



        Glide
                .with(this)//with 绑定生命周期，生命周期结束时取消加载
                .load(IMG_URL)
                .asBitmap()
                .override(1000,1000)//指定加载多少像素的照片
                .into(mImageView1);
    }
}

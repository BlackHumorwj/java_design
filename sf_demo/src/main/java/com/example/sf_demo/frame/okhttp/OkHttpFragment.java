package com.example.sf_demo.frame.okhttp;

import android.view.View;

import com.example.sf_demo.R;
import com.example.sf_demo.base.BaseFragment;

import java.io.IOException;

/**
 * @time 2020/1/10 17:06
 * @desc
 */
public class OkHttpFragment extends BaseFragment implements View.OnClickListener {


    private OkHttpUtil mOkHttpUtil;

    private static final String url = "http://www.wooyun.org";

    @Override
    protected int getLayoutResID() {
        return R.layout.okhttp_fragment;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.btn_get).setOnClickListener(this);
        view.findViewById(R.id.btn_post).setOnClickListener(this);
    }
    @Override
    protected void initData() {
        mOkHttpUtil = new OkHttpUtil();
    }


    @Override
    public void onClick(View v) {
        final int vId = v.getId();
        switch (vId){
            case R.id.btn_get:

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mOkHttpUtil.httpGet(url);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();


                break;
            case R.id.btn_post:


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mOkHttpUtil.httpPostKeyValue("https://en.wikipedia.org/w/index.php");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                break;
        }

    }
}

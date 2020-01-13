package com.example.sf_demo.frame.okhttp;

import android.view.View;
import android.widget.TextView;

import com.example.sf_demo.R;
import com.example.sf_demo.base.BaseFragment;
import com.example.sf_demo.frame.okhttp.chain.ChainDemo;

import java.io.IOException;

/**
 * @time 2020/1/10 17:06
 * @desc
 */
public class OkHttpFragment extends BaseFragment implements View.OnClickListener {


    private OkHttpUtil mOkHttpUtil;

    private static final String url = "http://www.wooyun.org";
    private TextView mTvDesc;

    @Override
    protected int getLayoutResID() {
        return R.layout.okhttp_fragment;
    }

    @Override
    protected void initView(View view) {
        view.findViewById(R.id.btn_get).setOnClickListener(this);
        view.findViewById(R.id.btn_synchronous_get).setOnClickListener(this);
        view.findViewById(R.id.btn_post).setOnClickListener(this);
        mTvDesc = view.findViewById(R.id.text);

    }
    @Override
    protected void initData() {
        mOkHttpUtil = new OkHttpUtil();

        ChainDemo.doPrimerChain();

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
                            mOkHttpUtil.httpGet(url,null);
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
            case R.id.btn_synchronous_get:


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
//                            mOkHttpUtil.httpHead("https://api.github.com/repos/square/okhttp/issues", new DemoCallBack<CharSequence>() {
//                                @Override
//                                public void callBack(CharSequence charSequence) {
//                                    mTvDesc.setText(charSequence);
//                                }
//                            });
//                            mOkHttpUtil.postString("https://api.github.com/markdown/raw", new DemoCallBack<CharSequence>() {
//                                @Override
//                                public void callBack(CharSequence charSequence) {
//                                    mTvDesc.setText(charSequence);
//                                }
//                            });
//                            mOkHttpUtil.postStreaming("https://api.github.com/markdown/raw", new DemoCallBack<CharSequence>() {
//                                @Override
//                                public void callBack(CharSequence charSequence) {
//                                    mTvDesc.setText(charSequence);
//                                }
//                            });
//                            mOkHttpUtil.postFile("https://api.github.com/markdown/raw", new DemoCallBack<CharSequence>() {
//                                @Override
//                                public void callBack(CharSequence charSequence) {
//                                    mTvDesc.setText(charSequence);
//                                }
//                            });
//                            mOkHttpUtil.postFormParameters("https://en.wikipedia.org/w/index.php", new DemoCallBack<CharSequence>() {
//                                @Override
//                                public void callBack(CharSequence charSequence) {
//                                    mTvDesc.setText(charSequence);
//                                }
//                            });
                            //mOkHttpUtil.responseCache(new File(Environment.getExternalStorageState()));


                            mOkHttpUtil.handlingAuthentication();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                break;
        }

    }
}

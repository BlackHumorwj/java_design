package com.example.sf_demo;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.sf_demo.base.BaseActivity;
import com.example.sf_demo.frame.okhttp.OkHttpFragment;

import java.util.ArrayList;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends BaseActivity {


    private AppCompatSpinner mSpinner;
    public static ArrayList<String> mList = new ArrayList<>();
    private FragmentTransaction mFragmentTransaction;

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }


    private static final String IMG_URL = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";


    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }


    @Override
    protected void initView() {
        mList.add(OkHttpFragment.class.getName());
        mList.add("dddd0");
        mList.add("dddd0");
        mList.add("dddd01");
        mSpinner = findViewById(R.id.spinner);
        mSpinner.setAdapter(new ArrayAdapter(this, R.layout.spinner_item, mList));
        //单独设置下拉的textview
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final String path = mList.get(position);
                final Class<?> aClass;
                try {
                    aClass = Class.forName(path);
                    Fragment fragment = (Fragment) aClass.newInstance();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, fragment).commit();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    protected void initData() {
        getAppMaxMemory();
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

    }


    /**
     * 获取应用能使用的最大内存
     *
     * @return
     */
    private int getAppMaxMemory() {


        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        Log.d("TAG", "Max memory is " + maxMemory + "KB----" + maxMemory / 1024 + "MB");
        return maxMemory;
    }

}

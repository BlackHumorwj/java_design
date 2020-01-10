package com.example.sf_demo.base;

import android.os.Bundle;

import com.example.sf_demo.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/**
 * @time 2020/1/10 16:26
 * @desc
 */
public abstract class BaseActivity extends AppCompatActivity {


    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResID());
        mToolbar = findViewById(R.id.toolbar);

        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }

        initView();
        initData();

    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract int getLayoutResID();
}

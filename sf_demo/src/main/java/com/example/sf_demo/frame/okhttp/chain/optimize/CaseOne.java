package com.example.sf_demo.frame.okhttp.chain.optimize;

import android.util.Log;

/**
 * @time 2020/1/13 19:42
 * @desc
 */
public class CaseOne extends BaseCase {

    public CaseOne(boolean isConsume) {
        super(isConsume);
    }

    @Override
    protected void doSomething() {
        Log.e("xxxx", this.getClass().getName());
    }
}

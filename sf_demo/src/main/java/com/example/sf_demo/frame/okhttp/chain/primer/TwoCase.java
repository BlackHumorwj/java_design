package com.example.sf_demo.frame.okhttp.chain.primer;

import android.text.TextUtils;

/**
 * @time 2020/1/13 20:15
 * @desc
 */
public class TwoCase implements ICase {



    @Override
    public void doSomething(String input, ICase iCase) {

        if (TextUtils.equals("2",input)){
            return;
        }

        //当前没有处理，回调回去让下面一个Case处理 iCase 其实是 CaseChainManager
        iCase.doSomething(input,iCase);

    }
}

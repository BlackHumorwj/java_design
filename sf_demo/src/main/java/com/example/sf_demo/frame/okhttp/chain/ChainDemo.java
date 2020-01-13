package com.example.sf_demo.frame.okhttp.chain;

import android.text.TextUtils;

import com.example.sf_demo.frame.okhttp.chain.optimize.CaseOne;
import com.example.sf_demo.frame.okhttp.chain.optimize.CaseTwo;
import com.example.sf_demo.frame.okhttp.chain.optimize.DefaultCase;
import com.example.sf_demo.frame.okhttp.chain.primer.CaseChainManager;
import com.example.sf_demo.frame.okhttp.chain.primer.OneCase;
import com.example.sf_demo.frame.okhttp.chain.primer.TwoCase;

/**
 * @time 2020/1/13 19:46
 * @desc
 */
public class ChainDemo {


    //初级版的责任链模式
    public static void doPrimerChain() {

        String input = "1";

        final CaseOne caseOne = new CaseOne(TextUtils.equals("1", input));

        final CaseTwo caseTwo = new CaseTwo(TextUtils.equals("2", input));

        final DefaultCase defaultCase = new DefaultCase(true);

        //赋值nextCase
        caseOne.setNextCase(caseTwo);

        caseTwo.setNextCase(defaultCase);

        caseOne.handleRequest();
    }

    //优化后的 责任链模式
    public static void optimizeChain() {

        final CaseChainManager chainManager = new CaseChainManager();

        chainManager//
                .addCase(new OneCase())//
                .addCase(new TwoCase())//
                .doSomething("1", chainManager);


    }
}

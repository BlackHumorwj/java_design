package com.example.sf_demo.frame.okhttp.chain;

import android.text.TextUtils;

import com.example.sf_demo.frame.okhttp.chain.optimize.CaseOne;
import com.example.sf_demo.frame.okhttp.chain.optimize.CaseTwo;
import com.example.sf_demo.frame.okhttp.chain.optimize.DefaultCase;

/**
 * @time 2020/1/13 19:46
 * @desc
 */
public class ChainDemo {

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
}

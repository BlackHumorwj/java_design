package com.example.sf_demo.frame.okhttp.chain.primer;

import java.util.ArrayList;

/**
 * @time 2020/1/13 20:17
 * @desc
 */
public class CaseChainManager implements ICase{

    private ArrayList<ICase> mCases = new ArrayList<>();

    private int index;

    //添加case
    public CaseChainManager addCase(ICase iCase){
        mCases.add(iCase);
        return this;
    }


    @Override
    public void doSomething(String input, ICase iCase) {

        if (index == mCases.size()){
            return;
        }
        final ICase currentCase = mCases.get(index);
        index ++;
        currentCase.doSomething(input,this);
    }
}

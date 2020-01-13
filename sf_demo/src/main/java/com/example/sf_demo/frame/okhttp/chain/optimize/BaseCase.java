package com.example.sf_demo.frame.okhttp.chain.optimize;

/**
 * @time 2020/1/13 19:38
 * @desc
 */
public abstract class BaseCase {

    //true表示可以处理改case
    private boolean isConsume;

    public BaseCase(boolean isConsume) {
        this.isConsume = isConsume;
    }


    private BaseCase nextCase;

    public void setNextCase(BaseCase nextCase) {
        this.nextCase = nextCase;
    }


    public void handleRequest() {
        if (isConsume) {
            doSomething();
        } else {
            if (nextCase != null) {
                nextCase.handleRequest();
            }
        }

    }

    protected abstract void doSomething();


}

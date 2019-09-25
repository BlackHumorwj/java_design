package com.example.design.cjx_ms.a_factory.a_easy_factory;

/**
 * @time 2019/9/25 19:41
 * @desc
 */
public class AddOperate implements Operate {
    @Override
    public int getResult(int numA, int numB) {
        return numA+numB;
    }
}

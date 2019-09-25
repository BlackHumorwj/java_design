package com.example.design.cjx_ms.a_factory.c_abs_factory;

/**
 * @time 2019/9/25 19:57
 * @desc 抽象工厂模式
 * 缺点：麻烦
 */
public interface IFactory {

    IOperateBook createOperateBook();

    IOperateUser createOperateUser();


}

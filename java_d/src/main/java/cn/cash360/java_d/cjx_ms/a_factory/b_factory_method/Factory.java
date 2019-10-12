package cn.cash360.java_d.cjx_ms.a_factory.b_factory_method;

import cn.cash360.java_d.cjx_ms.a_factory.a_easy_factory.Operate;

/**
 * @time 2019/9/25 19:48
 * @desc 工厂模式，工厂抽象成接口，针对不同的产品，创建对应的工厂类<><br/>
 * 缺点：每增加一个产品类，就需要增加一个对应的工厂类，增加了额外的开发量
 */
public interface Factory {

    Operate createOperate();
}

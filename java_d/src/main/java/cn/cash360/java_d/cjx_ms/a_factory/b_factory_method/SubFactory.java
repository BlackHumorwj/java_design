package cn.cash360.java_d.cjx_ms.a_factory.b_factory_method;


import cn.cash360.java_d.cjx_ms.a_factory.a_easy_factory.Operate;
import cn.cash360.java_d.cjx_ms.a_factory.a_easy_factory.SubOperate;

/**
 * @time 2019/9/25 19:49
 * @desc
 */
public class SubFactory implements Factory {
    @Override
    public Operate createOperate() {
        return new SubOperate();
    }
}

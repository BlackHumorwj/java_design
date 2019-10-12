package cn.cash360.java_d.cjx_ms.a_factory.c_abs_factory;

/**
 * @time 2019/9/25 20:05
 * @desc
 */
public class WolfFactory implements IFactory {
    @Override
    public IOperateBook createOperateBook() {
        return new WolfOperateBook();
    }

    @Override
    public IOperateUser createOperateUser() {
        return new WolfOperateUser();
    }
}

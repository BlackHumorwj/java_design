package cn.cash360.java_d.cjx_ms.a_factory.c_abs_factory;

/**
 * @time 2019/9/25 19:58
 * @desc
 */
public class TigerFactory implements IFactory {
    @Override
    public IOperateBook createOperateBook() {

        return new TigerOperateBook();
    }

    @Override
    public IOperateUser createOperateUser() {
        return new TigerOperateUser();
    }
}

package cn.cash360.java_d.cjx_ms.a_factory.a_easy_factory;

/**
 * @time 2019/9/25 19:41
 * @desc
 */
public class SubOperate implements Operate {
    @Override
    public int getResult(int numA, int numB) {
        return numA - numB;
    }
}

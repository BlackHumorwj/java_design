package cn.cash360.java_d.cjx_ms.a_factory.a_easy_factory;

/**
 * @time 2019/9/25 19:42
 * @desc 简单工厂模式<br />
 * <p>
 * 缺点：工厂类代码臃肿，耦合高，修改时需要打开工厂类，违反开闭原则
 */
public class EasyFactory {


    public static Operate createOperate(String operateTye) {
        Operate operate = null;

        switch (operateTye) {
            case "+":
                operate = new AddOperate();
                break;
            case "-":
                operate = new SubOperate();

                break;
        }

        return operate;


    }

}

package cn.cash360.java_d;

import cn.cash360.java_d.cjx_ms.f_adapter.class_adapter.ClassAdapter;
import cn.cash360.java_d.cjx_ms.f_adapter.class_adapter.NewInterface;
import cn.cash360.java_d.cjx_ms.f_adapter.class_adapter.Old;
import cn.cash360.java_d.cjx_ms.f_adapter.interface_adapter.SourceSub1;
import cn.cash360.java_d.cjx_ms.f_adapter.object_adapter.Wrapper;
import cn.cash360.java_d.cjx_ms.g_decorator.Decorator;
import cn.cash360.java_d.cjx_ms.g_decorator.Original;
import cn.cash360.java_d.cjx_ms.g_decorator.Source;
import cn.cash360.java_d.proxy.proxy_jt.Student;
import cn.cash360.java_d.proxy.proxy_jt.StudentProxy;
import cn.cash360.java_d.proxy.proxy_jt.Teacher;

public class myClass {


    public static void main(String[] args) {
        Teacher teacher = new Student("bo jin");

        StudentProxy proxy = new StudentProxy(teacher);

        System.out.println(System.getProperty("file.encoding"));

        proxy.giveMoney();

        adapter();


        decorator();


    }

    /**
     * 装饰
     */
    private static void decorator() {


        /*
        装饰器模式的应用场景：

        1、需要扩展一个类的功能。

        2、动态的为一个对象增加功能，而且还能动态撤销。（继承不能做到这一点，继承的功能是静态的，不能动态增删。）

        缺点：产生过多相似的对象，不易排错！
         */

        Source source = new Original();

        Source decorator = new Decorator(source);

        decorator.method();


    }


    private static void adapter() {

        //类适配器模式  将老的方法适配的新的接口中
        //类的适配器模式：当希望将一个类转换成满足另一个新接口的类时，可以使用类的适配器模式，创建一个新类，继承原有的类，实现新的接口即可。
        NewInterface newInterface = new ClassAdapter();
        newInterface.method2();
        newInterface.method1();

        //对象适配器模式
        //当希望将一个对象转换成满足另一个新接口的对象时，可以创建一个Wrapper类，持有原类的一个实例，在Wrapper类的方法中，调用实例的方法就行。
        final Old old = new Old();
        final Wrapper wrapper = new Wrapper(old);
        wrapper.method1();
        wrapper.method2();


        //接口适配器  不用重写所有的接口方法
        //当不希望实现一个接口中所有的方法时，可以创建一个抽象类Wrapper，实现所有方法，我们写别的类的时候，继承抽象类即可。
        NewInterface newI = new SourceSub1();
        newI.method1();

    }


}

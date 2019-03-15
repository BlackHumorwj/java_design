package cn.cash360.java_d.proxy.proxy_dt;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @time 2019/3/15 16:52
 * @desc 动态代理
 */
//https://www.cnblogs.com/qdhxhz/p/9241412.html
public class ProxyDongTai {

    public interface IHello {
        void sayHello();
    }


    static class LiSiHello implements IHello {
        @Override
        public void sayHello() {
            System.out.println("hello world");
        }
    }

    static class HwInvocationHandler implements InvocationHandler {

        //被代理的对象
        private Object target;

        public HwInvocationHandler(Object target) {
            this.target = target;
        }


        //todo 方法参数详解
        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

            //添加其他方法增强功能  Aspect Oriented Programming 面向切面编程

            //执行目标方法
            Object o1 = method.invoke(target, objects);

            System.out.println("------插入后置处理代码-------------");

            return o1;
        }
    }


    public static void main(String[] arg) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        //todo 反射获取实例对象
        //todo ClassLoader  类加载器
        //获取动态代理字节码对象
        Class<?> proxyClass = Proxy.getProxyClass(IHello.class.getClassLoader(), IHello.class);
        //获取代理类的构造函数 并传入参数类型  HwInvocationHandler.class
        Constructor<?> proxyClassConstructor = proxyClass.getConstructor(InvocationHandler.class);
        //通过构造函数来创建动态代理对象，将自定义的 HwInvocationHandler对象实例传入

        IHello hello = (IHello) proxyClassConstructor.newInstance(new HwInvocationHandler(new LiSiHello()));

        //公共代理对象 调用目标对象
        hello.sayHello();


        //todo 静态和动态的区别
    }

    //动态代理类另一只创建方式
    void createInvocationHandler() {

        IHello hello = (IHello) Proxy.newProxyInstance(IHello.class.getClassLoader(), new Class[]{IHello.class},//相同的接口类
                new HwInvocationHandler(new LiSiHello()));//

        hello.sayHello();

    }


}

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

        /**
         * @param o       被代理对象
         * @param method  Method
         * @param objects
         * @return
         * @throws Throwable
         */
        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

            //添加其他方法增强功能  Aspect Oriented Programming 面向切面编程

            //执行被代理对象的 目标方法 invoke
            //并得到返回值
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
        //获取代理类的构造函数 并传入参数类型  InvocationHandler.class
        Constructor<?> proxyClassConstructor = proxyClass.getConstructor(InvocationHandler.class);
        //通过构造函数来创建动态代理对象，将自定义的 HwInvocationHandler对象实例传入

        IHello hello = (IHello) proxyClassConstructor.newInstance(new HwInvocationHandler(new LiSiHello()));

        //公共代理对象 调用目标对象
        hello.sayHello();


        //todo 静态和动态的区别
    }

    //动态代理类另一种创建方式
    void createInvocationHandler() {

        //拿到
        IHello hello = (IHello) Proxy.newProxyInstance(//
                IHello.class.getClassLoader(),//
                new Class[]{IHello.class},//相同的接口类，
                new HwInvocationHandler(new LiSiHello()));//动态代理类 构造中传入 被代理类

        hello.sayHello();

    }


    /*
     *动态代理 使用了反射的机制，来先了解下反射的机制
     *  Method -- 方法的实例对象，一个方法对应一个Method对象 里面包含了方法的 修饰符 方法名 参数列表 返回值 等
     */



}

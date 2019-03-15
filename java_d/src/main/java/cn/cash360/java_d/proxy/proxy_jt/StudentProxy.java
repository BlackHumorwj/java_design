package cn.cash360.java_d.proxy.proxy_jt;

/**
 * @time 2019/3/15 16:12
 * @desc 代理类 替 学生上交
 */

public class StudentProxy implements Teacher {

    /**
     * 被代理的对象
     */
    Student mStudent;

    public StudentProxy(Teacher teacher) {
        if (teacher.getClass() == Student.class) {
            this.mStudent = (Student) teacher;
        }
    }


    @Override
    public void giveMoney() {
        System.out.println("我今天收到了100块\n");
        mStudent.giveMoney();
        System.out.println("收到请回复我哟");
    }

    // *静态代理模式

    /// 学生交学费没有直接去交，而是通过代理来进行的。
    /// 代理模式主要有一个公共的接口、
    /// 一个具体的类、一个代理的类，
    /// 代理类持有具体类的实例，
    /// 代为执行具体类实例方法
    ///

}

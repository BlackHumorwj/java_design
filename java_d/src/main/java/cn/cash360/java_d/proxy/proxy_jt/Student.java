package cn.cash360.java_d.proxy.proxy_jt;

/**
 * @time 2019/3/15 16:10
 * @desc 2��������Ķ���
 */

public class Student implements Teacher {

    String name;

    public Student(String name) {
        this.name = name;
    }


    /**
     * ʵ�ַ���
     */
    @Override
    public void giveMoney() {
        System.out.println(name + "100");
    }
}

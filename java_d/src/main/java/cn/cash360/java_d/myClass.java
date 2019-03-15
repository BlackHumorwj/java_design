package cn.cash360.java_d;

import cn.cash360.java_d.proxy.proxy_jt.Student;
import cn.cash360.java_d.proxy.proxy_jt.StudentProxy;
import cn.cash360.java_d.proxy.proxy_jt.Teacher;

public class myClass {


    public static void main(String[] args) {
        Teacher teacher = new Student("bo jin");

        StudentProxy proxy = new StudentProxy(teacher);

        System.out.println(System.getProperty("file.encoding"));

        proxy.giveMoney();

    }

}

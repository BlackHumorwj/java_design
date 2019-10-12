package cn.cash360.java_d.cjx_ms.e_prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @time 2019/9/25 20:21
 * @desc 原型模式：将一个对象作为原型，对其进行复制、克隆，产生一个和原对象类似的新对象<><br/>
 * 浅复制：基本数据类型会重新创建，引用数据类型还是原对象所指向的
 * 深复制：引用数据类型也会重新创建，分配新地址
 */
public class Prototype implements Cloneable {


    /**
     * 浅复制
     */
    @Override
    protected Prototype clone() throws CloneNotSupportedException {
        Prototype prototype = (Prototype) super.clone();
        return prototype;
    }


    /**
     * 深度赋值
     */
    protected Object deepClone() throws IOException, ClassNotFoundException {

        //将对象写入二进制流中
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(bos);
        objectOutputStream.writeObject(this);


        //取出二进制流中的对象
        final ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        final ObjectInputStream ois = new ObjectInputStream(bis);

        return ois.readObject();


    }


}

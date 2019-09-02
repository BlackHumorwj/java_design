package cn.cash360.ui.activity.advanced.mvvm.demo1;

import java.io.Serializable;

/**
 * @time 2019/9/2 10:20
 * @desc
 */
public class User implements Serializable {


    private int id ;
    private String name;

    public User(String userName) {
        this.name = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

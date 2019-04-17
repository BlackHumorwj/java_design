package cn.cash360.bean;

/**
 * @time 2019/4/17 13:45
 * @desc
 */

public class PrimerBean<T> {

    public Class<T> activity;

    public String name;

    public PrimerBean(Class<T> activity, String name) {
        this.activity = activity;
        this.name = name;
    }
}

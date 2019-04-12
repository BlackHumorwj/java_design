package cn.cash360.java_lib;

/**
 * @time 2019/3/19 9:27
 * @desc
 */

public class MethodDemo {

    private String mString;

    public MethodDemo() {

    }

    public MethodDemo(String s) {
        this.mString = s;
    }


    public void setString(String string) {
        mString = string;
    }

    public String getString() {
        return mString;
    }

}

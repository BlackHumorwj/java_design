package cn.cash360.java_d.cjx_ms.f_adapter.object_adapter;

import cn.cash360.java_d.cjx_ms.f_adapter.class_adapter.NewInterface;
import cn.cash360.java_d.cjx_ms.f_adapter.class_adapter.Old;

/**
 * @time 2019/9/26 16:48
 * @desc
 */
public class Wrapper implements NewInterface {

   Old mOld;

    public Wrapper(Old old){
        this.mOld = old;
    }


    @Override
    public void method1() {
        mOld.method1();
    }

    @Override
    public void method2() {
        System.out.println("object adapter");
    }
}

package cn.cash360.java_d.cjx_ms.g_decorator;

/**
 * @time 2019/9/26 17:17
 * @desc
 */
public class Decorator implements Source {

    Source mOriginal;


   public Decorator(Source original){
       this.mOriginal = original;
   }



    @Override
    public void method() {


       //加点控制的条件

        mOriginal.method();




    }
}

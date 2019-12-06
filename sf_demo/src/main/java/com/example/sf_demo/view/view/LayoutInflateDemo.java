package com.example.sf_demo.view.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.sf_demo.R;

/**
 * @time 2019/12/6 10:56
 * @desc
 */
public class LayoutInflateDemo {


    public void layoutInfalte(Context context) {
        LayoutInflater layoutInflater;
        //创建实例 一
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //或者 二 ,封装了一层
        layoutInflater = LayoutInflater.from(context);


        //使用
        final View view = layoutInflater.inflate(R.layout.support_simple_spinner_dropdown_item, null);


        //源码解析：
        /**

         LayoutInflater#inflate()



         */




    }

}

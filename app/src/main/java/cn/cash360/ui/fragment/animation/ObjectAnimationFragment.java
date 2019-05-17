package cn.cash360.ui.fragment.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.cash360.java_design.R;
import cn.cash360.ui.activity.ainination.AnimationActivity;
import cn.cash360.ui.fragment.tab.BaseFragment;
/*
 * 一、属性动画ObjectAnimator原理
 *      属性动画框架工作原理可以总结为如下三步：
            1 在创建属性动画时如果没有设置属性的初始值，此时Android系统就会通过该属性的get方法获取初始值，所以在没有设置属性的初始值时，必须提供该属性的get方法，否者程序会Crash。
            2 在动画播放的过程中，属性动画框架会利用时间流逝的百分比获取属性值改变的百分比（即通过时间插值器），接着利用获取的属性值改变的百分比获取改变后的属性值（即通过类型估值器）。
            3 通过该属性的set方法将改变后的属性值设置到对象中。

 *
 * 二、语法：
 * <set>对应AnimatorSet（属性动画集合）类；
 * <objectAnimator>对应ObjectAnimator类；
 * <animator>标签对应ValueAnimator类；

     <set
      android:ordering=["together" | "sequentially"]>

        android:ordering
        该属性有如下两个可选值：
        together：表示动画集合中的子动画同时播放。
        sequentially：表示动画集合中的子动画按照书写的先后顺序依次播放。
        该属性的默认值是together。


        <objectAnimator
            android:propertyName="string"
            android:duration="int"
            android:valueFrom="float | int | color"
            android:valueTo="float | int | color"
            android:startOffset="int"
            android:repeatCount="int"
            android:repeatMode=["repeat" | "reverse"]
            android:valueType=["intType" | "floatType"]/>


        android:propertyName
             String. Required. 属性动画作用的属性名称
        android:duration
             int. 表示动画的周期，默认值为300毫秒
        android:valueFrom
             float, int, or color. 表示属性的初始值
        android:valueTo
              float, int, or color. Required. 表示属性的结束值
        android:startOffset
              int. 表示调用start方法后延迟多少毫秒开始播放动画
        android:repeatCount
               int. 表示动画的重复次数，-1代表无限循环，默认值为0，表示动画只播放一次。
        android:repeatMode
               表示动画的重复模式，该属性有如下两个可选值：
                        repeat：表示连续重复
                        reverse：表示逆向重复
        android:valueType
             Keyword.  表示android:propertyName所指定属性的类型，有intType和floatType两个可选项，
             分别代表属性的类型为整型和浮点型，另外如果属性是颜色值，那么就不需要指定
        android:valueType属性，Android系统会自动对颜色类型的属性做处理。
              默认值为floatType。


        <animator
            android:duration="int"
            android:valueFrom="float | int | color"
            android:valueTo="float | int | color"
            android:startOffset="int"
            android:repeatCount="int"
            android:repeatMode=["repeat" | "reverse"]
            android:valueType=["intType" | "floatType"]/>

        <set>
            ...
        </set>
    </set>
 *
 */


/**
 * @time 2019/5/10 11:55
 * @desc
 */
public class ObjectAnimationFragment extends BaseFragment implements View.OnClickListener {


    private LinearLayout mLlRoot;
    private TextView mIvAlpha;
    private TextView mTvScale;
    private TextView mTvTrans;
    private TextView mTvRotate;
    private TextView mTvCustom;
    private TextView mTvValue;

    public static ObjectAnimationFragment newInstance() {
        Bundle args = new Bundle();
        ObjectAnimationFragment fragment = new ObjectAnimationFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_objector_animation;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        mIvAlpha = mContentView.findViewById(R.id.tv_alpha);
        mIvAlpha.setOnClickListener(this);
        mTvScale = mContentView.findViewById(R.id.tv_scale);
        mTvScale.setOnClickListener(this);
        mTvTrans = mContentView.findViewById(R.id.tv_trans);
        mTvTrans.setOnClickListener(this);
        mTvRotate = mContentView.findViewById(R.id.tv_rotate);
        mTvRotate.setOnClickListener(this);
        mTvCustom = mContentView.findViewById(R.id.tv_custom);
        mTvCustom.setOnClickListener(this);
        mTvValue = mContentView.findViewById(R.id.tv_value);
        mTvValue.setOnClickListener(this);
    }

    /**
     * 透明度动画
     */
    private void startAlphaAnim() {

        // ivAlpha.setAlpha();

        ObjectAnimator.ofFloat(mIvAlpha, "alpha", 1, 0, 1)//
                .setDuration(4000)//
                .start();
    }

    /**
     * 缩放动画
     */
    private void startScaleAnim() {

        //利用AnimatorSet和ObjectAnimator实现缩放动画
        final AnimatorSet animatorSet = new AnimatorSet();
        mTvScale.setPivotX(mTvScale.getWidth() / 2);
        mTvScale.setPivotY(mTvScale.getHeight() / 2);
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(mTvScale, "scaleX", 1, 0).setDuration(5000),
                ObjectAnimator.ofFloat(mTvScale, "scaleY", 1, 0).setDuration(5000));
        animatorSet.start();


    }

    private void startTransAnim() {

        //利用AnimatorSet和ObjectAnimator实现平移动画
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(mTvTrans, "translationX", 20, 100).setDuration(2000),
                ObjectAnimator.ofFloat(mTvTrans, "translationY", 20, 100).setDuration(2000));
        animatorSet.start();
    }

    private void startRotateAnim() {
        //利用ObjectAnimator实现旋转动画
        mTvRotate.setPivotX(mTvRotate.getWidth() / 2);
        mTvRotate.setPivotY(mTvRotate.getHeight() / 2);
        ObjectAnimator.ofFloat(mTvRotate, "rotation", 0, 360)
                .setDuration(1000).start();
    }


    private void startObjectAnim() {
        //对 ViewWrapper height 属性进行动画操作
        final ViewWrapper wrapper = new ViewWrapper(mContentView);
        ObjectAnimator.ofFloat(wrapper, "height", mContentView.getMeasuredHeight()).setDuration(5000).start();
    }


    public class ViewWrapper {
        private View mView;

        public ViewWrapper(View view) {
            mView = view;
        }

        public void setHeight(float height) {
            mView.getLayoutParams().height = (int) height;
            mView.requestLayout();
        }

        public int getHeight() {
            return mView.getLayoutParams().height;
        }

    }


    @Override
    public void onClick(View v) {
        final int id = v.getId();
        switch (id) {
            case R.id.tv_alpha:
                startAlphaAnim();
                break;
            case R.id.tv_scale:
                startScaleAnim();
                break;
            case R.id.tv_trans:
                startTransAnim();
                break;
            case R.id.tv_rotate:
                startRotateAnim();
                break;
            case R.id.tv_custom:
               startObjectAnim();
                break;
            case R.id.tv_value:
                startActivity(AnimationActivity.newInstance(mActivity, AnimationActivity.VALUE_ANIMATION_FRAGMENT));
                break;
        }


    }
}

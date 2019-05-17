package cn.cash360.ui.fragment.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import cn.cash360.java_design.R;
import cn.cash360.ui.fragment.tab.BaseFragment;
/*
 * 一、属性动画ValueAnimator原理
 *      属性动画框架工作原理可以总结为如下三步：
            2 在动画播放的过程中，属性动画框架会利用时间流逝的百分比获取属性值改变的百分比（即通过时间插值器），接着利用获取的属性值改变的百分比获取改变后的属性值（即通过类型估值器）。

 *
 * 二、语法：
 * <animator>标签对应ValueAnimator类；

    与<objectAnimator>标签相比，除了没有android:propertyName属性和valueFrom 属性是Required的之外，
    其他的属性都相同并且属性的作用也一样。


    ValueAnimator类就是一个数值生成器。你只要给ValueAnimator提供一个初始值、结束值和周期时间，ValueAnimator就会按照属性动画框架工作原理的第2步中的步骤生成具有一定规则的数字
 *
 */


/**
 * @time 2019/5/10 11:55
 * @desc
 */
public class ValueAnimatorFragment extends BaseFragment implements View.OnClickListener {


    private TextView mIvAlpha;
    private Button mButton;
//    private int mMeasuredHeight;
    private int mMeasuredWidth;

    public static ValueAnimatorFragment newInstance() {
        Bundle args = new Bundle();
        ValueAnimatorFragment fragment = new ValueAnimatorFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_value_animation;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        mIvAlpha = mContentView.findViewById(R.id.tv_alpha);
        mButton = view.findViewById(R.id.btn_start);
        mButton.setOnClickListener(this);

        //measure(0,0) 传递的0解包后对应的 SpecMode 为 UNSPECIFIED。
        //mIvAlpha.measure(0, 0);

        mIvAlpha.post(new Runnable() {
            @Override
            public void run() {
              //  mMeasuredHeight = mIvAlpha.getMeasuredHeight();
                mMeasuredWidth = mIvAlpha.getMeasuredWidth();
            }
        });


    }

    @Override
    public void onClick(View v) {
        if (mIvAlpha.getVisibility() == View.VISIBLE) {
            hide();
        } else {
            show();
        }
    }

    private void show() {
        mIvAlpha.setVisibility(View.VISIBLE);
        final ValueAnimator animator = createAnimator(mIvAlpha, 0, mMeasuredWidth);

        animator.setInterpolator(new DecelerateInterpolator());
        animator.setDuration(7000);
        animator.start();
    }

    private void hide() {
        final ValueAnimator animator = createAnimator(mIvAlpha, mMeasuredWidth, 0);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        //节点监听
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mIvAlpha.setVisibility(View.GONE);
            }
        });
        animator.setDuration(7000);
        animator.start();
    }

    private ValueAnimator createAnimator(final View view, int start, int end) {

        final ValueAnimator animator = ValueAnimator.ofInt(start, end);
        //过程动画监听
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.width = (int) animation.getAnimatedValue();
                view.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }
}

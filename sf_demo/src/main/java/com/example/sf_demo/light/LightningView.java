package com.example.sf_demo.light;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

/**
 * @time 2019/11/25 17:59
 * @desc
 */
public class LightningView  extends FrameLayout {
    private Shader mGradient;
    private Matrix mGradientMatrix;
    private Paint mPaint;
    private int mViewWidth = 0, mViewHeight = 0;
    private float mTranslateX = 0, mTranslateY = 0;
    private boolean mAnimating = false;
    private Rect rect;
    private ValueAnimator valueAnimator;
    private boolean autoRun = true; //是否自动运行动画

    public LightningView(Context context) {
        super(context);
        init();
    }

    public LightningView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LightningView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        rect = new Rect();
        mPaint = new Paint();
        initGradientAnimator();
    }

    public void setAutoRun(boolean autoRun) {
        this.autoRun = autoRun;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        rect.set(0, 0, getWidth(), getHeight());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWidth == 0) {
            mViewWidth = getWidth();
            mViewHeight = getHeight();
            if (mViewWidth > 0) {
                //亮光闪过
                mGradient = new LinearGradient(0, 0, mViewWidth / 2, mViewHeight,
                        new int[]{0x00ffffff, 0x73ffffff, 0x00ffffff,  0x99ffffff, 0x00ffffff},
                        new float[]{0.2f,       0.35f,      0.5f,        0.7f,      1},
                        Shader.TileMode.CLAMP);
                mPaint.setShader(mGradient);
                mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN));
                mGradientMatrix = new Matrix();
                mGradientMatrix.setTranslate(-2 * mViewWidth, mViewHeight);
                mGradient.setLocalMatrix(mGradientMatrix);
                rect.set(0, 0, w, h);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mAnimating && mGradientMatrix != null) {
            canvas.drawRect(rect, mPaint);
        }
    }

    private void initGradientAnimator() {
        valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(5000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (Float) animation.getAnimatedValue();
                //❶ 改变每次动画的平移x、y值，范围是[-2mViewWidth, 2mViewWidth]
                mTranslateX = 4 * mViewWidth * v - mViewWidth * 2;
                mTranslateY = mViewHeight * v;
                //❷ 平移matrix, 设置平移量
                if (mGradientMatrix != null) {
                    mGradientMatrix.setTranslate(mTranslateX, mTranslateY);
                }
                //❸ 设置线性变化的matrix
                if (mGradient != null) {
                    mGradient.setLocalMatrix(mGradientMatrix);
                }
                //❹ 重绘 调用onDraw()
                invalidate();
            }
        });

        if (autoRun) {
            valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
            getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                @Override
                public void onGlobalLayout() {
                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    mAnimating = true;
                    if (valueAnimator != null) {
                        valueAnimator.start();
                    }
                }
            });
        }
    }

    //停止动画
    public void stopAnimation() {
        if (mAnimating && valueAnimator != null) {
            mAnimating = false;
            valueAnimator.cancel();
            invalidate();
        }
    }

    //开始动画
    public void startAnimation() {
        if (!mAnimating && valueAnimator != null) {
            mAnimating = true;
            valueAnimator.start();
        }
    }
}

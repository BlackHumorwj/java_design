package com.example.sf_demo.view.lightView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * @time 2019/12/5 17:43
 * @desc
 */
public class GradientView extends FrameLayout {

    private LinearGradient mGradient;

    private Paint mPaint;
    private Rect rect;

    public GradientView(Context context) {
        this(context, null);
    }

    public GradientView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        rect = new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        rect.set(0, 0, getWidth(), getHeight());
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        final int mViewWidth = getWidth();
        final int mViewHeight = getHeight();

        //亮光闪过
        mGradient = new LinearGradient(0, 0, mViewWidth / 2, mViewHeight,
                new int[]{0x00008577, 0x73008577, 0x00008577,  0x99008577, 0x00008577},
                new float[]{0.2f,       0.35f,      0.5f,        0.7f,      1},
                Shader.TileMode.CLAMP);
        mPaint.setShader(mGradient);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DARKEN));

        rect.set(0, 0, w, h);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(rect,mPaint);

    }
}

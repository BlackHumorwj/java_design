package cn.cash360.ui.activity.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @time 2019/4/30 17:15
 * @desc
 */
public class CanvasView1 extends View {


    private Paint mPaint;

    public CanvasView1(Context context) {
        super(context);
    }

    public CanvasView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10);//设置画笔的宽度 10px
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置背景颜色
        canvas.drawColor(Color.GRAY);
        //画点
        canvas.drawPoint(10, 10, mPaint);
        canvas.drawPoints(new float[]{20, 20, 20, 40, 20, 60}, mPaint);

        //绘制直线
        canvas.drawLine(30, 40, 50, 100, mPaint);

        //绘制矩形
        mPaint.setColor(Color.WHITE);
        final Rect rect = new Rect(60, 60, 190, 190);
        canvas.drawRect(rect, mPaint);

        //绘制圆角矩形
        canvas.drawRoundRect(new RectF(160, 260, 290, 290), 30, 30, mPaint);

    }
}



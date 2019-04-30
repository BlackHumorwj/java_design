package cn.cash360.ui.activity.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * @param widthMeasureSpec  32位int 值。高两位bit 能表示四种可能 表示测量的模式；低30位表示具体尺寸
     *                          todo google这样设计的好处：
     *                          测量模式分为三种：
     *                          1、 MeasureSpec.UNSPECIFIED 父容器没有对当前View有任何限制，当前View可以任意取尺寸 ,这种情况一般要特殊处理，给默认值
     *                          2、 MeasureSpec.EXACTLY 当前的尺寸就是当前View对应的尺寸 。对应：match_parent  10dp等
     *                          3、 MeasureSpec.AT_MOST 当前尺寸是当前View能取的最大尺寸     对于：wrap_content
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(100, widthMeasureSpec);
        int height = getMySize(90, heightMeasureSpec);

        //取等边
        setMeasuredDimension(Math.min(width, height), Math.min(width, height));
    }

    private int getMySize(int defaultSize, int measureSpec) {
        int mySize = 0;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case MeasureSpec.UNSPECIFIED://没有指定大小 取默认值
                mySize = defaultSize;
                break;
            case MeasureSpec.AT_MOST://取具体值
                mySize = size;
                break;
            case MeasureSpec.EXACTLY://取具体值
                mySize = size;
                break;
        }
        return mySize;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制想要的图形
        //需要设置参数 来 确定要绘制的图形
        //比如 绘制一个圆  需要确定 圆心坐标和圆的半径
        int radius = getMeasuredHeight() / 2;
        float cx = getPaddingLeft() + radius;
        float cy = getPaddingTop() + radius;

        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        canvas.drawCircle(cx, cy, radius, paint);

    }
}

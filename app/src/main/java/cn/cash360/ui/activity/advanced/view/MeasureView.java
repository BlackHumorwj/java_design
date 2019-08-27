package cn.cash360.ui.activity.advanced.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @time 2019/8/20 14:44
 * @desc
 */
public class MeasureView extends View {
    public MeasureView(Context context) {
        super(context);
    }

    public MeasureView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //自定义view须知
        //view 支持 wrap_content
        //view 支持 padding
        //view中避免使用 Handler
        //view中有线程动画，需要及时停止，onDetachedFromWindow()
        //View带滑动嵌套时，需要处理好滑动冲突
    }


    public static class MyTextView extends View {

        private Paint mPaint = new Paint();


        public MyTextView(Context context) {
            this(context, null);
        }

        public MyTextView(Context context, AttributeSet attrs) {
            super(context, attrs);
            mPaint.setColor(Color.RED);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
            final int heightMode = MeasureSpec.getMode(heightMeasureSpec);

            final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
            final int heightSize = MeasureSpec.getSize(heightMeasureSpec);

            if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(200, 200);
            } else if (widthMode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(200, heightSize);
            } else if (heightMode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(widthSize, 200);
            } else {
                setMeasuredDimension(widthSize, heightSize);
            }


        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);


            canvas.drawText("2019-2", 10 + getPaddingLeft(), 10 + getPaddingTop(), mPaint);
        }
    }



}

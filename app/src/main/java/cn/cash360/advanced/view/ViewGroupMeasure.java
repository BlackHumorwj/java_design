package cn.cash360.advanced.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * @time 2019/8/27 15:13
 * @desc
 */
public class ViewGroupMeasure extends ViewGroup {

    private float mStartX;
    private float mStartY;

    private Scroller mScroller;
    private float mStartX1;
    private int mMeasuredChildWidth;

    public ViewGroupMeasure(Context context) {
        super(context);
    }

    public ViewGroupMeasure(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //测量子View
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        final int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        final int childCount = getChildCount();
        if (childCount == 0) {
            measure(0, 0);
            return;
        }
        final View childAt = getChildAt(0);

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(childAt.getMeasuredWidth() * childCount, childAt.getMeasuredHeight());
        } else if (widthMode == MeasureSpec.AT_MOST) {

            setMeasuredDimension(childAt.getMeasuredWidth() * childCount, heightSize);

        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, childAt.getMeasuredHeight());
        } else {
            setMeasuredDimension(widthSize, heightSize);


        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int childCount = getChildCount();

        int right = 0;

        for (int i = 0; i < childCount; i++) {
            final View childAt = getChildAt(i);

            if (childAt.getVisibility() == VISIBLE) {
                mMeasuredChildWidth = childAt.getMeasuredWidth();
                childAt.layout(right, getPaddingTop(), right + mMeasuredChildWidth, childAt.getMeasuredHeight());
                right += mMeasuredChildWidth;
            }

        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean isIntercept = false;
        //左右滑动拦截
        //上下滑动不拦截
        final int evAction = ev.getAction();

        switch (evAction) {
            case MotionEvent.ACTION_DOWN:
                mStartX = ev.getX();
                mStartY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                float startX = ev.getX();
                float startY = ev.getY();

                float delX = startX - mStartX;
                float delY = startY - startY;

                if (Math.abs(delX) < Math.abs(delY)) {
                    isIntercept = false;
                } else {
                    isIntercept = true;
                }

                mStartX = startX;
                mStartY = startY;

                break;
            case MotionEvent.ACTION_UP:
                isIntercept = false;

                break;
        }

        return isIntercept;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        final int evAction = ev.getAction();

        switch (evAction) {
            case MotionEvent.ACTION_DOWN:

                mStartX1 = ev.getX();

                mStartX = mStartX1;
                break;
            case MotionEvent.ACTION_MOVE:

                final int scrollXDel = getScrollX();
                //当前的索引
                final int indexDel = scrollXDel / mMeasuredChildWidth;

                float startX = ev.getX();

                float delX = startX - mStartX;

                if (delX > 0) {

                    if (indexDel == 0) {
                        return true;
                    }

                } else if (delX < 0) {
                    if (indexDel == getChildCount() - 1) {
                        return true;
                    }
                }


                scrollBy((int) -delX, 0);

                mStartX = startX;

                break;
            case MotionEvent.ACTION_UP:

                final float evX = ev.getX();

                final int scrollX = getScrollX();


                //当前的索引
                final int index = scrollX / mMeasuredChildWidth;


                int x = (index + 1) * mMeasuredChildWidth - scrollX;
                Log.e("xx", scrollX + "---" + index + "----" + mMeasuredChildWidth + "---" + x);

                if (evX - mStartX1 < 0) {
                    if (index == getChildCount() - 1) {
                        return true;
                    }
                    smoothScrollBy(x, 0);
                } else {
                    smoothScrollBy(-(scrollX - index * mMeasuredChildWidth), 0);
                }


                break;
        }


        return true;
    }

    private void smoothScrollBy(int dx, int dy) {

        Log.e("yy",dx+"");

        mScroller.startScroll(getScrollX(), 0, dx, 0, 500);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}

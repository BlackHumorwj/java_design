package cn.cash360.ui.activity.ainination;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.text.MessageFormat;

import cn.cash360.java_design.R;
import cn.cash360.ui.activity.base.BaseActivity;

/**
 * @time 2019/4/17 13:47
 * @desc 参考：https://www.gcssloop.com/customview/gestruedector
 * https://www.gcssloop.com/customview/scalegesturedetector
 */

public class GestureActivity extends BaseActivity {


    private TextView mTvGesture;
    private TextView mTvScaleGesture;

    public static Intent newInstance(Context context) {
        Intent intent = new Intent(context, GestureActivity.class);
        return intent;
    }


    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_gesture);
        mTvGesture = (TextView) findViewById(R.id.tv_view);
        mTvScaleGesture = (TextView) findViewById(R.id.tv_scale_gesture);
        initGesture();
        initScaleGesture();
    }


    @SuppressLint("ClickableViewAccessibility")
    private void initGesture() {

        final GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {

            /**
             * 为了保证事件被唯一的 View 消费，哪个 View 消费了 down 事件，后续的内容就会传递给该 View
             * 想让一个 View 能够接收到事件，有两种做法：
             * 1、让该 View 可以点击，因为可点击状态会默认消费 down 事件。
             * 2、手动消费掉 down 事件。
             */
            @Override
            public boolean onDown(MotionEvent e) {
                return super.onDown(e);
            }

            /**
             *
             * @param e1 手指按下时的 Event。
             * @param e2 手指抬起时的 Event。
             * @param velocityX 在 X 轴上的运动速度(像素／秒)。
             * @param velocityY 在 Y 轴上的运动速度(像素／秒)。
             * @return
             */
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return super.onFling(e1, e2, velocityX, velocityY);
            }

            /**
             * 长按事件
             */
            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
            }


            /**
             * 这个消息和 onSingleTapConfirmed 类似，也是一种延时回调，延迟时间是 180 ms
             * @param e
             */
            @Override
            public void onShowPress(MotionEvent e) {
                super.onShowPress(e);
            }

            /**
             * @param e1 手指按下时的 Event。
             * @param e2 手指抬起时的 Event。
             * @param distanceX  负数：从左往右滑动    正数：从右往左滑动
             * @param distanceY  负数：从上往下滑动    正数：从下往上滑动
             */
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                mTvGesture.setText(MessageFormat.format("distanceX{0}-----distanceY = {1}", distanceX, distanceY));
                return super.onScroll(e1, e2, distanceX, distanceY);
            }


            /**
             * 它在双击事件确定发生时会对第二次按下产生的 MotionEvent 信息进行回调。
             */
            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                switch (e.getActionMasked()) {
                    case MotionEvent.ACTION_UP:
                        Logger.e("第二次抬起时触发");
                        break;
                }
                return super.onDoubleTapEvent(e);
            }

            //双击
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                Logger.e("第二次按下时触发");
                Toast.makeText(mActivity, "双击", Toast.LENGTH_SHORT).show();
                return super.onDoubleTap(e);
            }

            //单击
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                Toast.makeText(mActivity, "单击", Toast.LENGTH_SHORT).show();
                return super.onSingleTapConfirmed(e);
            }

            /**
             * 用户单击抬起时的回调
             * 和上面的 onSingleTapConfirmed 之间有何不同呢？和 onClick 又有何不同呢？
             * onSingleTapUp :  在双击的第一次抬起时触发
             * onSingleTapConfirmed  : 双击发生时不会触发。
             * onClick : 在双击事件时触发两次。
             * @return
             */
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return super.onSingleTapUp(e);
            }
        });


        mTvGesture.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });

    }


    /**
     * 缩放手势监听
     * 缩放中心：计算原理 计算中心点的原理其实也非常简单，那就是将所有的坐标都加起来，然后除以数量即可
     * 缩放因子：计算缩放比例也很简单，就是计算各个手指到焦点的平均距离，在用户手指移动后用新的平均距离除以旧的平均距离，并以此计算得出缩放比例
     */
    @SuppressLint("ClickableViewAccessibility")
    private void initScaleGesture() {


        final ScaleGestureDetector.OnScaleGestureListener onScaleGestureListener = new ScaleGestureDetector.OnScaleGestureListener() {

            @Override
            public boolean onScale(ScaleGestureDetector detector) {

                mTvScaleGesture.setText(MessageFormat.format("focusX = {0}\nfocusY = {1}\n scaleFactor = {2}", detector.getFocusX(), detector.getFocusY(), detector.getScaleFactor()));

                return false;//如果返回 true 则表示当前缩放事件已经被处理，检测器会重新积累缩放因子，返回 false 则会继续积累缩放因子。
            }

            @Override
            public boolean onScaleBegin(ScaleGestureDetector detector) {
                mTvScaleGesture.setText("开始");
                return true;//如果返回 false 则表示不使用当前这次缩放手势。
            }

            @Override
            public void onScaleEnd(ScaleGestureDetector detector) {
                //缩放手势结束。
                // mTvScaleGesture.setText("结束");
            }
        };

        final ScaleGestureDetector scaleGestureDetector = new ScaleGestureDetector(mActivity, onScaleGestureListener);


        mTvScaleGesture.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                scaleGestureDetector.onTouchEvent(event);
                return true;//当前控件消费掉
            }
        });


    }


}

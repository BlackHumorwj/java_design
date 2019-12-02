package com.example.sf_demo.view.screenRefresh;

import android.view.View;

/**
 * @time 2019/12/2 10:55
 * @desc
 */
public class RefreshUtil {

    /*

    一、典型的显示系统组成
        CPU：负责计算数据，把计算好数据交给GPU；
        GPU：对图形进行渲染，渲染好后放到buffer里存起来；
        display（屏幕或显示器）：将buffer里的数据呈现到屏幕上。
             CPU和GPU负责数据的处理，处理完之后存入buffer，display会以固定的频率去读取 buffer 数据，然后显示出来。

    二、Android 中的显示系统
      1、CPU计算屏幕数据：对应View树的绘制过程，
         也就是Activity对应的视图从根布局DecorView
         开始层层遍历每个View，分别执行测量、布局、绘制三个操作的过程

       Android每隔16.6ms刷新一次屏幕是指，Display以固定的频率，将buffer里的屏幕数据显示出来。

       VSync 信号就是屏幕刷新信号。当收到刷新信号的时候，Display会从buffer中读取上一次CPU计算并存入buffer中的数据。此时CPU会开始计算下一帧显示的数据。

           当App界面不需要刷新时（用户无操作，界面无动画），
           app就接收不到屏幕刷新信号所有也就不会让CPU再去绘
           制视图树计算画面数据工作，但是底层仍然会每隔16.6ms
           切换下一帧的画面，知识这个下一帧画面一直是相同的内容。

     */



    //请求绘制操作
    public void doInvalidate(View view){

        view.invalidate();
        view.postInvalidate();

        /*
         ViewRootImpl#scheduleTraversals()

         Traversal：遍历

        一般每个Activity中对应一个 DecorView 和 ViewRootImpl。每个界面上的View的刷新、绘制、点击事件都是由 ViewRootImpl 发起的。

         Q1、 DecorView 和 ViewRootImpl 是什么时候绑定的？

             Activity启动在ActivityThread中进行，handleLaunchActivity()会依次间接调用Activity的onCreate(),onStart(),onResume().
         在执行完这些后ActivityThread会调用WindowManager#addView(),而这个addView()最终其实是调用了 WindowManagerGlobal#add().

         此方法会进行View树的绘制和刷新，所以在 onCreate() 或者 onResume 中都获取不到View的宽高,一个 Activity 界面的绘制，其实是在 onResume() 之后才开始的。


         WindowManagerGlobal#add()
         、、、
         public void addView(View view, ViewGroup.LayoutParams params,
         Display display, Window parentWindow) {

             //1 实例化 ViewRootImpl对象
             root = new ViewRootImpl(view.getContext(), display);

             view.setLayoutParams(wparams);

             mViews.add(view);
             mRoots.add(root);

            //2. 调用ViewRootImpl的setView()，并将DecorView作为参数传递进去
             root.setView(view, wparams, panelParentView);

         }
         、、、


        ViewRootImpl#setView()
        、、、
        public void setView(View view, WindowManager.LayoutParams attrs, View panelParentView) {
            synchronized (this) {
                if (mView == null) {
                    //1. view 是 DecorView
                    mView = view;

                    ...
                    //2.发起布局请求
                    requestLayout();
                    ...
                    //3.将当前ViewRootImpl对象this,作为参数调用了DecorView的assignParent
                    view.assignParent(this);
                    ...
                }
            }
        }
        、、、

        View#assignParent()
        、、、
        // parent  ViewRootImpl
         void assignParent(ViewParent parent) {
            if (mParent == null) {

            //将ViewRootImpl作为mParent,与DecorView进行绑定，View#invalidate()等方法
                内部都会有一个循环查找parent的方法，而这个parent就是ViewRootImpl,所以最终View的一些
                刷新操作都会调用ViewRootImpl的方法

                mParent = parent;
            } else if (parent == null) {
                mParent = null;
            } else {
                throw new RuntimeException("view " + this + " being added, but"
                        + " it already has a parent");
            }
        }
        、、、


         ViewRootImpl#requestLayout()

         、、、
         public void requestLayout() {
             if (!mHandlingLayoutInLayoutRequest) {
             //1.检查该操作是否是在主线程中执行
             checkThread();
             mLayoutRequested = true;
             //2.安排一次遍历绘制View树的任务
             scheduleTraversals();
            }
         }
         、、、


        ViewRootImpl#scheduleTraversals()
        、、、
        void scheduleTraversals() {
            //防止一帧内重复注册刷新任务
            if (!mTraversalScheduled) {
                mTraversalScheduled = true;
                //往消息队列发送同步屏障消息（拦截同步消息的执行），MessageQueue调用next()方法取出队头的Message消息，
                当发现消息是同步屏障消息的时候，会遍历队列，找出设置异步标志的消息，如果找到了就执行，否则就让next()方法陷入
                阻塞状态，直到异步屏障消息被移除
                mTraversalBarrier = mHandler.getLooper().getQueue().postSyncBarrier();

                //此方法会注册一个监听，用来观察native层发出的刷新屏幕信号
                //底层会调用 DisplayEventReceiver#onVsync()
                mChoreographer.postCallback(Choreograhper.CALLBACK_TRAVERSAL, mTraversalRunnable, null);
                ...
            }
        }
        、、、

        ViewRootImpl $TraversalRunnable mTraversalRunnable
        、、、
        final class TraversalRunnable implements Runnable {
            @Override
            public void run() {
                doTraversal();
            }
        }
        、、、

        ViewRootImpl # doTraversal()
        、、、
        void doTraversal() {
            if (mTraversalScheduled) {
                mTraversalScheduled = false;
                mHandler.getLooper().getQueue().removeSyncBarrier(mTraversalBarrier);
                ...

                //1. 执行遍历绘制View树
                performTraversals();
                ...
            }
        }
        、、、


        ViewRootImpl# performTraversals()
        、、、
         //该方法实在太过复杂，所以将无关代码全部都省略掉，只留下关键代码和代码结构
        private void performTraversals() {
            ...
            if (...) {
                ...
                if (...) {
                    if (...) {
                        ...
                        //1.测量
                        performMeasure(childWidthMeasureSpec, childHeightMeasureSpec);
                        ...

                        layoutRequested = true;
                    }
                }
            } ...

            final boolean didLayout = layoutRequested && (!mStopped || mReportNextDraw);
            ...

            if (didLayout) {
                //2.布局
                performLayout(lp, mWidth, mHeight);
                ...
            }

            ...

            boolean cancelDraw = mAttachInfo.mTreeObserver.dispatchOnPreDraw() || !isViewVisible;

            if (!cancelDraw && !newSurface) {
                ...
                //3.绘制
                performDraw();
            }...

            ...
        }
        、、、


         */


        //https://mp.weixin.qq.com/s/OfeHn8Gf3MBaDvxs-DOzNg

    }

}

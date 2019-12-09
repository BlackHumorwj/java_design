package com.example.sf_demo.view.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.sf_demo.R;

/**
 * @time 2019/12/6 10:56
 * @desc
 */
public class LayoutInflateDemo {


    public void layoutInfalte(Context context) {
        LayoutInflater layoutInflater;
        //创建实例 一
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //或者 二 ,封装了一层
        layoutInflater = LayoutInflater.from(context);


        //使用
        final View view = layoutInflater.inflate(R.layout.support_simple_spinner_dropdown_item, null);


        //源码解析：

        //region  1、LayoutInflater#inflate()：View
        /*
          public View inflate(XmlPullParser parser, @Nullable ViewGroup root, boolean attachToRoot) {
                synchronized (mConstructorArgs) {
                    Trace.traceBegin(Trace.TRACE_TAG_VIEW, "inflate");

                    final Context inflaterContext = mContext;
                    final AttributeSet attrs = Xml.asAttributeSet(parser);
                    Context lastContext = (Context) mConstructorArgs[0];
                    mConstructorArgs[0] = inflaterContext;
                    View result = root;

                    try {
                        advanceToRootNode(parser);
                        final String name = parser.getName();

                        if (TAG_MERGE.equals(name)) {
                            if (root == null || !attachToRoot) {
                                throw new InflateException("<merge /> can be used only with a valid "
                                        + "ViewGroup root and attachToRoot=true");
                            }

                            rInflate(parser, root, inflaterContext, attrs, false);
                        } else {
                            // Temp is the root view that was found in the xml

                           //创建根布局 temp 会调用 LayoutInflater#createView()：View 反射创建View
                            final View temp = createViewFromTag(root, name, inflaterContext, attrs);

                            ViewGroup.LayoutParams params = null;

                            if (root != null) {
                                // Create layout params that match root, if supplied
                                params = root.generateLayoutParams(attrs);
                                if (!attachToRoot) {
                                    // Set the layout params for temp if we are not
                                    // attaching. (If we are, we use addView, below)
                                    temp.setLayoutParams(params);
                                }
                            }


                            // Inflate all children under temp against its context.
                            //循环遍历出子View 并添加到父View中
                            rInflateChildren(parser, temp, attrs, true);


                            // We are supposed to attach all the views we found (int temp)
                            // to root. Do that now.
                            if (root != null && attachToRoot) {
                                root.addView(temp, params);
                            }

                            // Decide whether to return the root that was passed in or the
                            // top view found in xml.
                            if (root == null || !attachToRoot) {
                                result = temp;
                            }
                        }

                    } catch (XmlPullParserException e) {
                        final InflateException ie = new InflateException(e.getMessage(), e);
                        ie.setStackTrace(EMPTY_STACK_TRACE);
                        throw ie;
                    } catch (Exception e) {
                        final InflateException ie = new InflateException(
                                getParserStateDescription(inflaterContext, attrs)
                                + ": " + e.getMessage(), e);
                        ie.setStackTrace(EMPTY_STACK_TRACE);
                        throw ie;
                    } finally {
                        // Don't retain static reference on context.
                        mConstructorArgs[0] = lastContext;
                        mConstructorArgs[1] = null;

                        Trace.traceEnd(Trace.TRACE_TAG_VIEW);
                    }
                    return result;
                }
            }


         */
        //endregion

        //region 2、 LayoutInflater#rInflate
        /*
         void rInflate(XmlPullParser parser, View parent, Context context,
                    AttributeSet attrs, boolean finishInflate) throws XmlPullParserException, IOException {

                final int depth = parser.getDepth();
                int type;
                boolean pendingRequestFocus = false;

                while (((type = parser.next()) != XmlPullParser.END_TAG ||
                        parser.getDepth() > depth) && type != XmlPullParser.END_DOCUMENT) {

                    if (type != XmlPullParser.START_TAG) {
                        continue;
                    }

                    final String name = parser.getName();

                    if (TAG_REQUEST_FOCUS.equals(name)) {
                        pendingRequestFocus = true;
                        consumeChildElements(parser);
                    } else if (TAG_TAG.equals(name)) {
                        parseViewTag(parser, parent, attrs);
                    } else if (TAG_INCLUDE.equals(name)) {
                        if (parser.getDepth() == 0) {
                            throw new InflateException("<include /> cannot be the root element");
                        }
                        parseInclude(parser, context, parent, attrs);
                    } else if (TAG_MERGE.equals(name)) {
                        throw new InflateException("<merge /> must be the root element");
                    } else {
                        final View view = createViewFromTag(parent, name, context, attrs);
                        final ViewGroup viewGroup = (ViewGroup) parent;
                        final ViewGroup.LayoutParams params = viewGroup.generateLayoutParams(attrs);
                        rInflateChildren(parser, view, attrs, true);
                        viewGroup.addView(view, params);
                    }
                }

                if (pendingRequestFocus) {
                    parent.restoreDefaultFocus();
                }

                if (finishInflate) {
                    parent.onFinishInflate();
                }
            }


         */
        //endregion



    }

}

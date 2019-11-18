package com.example.article_demo

import android.graphics.*
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.io.PipedReader
import java.io.PipedWriter

/**
 * @time 2019/11/7 11:59
 * @desc
 */
class MainActivity : AppCompatActivity() {


    private lateinit var thread: Thread
    private lateinit var pipedReader: PipedReader
    private lateinit var pipedWriter: PipedWriter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPipes()

        Handler()


        val requestOptions = RequestOptions().placeholder(R.drawable.abc_ab_share_pack_mtrl_alpha)
                .error(R.drawable.abc_ic_arrow_drop_right_black_24dp)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(300, 400)


        Glide.with(this).load("").apply(requestOptions).into(iv_image)


        val hashMap = HashMap<String, String>()
        hashMap.put("key", "value")





        initSpannable()

    }

    private fun initSpannable() {

        val spanstr = SpannableString("锦瑟无端五十弦，一弦一柱思华年。\n" + "庄生晓梦迷蝴蝶，望帝春心托杜鹃。\n" +
                "沧海月明珠有泪，蓝田日暖玉生烟。\n" + "此情可待成追忆，只是当时已惘然。");

        //插入图片  IconMarginSpan
        val bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);

        spanstr.setSpan(IconMarginSpan(bitmap, 10), 0, 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

//图片插入文本 DrawableMarginSpan
        spanstr.setSpan(DrawableMarginSpan(resources.getDrawable(R.mipmap.ic_launcher_round)), 0, 1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

//设置背景颜色
        spanstr.setSpan(BackgroundColorSpan(Color.YELLOW), 0, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

//设置字体颜色
        spanstr.setSpan(ForegroundColorSpan(Color.RED), 3, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

//设置文本可点击，有点击事件
        spanstr.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(getApplicationContext(), "锦瑟", Toast.LENGTH_SHORT).show();

            }
        }, 8, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//要相应点击事件必须加上这一步
        tvSpannable.setMovementMethod(LinkMovementMethod.getInstance());


// 修饰效果，模糊BlurMaskFilter   第一个参数 为模糊度，数越大越模糊  ，第二个数为样式
        val filterINNER = BlurMaskFilter(10f, BlurMaskFilter.Blur.INNER);
        spanstr.setSpan(MaskFilterSpan(filterINNER), 12, 14, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        val filterNORMAL = BlurMaskFilter(1f, BlurMaskFilter.Blur.NORMAL);
        spanstr.setSpan(MaskFilterSpan(filterNORMAL), 14, 18, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        val filterOUTER = BlurMaskFilter(10f, BlurMaskFilter.Blur.OUTER);
        spanstr.setSpan(MaskFilterSpan(filterOUTER), 18, 21, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        val filterSOLID = BlurMaskFilter(10f, BlurMaskFilter.Blur.SOLID);
        spanstr.setSpan(MaskFilterSpan(filterSOLID), 21, 24, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

//浮雕效果 EmbossMaskFilter
        val filter = EmbossMaskFilter(floatArrayOf(20f, 20f, 20f), 0.5f, 1f, 10f);
        spanstr.setSpan(MaskFilterSpan(filter), 24, 27, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

//删除线（中划线）StrikethroughSpan
        spanstr.setSpan(StrikethroughSpan(), 27, 29, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

//下划线 UnderlineSpan
        spanstr.setSpan(UnderlineSpan(), 29, 32, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

//竖线   QuoteSpan
        spanstr.setSpan(QuoteSpan(Color.RED), 32, 33, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

//绝对大小 AbsoluteSizeSpan
        spanstr.setSpan(AbsoluteSizeSpan(40), 33, 36, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

//设置图片，基于文本基线或底部对齐 DynamicDrawableSpan
//new DynamicDrawableSpan(DynamicDrawableSpan.ALIGN_BOTTOM)也可以直接写成new DynamicDrawableSpan()
        spanstr.setSpan(object : DynamicDrawableSpan() {
            override fun getDrawable(): Drawable {
                val drawable = getResources().getDrawable(R.mipmap.ic_launcher);
                drawable.setBounds(0, 0, 50, 50);
                return drawable;
            }


        }, 36, 37, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

//图片 ImageSpan
        val imageSpan = ImageSpan(getResources().getDrawable(R.mipmap.ic_launcher));
        spanstr.setSpan(imageSpan, 38, 40, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

//字体相对大小 RelativeSizeSpan
        spanstr.setSpan(RelativeSizeSpan(2f), 40, 43, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

//基于X轴缩放  ScaleXSpan
        spanstr.setSpan(ScaleXSpan(2f), 43, 45, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

//字体样式，粗体 BOLD、斜体 ITALIC等 StyleSpan
        spanstr.setSpan(StyleSpan(Typeface.BOLD_ITALIC), 45, 50, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

//下标 SubscriptSpan
        spanstr.setSpan(SubscriptSpan(), 50, 53, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

//上标 SuperscriptSpan
        spanstr.setSpan(SuperscriptSpan(), 53, 56, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

//文本外貌 TextAppearanceSpan       family值：monospace    serif   sans-serif
        val textSpan = TextAppearanceSpan("sans-serif", Typeface.BOLD_ITALIC,
                resources.getDimensionPixelSize(R.dimen.textSize), resources.getColorStateList(R.color.colorAccent)
                , resources.getColorStateList(R.color.colorAccent));

        spanstr.setSpan(textSpan, 56, 59, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

//文本字体   TypefaceSpan       family值：monospace    serif   sans-serif
        val typefaceSpan = TypefaceSpan("monospace");
        spanstr.setSpan(typefaceSpan, 59, 63, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);//感觉没什么变化

//文本超链接  URLSpan
        spanstr.setSpan(URLSpan("http://music.163.com/#/song/464192018?userid=1337800695"), 63, 67,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

//文本对齐方式   AlignmentSpan.Standard
        val standard = AlignmentSpan.Standard(Layout.Alignment.ALIGN_CENTER);
        spanstr.setSpan(standard, 0, 67, Spannable.SPAN_INCLUSIVE_INCLUSIVE);


        tvSpannable.text = spanstr

    }


    /**
     * 管道流
     */
    private fun initPipes() {
        //读数据
        pipedReader = PipedReader()
        //写数据
        pipedWriter = PipedWriter()
        //绑定
        pipedWriter.connect(pipedReader)

        etTest.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //写数据
                pipedWriter.write(s.toString())
            }
        })
        thread = Thread(TextRunnable(pipedReader))
        thread.start()
    }


    class TextRunnable(private val pipedReader: PipedReader) : Runnable {

        override fun run() {
            //如果线程没有断开
            while (!Thread.currentThread().isInterrupted) {
                var arr = CharArray(1024)
                try {
                    do {
                        var len = pipedReader.read(arr)

                        //另一个线程中读取数据
                        Log.i("dddd", String(arr, 0, len))

                        if (len == -1) {
                            break
                        }
                    } while (true)
                } catch (e: IOException) {
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        pipedReader.close()
        pipedWriter.close()
        thread.interrupt()
    }


}
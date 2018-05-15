package wusong.com.simpleviewapp.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by SongUp on 2018/5/9.
 */

public class SimpleView extends View {
    private String TAG = SimpleView.class.getSimpleName();
    //初始化参数，获取自定义属性
    public SimpleView(Context context) {
        super(context);
    }

    public SimpleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //测量View的大小  MeasureSpec：度量值
    //widthMeasureSpec : 32位二进制，31和30位代表模式
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        如果对View的宽高进行修改了，
//        不要调用super.onMeasure(widthMeasureSpec,heightMeasureSpec);
//        要调用setMeasuredDimension(widthsize,heightsize); 这个函数。
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int wid = MeasureSpec.getSize(widthMeasureSpec);
        int widMod = MeasureSpec.getMode(widthMeasureSpec);
        int hei = MeasureSpec.getSize(heightMeasureSpec);
        int heiMod = MeasureSpec.getMode(heightMeasureSpec);

        Log.e(TAG, "WidMod = "+ widMod);
        Log.e(TAG, "wid = "+wid);
        Log.e(TAG, "heiMod = "+ heiMod);
        Log.e(TAG, "wid = "+hei);

    }

    /**
     * 确定View的大小，这个是在View大小改变时调用
     * 注意：
     *  在测量完View并使用setMeasuredDimension函数之后View的大小基本上已经确定了
     *  View的大小不仅由View本身控制，而且受父控件的影响，所以我们在确定View大小的时候最好使用系统提供的onSizeChanged回调函数。
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    //确定子view的位置
    //在自定义ViewGroup中，onLayout一般是循环取出子View，然后经过计算得出各个子View位置的坐标值，然后用以下函数设置子View位置。
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }
//绘制内容
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
//xml中view 以及他们的子节点填充完成时调用
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }
}

package wusong.com.simpleviewapp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import wusong.com.simpleviewapp.R;

/**
 * Created by SongUp on 2018/5/13.
 */

public class MyTextView extends View {

    private String mText = "我是自定义的View";//需要绘制的文字
    private int mTextColor; //绘制文字的颜色
    private float mTextSize;  //需要绘制文本的大小
    private Rect bound; //需要绘制文本的区域
    private Paint paint; //画笔
    int widMod;
    int heiMode;

    public MyTextView(Context context) {
        this(context, null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widSize = MeasureSpec.getSize(widthMeasureSpec);
        widMod = MeasureSpec.getMode(widthMeasureSpec);
        int heiSize = MeasureSpec.getSize(heightMeasureSpec);
        heiMode = MeasureSpec.getMode(heightMeasureSpec);

        int viewWid = 0;
        int viewHei = 0;

        //测量宽度
        switch (widMod){
            case MeasureSpec.EXACTLY:
                viewWid = widSize;
                break;
            case MeasureSpec.AT_MOST:
                viewWid = bound.width() + getPaddingLeft() + getPaddingRight();
                break;
        }
        //测量高度
        switch (heiMode){
            case MeasureSpec.EXACTLY:
                viewHei = heiSize;
                break;
            case MeasureSpec.AT_MOST:
                viewHei = bound.height() + getPaddingTop() + getPaddingBottom();
                break;
        }

        setMeasuredDimension(viewWid, viewHei);



    }

    //在构造方法中，获取自定义属性的值
    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //获取自定义的属性
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyTextView, defStyleAttr, 0);
        mText = typedArray.getString(R.styleable.MyTextView_mtext);
        mTextColor = typedArray.getColor(R.styleable.MyTextView_mcolor, Color.BLACK);
        mTextSize = typedArray.getDimension(R.styleable.MyTextView_msize, 15);
        typedArray.recycle();   //注意typeArray的回收

//        mTextColor = Color.BLUE;
//        mTextSize = 100;
//        mText = "你猜啊";
        paint = new Paint();
        paint.setTextSize(mTextSize);
        paint.setColor(mTextColor);

        bound = new Rect();
        paint.getTextBounds(mText,0, mText.length(), bound);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (heiMode){
            case MeasureSpec.EXACTLY:
                canvas.drawText(mText, (getWidth() - bound.width())/2, (getHeight() - bound.height())/2,paint);
                break;
            case MeasureSpec.AT_MOST:
                canvas.drawText(mText, 0, bound.height(),paint);
                break;
        }

    }
}

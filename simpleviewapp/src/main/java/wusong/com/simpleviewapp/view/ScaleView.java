package wusong.com.simpleviewapp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import wusong.com.simpleviewapp.R;

/**
 * Created by SongUp on 2018/5/15.
 */

public class ScaleView extends View{

    int widMod, heiMode;
    //长的线 和 短的线 宽度
    private float longWidth, shortWidth;
    private float longHei, shorHei;
    private int scaleNum, lineYStart;
    private Paint paint;
    private int maxNum, minNum;
    private Rect bound;
    private int textLineSpace;

    public ScaleView(Context context) {
        this(context, null);
    }

    public ScaleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScaleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ScaleView, defStyleAttr, 0);
        longWidth = typedArray.getDimension(R.styleable.ScaleView_longWidth, dip2px(context, 2));
        shortWidth = typedArray.getDimension(R.styleable.ScaleView_shortWidth, dip2px(context, 2));
        scaleNum = typedArray.getInt(R.styleable.ScaleView_scaleNum, 5);
        maxNum = typedArray.getInt(R.styleable.ScaleView_maxNum, 5);
        minNum = typedArray.getInt(R.styleable.ScaleView_minNum, 0);
        minNum = typedArray.getInt(R.styleable.ScaleView_minNum, 0);
        typedArray.recycle();

        paint = new Paint();
        paint.setColor(Color.RED);
        longHei = dip2px(context, 10);
        shorHei = dip2px(context, 7);
        paint.setAntiAlias(true);
        bound = new Rect();
        paint.setTextSize(sp2px(context, 20));
        paint.setStrokeWidth(dip2px(context, 2));
        textLineSpace = dip2px(context, 6);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widSize = MeasureSpec.getSize(widthMeasureSpec);
        widMod = MeasureSpec.getMode(widthMeasureSpec);
        int heiSize = MeasureSpec.getSize(heightMeasureSpec);
        heiMode = MeasureSpec.getMode(heightMeasureSpec);

        float viewWid = 0, viewHei = 0;
        //测量宽度
        switch (widMod){
            case MeasureSpec.EXACTLY:
                viewWid = widSize;
                break;
            case MeasureSpec.AT_MOST:
                viewWid = longWidth * scaleNum *20 + getPaddingLeft() + getPaddingRight();
                break;
        }
        //测量高度
        switch (heiMode){
            case MeasureSpec.EXACTLY:
                viewHei = heiSize;
                break;
            case MeasureSpec.AT_MOST:
                viewHei = longHei + getPaddingTop() + getPaddingBottom();
                break;
        }

        setMeasuredDimension((int) viewWid, (int) viewHei);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int curNum = minNum;
        int devideNum = (maxNum - minNum)/scaleNum;
        String curNumStr = String.valueOf(curNum);
        paint.getTextBounds(curNumStr,0, curNumStr.length(), bound);
        int minNumWid = bound.width()/2;
        String maxNumStr = String.valueOf(maxNum);
        paint.getTextBounds(curNumStr,0, curNumStr.length(), bound);
        int maxNumWid = bound.width()/2;

        int scaleCount = scaleNum *2;
        float devideWidth = (getWidth() - minNumWid - maxNumWid)/scaleCount;
        float startX = getPaddingLeft() + minNumWid, startY = getPaddingTop();
        float stopX = startX,  stopY = longHei;

        canvas.drawText(curNumStr, startX - minNumWid, stopY+bound.height()+ textLineSpace, paint);
        canvas.drawLine(startX ,startY, stopX, stopY, paint);

        for(int i = 0; i< scaleCount; i++){
            startX = stopX + devideWidth;
            stopX = startX;
            if (i % 2 == 1) {
                stopY = startY + longHei;
                curNum += devideNum;

                curNumStr = String.valueOf(curNum);
                paint.getTextBounds(curNumStr,0, curNumStr.length(), bound);
                canvas.drawText(curNumStr, startX - 1.0f * bound.width()/2, stopY+bound.height()+ textLineSpace, paint);
            }
            else
                stopY = startY + shorHei;
            canvas.drawLine(startX,startY, stopX, stopY, paint);

        }

    }




    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int sp2px(Context context, float spVal){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

}

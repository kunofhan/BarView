package animate.han.com.bardraw;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by hyk on 2018/3/29.
 */

public class BarView extends View {

    private int leftStart;

    public BarView(Context context) {
        this(context, null);
    }

    public BarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    public BarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs, defStyleAttr);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width;
        int height;
        int widthSpec = MeasureSpec.getSize(widthMeasureSpec);
        int heifhtSpec = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSpec;
        } else {
            width = widthSpec / 2;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heifhtSpec;
        } else {
            height = heifhtSpec / 2;
        }

        setMeasuredDimension(width, height);

    }

    private int mWidth, mHeight, mStartWidth, mChartWidth, mSize;//屏幕宽度高度、柱状图起始位置、柱状图宽度

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mWidth = getWidth();
        mHeight = getHeight();
        leftStart = 100;
        mStartWidth = (getWidth() - 200) / 13;
        mSize = getWidth() / 26;
        mChartWidth = (getWidth() - 200) / 13 - mSize;
    }

    private List<Float> list = new ArrayList<>();//柱状图高度占比
    private List<Integer> selectIndexRoles = new ArrayList<>();
    private int number = 1000;//柱状图最大值

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        mPaint.setColor(lineColor);

        //画坐标轴
        Paint linePaint = new Paint();
        linePaint.setStrokeWidth(1);
        linePaint.setStyle(Paint.Style.FILL);
        linePaint.setColor(Color.parseColor("#000000"));
        //X轴
        canvas.drawLine(leftStart, mHeight - 60, mWidth - 100, mHeight - 60, linePaint);
        canvas.drawLine(mWidth - 110, mHeight - 70, mWidth - leftStart, mHeight - 60, linePaint);
        canvas.drawLine(mWidth - 110, mHeight - 50, mWidth - leftStart, mHeight - 60, linePaint);


        //Y轴
        canvas.drawLine(100, mHeight - 60, leftStart, 60, linePaint);
        canvas.drawLine(90, 70, leftStart, 60, linePaint);
        canvas.drawLine(110, 70, leftStart, 60, linePaint);
        canvas.drawLine(leftStart, mHeight - 60 - 200, mWidth - leftStart, mHeight - 60 - 200, linePaint);
        canvas.drawLine(leftStart, mHeight - 60 - 300, mWidth - leftStart, mHeight - 60 - 300, linePaint);
        canvas.drawLine(leftStart, mHeight - 60 - 400, mWidth - leftStart, mHeight - 60 - 400, linePaint);

        Paint textPaint = new Paint();
        textPaint.setTextSize(26);
        textPaint.setColor(Color.parseColor("#0089ff"));
        textPaint.setTextAlign(Paint.Align.CENTER);
        Rect textRect = new Rect();
        for (int i = 1; i < 13; i++) {
            String s1 = String.valueOf(i) + "月";
            textPaint.getTextBounds(s1, 0, s1.length(), textRect);
            canvas.drawText(s1, mStartWidth * i + leftStart - textRect.width() / 2, mHeight - 50 + textRect.height(), textPaint);
            canvas.drawLine(mStartWidth * i + leftStart - textRect.width() / 2, mHeight - 60, mStartWidth * i + leftStart - textRect.width() / 2, mHeight - 50, linePaint);
        }
        canvas.drawText("x轴", mWidth - 50, mHeight - 50, textPaint);
        canvas.drawText("y轴", 50, 60, textPaint);
        RectF barRectf = new RectF();
        Paint barPaint = new Paint();
        barPaint.setStyle(Paint.Style.FILL);
        barPaint.setColor(Color.parseColor("#93ff00"));
        for (int i = 1; i < 13; i++) {
            barRectf.left = mStartWidth * i + leftStart - mSize/*/2*/;
            barRectf.right = mStartWidth * i + leftStart /*+ mSize/2*/;
            barRectf.bottom = mHeight - 60;
            Random random = new Random();
            float floatNumber = random.nextFloat();
            int top = (int) ((mHeight - 200) * floatNumber);
            System.out.println("mHeight:" + mHeight + "  top:" + top);
            barRectf.top = top;
            canvas.drawRect(barRectf, barPaint);
        }
    }
}

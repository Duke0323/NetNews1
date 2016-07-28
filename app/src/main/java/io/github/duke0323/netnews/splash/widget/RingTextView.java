package io.github.duke0323.netnews.splash.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ${Duke} on 2016/6/25.
 */
public class RingTextView extends View {
    Paint circlePaint;
    TextPaint mTextPaint;
    int diameter;
    Paint ringPaint;
    int padding = 5;
    private RectF mRectF;
    int center;
    IOnClickRingListener mListener;
    private int mAngle;
    String content = "跳过";

    public void setListener(IOnClickRingListener listener) {
        mListener = listener;
    }

    public RingTextView(Context context) {
        super(context);
    }

    public RingTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //文字画笔
        mTextPaint = new TextPaint();
        mTextPaint.setTextSize(20);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        float text_width = mTextPaint.measureText(content);
        //3padding+text+3padding
        diameter = (int) (text_width + padding * 6);
        center = diameter / 2;
        //yuan
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(Color.GRAY);

        ringPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        ringPaint.setStyle(Paint.Style.STROKE);
        ringPaint.setStrokeWidth(padding / 2);
        ringPaint.setColor(Color.RED);
        mRectF = new RectF(padding / 2, padding / 2, diameter - padding / 2, diameter - padding / 2);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //圆
        setMeasuredDimension(diameter, diameter);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(center, center, center, circlePaint);
        int y = (int) (center - (mTextPaint.ascent() + mTextPaint.descent()) / 2);
        canvas.drawText(content, padding * 3, y, mTextPaint);
        canvas.save();
        canvas.rotate(-90, center, center);
        //false true 在于中间直径连没连
        canvas.drawArc(mRectF, 0, mAngle, false, ringPaint);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                setAlpha(0.3f);
                break;
            case MotionEvent.ACTION_UP:
                if (mListener != null) {
                    mListener.onClick();
                }
                setAlpha(1.0f);
                break;
        }
        return true;
    }

    public void setProgress(int now, int all) {
        int space = 360 / all;
        mAngle = now * space;
        invalidate();
    }
}

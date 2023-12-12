package com.selincengiz.ghibli.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View {
    private Path path = new Path();
    private Paint paint = new Paint();

    public MyView(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        PaintSettings();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);
        PaintSettings();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFocusable(true);
        setFocusableInTouchMode(true);
        PaintSettings();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setFocusable(true);
        setFocusableInTouchMode(true);
        PaintSettings();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path,paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float pointX = event.getX();

        float pointY =event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(pointX, pointY);
                break;

            case MotionEvent.ACTION_MOVE:
                //Draws line between first point and last point
                path.lineTo(pointX, pointY);
                break;
            default:
                return false;

        }
        //Çizdir
        invalidate();
        return true;

    }


    private void PaintSettings() {
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(10);
    }


}

package com.paint.series.paintbase;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class MyDashView extends View {

    private int dashDx = 0;

    public MyDashView(Context context) {
        super(context);
    }

    public MyDashView(Context context, AttributeSet attrs) {
        super(context, attrs);

        startAnim();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawDashPathEffect(canvas);
    }

    private Paint getPaint() {
        Paint paint = new Paint();
        paint.setStrokeWidth(6);
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        return paint;
    }

    private void drawDashPathEffect(Canvas canvas) {
        Paint paint = getPaint();
        Path path = new Path();
        path.moveTo(100, 500);
        path.lineTo(400, 100);
        path.lineTo(700, 900);

        canvas.drawPath(path, paint);
        paint.setColor(Color.BLACK);

        //使用DashPathEffect 画线段
        paint.setPathEffect(new DashPathEffect(new float[]{20, 10, 100, 100}, 0));
        canvas.translate(0, 100);
        canvas.drawPath(path, paint);

        //画同一条线段，偏移值为15
        paint.setPathEffect(new DashPathEffect(new float[]{20, 10, 100, 100}, dashDx));
        paint.setColor(Color.GRAY);
        canvas.translate(0, 100);
        canvas.drawPath(path, paint);
    }

    public void startAnim() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 220);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dashDx = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.start();
    }

}

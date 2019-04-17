package com.paint.series.xfermode;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.paint.series.R;

public class HeartMapView extends View {

    private Paint mBitmapPaint;

    private Bitmap mBitmapSRC, mBitmapDST;

    private int dx;

    public HeartMapView(Context context) {
        super(context);
        setLayerType(LAYER_TYPE_HARDWARE, null);

        mBitmapPaint = new Paint();
        mBitmapPaint.setColor(Color.RED);

        mBitmapDST = BitmapFactory.decodeResource(getResources(), R.mipmap.heartmap);
        mBitmapSRC = Bitmap.createBitmap(mBitmapDST.getWidth(), mBitmapDST.getHeight(), Bitmap.Config.ARGB_8888);

        startAnimation();
    }

    private void startAnimation() {
        ValueAnimator animator = ValueAnimator.ofInt(0, mBitmapDST.getWidth());
        animator.setDuration(5000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //清空 src bitmap 的内容，变成透明的
        Canvas c = new Canvas(mBitmapSRC);
        c.drawColor(Color.RED, PorterDuff.Mode.CLEAR);

        //画不透明的矩形区域
        c.drawRect(mBitmapDST.getWidth() - dx, 0, mBitmapDST.getWidth(), mBitmapDST.getHeight(), mBitmapPaint);

        //画目标图片
        canvas.drawBitmap(mBitmapDST, 0, 0, mBitmapPaint);
        mBitmapPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(mBitmapSRC, 0, 0, mBitmapPaint);
        mBitmapPaint.setXfermode(null);
    }
}

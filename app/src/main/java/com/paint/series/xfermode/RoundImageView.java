package com.paint.series.xfermode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.paint.series.R;

public class RoundImageView extends View {

    private Paint mBitMapPaint;

    private Bitmap mBitmapSRC, mBitmapDST;

    public RoundImageView(Context context) {
        this(context, null);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mBitMapPaint = new Paint();
        mBitmapSRC = BitmapFactory.decodeResource(getResources(), R.mipmap.xyjy6);
        mBitmapDST = BitmapFactory.decodeResource(getResources(), R.mipmap.shade);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mBitmapDST, 0, 0,mBitMapPaint);
        mBitMapPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mBitmapSRC, 0 ,0, mBitMapPaint);

        mBitMapPaint.setXfermode(null);
    }
}

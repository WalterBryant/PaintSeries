package com.paint.series.xfermode.other;

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

public class TwitterViewMULTIPLY extends View {
    private Paint mBitPaint;
    private Bitmap BmpDST, BmpSRC;

    public TwitterViewMULTIPLY(Context context,  AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mBitPaint = new Paint();
        BmpDST = BitmapFactory.decodeResource(getResources(), R.mipmap.twiter_bg, null);
        BmpSRC = BitmapFactory.decodeResource(getResources(), R.mipmap.twiter_light, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(BmpDST, 0, 0, mBitPaint);
        mBitPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
        canvas.drawBitmap(BmpSRC, 0, 0, mBitPaint);
        mBitPaint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }
}

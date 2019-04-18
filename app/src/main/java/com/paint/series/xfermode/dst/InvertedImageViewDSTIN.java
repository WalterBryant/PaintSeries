package com.paint.series.xfermode.dst;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.paint.series.R;

public class InvertedImageViewDSTIN extends View {

    private Paint mPaint;
    private Bitmap BmpDST, BmpSRC, BmpRevert;

    public InvertedImageViewDSTIN(Context context,  AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint();
        BmpDST = BitmapFactory.decodeResource(getResources(), R.mipmap.xyjy6, null);
        BmpSRC = BitmapFactory.decodeResource(getResources(), R.mipmap.invert_shade, null);

        Matrix matrix = new Matrix();
        matrix.setScale(1f, -1f);

        //生成倒影图
        BmpRevert = Bitmap.createBitmap(BmpDST, 0 ,0, BmpDST.getWidth(), BmpDST.getHeight(), matrix, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //线画出小狗图片
        canvas.drawBitmap(BmpDST, 0 ,0 , mPaint);

        //再画出倒影
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.translate(0, BmpSRC.getHeight());

        canvas.drawBitmap(BmpRevert, 0, 0, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(BmpSRC, 0, 0, mPaint);

        mPaint.setXfermode(null);

        canvas.restoreToCount(layerId);
    }
}

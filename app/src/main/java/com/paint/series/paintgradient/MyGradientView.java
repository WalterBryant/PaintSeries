package com.paint.series.paintgradient;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;

import com.paint.series.R;

public class MyGradientView extends View {

    private Paint mPaint;
    private Bitmap mBitmap = null;

    private int mWidth;
    private int mHeight;
    private int[] mColors = {Color.YELLOW, Color.RED, Color.BLUE};

    public MyGradientView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBitmap = ((BitmapDrawable) getResources().getDrawable(R.mipmap.heart)).getBitmap();
        mPaint = new Paint();
        mWidth = mBitmap.getWidth();
        mHeight = mBitmap.getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        /**
         * TileMode.CLAMP 拉伸最后一个像素去铺满剩下的地方
         * TileMode.MIRROR 通过镜像翻转铺满剩下的地方。
         * TileMode.REPEAT 重复图片平铺整个画面（电脑设置壁纸）
         */
        /*BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.MIRROR,
                Shader.TileMode.MIRROR);
        mPaint.setShader(bitmapShader);
        mPaint.setAntiAlias(true);

        //设置像素矩阵，来调整大小，为了解决宽高不一致的问题
        float scale = Math.max(mWidth, mHeight) / Math.min(mWidth, mHeight);

        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale);
        bitmapShader.setLocalMatrix(matrix);

//        canvas.drawCircle(mHeight / 2, mHeight / 2, mHeight / 2, mPaint);
//        canvas.drawOval(new RectF(0, 0, mWidth, mHeight), mPaint);
//        canvas.drawRect(new Rect(0, 0, 300, 700), mPaint);

        //通过shapeDrawable 也可以实现
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setShader(bitmapShader);
        shapeDrawable.setBounds(0, 0, mWidth, mWidth);
        shapeDrawable.draw(canvas);*/


        /**线性渐变
         * x0, y0, 起始点
         *  x1, y1, 结束点
         * int[]  mColors, 中间依次要出现的几个颜色
         * float[] positions,数组大小跟colors数组一样大，中间依次摆放的几个颜色分别放置在那个位置上(参考比例从左往右)
         *    tile
         */
       /* LinearGradient linearGradient = new LinearGradient(0, 0, 800, 800, mColors, null, Shader.TileMode.CLAMP);
//        linearGradient = new LinearGradient(0, 0, 400, 400, mColors, null, Shader.TileMode.REPEAT);
        mPaint.setShader(linearGradient);
        canvas.drawRect(0, 0, 800, 800, mPaint);*/

        /*RadialGradient radialGradient = new RadialGradient(300, 300, 100, mColors, null, Shader.TileMode.REPEAT);
        mPaint.setShader(radialGradient);
        canvas.drawCircle(300, 300, 300, mPaint);*/

       /* SweepGradient sweepGradient = new SweepGradient(300, 300, mColors, null);
        mPaint.setShader(sweepGradient);
        canvas.drawCircle(300, 300, 300, mPaint);*/

        /************用ComposeShader即可实现心形图渐变效果***************/
        //创建BitmapShader， 用以绘制心
        BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        //创建LinearGradient, 用以产生从左上角到右下角的颜色渐变效果
        LinearGradient linearGradient = new LinearGradient(0, 0, mWidth, mHeight, Color.RED, Color.BLUE, Shader.TileMode.CLAMP);
        //bitmapShader 对应目标像素，linearGradient 对应源像素，像素颜色混合采用 MUTIPLY 模式
        ComposeShader composeShader = new ComposeShader(bitmapShader, linearGradient, PorterDuff.Mode.MULTIPLY);
        //将组合的composeShader 作为画笔 paint 绘图所使用的shader
        mPaint.setShader(composeShader);
        //用composeShader 绘制矩形区域
        canvas.drawRect(0, 0, mWidth, mHeight, mPaint);
    }
}

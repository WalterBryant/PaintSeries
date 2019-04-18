package com.paint.series.paintgradient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.paint.series.R;
import com.paint.series.xfermode.HeartMapView;
import com.paint.series.xfermode.RoundImageView;

public class GradientViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradient_view);

        /*ZoomImageView zoomImageView = new ZoomImageView(this);
        setContentView(zoomImageView);*/

//        RoundImageView view = new RoundImageView(this);
       /* HeartMapView view = new HeartMapView(this);
        setContentView(view);*/
    }
}

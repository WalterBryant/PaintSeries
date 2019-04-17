package com.paint.series;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.paint.series.circleprogressbar.CircleProgressActivity;
import com.paint.series.paintbase.PaintBaseActivity;
import com.paint.series.paintgradient.GradientViewActivity;
import com.paint.series.walterfall.WaterFallActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.water_fall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(MainActivity.this, WaterFallActivity.class));
            }
        });

        findViewById(R.id.circle_progress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CircleProgressActivity.class));
            }
        });

        findViewById(R.id.paint_base).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PaintBaseActivity.class));
            }
        });

        findViewById(R.id.gradient_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GradientViewActivity.class));
            }
        });
    }

}

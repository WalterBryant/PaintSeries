package com.paint.series;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.paint.series.walterfall.WaterFallLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static int IMG_COUNT = 5;
    WaterFallLayout walterFallLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        walterFallLayout = findViewById(R.id.waterfallLayout);
        findViewById(R.id.add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addView(walterFallLayout);
            }
        });
    }

    public void addView(WaterFallLayout walterFallLayout) {
        Random random = new Random();
        Integer num = Math.abs(random.nextInt());
        WaterFallLayout.LayoutParams layoutParams = new WaterFallLayout.LayoutParams(
                WaterFallLayout.LayoutParams.MATCH_PARENT,
                WaterFallLayout.LayoutParams.MATCH_PARENT);
        ImageView imageView = new ImageView(this);
        if (num % IMG_COUNT == 0) {
            imageView.setImageResource(R.mipmap.pic_1);
        } else if (num % IMG_COUNT == 1) {
            imageView.setImageResource(R.mipmap.pic_2);
        } else if (num % IMG_COUNT == 2) {
            imageView.setImageResource(R.mipmap.pic_3);
        } else if (num % IMG_COUNT == 3) {
            imageView.setImageResource(R.mipmap.pic_4);
        } else if (num % IMG_COUNT == 4) {
            imageView.setImageResource(R.mipmap.pic_5);
        }
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        walterFallLayout.addView(imageView, layoutParams);

        walterFallLayout.setOnItemClickListener(new WaterFallLayout.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int index) {
                Toast.makeText(MainActivity.this, "item==" + index, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package com.paint.series.radar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.paint.series.R;

public class RadarActivity extends AppCompatActivity {

    private RadarView mRadarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar);

//        mRadarView = findViewById(R.id.radarview);
    }

    public void start(View view) {
        mRadarView.startScan();
    }

    public void stop(View view) {
        mRadarView.stopScan();
    }
}

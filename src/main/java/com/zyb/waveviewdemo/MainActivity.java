package com.zyb.waveviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.zyb.waveviewdemo.widget.WaveView;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    
    private WaveView wave;
    private Button btnStart;
    private Button btnStop;
    
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wave = (WaveView) findViewById(R.id.wave);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }
    
    /**
     * 跟随生命周期
     */
    @Override
    protected void onResume () {
        super.onResume();
        wave.startWave(); //开启波浪动画
    }
    
    /**
     * 跟随生命周期
     */
    @Override
    protected void onStop () {
        super.onStop();
        wave.stopWave();//取消波浪动画
    }
    
    @Override
    public void onClick (View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                wave.startWave(); //开启波浪动画
                break;
            
            case R.id.btnStop:
                wave.stopWave();
                break;
        }
    }
}

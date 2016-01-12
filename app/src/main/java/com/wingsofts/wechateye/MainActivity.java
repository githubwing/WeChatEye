package com.wingsofts.wechateye;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    private SeekBar mSeekBar;
    private WeChatEye mWeChatEye;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSeekBar = (SeekBar) findViewById(R.id.seekBar);
        mWeChatEye = (WeChatEye) findViewById(R.id.weChat);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mWeChatEye.setPercent(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}

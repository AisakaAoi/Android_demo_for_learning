package com.example.a9_activitylifecircledemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * <pre>
 * author : ZONGNAN.CHEN
 * time : 2021/07/20
 * </pre>
 */
public class VideoPlayerActivity extends Activity {

    private static final String TAG = "VideoPlayerActivity";
    private TextView mStatusText;
    private Button mPlayerControlBtn;
    private boolean isPlay = false;
    private boolean isStopAtAim = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initView();
        initListener();
    }

    private void initListener() {
        mPlayerControlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlay) {
                    stop();
                } else {
                    play();
                }
            }
        });
    }

    private void play() {
        Log.d(TAG, "播放电影...");
        isPlay = true;
        mStatusText.setText("正在播放电影...");
        mPlayerControlBtn.setText("暂停");
    }

    private void stop() {
        Log.d(TAG, "暂停电影...");
        isPlay = false;
        mStatusText.setText("电影暂停中...");
        mPlayerControlBtn.setText("播放");
    }

    private void initView() {
        mStatusText = (TextView) this.findViewById(R.id.current_play_status);
        mPlayerControlBtn = (Button) this.findViewById(R.id.player_control);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart...开始播放");
        if (isStopAtAim && !isPlay) {
            play();
            isStopAtAim = false;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStart...暂停播放");
        if (isPlay) {
            stop();
            isStopAtAim = true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

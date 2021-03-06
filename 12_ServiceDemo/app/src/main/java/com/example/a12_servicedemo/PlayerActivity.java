package com.example.a12_servicedemo;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.annotation.Nullable;

import com.example.a12_servicedemo.interfaces.IPlayViewControl;
import com.example.a12_servicedemo.interfaces.IPlayerControl;
import com.example.a12_servicedemo.services.PlayerService;

/**
 * <pre>
 * author : ZONGNAN.CHEN
 * time : 2021/07/22
 * </pre>
 */
public class PlayerActivity extends Activity {

    private static final String TAG = PlayerActivity.class.getName();
    private SeekBar mSeekBar;
    private Button mPlayOrPause;
    private Button mStop;
    private PlayerConnection mPlayerConnection;
    private IPlayerControl mIPlayerControl;
    private boolean isUserTouchProgressBar = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initView();
        initEvent();
        initService();
        initBindService();
    }

    private void initService() {
        startService(new Intent(this, PlayerService.class));
        Log.d(TAG, "initService()...");
    }

    private void initBindService() {
        Intent intent = new Intent(this, PlayerService.class);
        if (mPlayerConnection == null) {
            mPlayerConnection = new PlayerConnection();
        }
        bindService(intent, mPlayerConnection, BIND_AUTO_CREATE);
        Log.d(TAG, "initBindService()...");
    }

    private class PlayerConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIPlayerControl = (IPlayerControl) service;
            mIPlayerControl.registerViewController(mIPlayViewControl);
            Log.d(TAG, "onServiceConnected()...");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIPlayerControl = null;
            Log.d(TAG, "onServiceDisconnected()...");
        }
    }

    private void initEvent() {
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // ?????????????????????
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // ??????????????????
                isUserTouchProgressBar = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int touchProgress = seekBar.getProgress();
                Log.d(TAG, "touchProgress -> " + touchProgress);
                // ????????????
                if (mIPlayerControl != null) {
                    mIPlayerControl.seekTo(touchProgress);
                }
                isUserTouchProgressBar = false;
            }
        });
        mPlayOrPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ??????????????????
                if (mIPlayerControl != null) {
                    mIPlayerControl.startOrPausePlay();
                }
            }
        });
        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ??????
                if (mIPlayerControl != null) {
                    mIPlayerControl.stopPlay();
                }
            }
        });
    }

    private void initView() {
        mSeekBar = (SeekBar) this.findViewById(R.id.seek);
        mPlayOrPause = (Button) this.findViewById(R.id.play_or_pause_btn);
        mStop = (Button) this.findViewById(R.id.stop_btn);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()...");
        if (mPlayerConnection != null) {
            // ????????????
            mIPlayerControl.unRegisterViewController();
            unbindService(mPlayerConnection);
        }
    }

    private IPlayViewControl mIPlayViewControl = new IPlayViewControl() {
        @Override
        public void onPlayerStateChange(int state) {
            // ????????????????????????????????????UI
            switch (state) {
                case IPlayerControl.PLAY_STATE_PLAY:
                    // ????????? ???????????????????????????
                    mPlayOrPause.setText("??????");
                    break;
                case IPlayerControl.PLAY_STATE_PAUSE:
                case IPlayerControl.PLAY_STATE_STOP:
                    // ?????????/????????? ????????????????????????
                    mPlayOrPause.setText("??????");
                    break;
            }
        }

        @Override
        public void onSeekChange(int seek) {
            Log.d(TAG, "current thread -> " + Thread.currentThread().getName());
            // ?????????????????? ??????????????? ?????????????????????????????????????????? ????????????\
            // ?????????Log?????? ?????????????????? ???????????????????????????UI
            // ?????????????????????????????????
            // ?????????Android????????????????????????????????????????????????
            // ?????????progressBar ???????????????surfaceView
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (!isUserTouchProgressBar) {
                        mSeekBar.setProgress(seek);
                    }
                }
            });
        }
    };
}

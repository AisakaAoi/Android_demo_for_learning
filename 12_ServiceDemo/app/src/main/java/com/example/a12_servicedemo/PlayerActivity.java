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
                // 进度条发生变化
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // 进度条被触摸
                isUserTouchProgressBar = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int touchProgress = seekBar.getProgress();
                Log.d(TAG, "touchProgress -> " + touchProgress);
                // 停止拖动
                if (mIPlayerControl != null) {
                    mIPlayerControl.seekTo(touchProgress);
                }
                isUserTouchProgressBar = false;
            }
        });
        mPlayOrPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 播放或者暂停
                if (mIPlayerControl != null) {
                    mIPlayerControl.startOrPausePlay();
                }
            }
        });
        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 停止
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
            // 释放资源
            mIPlayerControl.unRegisterViewController();
            unbindService(mPlayerConnection);
        }
    }

    private IPlayViewControl mIPlayViewControl = new IPlayViewControl() {
        @Override
        public void onPlayerStateChange(int state) {
            // 我们要根据播放状态来修改UI
            switch (state) {
                case IPlayerControl.PLAY_STATE_PLAY:
                    // 播放中 则把按钮显示成暂停
                    mPlayOrPause.setText("暂停");
                    break;
                case IPlayerControl.PLAY_STATE_PAUSE:
                case IPlayerControl.PLAY_STATE_STOP:
                    // 暂停中/停止中 则把按钮显示播放
                    mPlayOrPause.setText("播放");
                    break;
            }
        }

        @Override
        public void onSeekChange(int seek) {
            Log.d(TAG, "current thread -> " + Thread.currentThread().getName());
            // 改变播放进度 有一个条件 当用户的手触摸到进度条的时候 就不更新\
            // 从上面Log发现 这不是主线程 所以不可以用于更新UI
            // 但该更新进度不会崩溃？
            // 是因为Android里有两个控件是可以用子线程去更新
            // 一个是progressBar 另外一个是surfaceView
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

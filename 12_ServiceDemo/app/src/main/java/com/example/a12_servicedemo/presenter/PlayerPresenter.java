package com.example.a12_servicedemo.presenter;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.util.Log;

import com.example.a12_servicedemo.interfaces.IPlayViewControl;
import com.example.a12_servicedemo.interfaces.IPlayerControl;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * <pre>
 * author : zongnan.chen
 * time : 2021/07/22
 * </pre>
 */
public class PlayerPresenter extends Binder implements IPlayerControl {

    private static final String TAG = PlayerPresenter.class.getName();
    private IPlayViewControl mViewController;
    private int mCurrentState = PLAY_STATE_STOP;
    private MediaPlayer mMediaPlayer;
    private Timer mTimer;
    private SeekTimeTask mTimeTask;

    @Override
    public void registerViewController(IPlayViewControl viewController) {
        this.mViewController = viewController;
    }

    @Override
    public void unRegisterViewController() {
        this.mViewController = null;
    }

    @Override
    public void startOrPausePlay() {
        Log.d(TAG, "startOrPausePlay()...");
        if (mCurrentState == PLAY_STATE_STOP) {
            // 创建播放器
            initPlayer();
            // 设置数据源
            try {
                // TODO 加上音乐路径
                mMediaPlayer.setDataSource("TODO 音乐路径");
                mMediaPlayer.prepare();
                mMediaPlayer.start();
                mCurrentState = PLAY_STATE_PLAY;
                startTimer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (mCurrentState == PLAY_STATE_PLAY) {
            if (mMediaPlayer != null) {
                mMediaPlayer.pause();
                mCurrentState = PLAY_STATE_PAUSE;
                stopTimer();
            }
        } else if (mCurrentState == PLAY_STATE_PAUSE) {
            if (mMediaPlayer != null) {
                mMediaPlayer.start();
                mCurrentState = PLAY_STATE_PLAY;
                startTimer();
            }
        }
        // 通知UI更新
        if (mViewController != null) {
            mViewController.onPlayerStateChange(mCurrentState);
        }
    }

    private void initPlayer() {
        if (mMediaPlayer == null) {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        }
    }

    @Override
    public void stopPlay() {
        Log.d(TAG, "stopPlay()...");
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
            // 更新播放状态
            mCurrentState = PLAY_STATE_STOP;
            stopTimer();
            if (mViewController != null) {
                mViewController.onPlayerStateChange(mCurrentState);
            }
        }
    }

    @Override
    public void seekTo(int seek) {
        Log.d(TAG, "seekTo()... -> " + seek);
        // 0~100之间 需要做一个转换 得到的seek其实是个百分比
        if (mMediaPlayer != null) {
            int tarSeek = (int) (seek * 1.0f / 100 * mMediaPlayer.getDuration());
            mMediaPlayer.seekTo(tarSeek);
        }
    }

    // 开启一个timerTask
    private void startTimer() {
        if (mTimer == null) {
            mTimer = new Timer();
        }
        if (mTimeTask == null) {
            mTimeTask = new SeekTimeTask();
        }
        mTimer.schedule(mTimeTask, 0, 500);
    }

    private void stopTimer() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer= null;
        }
        if (mTimeTask != null) {
            mTimeTask.cancel();
            mTimeTask = null;
        }
    }

    private class SeekTimeTask extends TimerTask {

        @Override
        public void run() {
            if (mMediaPlayer == null) {
                // 获取当前的播放进度
                int currentPosition = mMediaPlayer.getCurrentPosition();
                Log.d(TAG, "current play position -> " + currentPosition);
                int curPosition = (int) (currentPosition * 1.0f / mMediaPlayer.getDuration() * 100);
                mViewController.onSeekChange(curPosition);
            }
        }
    }
}

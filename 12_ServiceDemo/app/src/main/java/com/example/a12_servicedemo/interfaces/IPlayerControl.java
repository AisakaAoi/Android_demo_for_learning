package com.example.a12_servicedemo.interfaces;

/**
 * <pre>
 * author : ZONGNAN.CHEN
 * time : 2021/07/22
 * </pre>
 */
public interface IPlayerControl {

    // 播放状态
    int PLAY_STATE_PLAY = 1;
    int PLAY_STATE_PAUSE = 2;
    int PLAY_STATE_STOP = 3;

    void registerViewController(IPlayViewControl viewController);
    void unRegisterViewController();
    void startOrPausePlay();
    void stopPlay();
    void seekTo(int seek);
}

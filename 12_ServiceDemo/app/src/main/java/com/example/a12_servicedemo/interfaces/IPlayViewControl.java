package com.example.a12_servicedemo.interfaces;

/**
 * <pre>
 * author : zongnan.chen
 * time : 2021/07/22
 * </pre>
 */
public interface IPlayViewControl {
    void onPlayerStateChange(int state);
    void onSeekChange(int seek);
}

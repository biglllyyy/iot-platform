package me.iot.das.common.core;

public interface IServer {
    void start();

    void stop();

    void restart();

    boolean isRunning();
}

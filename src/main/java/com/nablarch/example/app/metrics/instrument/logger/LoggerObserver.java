package com.nablarch.example.app.metrics.instrument.logger;

import nablarch.core.log.basic.LogLevel;

public interface LoggerObserver {

    void onOutputLog(String loggerName, LogLevel logLevel, String message, Object... options);
    void onOutputLog(String loggerName, LogLevel logLevel, String message, Throwable error, Object... options);
}

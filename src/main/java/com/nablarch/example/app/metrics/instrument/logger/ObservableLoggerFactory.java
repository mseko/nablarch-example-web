package com.nablarch.example.app.metrics.instrument.logger;

import nablarch.core.log.Logger;
import nablarch.core.log.basic.BasicLoggerFactory;
import nablarch.core.log.basic.LogLevel;

import java.util.concurrent.CopyOnWriteArrayList;

public class ObservableLoggerFactory extends BasicLoggerFactory {
    private static CopyOnWriteArrayList<LoggerObserver> observers = new CopyOnWriteArrayList<>();

    @Override
    public Logger get(String name) {
        Logger logger = super.get(name);

        return new ObservableLogger(name, logger, new LoggerObserver() {
            @Override
            public void onOutputLog(String loggerName, LogLevel logLevel, String message, Object... options) {
                for (LoggerObserver observer : observers) {
                    observer.onOutputLog(loggerName, logLevel, message, options);
                }
            }

            @Override
            public void onOutputLog(String loggerName, LogLevel logLevel, String message, Throwable error, Object... options) {
                for (LoggerObserver observer : observers) {
                    observer.onOutputLog(loggerName, logLevel, message, error, options);
                }
            }
        });
    }

    public static void addLoggerObserver(LoggerObserver observer) {
        observers.add(observer);
    }
}

package com.nablarch.example.app.metrics.instrument.logger;

import nablarch.core.log.basic.LogContext;
import nablarch.core.log.basic.LogWriter;
import nablarch.core.log.basic.ObjectSettings;

import java.util.concurrent.CopyOnWriteArrayList;

public class LogPublisher implements LogWriter {
    private static final CopyOnWriteArrayList<LogListener> listenerList = new CopyOnWriteArrayList<>();

    public static void addListener(LogListener listener) {
        listenerList.add(listener);
    }

    @Override
    public void write(LogContext context) {
        for (LogListener logListener : listenerList) {
            logListener.onWritten(context);
        }
    }

    @Override
    public void initialize(ObjectSettings settings) {/*noop*/}

    @Override
    public void terminate() {/*noop*/}
}

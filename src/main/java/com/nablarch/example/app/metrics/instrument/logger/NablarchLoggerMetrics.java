package com.nablarch.example.app.metrics.instrument.logger;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import nablarch.core.log.basic.LogLevel;

public class NablarchLoggerMetrics implements MeterBinder {
    @Override
    public void bindTo(MeterRegistry registry) {
        ObservableLoggerFactory.addLoggerObserver(new LoggerObserver() {
            @Override
            public void onOutputLog(String loggerName, LogLevel logLevel, String message, Object... options) {
                countUp(loggerName, logLevel);
            }

            @Override
            public void onOutputLog(String loggerName, LogLevel logLevel, String message, Throwable error, Object... options) {
                countUp(loggerName, logLevel);
            }

            private void countUp(String loggerName, LogLevel logLevel) {
                Counter counter = registry.counter("log.count", "level", logLevel.name(), "name", loggerName);
                counter.increment();
            }
        });
    }
}

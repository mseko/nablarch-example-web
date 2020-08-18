package com.nablarch.example.app.metrics.instrument;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;

public class NablarchLoggerMetrics implements MeterBinder {
    @Override
    public void bindTo(MeterRegistry registry) {
        Counter fatalCounter = Counter.builder("log.count")
                .tag("level", "fatal")
                .register(registry);
        MetricsLoggerFactory.setFatalCounter(fatalCounter);

        Counter errorCounter = Counter.builder("log.count")
                .tag("level", "error")
                .register(registry);
        MetricsLoggerFactory.setErrorCounter(errorCounter);

        Counter warnCounter = Counter.builder("log.count")
                .tag("level", "warn")
                .register(registry);
        MetricsLoggerFactory.setWarnCounter(warnCounter);

        Counter infoCounter = Counter.builder("log.count")
                .tag("level", "info")
                .register(registry);
        MetricsLoggerFactory.setInfoCounter(infoCounter);

        Counter debugCounter = Counter.builder("log.count")
                .tag("level", "debug")
                .register(registry);
        MetricsLoggerFactory.setDebugCounter(debugCounter);

        Counter traceCounter = Counter.builder("log.count")
                .tag("level", "trace")
                .register(registry);
        MetricsLoggerFactory.setTraceCounter(traceCounter);
    }
}

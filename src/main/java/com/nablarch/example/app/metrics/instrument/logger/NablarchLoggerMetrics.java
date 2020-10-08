package com.nablarch.example.app.metrics.instrument.logger;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;

public class NablarchLoggerMetrics implements MeterBinder {
    @Override
    public void bindTo(MeterRegistry registry) {
        LogPublisher.addListener(context -> {
            Counter counter = registry.counter("log.count",
                    "level", context.getLevel().name(),
                    "logger", context.getRuntimeLoggerName());
            counter.increment();
        });
    }
}

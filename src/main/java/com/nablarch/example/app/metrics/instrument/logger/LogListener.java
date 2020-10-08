package com.nablarch.example.app.metrics.instrument.logger;

import nablarch.core.log.basic.LogContext;

public interface LogListener {
    void onWritten(LogContext context);
}

package com.nablarch.example.app.metrics.instrument.logger;

import nablarch.core.log.Logger;
import nablarch.core.log.basic.LogLevel;
import nablarch.core.util.annotation.Published;

public class ObservableLogger implements Logger {
    private final String loggerName;
    private final Logger delegate;
    private final LoggerObserver observer;

    public ObservableLogger(String loggerName, Logger delegate, LoggerObserver observer) {
        this.loggerName = loggerName;
        this.delegate = delegate;
        this.observer = observer;
    }

    @Override
    public boolean isFatalEnabled() {
        return delegate.isFatalEnabled();
    }

    @Override
    public void logFatal(String message, Object... options) {
        delegate.logFatal(message, options);
        if (isFatalEnabled()) {
            observer.onOutputLog(loggerName, LogLevel.FATAL, message, options);
        }
    }

    @Override
    public void logFatal(String message, Throwable error, Object... options) {
        delegate.logFatal(message, error, options);
        if (isFatalEnabled()) {
            observer.onOutputLog(loggerName, LogLevel.FATAL, message, error, options);
        }
    }

    @Override
    public boolean isErrorEnabled() {
        return delegate.isErrorEnabled();
    }

    @Override
    public void logError(String message, Object... options) {
        delegate.logError(message, options);
        if (isErrorEnabled()) {
            observer.onOutputLog(loggerName, LogLevel.ERROR, message, options);
        }
    }

    @Override
    public void logError(String message, Throwable error, Object... options) {
        delegate.logError(message, error, options);
        if (isErrorEnabled()) {
            observer.onOutputLog(loggerName, LogLevel.ERROR, message, error, options);
        }
    }

    @Override
    @Published
    public boolean isWarnEnabled() {
        return delegate.isWarnEnabled();
    }

    @Override
    @Published
    public void logWarn(String message, Object... options) {
        delegate.logWarn(message, options);
        if (isWarnEnabled()) {
            observer.onOutputLog(loggerName, LogLevel.WARN, message, options);
        }
    }

    @Override
    @Published
    public void logWarn(String message, Throwable error, Object... options) {
        delegate.logWarn(message, error, options);
        if (isWarnEnabled()) {
            observer.onOutputLog(loggerName, LogLevel.WARN, message, error, options);
        }
    }

    @Override
    @Published
    public boolean isInfoEnabled() {
        return delegate.isInfoEnabled();
    }

    @Override
    @Published
    public void logInfo(String message, Object... options) {
        delegate.logInfo(message, options);
        if (isInfoEnabled()) {
            observer.onOutputLog(loggerName, LogLevel.INFO, message, options);
        }
    }

    @Override
    @Published
    public void logInfo(String message, Throwable error, Object... options) {
        delegate.logInfo(message, error, options);
        if (isInfoEnabled()) {
            observer.onOutputLog(loggerName, LogLevel.INFO, message, error, options);
        }
    }

    @Override
    @Published
    public boolean isDebugEnabled() {
        return delegate.isDebugEnabled();
    }

    @Override
    @Published
    public void logDebug(String message, Object... options) {
        delegate.logDebug(message, options);
        if (isDebugEnabled()) {
            observer.onOutputLog(loggerName, LogLevel.DEBUG, message, options);
        }
    }

    @Override
    @Published
    public void logDebug(String message, Throwable error, Object... options) {
        delegate.logDebug(message, error, options);
        if (isDebugEnabled()) {
            observer.onOutputLog(loggerName, LogLevel.DEBUG, message, error, options);
        }
    }

    @Override
    public boolean isTraceEnabled() {
        return delegate.isTraceEnabled();
    }

    @Override
    public void logTrace(String message, Object... options) {
        delegate.logTrace(message, options);
        if (isTraceEnabled()) {
            observer.onOutputLog(loggerName, LogLevel.TRACE, message, options);
        }
    }

    @Override
    public void logTrace(String message, Throwable error, Object... options) {
        delegate.logTrace(message, error, options);
        if (isTraceEnabled()) {
            observer.onOutputLog(loggerName, LogLevel.TRACE, message, error, options);
        }
    }
}

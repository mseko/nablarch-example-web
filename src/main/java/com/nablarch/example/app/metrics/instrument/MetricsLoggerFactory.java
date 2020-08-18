package com.nablarch.example.app.metrics.instrument;

import io.micrometer.core.instrument.Counter;
import nablarch.core.log.Logger;
import nablarch.core.log.basic.BasicLoggerFactory;
import nablarch.core.util.annotation.Published;

public class MetricsLoggerFactory extends BasicLoggerFactory {
    private static Counter fatalCounter;
    private static Counter errorCounter;
    private static Counter warnCounter;
    private static Counter infoCounter;
    private static Counter debugCounter;
    private static Counter traceCounter;

    public static void setFatalCounter(Counter fatalCounter) {
        MetricsLoggerFactory.fatalCounter = fatalCounter;
    }

    public static void setErrorCounter(Counter errorCounter) {
        MetricsLoggerFactory.errorCounter = errorCounter;
    }

    public static void setWarnCounter(Counter warnCounter) {
        MetricsLoggerFactory.warnCounter = warnCounter;
    }

    public static void setInfoCounter(Counter infoCounter) {
        MetricsLoggerFactory.infoCounter = infoCounter;
    }

    public static void setDebugCounter(Counter debugCounter) {
        MetricsLoggerFactory.debugCounter = debugCounter;
    }

    public static void setTraceCounter(Counter traceCounter) {
        MetricsLoggerFactory.traceCounter = traceCounter;
    }

    @Override
    public Logger get(String name) {
        Logger logger = super.get(name);
        return new ProxyLogger(logger);
    }

    private static class ProxyLogger implements Logger {
        private final Logger delegate;

        private void count(Counter counter) {
            if (counter != null) {
                counter.increment();
            }
        }

        private ProxyLogger(Logger delegate) {
            this.delegate = delegate;
        }

        @Override
        public boolean isFatalEnabled() {
            return delegate.isFatalEnabled();
        }

        @Override
        public void logFatal(String message, Object... options) {
            delegate.logFatal(message, options);
            if (isFatalEnabled()) {
                count(fatalCounter);
            }
        }

        @Override
        public void logFatal(String message, Throwable error, Object... options) {
            delegate.logFatal(message, error, options);
            if (isFatalEnabled()) {
                count(fatalCounter);
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
                count(errorCounter);
            }
        }

        @Override
        public void logError(String message, Throwable error, Object... options) {
            delegate.logError(message, error, options);
            if (isErrorEnabled()) {
                count(errorCounter);
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
                count(warnCounter);
            }
        }

        @Override
        @Published
        public void logWarn(String message, Throwable error, Object... options) {
            delegate.logWarn(message, error, options);
            if (isWarnEnabled()) {
                count(warnCounter);
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
                count(infoCounter);
            }
        }

        @Override
        @Published
        public void logInfo(String message, Throwable error, Object... options) {
            delegate.logInfo(message, error, options);
            if (isInfoEnabled()) {
                count(infoCounter);
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
                count(debugCounter);
            }
        }

        @Override
        @Published
        public void logDebug(String message, Throwable error, Object... options) {
            delegate.logDebug(message, error, options);
            if (isDebugEnabled()) {
                count(debugCounter);
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
                count(traceCounter);
            }
        }

        @Override
        public void logTrace(String message, Throwable error, Object... options) {
            delegate.logTrace(message, error, options);
            if (isTraceEnabled()) {
                count(traceCounter);
            }
        }
    }
}

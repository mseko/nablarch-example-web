package com.nablarch.example.app.test.handler;

import nablarch.fw.ExecutionContext;
import nablarch.fw.Handler;
import nablarch.fw.web.HttpRequest;

public class TestCallbackHandler implements Handler<Object, Object> {
    @Override
    public Object handle(Object request, ExecutionContext context) {
        Runnable callback = context.getRequestScopedVar("test.callback");
        if (null != callback) {
            callback.run();
        }
        return context.handleNext(request);
    }
}

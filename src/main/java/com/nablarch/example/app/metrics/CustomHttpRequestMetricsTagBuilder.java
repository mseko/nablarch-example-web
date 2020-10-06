package com.nablarch.example.app.metrics;

import io.micrometer.core.instrument.Tag;
import nablarch.fw.ExecutionContext;
import nablarch.fw.web.HttpRequest;
import nablarch.integration.micrometer.instrument.handler.http.HttpRequestMetricsTagBuilder;

import java.util.Arrays;
import java.util.List;

public class CustomHttpRequestMetricsTagBuilder implements HttpRequestMetricsTagBuilder {
    @Override
    public List<Tag> build(HttpRequest request, ExecutionContext context, Throwable thrownThrowable) {
        return Arrays.asList(
            Tag.of("hello", "world")
        );
    }
}

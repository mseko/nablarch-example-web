package com.nablarch.example.app.metrics;

import io.micrometer.core.instrument.Tag;
import nablarch.fw.ExecutionContext;
import nablarch.fw.web.HttpRequest;
import nablarch.integration.micrometer.instrument.handler.HandlerMetricsMetaDataBuilder;

import java.util.Arrays;
import java.util.List;

public class CustomHttpRequestMetricsTagBuilder implements HandlerMetricsMetaDataBuilder<HttpRequest, Object> {

    @Override
    public String getMetricsName() {
        return "http.server.request";
    }

    @Override
    public String getMetricsDescription() {
        return "Custom HTTP Request Metrics";
    }

    @Override
    public List<Tag> buildTagList(HttpRequest param, ExecutionContext executionContext, Object o, Throwable thrownThrowable) {
        return Arrays.asList(
            Tag.of("hello", "world")
        );
    }
}

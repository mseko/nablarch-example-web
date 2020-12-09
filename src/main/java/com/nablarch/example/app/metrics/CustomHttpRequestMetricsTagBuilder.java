package com.nablarch.example.app.metrics;

import io.micrometer.core.instrument.Tag;
import nablarch.fw.ExecutionContext;
import nablarch.fw.web.HttpRequest;
import nablarch.integration.micrometer.instrument.MetricsMetaData;
import nablarch.integration.micrometer.instrument.handler.HandlerMetricsMetaDataBuilder;

import java.util.Arrays;

public class CustomHttpRequestMetricsTagBuilder implements HandlerMetricsMetaDataBuilder<HttpRequest, Object> {

    @Override
    public MetricsMetaData build(HttpRequest param, ExecutionContext executionContext, Object o, Throwable thrownThrowable) {
        return new MetricsMetaData("http.server.request", "Custom HTTP Request Metrics", Arrays.asList(
                Tag.of("hello", "world")
        ));
    }
}

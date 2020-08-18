package com.nablarch.example.app.metrics.instrument;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import nablarch.fw.ExecutionContext;
import nablarch.fw.web.HttpRequest;
import nablarch.fw.web.HttpRequestHandler;
import nablarch.fw.web.HttpResponse;
import nablarch.fw.web.servlet.ServletExecutionContext;

import java.time.Duration;

public class HttpRequestPercentileHandler implements HttpRequestHandler {
    private MeterRegistry meterRegistry;

    @Override
    public HttpResponse handle(HttpRequest httpRequest, ExecutionContext context) {
        Timer.Sample sample = Timer.start(meterRegistry);
        try {
            return context.handleNext(httpRequest);
        } finally {
            int statusCode = ((ServletExecutionContext) context).getServletResponse().getStatus();

            Timer timer = Timer.builder("http.request")
                    .publishPercentiles(0.5, 0.90)
                    .publishPercentileHistogram()
                    .serviceLevelObjectives(Duration.ofMillis(10))
                    .minimumExpectedValue(Duration.ofMillis(1))
                    .maximumExpectedValue(Duration.ofSeconds(10))
                    .tags("statusCode", String.valueOf(statusCode),
                            "requestPath", httpRequest.getRequestPath())
                    .register(meterRegistry);
            sample.stop(timer);
        }
    }

    public void setMeterRegistry(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }
}

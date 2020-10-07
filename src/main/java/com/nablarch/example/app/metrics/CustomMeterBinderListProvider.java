package com.nablarch.example.app.metrics;

import com.nablarch.example.app.metrics.instrument.logger.NablarchLoggerMetrics;
import io.micrometer.core.instrument.binder.MeterBinder;
import nablarch.integration.micrometer.DefaultMeterBinderListProvider;

import java.util.Arrays;
import java.util.List;

public class CustomMeterBinderListProvider extends DefaultMeterBinderListProvider {

    @Override
    protected List<MeterBinder> createMeterBinderList() {
        return Arrays.asList(
            new NablarchLoggerMetrics()
//            new TomcatMetrics(null, null)
        );
    }
}

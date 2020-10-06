package com.nablarch.example.app.metrics.instrument;

import nablarch.integration.micrometer.cloudwatch.CloudWatchAsyncClientProvider;
import software.amazon.awssdk.services.cloudwatch.CloudWatchAsyncClient;

public class CustomCloudWatchAsyncClientProvider implements CloudWatchAsyncClientProvider {
    @Override
    public CloudWatchAsyncClient provide() {
        return CloudWatchAsyncClient.builder().build();
    }
}

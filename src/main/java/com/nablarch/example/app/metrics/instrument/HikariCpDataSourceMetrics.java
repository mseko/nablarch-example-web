package com.nablarch.example.app.metrics.instrument;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariCpDataSourceMetrics implements MeterBinder {
    private HikariDataSource dataSource;

    @Override
    public void bindTo(MeterRegistry registry) {
        try (Connection con = dataSource.getConnection()) {
            // getConnection() が一度も呼ばれていないと getHikariPoolMXBean() が null を返すので、
            // getConnection() のコールだけを行っている
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        HikariPoolMXBean hikariPoolMXBean = dataSource.getHikariPoolMXBean();

        Gauge.builder("hikariPool.active", hikariPoolMXBean, HikariPoolMXBean::getActiveConnections)
                .register(registry);

        Gauge.builder("hikariPool.idel", hikariPoolMXBean, HikariPoolMXBean::getIdleConnections)
                .register(registry);

        Gauge.builder("hikariPool.total",  hikariPoolMXBean, HikariPoolMXBean::getTotalConnections)
                .register(registry);

        Gauge.builder("hikariPool.awaiting", hikariPoolMXBean, HikariPoolMXBean::getThreadsAwaitingConnection)
                .register(registry);
    }

    public void setDataSource(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }
}

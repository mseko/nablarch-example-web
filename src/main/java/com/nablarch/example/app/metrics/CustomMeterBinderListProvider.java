package com.nablarch.example.app.metrics;

import com.nablarch.example.app.metrics.instrument.logger.NablarchLoggerMetrics;
import io.micrometer.core.instrument.binder.MeterBinder;
import nablarch.core.log.basic.LogLevel;
import nablarch.core.repository.initialization.Initializable;
import nablarch.integration.micrometer.DefaultMeterBinderListProvider;
import nablarch.integration.micrometer.instrument.MetricsMetaData;
import nablarch.integration.micrometer.instrument.binder.jmx.JmxGaugeMetrics;
import nablarch.integration.micrometer.instrument.binder.jmx.MBeanAttributeCondition;
import nablarch.integration.micrometer.instrument.binder.logging.LogCountMetrics;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class CustomMeterBinderListProvider extends DefaultMeterBinderListProvider implements Initializable {

    private DataSource dataSource;

    @Override
    protected List<MeterBinder> createMeterBinderList() {
        return Arrays.asList(
            new LogCountMetrics(LogLevel.DEBUG),
            new JmxGaugeMetrics(
                new MetricsMetaData("db.pool.total", "Total DB pool count."),
                new MBeanAttributeCondition("com.zaxxer.hikari:type=Pool (HikariPool-1)", "TotalConnections")
            ),
            new JmxGaugeMetrics(
                new MetricsMetaData("db.pool.active", "Active DB pool count."),
                new MBeanAttributeCondition("com.zaxxer.hikari:type=Pool (HikariPool-1)", "ActiveConnections")
            ),
            new JmxGaugeMetrics(
                new MetricsMetaData("thread.count.current", "Current thread count."),
                // 組み込みTomcatを使う場合(waitt:run)は、ObjectNameが"Tomcat:type=..."になるので注意
                new MBeanAttributeCondition("Catalina:type=ThreadPool,name=\"http-nio-8080\"", "currentThreadCount")
            )
        );
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void initialize() {
        try (Connection con = dataSource.getConnection()) {
            // HikariCP の MBean 登録は初回コネクション接続時なので、先に接続しておかないと MBean が取れずに警告ログが出力されてしまう
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package com.nablarch.example.app.metrics.instrument.dao;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import nablarch.common.dao.DaoContext;

import javax.persistence.OptimisticLockException;
import java.util.List;

public class EntityCountMetricsDecoratorFactory implements DaoContextDecoratorFactory {
    private MeterRegistry meterRegistry;

    @Override
    public DaoContext decorate(DaoContext delegate) {
        return new DaoContextDecoratorSupport(delegate) {

            @Override
            public <T> int update(T entity) throws OptimisticLockException {
                int count = super.update(entity);
                Counter counter = meterRegistry.counter("entity.update", "name", entity.getClass().getCanonicalName());
                counter.increment();
                return count;
            }

            @Override
            public <T> void batchUpdate(List<T> entities) {
                super.batchUpdate(entities);
                if (!entities.isEmpty()) {
                    Counter counter = meterRegistry.counter("entity.update", "name", entities.get(0).getClass().getCanonicalName());
                    counter.increment(entities.size());
                }
            }

            @Override
            public <T> void insert(T entity) {
                super.insert(entity);
                Counter counter = meterRegistry.counter("entity.insert", "name", entity.getClass().getCanonicalName());
                counter.increment();
            }

            @Override
            public <T> void batchInsert(List<T> entities) {
                super.batchInsert(entities);
                if (!entities.isEmpty()) {
                    Counter counter = meterRegistry.counter("entity.insert", "name", entities.get(0).getClass().getCanonicalName());
                    counter.increment(entities.size());
                }
            }

            @Override
            public <T> int delete(T entity) {
                int count = super.delete(entity);
                Counter counter = meterRegistry.counter("entity.delete", "name", entity.getClass().getCanonicalName());
                counter.increment();
                return count;
            }

            @Override
            public <T> void batchDelete(List<T> entities) {
                super.batchDelete(entities);
                if (!entities.isEmpty()) {
                    Counter counter = meterRegistry.counter("entity.delete", "name", entities.get(0).getClass().getCanonicalName());
                    counter.increment(entities.size());
                }
            }
        };
    }

    public void setMeterRegistry(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }
}

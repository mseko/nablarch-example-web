package com.nablarch.example.app.metrics.instrument.dao;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import nablarch.common.dao.DaoContext;
import nablarch.common.dao.EntityList;
import nablarch.core.util.annotation.Published;

import javax.persistence.OptimisticLockException;
import java.util.List;

public class SqlTimeMetricsDecoratorFactory implements DaoContextDecoratorFactory {
    private MeterRegistry meterRegistry;

    @Override
    public DaoContext decorate(DaoContext delegate) {
        return new DaoContextDecoratorSupport(delegate) {

            @Override
            public <T> T findById(Class<T> entityClass, Object... id) {
                Timer timer = meterRegistry.timer("sql.time",
                        "entity", entityClass.getCanonicalName(),
                        "sql.id", "None",
                        "type", "select");
                return timer.record(() -> delegate.findById(entityClass, id));
            }

            @Override
            @Published
            public <T> EntityList<T> findAll(Class<T> entityClass) {
                Timer timer = meterRegistry.timer("sql.time",
                        "entity", entityClass.getCanonicalName(),
                        "sql.id", "None",
                        "type", "select");
                return timer.record(() -> delegate.findAll(entityClass));
            }

            @Override
            @Published
            public <T> EntityList<T> findAllBySqlFile(Class<T> entityClass, String sqlId, Object params) {
                Timer timer = meterRegistry.timer("sql.time",
                        "entity", entityClass.getCanonicalName(),
                        "type", "select",
                        "sql.id", sqlId);
                return timer.record(() -> delegate.findAllBySqlFile(entityClass, sqlId, params));
            }

            @Override
            @Published
            public <T> EntityList<T> findAllBySqlFile(Class<T> entityClass, String sqlId) {
                Timer timer = meterRegistry.timer("sql.time",
                        "entity", entityClass.getCanonicalName(),
                        "type", "select",
                        "sql.id", sqlId);
                return timer.record(() -> delegate.findAllBySqlFile(entityClass, sqlId));
            }

            @Override
            public <T> T findBySqlFile(Class<T> entityClass, String sqlId, Object params) {
                Timer timer = meterRegistry.timer("sql.time",
                        "entity", entityClass.getCanonicalName(),
                        "type", "select",
                        "sql.id", sqlId);
                return timer.record(() -> delegate.findBySqlFile(entityClass, sqlId, params));
            }

            @Override
            public <T> long countBySqlFile(Class<T> entityClass, String sqlId, Object params) {
                Timer timer = meterRegistry.timer("sql.time",
                        "entity", entityClass.getCanonicalName(),
                        "type", "select",
                        "sql.id", sqlId);
                return timer.record(() -> delegate.countBySqlFile(entityClass, sqlId, params));
            }

            @Override
            public <T> int update(T entity) throws OptimisticLockException {
                Timer timer = meterRegistry.timer("sql.time",
                        "entity", entity.getClass().getCanonicalName(),
                        "sql.id", "None",
                        "type", "update");
                return timer.record(() -> delegate.update(entity));
            }

            @Override
            public <T> void batchUpdate(List<T> entities) {
                if (entities.isEmpty()) {
                    delegate.batchUpdate(entities);
                    return;
                }

                Timer timer = meterRegistry.timer("sql.time",
                        "entity", entities.get(0).getClass().getCanonicalName(),
                        "sql.id", "None",
                        "type", "update");

                timer.record(() -> delegate.batchUpdate(entities));
            }

            @Override
            public <T> void insert(T entity) {
                Timer timer = meterRegistry.timer("sql.time",
                        "entity", entity.getClass().getCanonicalName(),
                        "sql.id", "None",
                        "type", "insert");

                timer.record(() -> delegate.insert(entity));
            }

            @Override
            public <T> void batchInsert(List<T> entities) {
                if (entities.isEmpty()) {
                    delegate.batchInsert(entities);
                    return;
                }
                Timer timer = meterRegistry.timer("sql.time",
                        "entity", entities.get(0).getClass().getCanonicalName(),
                        "sql.id", "None",
                        "type", "insert");

                timer.record(() -> delegate.batchInsert(entities));
            }

            @Override
            public <T> int delete(T entity) {
                Timer timer = meterRegistry.timer("sql.time",
                        "entity", entity.getClass().getCanonicalName(),
                        "sql.id", "None",
                        "type", "delete");
                return timer.record(() -> delegate.delete(entity));
            }

            @Override
            public <T> void batchDelete(List<T> entities) {
                if (entities.isEmpty()) {
                    delegate.batchInsert(entities);
                    return;
                }

                Timer timer = meterRegistry.timer("sql.time",
                        "entity", entities.get(0).getClass().getCanonicalName(),
                        "sql.id", "None",
                        "type", "delete");

                timer.record(() -> delegate.batchDelete(entities));
            }
        };
    }

    public void setMeterRegistry(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }
}

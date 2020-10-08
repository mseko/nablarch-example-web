package com.nablarch.example.app.metrics.instrument.dao;

import nablarch.common.dao.DaoContext;
import nablarch.common.dao.EntityList;
import nablarch.core.util.annotation.Published;

import javax.persistence.OptimisticLockException;
import java.util.List;

public class DaoContextDecoratorSupport implements DaoContext {
    private final DaoContext delegate;

    public DaoContextDecoratorSupport(DaoContext delegate) {
        this.delegate = delegate;
    }

    @Override
    public <T> T findById(Class<T> entityClass, Object... id) {
        return delegate.findById(entityClass, id);
    }

    @Override
    @Published
    public <T> EntityList<T> findAll(Class<T> entityClass) {
        return delegate.findAll(entityClass);
    }

    @Override
    @Published
    public <T> EntityList<T> findAllBySqlFile(Class<T> entityClass, String sqlId, Object params) {
        return delegate.findAllBySqlFile(entityClass, sqlId, params);
    }

    @Override
    @Published
    public <T> EntityList<T> findAllBySqlFile(Class<T> entityClass, String sqlId) {
        return delegate.findAllBySqlFile(entityClass, sqlId);
    }

    @Override
    public <T> T findBySqlFile(Class<T> entityClass, String sqlId, Object params) {
        return delegate.findBySqlFile(entityClass, sqlId, params);
    }

    @Override
    public <T> long countBySqlFile(Class<T> entityClass, String sqlId, Object params) {
        return delegate.countBySqlFile(entityClass, sqlId, params);
    }

    @Override
    public <T> int update(T entity) throws OptimisticLockException {
        return delegate.update(entity);
    }

    @Override
    public <T> void batchUpdate(List<T> entities) {
        delegate.batchUpdate(entities);
    }

    @Override
    public <T> void insert(T entity) {
        delegate.insert(entity);
    }

    @Override
    public <T> void batchInsert(List<T> entities) {
        delegate.batchInsert(entities);
    }

    @Override
    public <T> int delete(T entity) {
        return delegate.delete(entity);
    }

    @Override
    public <T> void batchDelete(List<T> entities) {
        delegate.batchDelete(entities);
    }

    @Override
    public DaoContext page(long page) {
        return delegate.page(page);
    }

    @Override
    public DaoContext per(long per) {
        return delegate.per(per);
    }

    @Override
    public DaoContext defer() {
        return delegate.defer();
    }
}

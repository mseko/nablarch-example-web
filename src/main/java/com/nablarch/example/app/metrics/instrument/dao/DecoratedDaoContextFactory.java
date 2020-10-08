package com.nablarch.example.app.metrics.instrument.dao;

import nablarch.common.dao.DaoContext;
import nablarch.common.dao.DaoContextFactory;

import java.util.ArrayList;
import java.util.List;

public class DecoratedDaoContextFactory extends DaoContextFactory {
    private DaoContextFactory delegate;
    private List<DaoContextDecoratorFactory> daoContextDecoratorFactoryList = new ArrayList<>();

    public void setDelegate(DaoContextFactory delegate) {
        this.delegate = delegate;
    }

    @Override
    public DaoContext create() {
        DaoContext daoContext = delegate.create();
        for (DaoContextDecoratorFactory factory : daoContextDecoratorFactoryList) {
            daoContext = factory.decorate(daoContext);
        }
        return daoContext;
    }

    public void setDaoContextDecoratorFactoryList(List<DaoContextDecoratorFactory> daoContextDecoratorFactoryList) {
        this.daoContextDecoratorFactoryList = daoContextDecoratorFactoryList;
    }
}

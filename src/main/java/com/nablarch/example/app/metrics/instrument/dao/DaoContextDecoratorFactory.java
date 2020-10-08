package com.nablarch.example.app.metrics.instrument.dao;

import nablarch.common.dao.DaoContext;

public interface DaoContextDecoratorFactory {

    DaoContext decorate(DaoContext delegate);
}

package com.dashidao.foundation.service;

import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.query.support.IQueryObject;
import com.dashidao.foundation.domain.Accessory;
import com.dashidao.foundation.domain.Infrastructure;

import java.util.List;
import java.util.Map;

public abstract interface IInfrastructureService {
    public abstract boolean save(Infrastructure paramAccessory);

    public abstract boolean delete(Long paramLong);

    public abstract boolean update(Infrastructure paramAccessory);

    public abstract IPageList list(IQueryObject paramIQueryObject);

    public abstract Infrastructure getObjById(Long paramLong);

    public abstract Infrastructure getObjByProperty(String paramString1, String paramString2);

    public abstract List<Infrastructure> query(String paramString, Map paramMap, int paramInt1, int paramInt2);
}





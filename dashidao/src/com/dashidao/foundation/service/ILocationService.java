package com.dashidao.foundation.service;

import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.query.support.IQueryObject;
import com.dashidao.foundation.domain.IntegralGoods;
import com.dashidao.foundation.domain.Location;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract interface ILocationService {
    public abstract boolean save(Location paramIntegralGoods);

    public abstract Location getObjById(Long paramLong);

    public abstract boolean delete(Long paramLong);

    public abstract boolean batchDelete(List<Serializable> paramList);

    public abstract IPageList list(IQueryObject paramIQueryObject);

    public abstract boolean update(Location paramIntegralGoods);

    public abstract List<Location> query(String paramString, Map paramMap, int paramInt1, int paramInt2);
}





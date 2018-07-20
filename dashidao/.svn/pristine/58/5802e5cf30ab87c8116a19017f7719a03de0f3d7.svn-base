package com.dashidao.foundation.service;

import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.query.support.IQueryObject;
import com.dashidao.foundation.domain.Store;
import com.dashidao.foundation.domain.StoreGoods;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract interface IStoreGoodsService {
    public abstract boolean save(StoreGoods paramStore);

    public abstract StoreGoods getObjById(Long paramLong);

    public abstract boolean delete(Long paramLong);

    public abstract boolean batchDelete(List<Serializable> paramList);

    public abstract IPageList list(IQueryObject paramIQueryObject);

    public abstract boolean update(StoreGoods paramStore);

    public abstract List<StoreGoods> query(String paramString, Map paramMap, int paramInt1, int paramInt2);

    public abstract StoreGoods getObjByProperty(String paramString, Object paramObject);
}





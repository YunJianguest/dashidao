package com.dashidao.foundation.service;

import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.query.support.IQueryObject;
import com.dashidao.foundation.domain.StackGoods;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract interface IStackGoodsService {
    public abstract boolean save(StackGoods paramStackGoods);

    public abstract StackGoods getObjById(Long paramLong);

    public abstract boolean delete(Long paramLong);

    public abstract boolean batchDelete(List<Serializable> paramList);

    public abstract IPageList list(IQueryObject paramIQueryObject);

    public abstract boolean update(StackGoods paramStackGoods);

    public abstract List<StackGoods> query(String paramString, Map paramMap, int paramInt1, int paramInt2);

    public abstract StackGoods getObjByProperty(String paramString, Object paramObject);
}





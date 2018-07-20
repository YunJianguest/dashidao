package com.dashidao.foundation.service;

import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.query.support.IQueryObject;
import com.dashidao.foundation.domain.OrderCom;
import com.dashidao.foundation.domain.Store;
import com.dashidao.foundation.domain.StoreGoods;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract interface IOrderComService {
    public abstract boolean save(OrderCom paramStore);

    public abstract OrderCom getObjById(Long paramLong);

    public abstract boolean delete(Long paramLong);

    public abstract boolean batchDelete(List<Serializable> paramList);

    public abstract IPageList list(IQueryObject paramIQueryObject);

    public abstract boolean update(OrderCom paramStore);

    public abstract List<OrderCom> query(String paramString, Map paramMap, int paramInt1, int paramInt2);

    public abstract OrderCom getObjByProperty(String paramString, Object paramObject);
}





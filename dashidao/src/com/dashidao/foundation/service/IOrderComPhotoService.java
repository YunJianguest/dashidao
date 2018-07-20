package com.dashidao.foundation.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.query.support.IQueryObject;
import com.dashidao.foundation.domain.OrderComPhoto;

public abstract interface  IOrderComPhotoService {
	public abstract boolean save(OrderComPhoto paramStore);

    public abstract OrderComPhoto getObjById(Long paramLong);

    public abstract boolean delete(Long paramLong);

    public abstract boolean batchDelete(List<Serializable> paramList);

    public abstract IPageList list(IQueryObject paramIQueryObject);

    public abstract boolean update(OrderComPhoto paramStore);

    public abstract List<OrderComPhoto> query(String paramString, Map paramMap, int paramInt1, int paramInt2);

    public abstract OrderComPhoto getObjByProperty(String paramString, Object paramObject);

}

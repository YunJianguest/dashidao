package com.dashidao.foundation.service;

import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.query.support.IQueryObject;
import com.dashidao.foundation.domain.Area;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract interface IAreaService {
    public abstract boolean save(Area paramArea);

    public abstract Area getObjById(Long paramLong);

    public abstract boolean delete(Long paramLong);

    public abstract boolean batchDelete(List<Serializable> paramList);

    public abstract IPageList list(IQueryObject paramIQueryObject);

    public abstract boolean update(Area paramArea);

    public abstract List<Area> query(String paramString, Map paramMap, int paramInt1, int paramInt2);
}





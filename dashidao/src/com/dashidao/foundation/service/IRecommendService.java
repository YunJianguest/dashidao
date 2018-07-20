package com.dashidao.foundation.service;

import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.query.support.IQueryObject;
import com.dashidao.foundation.domain.Recommend;

import java.util.List;
import java.util.Map;

public abstract interface IRecommendService {
    public abstract boolean save(Recommend paramRecommend);

    public abstract boolean delete(Long paramLong);

    public abstract boolean update(Recommend paramAccessory);

    public abstract IPageList list(IQueryObject paramIQueryObject);

    public abstract Recommend getObjById(Long paramLong);

    public abstract Recommend getObjByProperty(String paramString1, String paramString2);

    public abstract List<Recommend> query(String paramString, Map paramMap, int paramInt1, int paramInt2);
    
}





package com.dashidao.foundation.service;

import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.query.support.IQueryObject;
import com.dashidao.foundation.domain.GoodsClassStaple;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract interface IGoodsClassStapleService {
    public abstract boolean save(GoodsClassStaple paramGoodsClassStaple);

    public abstract GoodsClassStaple getObjById(Long paramLong);

    public abstract boolean delete(Long paramLong);

    public abstract boolean batchDelete(List<Serializable> paramList);

    public abstract IPageList list(IQueryObject paramIQueryObject);

    public abstract boolean update(GoodsClassStaple paramGoodsClassStaple);

    public abstract List<GoodsClassStaple> query(String paramString, Map paramMap, int paramInt1, int paramInt2);
}





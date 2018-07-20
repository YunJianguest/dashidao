package com.dashidao.foundation.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.query.support.IQueryObject;
import com.dashidao.foundation.domain.ChongZhiJiLu;

public abstract interface IChongZhiJiLuService {
	public abstract boolean save(ChongZhiJiLu chongZhiJiLu);
    public abstract ChongZhiJiLu getObjById(Long paramLong);

    public abstract boolean delete(Long paramLong);

    public abstract boolean batchDelete(List<Serializable> paramList);

    public abstract IPageList list(IQueryObject paramIQueryObject);

    public abstract boolean update(ChongZhiJiLu paramTransArea);

    public abstract List<ChongZhiJiLu> query(String paramString, Map paramMap, int paramInt1, int paramInt2);

}

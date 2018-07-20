package com.dashidao.foundation.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.query.support.IQueryObject;
import com.dashidao.foundation.domain.SuCaiPhoto;

public abstract interface ISuCaiPhotoService {
	public abstract boolean save(SuCaiPhoto suCaiPhoto);
    public abstract SuCaiPhoto getObjById(Long paramLong);

    public abstract boolean delete(Long paramLong);

    public abstract boolean batchDelete(List<Serializable> paramList);

    public abstract IPageList list(IQueryObject paramIQueryObject);

    public abstract boolean update(SuCaiPhoto paramTransArea);

    public abstract List<SuCaiPhoto> query(String paramString, Map paramMap, int paramInt1, int paramInt2);

}

package com.dashidao.foundation.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.query.support.IQueryObject;
import com.dashidao.foundation.domain.KeFuPhoto;

public abstract interface IKeFuPhotoService {
	public abstract boolean save(KeFuPhoto keFuPhoto);
    public abstract KeFuPhoto getObjById(Long paramLong);

    public abstract boolean delete(Long paramLong);

    public abstract boolean batchDelete(List<Serializable> paramList);

    public abstract IPageList list(IQueryObject paramIQueryObject);

    public abstract boolean update(KeFuPhoto paramTransArea);

    public abstract List<KeFuPhoto> query(String paramString, Map paramMap, int paramInt1, int paramInt2);

}

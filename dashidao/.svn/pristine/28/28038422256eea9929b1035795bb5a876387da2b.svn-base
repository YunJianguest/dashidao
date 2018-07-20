package com.dashidao.foundation.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.query.support.IQueryObject;
import com.dashidao.foundation.domain.TopPhoto;

public abstract interface ITopPhotoService {
	
	public abstract boolean save(TopPhoto topPhoto);
    public abstract TopPhoto getObjById(Long paramLong);

    public abstract boolean delete(Long paramLong);

    public abstract boolean batchDelete(List<Serializable> paramList);

    public abstract IPageList list(IQueryObject paramIQueryObject);

    public abstract boolean update(TopPhoto paramTransArea);

    public abstract List<TopPhoto> query(String paramString, Map paramMap, int paramInt1, int paramInt2);


}

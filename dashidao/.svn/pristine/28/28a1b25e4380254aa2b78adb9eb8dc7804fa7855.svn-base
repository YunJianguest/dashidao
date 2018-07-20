package com.dashidao.foundation.service;

import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.query.support.IQueryObject;
import com.dashidao.foundation.domain.Commission;
import com.dashidao.foundation.domain.ExpressCompany;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract interface ICommissionService {
    public abstract boolean save(Commission commission);

    public abstract Commission getObjById(Long paramLong);

    public abstract boolean delete(Long paramLong);

    public abstract boolean batchDelete(List<Serializable> paramList);

    public abstract IPageList list(IQueryObject paramIQueryObject);

    public abstract boolean update(Commission commission);

    public abstract List<Commission> query(String paramString, Map paramMap, int paramInt1, int paramInt2);
}





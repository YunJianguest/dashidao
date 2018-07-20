package com.dashidao.foundation.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.query.support.IQueryObject;
import com.dashidao.foundation.domain.ElectronicInvoice;


public abstract interface IElectronicInvoiceService {
	public abstract boolean save(ElectronicInvoice mm);
    public abstract ElectronicInvoice getObjById(Long paramLong);

    public abstract boolean delete(Long paramLong);

    public abstract boolean batchDelete(List<Serializable> paramList);

    public abstract IPageList list(IQueryObject paramIQueryObject);

    public abstract boolean update(ElectronicInvoice paramTransArea);

    public abstract List<ElectronicInvoice> query(String paramString, Map paramMap, int paramInt1, int paramInt2);
}

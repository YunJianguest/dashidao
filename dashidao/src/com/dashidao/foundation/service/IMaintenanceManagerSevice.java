package com.dashidao.foundation.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.query.support.IQueryObject;
import com.dashidao.foundation.domain.MaintenanceManager;

public abstract interface IMaintenanceManagerSevice {
	public abstract boolean save(MaintenanceManager mm);
    public abstract MaintenanceManager getObjById(Long paramLong);

    public abstract boolean delete(Long paramLong);

    public abstract boolean batchDelete(List<Serializable> paramList);

    public abstract IPageList list(IQueryObject paramIQueryObject);

    public abstract boolean update(MaintenanceManager paramTransArea);

    public abstract List<MaintenanceManager> query(String paramString, Map paramMap, int paramInt1, int paramInt2);
}

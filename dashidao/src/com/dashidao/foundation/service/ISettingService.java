package com.dashidao.foundation.service;

import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.query.support.IQueryObject;
import com.dashidao.foundation.domain.Setting;

import java.util.List;
import java.util.Map;

public abstract interface ISettingService {
    public abstract boolean save(Setting paramSetting);

    public abstract boolean delete(Long paramLong);

    public abstract boolean update(Setting paramSetting);

    public abstract IPageList list(IQueryObject paramIQueryObject);

    public abstract Setting getObjById(Long paramLong);

    public abstract Setting getObjByProperty(String paramString1, String paramString2);

    public abstract List<Setting> query(String paramString, Map paramMap, int paramInt1, int paramInt2);
}





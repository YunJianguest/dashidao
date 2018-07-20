package com.dashidao.foundation.service;

import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.query.support.IQueryObject;
import com.dashidao.foundation.domain.Role;

import java.util.List;
import java.util.Map;

public abstract interface IRoleService {
    public abstract boolean save(Role paramRole);

    public abstract boolean delete(Long paramLong);

    public abstract boolean update(Role paramRole);

    public abstract Role getObjById(Long paramLong);

    public abstract List<Role> query(String paramString, Map paramMap, int paramInt1, int paramInt2);

    public abstract IPageList list(IQueryObject paramIQueryObject);

    public abstract Role getObjByProperty(String paramString, Object paramObject);
}





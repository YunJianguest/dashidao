package com.dashidao.foundation.service;

import java.util.List;
import java.util.Map;

import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.query.support.IQueryObject;
import com.dashidao.foundation.domain.Certification;

public abstract interface ICertificationService {
	public abstract Certification getObjById(int paramLong);

	public abstract boolean save(Certification paramCertification);
	
	public List<Certification> query(String query, Map params, int begin, int max);
}

package com.dashidao.foundation.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dashidao.core.dao.IGenericDAO;
import com.dashidao.core.query.GenericPageList;
import com.dashidao.core.query.PageObject;
import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.query.support.IQueryObject;
import com.dashidao.foundation.domain.ElectronicInvoice;
import com.dashidao.foundation.domain.MaintenanceManager;
import com.dashidao.foundation.domain.WuLiuMuBan;
import com.dashidao.foundation.service.IElectronicInvoiceService;
@Service
@Transactional
public class ElectronicInvoiceServiceImpl implements IElectronicInvoiceService {
	@Resource(name = "electronicInvoiceDao")
    private IGenericDAO<ElectronicInvoice> electronicInvoice;
	@Override
	public boolean save(ElectronicInvoice mm) {
		try {
            this.electronicInvoice.save(mm);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
	}

	@Override
	public ElectronicInvoice getObjById(Long paramLong) {
		ElectronicInvoice transArea = (ElectronicInvoice)this.electronicInvoice.get(paramLong);
        if (transArea != null){
            return transArea;
        }

        return null;
	}

	@Override
	public boolean delete(Long paramLong) {
		try {
            this.electronicInvoice.remove(paramLong);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
	}

	@Override
	public boolean batchDelete(List<Serializable> paramList) {
		for (Serializable id : paramList){
            delete((Long)id);
        }

        return true;
	}

	@Override
	public IPageList list(IQueryObject properties) {
		if (properties == null){
            return null;
        }
        String query = properties.getQuery();
        Map params = properties.getParameters();
        GenericPageList pList = new GenericPageList(ElectronicInvoice.class, query,
                params, this.electronicInvoice);
        if (properties != null){
            PageObject pageObj = properties.getPageObj();
            if (pageObj != null)
                pList.doList(pageObj.getCurrentPage() == null ? 0 : pageObj
                             .getCurrentPage().intValue(), pageObj.getPageSize() == null ? 0 :
                             pageObj.getPageSize().intValue());
        }else{
            pList.doList(0, -1);
        }

        return pList;
	}

	@Override
	public boolean update(ElectronicInvoice paramTransArea) {
		try {
            this.electronicInvoice.update(paramTransArea);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
	}

	@Override
	public List<ElectronicInvoice> query(String query, Map params, int begin, int max) {
		return this.electronicInvoice.query(query, params, begin, max);
	}

}

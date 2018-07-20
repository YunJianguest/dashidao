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
import com.dashidao.foundation.domain.QingCangShangPin;
import com.dashidao.foundation.service.IQingCangShangPinServiceImpl;

@Service
@Transactional
public class QingCangShangPinServiceImpl implements IQingCangShangPinServiceImpl {

	@Resource(name = "qingCangShangPinDao")
    private IGenericDAO<QingCangShangPin> qingCangShangPinDao;
	
	@Override
	public boolean save(QingCangShangPin qingcanghuodong) {
		// TODO Auto-generated method stub
		 try {
	            this.qingCangShangPinDao.save(qingcanghuodong);
	            return true;
	        } catch (Exception e){
	            e.printStackTrace();
	        }

	        return false;
	}

	@Override
	public QingCangShangPin getObjById(Long id) {
		// TODO Auto-generated method stub
		QingCangShangPin transArea = (QingCangShangPin)this.qingCangShangPinDao.get(id);
        if (transArea != null){
            return transArea;
        }

        return null;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		try {
            this.qingCangShangPinDao.remove(id);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
	}

	@Override
	public boolean batchDelete(List<Serializable> paramList) {
		// TODO Auto-generated method stub
		for (Serializable id : paramList){
            delete((Long)id);
        }

        return true;
	}

	@Override
	public IPageList list(IQueryObject properties) {
		// TODO Auto-generated method stub
		if (properties == null){
            return null;
        }
        String query = properties.getQuery();
        Map params = properties.getParameters();
        GenericPageList pList = new GenericPageList(QingCangShangPin.class, query,
                params, this.qingCangShangPinDao);
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
	public boolean update(QingCangShangPin paramTransArea) {
		 try {
	            this.qingCangShangPinDao.update(paramTransArea);
	            return true;
	        } catch (Exception e){
	            e.printStackTrace();
	        }
	        return false;
	}

	@Override
	public List<QingCangShangPin> query(String paramString, Map paramMap, int paramInt1, int paramInt2) {
		// TODO Auto-generated method stub
		return this.qingCangShangPinDao.query(paramString, paramMap, paramInt1, paramInt2);
	}

}

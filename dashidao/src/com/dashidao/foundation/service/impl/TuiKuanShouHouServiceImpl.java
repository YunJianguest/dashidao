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
import com.dashidao.foundation.domain.TuiKuanShouHou;
import com.dashidao.foundation.service.ITuiKuanShouHouService;

@Service
@Transactional
public class TuiKuanShouHouServiceImpl  implements ITuiKuanShouHouService {
	
	 @Resource(name = "tuiKuanShouHouDao")
	    private IGenericDAO<TuiKuanShouHou> tuiKuanShouHouDao;

	@Override
	public boolean save(TuiKuanShouHou tuiKuanShouHou) {
		// TODO Auto-generated method stub
		 try {
	            this.tuiKuanShouHouDao.save(tuiKuanShouHou);
	            return true;
	        } catch (Exception e){
	            e.printStackTrace();
	        }
		return false;
	}

	@Override
	public TuiKuanShouHou getObjById(Long paramLong) {
		// TODO Auto-generated method stub
		TuiKuanShouHou transArea = (TuiKuanShouHou)this.tuiKuanShouHouDao.get(paramLong);
        if (transArea != null){
            return transArea;
        }
		return null;
	}

	@Override
	public boolean delete(Long paramLong) {
		// TODO Auto-generated method stub
		try {
            this.tuiKuanShouHouDao.remove(paramLong);
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
		return false;
	}

	@Override
	public IPageList list(IQueryObject properties) {
		// TODO Auto-generated method stub
		if (properties == null){
            return null;
        }
        String query = properties.getQuery();
        Map params = properties.getParameters();
        GenericPageList pList = new GenericPageList(TuiKuanShouHou.class, query,
                params, this.tuiKuanShouHouDao);
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
	public boolean update(TuiKuanShouHou paramTransArea) {
		// TODO Auto-generated method stub
		try {
            this.tuiKuanShouHouDao.update(paramTransArea);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public List<TuiKuanShouHou> query(String paramString, Map paramMap, int paramInt1, int paramInt2) {
		// TODO Auto-generated method stub
		 return this.tuiKuanShouHouDao.query(paramString, paramMap, paramInt1, paramInt2);
	}

}

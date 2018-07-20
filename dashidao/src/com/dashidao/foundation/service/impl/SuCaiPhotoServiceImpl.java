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
import com.dashidao.foundation.domain.SuCaiPhoto;
import com.dashidao.foundation.service.ISuCaiPhotoService;

@Service
@Transactional
public class SuCaiPhotoServiceImpl implements ISuCaiPhotoService {
	@Resource(name = "suCaiPhotoDao")
    private IGenericDAO<SuCaiPhoto> suCaiPhotoDao;

	@Override
	public boolean save(SuCaiPhoto suCai) {
		// TODO Auto-generated method stub
		try {
            this.suCaiPhotoDao.save(suCai);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

		return false;
	}

	@Override
	public SuCaiPhoto getObjById(Long id) {
		// TODO Auto-generated method stub
		SuCaiPhoto transArea = (SuCaiPhoto)this.suCaiPhotoDao.get(id);
        if (transArea != null){
            return transArea;
        }
		return null;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		 try {
	            this.suCaiPhotoDao.remove(id);
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
	        GenericPageList pList = new GenericPageList(SuCaiPhoto.class, query,
	                params, this.suCaiPhotoDao);
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
	public boolean update(SuCaiPhoto paramTransArea) {
		// TODO Auto-generated method stub
		 try {
	            this.suCaiPhotoDao.update(paramTransArea);
	            return true;
	        } catch (Exception e){
	            e.printStackTrace();
	        }

	        return false;
	}

	public List<SuCaiPhoto> query(String paramString, Map paramMap, int paramInt1, int paramInt2) {
		// TODO Auto-generated method stub
		 return this.suCaiPhotoDao.query(paramString, paramMap, paramInt1, paramInt2);
	}


}

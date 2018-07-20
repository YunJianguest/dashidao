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
import com.dashidao.foundation.domain.KeFuPhoto;
import com.dashidao.foundation.service.IKeFuPhotoService;

@Service
@Transactional
public class KeFuPhotoServiceImpl  implements IKeFuPhotoService {

	@Resource(name = "keFuPhotoDao")
    private IGenericDAO<KeFuPhoto> keFuPhotoDao;
	
	@Override
	public boolean save(KeFuPhoto keFuPhoto) {
		// TODO Auto-generated method stub
		 try {
	            this.keFuPhotoDao.save(keFuPhoto);
	            return true;
	        } catch (Exception e){
	            e.printStackTrace();
	        }
		return false;
	}

	@Override
	public KeFuPhoto getObjById(Long paramLong) {
		// TODO Auto-generated method stub
		KeFuPhoto transArea = (KeFuPhoto)this.keFuPhotoDao.get(paramLong);
        if (transArea != null){
            return transArea;
        }
		return null;
	}

	@Override
	public boolean delete(Long paramLong) {
		// TODO Auto-generated method stub
		try {
            this.keFuPhotoDao.remove(paramLong);
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
        GenericPageList pList = new GenericPageList(KeFuPhoto.class, query,
                params, this.keFuPhotoDao);
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
	public boolean update(KeFuPhoto paramTransArea) {
		// TODO Auto-generated method stub
		 try {
	            this.keFuPhotoDao.update(paramTransArea);
	            return true;
	        } catch (Exception e){
	            e.printStackTrace();
	        }

		return false;
	}

	@Override
	public List<KeFuPhoto> query(String paramString, Map paramMap, int paramInt1, int paramInt2) {
		// TODO Auto-generated method stub
		return this.keFuPhotoDao.query(paramString, paramMap, paramInt1, paramInt2);
	}

}

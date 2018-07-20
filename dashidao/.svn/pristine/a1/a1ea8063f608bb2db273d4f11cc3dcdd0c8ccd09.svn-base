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
import com.dashidao.foundation.domain.TopPhoto;
import com.dashidao.foundation.service.ITopPhotoService;

@Service
@Transactional
public class TopPhotoServiceImpl implements ITopPhotoService {
	
	@Resource(name = "topPhotoDao")
    private IGenericDAO<TopPhoto> topPhotoDao;

	@Override
	public boolean save(TopPhoto topPhoto) {
		// TODO Auto-generated method stub
		 try {
	            this.topPhotoDao.save(topPhoto);
	            return true;
	        } catch (Exception e){
	            e.printStackTrace();
	        }
	      return false;
	}

	@Override
	public TopPhoto getObjById(Long id) {
		TopPhoto transArea = (TopPhoto)this.topPhotoDao.get(id);
        if (transArea != null){
            return transArea;
        }

        return null;
	}

	@Override
	public boolean delete(Long paramLong) {
		// TODO Auto-generated method stub
		try {
            this.topPhotoDao.remove(paramLong);
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
        GenericPageList pList = new GenericPageList(TopPhoto.class, query,
                params, this.topPhotoDao);
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
	public boolean update(TopPhoto paramTransArea) {
		// TODO Auto-generated method stub
		 try {
	            this.topPhotoDao.update(paramTransArea);
	            return true;
	        } catch (Exception e){
	            e.printStackTrace();
	        }

		return false;
	}

	@Override
	public List<TopPhoto> query(String paramString, Map paramMap, int paramInt1, int max) {
		// TODO Auto-generated method stub
		return this.topPhotoDao.query(paramString, paramMap, paramInt1, max);
	}

}

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
import com.dashidao.foundation.domain.ChongZhiJiLu;
import com.dashidao.foundation.service.IChongZhiJiLuService;

@Service
@Transactional
public class ChongZhiJiLuServiceImpl implements IChongZhiJiLuService {

	@Resource(name = "chongZhiJiLuDao")
    private IGenericDAO<ChongZhiJiLu> chongZhiJiLuDao;
	
	@Override
	public boolean save(ChongZhiJiLu chongZhiJiLu) {
		// TODO Auto-generated method stub
		 try {
	            this.chongZhiJiLuDao.save(chongZhiJiLu);
	            return true;
	        } catch (Exception e){
	            e.printStackTrace();
	        }
		return false;
	}

	@Override
	public ChongZhiJiLu getObjById(Long id) {
		// TODO Auto-generated method stub
		ChongZhiJiLu transArea = (ChongZhiJiLu)this.chongZhiJiLuDao.get(id);
        if (transArea != null){
            return transArea;
        }
		return null;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		 try {
	            this.chongZhiJiLuDao.remove(id);
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
        GenericPageList pList = new GenericPageList(ChongZhiJiLu.class, query,
                params, this.chongZhiJiLuDao);
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
	public boolean update(ChongZhiJiLu paramTransArea) {
		// TODO Auto-generated method stub
		try {
            this.chongZhiJiLuDao.update(paramTransArea);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public List<ChongZhiJiLu> query(String paramString, Map paramMap, int paramInt1, int paramInt2) {
		// TODO Auto-generated method stub
		return this.chongZhiJiLuDao.query(paramString, paramMap, paramInt1, paramInt2);
	}

}

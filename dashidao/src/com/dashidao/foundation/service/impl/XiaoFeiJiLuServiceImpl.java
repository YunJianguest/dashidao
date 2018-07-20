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
import com.dashidao.foundation.domain.XiaoFeiJiLu;
import com.dashidao.foundation.service.IXiaoFeiJiLuService;


@Service
@Transactional
public class XiaoFeiJiLuServiceImpl implements IXiaoFeiJiLuService {
	
	@Resource(name = "xiaoFeiJiLuDao")
    private IGenericDAO<XiaoFeiJiLu> xiaoFeiJiLuDao;

	@Override
	public boolean save(XiaoFeiJiLu xiaoFeiJiLu) {
		// TODO Auto-generated method stub
		 try {
	            this.xiaoFeiJiLuDao.save(xiaoFeiJiLu);
	            return true;
	        } catch (Exception e){
	            e.printStackTrace();
	        }
		return false;
	}

	@Override
	public XiaoFeiJiLu getObjById(Long id) {
		// TODO Auto-generated method stub
		XiaoFeiJiLu transArea = (XiaoFeiJiLu)this.xiaoFeiJiLuDao.get(id);
        if (transArea != null){
            return transArea;
        }
		return null;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		  try {
	            this.xiaoFeiJiLuDao.remove(id);
	            return true;
	        } catch (Exception e){
	            e.printStackTrace();
	        }
		return false;
	}

	@Override
	public boolean batchDelete(List<Serializable> transAreaIds) {
		// TODO Auto-generated method stub
		for (Serializable id : transAreaIds){
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
        GenericPageList pList = new GenericPageList(XiaoFeiJiLu.class, query,
                params, this.xiaoFeiJiLuDao);
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
	public boolean update(XiaoFeiJiLu transArea) {
		// TODO Auto-generated method stub
		try {
            this.xiaoFeiJiLuDao.update(transArea);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

		return false;
	}

	@Override
	public List<XiaoFeiJiLu> query(String query, Map params, int begin, int max) {
		// TODO Auto-generated method stub
		return this.xiaoFeiJiLuDao.query(query, params, begin, max);
	}

}

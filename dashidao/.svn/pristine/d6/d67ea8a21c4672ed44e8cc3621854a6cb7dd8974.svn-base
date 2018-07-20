package com.dashidao.foundation.service.impl;

import com.dashidao.core.dao.IGenericDAO;
import com.dashidao.core.query.GenericPageList;
import com.dashidao.core.query.PageObject;
import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.query.support.IQueryObject;
import com.dashidao.foundation.domain.Area;
import com.dashidao.foundation.domain.Infrastructure;
import com.dashidao.foundation.service.IAreaService;
import com.dashidao.foundation.service.IInfrastructureService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InfrastructureServiceImpl
    implements IInfrastructureService {
    @Resource(name = "infrastructureDAO")
    private IGenericDAO<Infrastructure> infrastructureDao;

    public boolean save(Infrastructure area){
        try {
            this.infrastructureDao.save(area);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public Infrastructure getObjById(Long id){
    	Infrastructure area = (Infrastructure)this.infrastructureDao.get(id);
        if (area != null){
            return area;
        }

        return null;
    }

    public boolean delete(Long id){
        try {
            this.infrastructureDao.remove(id);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean batchDelete(List<Serializable> areaIds){
        for (Serializable id : areaIds){
            delete((Long)id);
        }

        return true;
    }

    public IPageList list(IQueryObject properties){
        if (properties == null){
            return null;
        }
        String query = properties.getQuery();
        Map params = properties.getParameters();
        GenericPageList pList = new GenericPageList(Area.class, query,
                params, this.infrastructureDao);
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

    public boolean update(Infrastructure area){
        try {
            this.infrastructureDao.update(area);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public List<Infrastructure> query(String query, Map params, int begin, int max){
        return this.infrastructureDao.query(query, params, begin, max);
    }

	@Override
	public Infrastructure getObjByProperty(String paramString1, String paramString2) {
		// TODO Auto-generated method stub
		return infrastructureDao.getBy(paramString1, paramString2);
	}
}





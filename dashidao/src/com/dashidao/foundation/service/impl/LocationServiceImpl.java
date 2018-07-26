package com.dashidao.foundation.service.impl;

import com.dashidao.core.dao.IGenericDAO;
import com.dashidao.core.query.GenericPageList;
import com.dashidao.core.query.PageObject;
import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.query.support.IQueryObject;
import com.dashidao.foundation.domain.Article;
import com.dashidao.foundation.domain.Location;
import com.dashidao.foundation.service.IArticleService;
import com.dashidao.foundation.service.ILocationService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class LocationServiceImpl
    implements ILocationService {
    @Resource(name = "locationDAO")
    private IGenericDAO<Location> locationDao;

    public boolean save(Location article){
        try {
            this.locationDao.save(article);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public Location getObjById(Long id){
    	Location article = (Location) this.locationDao.get(id);
        if (article != null){
            return article;
        }

        return null;
    }

    public boolean delete(Long id){
        try {
            this.locationDao.remove(id);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean batchDelete(List<Serializable> articleIds){
        for (Serializable id : articleIds){
            delete((Long) id);
        }

        return true;
    }

    public IPageList list(IQueryObject properties){
        if (properties == null){
            return null;
        }
        String query = properties.getQuery();
        Map params = properties.getParameters();
        GenericPageList pList = new GenericPageList(Article.class, query,
                params, this.locationDao);
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

    public boolean update(Location article){
        try {
            this.locationDao.update(article);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public List<Location> query(String query, Map params, int begin, int max){
        return this.locationDao.query(query, params, begin, max);
    }

    public Location getObjByProperty(String propertyName, Object value){
    	Location obj;
        try {
            return (Location) this.locationDao.getBy(propertyName, value);
        } catch (Exception e){
            obj = new Location(); 
        }

        return obj;
    }
}





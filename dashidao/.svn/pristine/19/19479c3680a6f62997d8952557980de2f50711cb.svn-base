package com.dashidao.foundation.service.impl;

import com.dashidao.core.dao.IGenericDAO;
import com.dashidao.core.query.GenericPageList;
import com.dashidao.core.query.PageObject;
import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.query.support.IQueryObject;
import com.dashidao.foundation.domain.Recommend;
import com.dashidao.foundation.service.IRecommendService;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RecommendService
    implements IRecommendService {
    @Resource(name = "recommendDAO")
    private IGenericDAO<Recommend> recommendDAO;

    public boolean delete(Long id){
        try {
            this.recommendDAO.remove(id);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public Recommend getObjById(Long id){
        return (Recommend)this.recommendDAO.get(id);
    }

    public boolean save(Recommend acc){
        try {
            this.recommendDAO.save(acc);
            return true;
        } catch (Exception e){
        }

        return false;
    }

    public boolean update(Recommend acc){
        try {
            this.recommendDAO.update(acc);
            return true;
        } catch (Exception e){
        }

        return false;
    }

    public List<Recommend> query(String query, Map params, int begin, int max){
        return this.recommendDAO.query(query, params, begin, max);
    }

    public IPageList list(IQueryObject properties){
        if (properties == null){
            return null;
        }
        String query = properties.getQuery();
        Map params = properties.getParameters();
        GenericPageList pList = new GenericPageList(Recommend.class, query,
                params, this.recommendDAO);
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

    public Recommend getObjByProperty(String propertyName, String value){
        return (Recommend)this.recommendDAO.getBy(propertyName, value);
    }
}





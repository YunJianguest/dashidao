package com.dashidao.foundation.service.impl;

import com.dashidao.core.dao.IGenericDAO;
import com.dashidao.core.query.GenericPageList;
import com.dashidao.core.query.PageObject;
import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.query.support.IQueryObject;
import com.dashidao.foundation.domain.Commission;
import com.dashidao.foundation.service.ICommissionService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ICommissionServiceImpl
    implements ICommissionService {
    @Resource(name = "commissionDAO")
    private IGenericDAO<Commission> CommissionDao;

    public boolean save(Commission Commission){
        try {
            this.CommissionDao.save(Commission);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public Commission getObjById(Long id){
        Commission Commission = (Commission)this.CommissionDao.get(id);
        if (Commission != null){
            return Commission;
        }

        return null;
    }

    public boolean delete(Long id){
        try {
            this.CommissionDao.remove(id);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean batchDelete(List<Serializable> CommissionIds){
        for (Serializable id : CommissionIds){
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
        GenericPageList pList = new GenericPageList(Commission.class, query,
                params, this.CommissionDao);
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

    public boolean update(Commission Commission){
        try {
            this.CommissionDao.update(Commission);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public List<Commission> query(String query, Map params, int begin, int max){
        return this.CommissionDao.query(query, params, begin, max);
    }

    public Commission getObjByProperty(String propertyName, Object value){
        return (Commission)this.CommissionDao.getBy(propertyName, value);
    }
}





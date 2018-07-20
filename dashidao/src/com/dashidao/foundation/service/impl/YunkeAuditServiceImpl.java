package com.dashidao.foundation.service.impl;

import com.dashidao.core.dao.IGenericDAO;
import com.dashidao.core.query.GenericPageList;
import com.dashidao.core.query.PageObject;
import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.query.support.IQueryObject;
import com.dashidao.foundation.domain.YunkeAudit;
import com.dashidao.foundation.service.IYunkeAuditService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class YunkeAuditServiceImpl
    implements IYunkeAuditService{
    @Resource(name = "yunkeAuditDAO")
    private IGenericDAO<YunkeAudit> yunkeAuditDAO;

    public boolean save(YunkeAudit activityGoods){
        try {
            this.yunkeAuditDAO.save(activityGoods);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public YunkeAudit getObjById(Long id){
    	YunkeAudit activityGoods = (YunkeAudit)this.yunkeAuditDAO.get(id);
        if (activityGoods != null){
            return activityGoods;
        }

        return null;
    }

    public boolean delete(Long id){
        try {
            this.yunkeAuditDAO.remove(id);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean batchDelete(List<Serializable> activityGoodsIds){
        for (Serializable id : activityGoodsIds){
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
        GenericPageList pList = new GenericPageList(YunkeAudit.class, query,
                params, this.yunkeAuditDAO);
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

    public boolean update(YunkeAudit activityGoods){
        try {
            this.yunkeAuditDAO.update(activityGoods);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public List<YunkeAudit> query(String query, Map params, int begin, int max){
        return this.yunkeAuditDAO.query(query, params, begin, max);
    }
}





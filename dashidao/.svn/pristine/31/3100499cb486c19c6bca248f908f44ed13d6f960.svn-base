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
import com.dashidao.foundation.domain.Evaluate;
import com.dashidao.foundation.domain.Goods;
import com.dashidao.foundation.domain.OrderCom;
import com.dashidao.foundation.service.IEvaluateService;

@Service
@Transactional
public class EvaluateServiceImpl
    implements IEvaluateService {
    @Resource(name = "evaluateDAO")
    private IGenericDAO<OrderCom> evaluateDao;
    
    private IGenericDAO<Evaluate> evaluateDao1;

    public boolean save(Evaluate evaluate){
        try {
            this.evaluateDao1.save(evaluate);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public OrderCom getObjById(Long id){
        OrderCom evaluate = (OrderCom)this.evaluateDao.get(id);
        if (evaluate != null){
            return evaluate;
        }

        return null;
    }

    public boolean delete(Long id){
        try {
            this.evaluateDao.remove(id);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean batchDelete(List<Serializable> evaluateIds){
        for (Serializable id : evaluateIds){
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
        GenericPageList pList = new GenericPageList(OrderCom.class, query,
                params, this.evaluateDao);
        if (properties != null){
            PageObject pageObj = properties.getPageObj();
            if (pageObj != null)
                pList.doList(
                    pageObj.getCurrentPage() == null ? 0 : pageObj
                    .getCurrentPage().intValue(),
                    pageObj.getPageSize() == null ? 0 : pageObj
                    .getPageSize().intValue());
        }else{
            pList.doList(0, -1);
        }

        return pList;
    }

    public boolean update(OrderCom evaluate){
        try {
            this.evaluateDao.update(evaluate);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public List<Evaluate> query(String query, Map params, int begin, int max){
        return this.evaluateDao.query(query, params, begin, max);
    }

    public List<Goods> query_goods(String query, Map params, int begin, int max){
        return this.evaluateDao.query(query, params, begin, max);
    }
}





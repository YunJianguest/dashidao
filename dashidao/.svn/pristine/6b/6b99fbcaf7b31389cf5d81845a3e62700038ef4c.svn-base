package com.dashidao.foundation.service.impl;

import com.dashidao.core.dao.IGenericDAO;
import com.dashidao.core.query.GenericPageList;
import com.dashidao.core.query.PageObject;
import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.query.support.IQueryObject;
import com.dashidao.foundation.domain.StackGoods;
import com.dashidao.foundation.service.IStackGoodsService;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StackGoodsServiceImpl
    implements IStackGoodsService {
    @Resource(name = "stackGoodsDAO")
    private IGenericDAO<StackGoods> stackGoodsDao;

    public boolean save(StackGoods StackGoods){
        try {
            this.stackGoodsDao.save(StackGoods);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public StackGoods getObjById(Long id){
    	StackGoods StackGoods = (StackGoods)this.stackGoodsDao.get(id);
        if (StackGoods != null){
            return StackGoods;
        }

        return null;
    }

    public boolean delete(Long id){
        try {
            this.stackGoodsDao.remove(id);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean batchDelete(List<Serializable> StackGoodsIds){
        for (Serializable id : StackGoodsIds){
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
        GenericPageList pList = new GenericPageList(StackGoods.class, query,
                params, this.stackGoodsDao);
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

    public boolean update(StackGoods StackGoods){
        try {
            this.stackGoodsDao.update(StackGoods);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public List<StackGoods> query(String query, Map params, int begin, int max){
        return this.stackGoodsDao.query(query, params, begin, max);
    }

    public StackGoods getObjByProperty(String propertyName, Object value){
        return (StackGoods)this.stackGoodsDao.getBy(propertyName, value);
    }

	
}





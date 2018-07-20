package com.dashidao.foundation.service.impl;

import com.dashidao.core.dao.IGenericDAO;
import com.dashidao.core.query.GenericPageList;
import com.dashidao.core.query.PageObject;
import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.query.support.IQueryObject;
import com.dashidao.foundation.domain.OrderComPhoto;
import com.dashidao.foundation.service.IOrderComPhotoService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderComPhotoServiceImpl
    implements IOrderComPhotoService {
    @Resource(name = "orderComPhotoDAO")
    private IGenericDAO<OrderComPhoto> ordercomPhotoDAO;

    public boolean save(OrderComPhoto transArea){
        try {
            this.ordercomPhotoDAO.save(transArea);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public OrderComPhoto getObjById(Long id){
    	OrderComPhoto transArea = (OrderComPhoto)this.ordercomPhotoDAO.get(id);
        if (transArea != null){
            return transArea;
        }

        return null;
    }

    public boolean delete(Long id){
        try {
            this.ordercomPhotoDAO.remove(id);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean batchDelete(List<Serializable> transAreaIds){
        for (Serializable id : transAreaIds){
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
        GenericPageList pList = new GenericPageList(OrderComPhoto.class, query,
                params, this.ordercomPhotoDAO);
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

    public boolean update(OrderComPhoto transArea){
        try {
            this.ordercomPhotoDAO.update(transArea);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public List<OrderComPhoto> query(String query, Map params, int begin, int max){
        return this.ordercomPhotoDAO.query(query, params, begin, max);
    }

	@Override
	public OrderComPhoto getObjByProperty(String paramString, Object paramObject) {
		// TODO Auto-generated method stub
		return (OrderComPhoto)this.ordercomPhotoDAO.getBy(paramString, paramObject);
	}
}





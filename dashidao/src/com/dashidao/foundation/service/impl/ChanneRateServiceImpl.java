package com.dashidao.foundation.service.impl;

import com.dashidao.core.dao.IGenericDAO;
import com.dashidao.core.query.GenericPageList;
import com.dashidao.core.query.PageObject;
import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.query.support.IQueryObject;
import com.dashidao.foundation.domain.Activity;
import com.dashidao.foundation.domain.ChannelRate;
import com.dashidao.foundation.service.IActivityService;
import com.dashidao.foundation.service.IChannelRateService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChanneRateServiceImpl
    implements IChannelRateService {
    @Resource(name = "channelrateDAO")
    private IGenericDAO<ChannelRate> channelrateDAO;

    public boolean save(ChannelRate channelRate){
        try {
            this.channelrateDAO.save(channelRate);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public ChannelRate getObjById(Long id){
        ChannelRate channelRate = (ChannelRate)this.channelrateDAO.get(id);
        if (channelRate != null){
            return channelRate;
        }

        return null;
    }

    public boolean delete(Long id){
        try {
            this.channelrateDAO.remove(id);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public boolean batchDelete(List<Serializable> activityIds){
        for (Serializable id : activityIds){
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
        GenericPageList pList = new GenericPageList(ChannelRate.class, query,
                params, this.channelrateDAO);
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

    public boolean update(ChannelRate channelRate){
        try {
            this.channelrateDAO.update(channelRate);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public List<ChannelRate> query(String query, Map params, int begin, int max){
        return this.channelrateDAO.query(query, params, begin, max);
    }
}





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
import com.dashidao.foundation.domain.WuLiuMuBan;
import com.dashidao.foundation.service.IWuLiuMuBanService;

@Service
@Transactional
public class WuLiuMuBanServiceImpl
    implements IWuLiuMuBanService {
    @Resource(name = "wuLiuMuBanDao")
    private IGenericDAO<WuLiuMuBan> wuLiuMuBanDao;

    public boolean save(WuLiuMuBan transArea){
        try {
            this.wuLiuMuBanDao.save(transArea);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public WuLiuMuBan getObjById(Long id){
    	WuLiuMuBan transArea = (WuLiuMuBan)this.wuLiuMuBanDao.get(id);
        if (transArea != null){
            return transArea;
        }

        return null;
    }

    public boolean delete(Long id){
        try {
            this.wuLiuMuBanDao.remove(id);
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
        GenericPageList pList = new GenericPageList(WuLiuMuBan.class, query,
                params, this.wuLiuMuBanDao);
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

    public boolean update(WuLiuMuBan transArea){
        try {
            this.wuLiuMuBanDao.update(transArea);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public List<WuLiuMuBan> query(String query, Map params, int begin, int max){
        return this.wuLiuMuBanDao.query(query, params, begin, max);
    }

}





package com.dashidao.foundation.service.impl;

import com.dashidao.core.dao.IGenericDAO;
import com.dashidao.core.query.GenericPageList;
import com.dashidao.core.query.PageObject;
import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.query.support.IQueryObject;
import com.dashidao.foundation.domain.Setting;
import com.dashidao.foundation.service.ISettingService;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SettingService
    implements ISettingService {
    @Resource(name = "settingDAO")
    private IGenericDAO<Setting> settingDAO;

    public boolean delete(Long id){
        try {
            this.settingDAO.remove(id);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public Setting getObjById(Long id){
        return (Setting)this.settingDAO.get(id);
    }

    public boolean save(Setting acc){
        try {
            this.settingDAO.save(acc);
            return true;
        } catch (Exception e){
        }

        return false;
    }

    public boolean update(Setting acc){
        try {
            this.settingDAO.update(acc);
            return true;
        } catch (Exception e){
        }

        return false;
    }

    public List<Setting> query(String query, Map params, int begin, int max){
        return this.settingDAO.query(query, params, begin, max);
    }

    public IPageList list(IQueryObject properties){
        if (properties == null){
            return null;
        }
        String query = properties.getQuery();
        Map params = properties.getParameters();
        GenericPageList pList = new GenericPageList(Setting.class, query,
                params, this.settingDAO);
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

    public Setting getObjByProperty(String propertyName, String value){
        return (Setting)this.settingDAO.getBy(propertyName, value);
    }
}





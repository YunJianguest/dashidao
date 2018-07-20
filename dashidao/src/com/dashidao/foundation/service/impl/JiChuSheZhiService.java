package com.dashidao.foundation.service.impl;

import com.dashidao.core.dao.IGenericDAO;
import com.dashidao.core.query.GenericPageList;
import com.dashidao.core.query.PageObject;
import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.query.support.IQueryObject;
import com.dashidao.foundation.domain.JiChuSheZhi;
import com.dashidao.foundation.service.IJiChuSheZhiService;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JiChuSheZhiService
    implements IJiChuSheZhiService {
    @Resource(name = "jiChuSheZhiDAO")
    private IGenericDAO<JiChuSheZhi> jiChuSheZhiDAO;

    public boolean delete(Long id){
        try {
            this.jiChuSheZhiDAO.remove(id);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public JiChuSheZhi getObjById(Long id){
        return (JiChuSheZhi)this.jiChuSheZhiDAO.get(id);
    }

    public boolean save(JiChuSheZhi acc){
        try {
            this.jiChuSheZhiDAO.save(acc);
            return true;
        } catch (Exception e){
        }

        return false;
    }

    public boolean update(JiChuSheZhi acc){
        try {
            this.jiChuSheZhiDAO.update(acc);
            return true;
        } catch (Exception e){
        }

        return false;
    }

    public List<JiChuSheZhi> query(String query, Map params, int begin, int max){
        return this.jiChuSheZhiDAO.query(query, params, begin, max);
    }

    public IPageList list(IQueryObject properties){
        if (properties == null){
            return null;
        }
        String query = properties.getQuery();
        Map params = properties.getParameters();
        GenericPageList pList = new GenericPageList(JiChuSheZhi.class, query,
                params, this.jiChuSheZhiDAO);
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

    public JiChuSheZhi getObjByProperty(String propertyName, String value){
        return (JiChuSheZhi)this.jiChuSheZhiDAO.getBy(propertyName, value);
    }
}





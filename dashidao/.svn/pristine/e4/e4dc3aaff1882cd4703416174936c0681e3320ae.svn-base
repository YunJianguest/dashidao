package com.dashidao.foundation.service.impl;

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
import com.dashidao.foundation.domain.Address;
import com.dashidao.foundation.domain.Area;
import com.dashidao.foundation.domain.Certification;
import com.dashidao.foundation.service.ICertificationService;

@Service
@Transactional
public class CertificationServiceImpl
    implements ICertificationService {
    @Resource(name = "certificationDAO")
    private IGenericDAO<Certification> certificationDao;

    public Certification getObjById(int user_id){
        Certification certification = (Certification)this.certificationDao.get(user_id);
        if (certification != null){
            return certification;
        }

        return null;
    }

    
    public boolean save(Certification certification){
        try {
            this.certificationDao.save(certification);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
    
    public List<Certification> query(String query, Map params, int begin, int max){
        return this.certificationDao.query(query, params, begin, max);
    }
}





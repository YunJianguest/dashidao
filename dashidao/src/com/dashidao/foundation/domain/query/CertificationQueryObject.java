package com.dashidao.foundation.domain.query;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.dashidao.core.query.QueryObject;

public class CertificationQueryObject extends QueryObject {
    public CertificationQueryObject(String currentPage, ModelAndView mv, String orderBy, String orderType){
        super(currentPage, mv, orderBy, orderType);
    }
    public CertificationQueryObject(String currentPage, Map map, String orderBy, String orderType){
        super(currentPage, map, orderBy, orderType);
    }

    public CertificationQueryObject(){
    }
}





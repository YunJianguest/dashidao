package com.dashidao.foundation.domain.query;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.dashidao.core.query.QueryObject;

public class LocationQueryObject extends QueryObject {
    public LocationQueryObject(String currentPage, ModelAndView mv, String orderBy, String orderType){
        super(currentPage, mv, orderBy, orderType);
    }
    public LocationQueryObject(String currentPage, Map map, String orderBy, String orderType){
        super(currentPage, map, orderBy, orderType);
    }

    public LocationQueryObject(){
    }
}





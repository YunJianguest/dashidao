package com.dashidao.foundation.domain.query;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.dashidao.core.query.QueryObject;

public class ActivityQueryObject extends QueryObject {
    public ActivityQueryObject(String currentPage, ModelAndView mv, String orderBy, String orderType){
        super(currentPage, mv, orderBy, orderType);
    }
    public ActivityQueryObject(String currentPage, Map map, String orderBy, String orderType){
        super(currentPage, map, orderBy, orderType);
    }

    public ActivityQueryObject(){
    }
    
    
}





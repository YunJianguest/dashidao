package com.dashidao.foundation.domain.query;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.dashidao.core.query.QueryObject;

public class StoreCartQueryObject extends QueryObject {
    public StoreCartQueryObject(String currentPage, ModelAndView mv, String orderBy, String orderType){
        super(currentPage, mv, orderBy, orderType);
    }
    public StoreCartQueryObject(String currentPage, Map obj, String orderBy, String orderType){
        super(currentPage, obj, orderBy, orderType);
    }

    public StoreCartQueryObject(){
    }
}





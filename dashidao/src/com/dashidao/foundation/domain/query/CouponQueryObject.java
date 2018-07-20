package com.dashidao.foundation.domain.query;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.dashidao.core.query.QueryObject;

public class CouponQueryObject extends QueryObject {
    public CouponQueryObject(String currentPage, ModelAndView mv, String orderBy, String orderType){
        super(currentPage, mv, orderBy, orderType);
    }
    public CouponQueryObject(String currentPage,Map params, String orderBy, String orderType){
        super(currentPage,params, orderBy, orderType);
    }

    public CouponQueryObject(){
    }
}





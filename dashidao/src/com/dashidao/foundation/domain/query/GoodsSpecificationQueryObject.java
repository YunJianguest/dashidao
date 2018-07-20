package com.dashidao.foundation.domain.query;

import org.springframework.web.servlet.ModelAndView;

import com.dashidao.core.query.QueryObject;

public class GoodsSpecificationQueryObject extends QueryObject {
    public GoodsSpecificationQueryObject(String currentPage, ModelAndView mv, String orderBy, String orderType){
        super(currentPage, mv, orderBy, orderType);
    }

    public GoodsSpecificationQueryObject(){
    }
}





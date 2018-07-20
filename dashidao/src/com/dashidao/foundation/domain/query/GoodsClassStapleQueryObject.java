package com.dashidao.foundation.domain.query;

import org.springframework.web.servlet.ModelAndView;

import com.dashidao.core.query.QueryObject;

public class GoodsClassStapleQueryObject extends QueryObject {
    public GoodsClassStapleQueryObject(String currentPage, ModelAndView mv, String orderBy, String orderType){
        super(currentPage, mv, orderBy, orderType);
    }

    public GoodsClassStapleQueryObject(){
    }
}





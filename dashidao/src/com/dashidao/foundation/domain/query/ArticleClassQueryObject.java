package com.dashidao.foundation.domain.query;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.dashidao.core.query.QueryObject;

public class ArticleClassQueryObject extends QueryObject {
    public ArticleClassQueryObject(String currentPage, ModelAndView mv, String orderBy, String orderType){
        super(currentPage, mv, orderBy, orderType);
    }
    
    public ArticleClassQueryObject(String currentPage, Map m, String orderBy, String orderType){
        super(currentPage, m, orderBy, orderType);
    }

    public ArticleClassQueryObject(){
    }
}





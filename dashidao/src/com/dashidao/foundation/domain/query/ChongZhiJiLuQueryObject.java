package com.dashidao.foundation.domain.query;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.dashidao.core.query.QueryObject;

public class ChongZhiJiLuQueryObject  extends QueryObject {
	public ChongZhiJiLuQueryObject(String currentPage, ModelAndView mv, String orderBy, String orderType){
        super(currentPage, mv, orderBy, orderType);
    }

    public ChongZhiJiLuQueryObject(){
    	
    }
    public ChongZhiJiLuQueryObject(String currentPage, Map map, String orderBy, String orderType){
        super(currentPage, map, orderBy, orderType);
    }

}

package com.dashidao.foundation.domain.query;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.dashidao.core.query.QueryObject;

public class QingCangShangPinQueryObject extends QueryObject {
	public QingCangShangPinQueryObject(String currentPage, ModelAndView mv, String orderBy, String orderType){
        super(currentPage, mv, orderBy, orderType);
    }

    public QingCangShangPinQueryObject(String currentPage, Map<String, Object> map, String orderBy, String orderType){
        super(currentPage, map, orderBy, orderType);
    }

    public QingCangShangPinQueryObject(){
    }
}

package com.dashidao.foundation.domain.query;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.dashidao.core.query.QueryObject;

public class TuiKuanShouHouQueryObject extends QueryObject {
	 public TuiKuanShouHouQueryObject(String currentPage, ModelAndView mv, String orderBy, String orderType){
	        super(currentPage, mv, orderBy, orderType);
	    }

	    public TuiKuanShouHouQueryObject(){
	    }
	    public TuiKuanShouHouQueryObject(String currentPage, Map map, String orderBy, String orderType){
	        super(currentPage, map, orderBy, orderType);
	    }

}

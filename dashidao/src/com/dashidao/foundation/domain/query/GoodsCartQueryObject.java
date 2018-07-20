package com.dashidao.foundation.domain.query;

import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import com.dashidao.core.query.QueryObject;

public class GoodsCartQueryObject extends QueryObject {
    public GoodsCartQueryObject(String currentPage, ModelAndView mv, String orderBy, String orderType){
        super(currentPage, mv, orderBy, orderType);
    }
    
    public GoodsCartQueryObject(String currentPage, Map m, String orderBy, String orderType){
        super(currentPage, m, orderBy, orderType);
    }

    public GoodsCartQueryObject(){
    }
}





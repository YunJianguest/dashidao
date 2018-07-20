package com.dashidao.manage.seller.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dashidao.core.domain.virtual.SysMap;
import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.security.support.SecurityUserHolder;
import com.dashidao.foundation.domain.Accessory;
import com.dashidao.foundation.domain.Goods;
import com.dashidao.foundation.domain.MaintenanceManager;
import com.dashidao.foundation.domain.OrderCom;
import com.dashidao.foundation.domain.OrderForm;
import com.dashidao.foundation.domain.Store;
import com.dashidao.foundation.domain.User;
import com.dashidao.foundation.domain.query.MaintenanceManagerQueryObject;
import com.dashidao.foundation.service.IMaintenanceManagerSevice;
import com.dashidao.foundation.service.ISysConfigService;
import com.dashidao.foundation.service.IUserConfigService;
import com.dashidao.jwt.filter.SignFilter;
import com.dashidao.jwt.util.ComplexOutPropertyPreFilter;
import com.dashidao.jwt.util.DateJsonValueProcessor;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

@Controller
public class MaintenanceManagerAction {
	@Autowired
    private ISysConfigService configService;

    @Autowired
    private IUserConfigService userConfigService;
    
    @Autowired
    private IMaintenanceManagerSevice maintenanceManagerSevice;
    
    @RequestMapping({ "/seller/sel_maintenance.htm" })
    @ResponseBody
    public void sel_maintenance(HttpServletRequest request, HttpServletResponse response,String currentPage) throws IOException{
    	
    	Map params = new HashMap();
    	/*MaintenanceManagerQueryObject eqo =new MaintenanceManagerQueryObject(currentPage, params,
               "addTime", "desc");
   	
    	IPageList pList = this.maintenanceManagerSevice.list(eqo);*/
    	List pList = this.maintenanceManagerSevice.query(
				"select obj from MaintenanceManager obj ",
				params, 0, 15);
    	JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
		
		// 需要过滤的类 + 属性
		Map<Class<?>, Set<String>> includeMap = new HashMap<Class<?>, Set<String>>();
		Set<String> set = new HashSet<String>();
		set.add("id");
		set.add("order");
		set.add("content");
		set.add("states");
		includeMap.put(MaintenanceManager.class, set);
		
		set = new HashSet<String>();
		set.add("id");
		set.add("totalPrice");
		set.add("store");
		includeMap.put(OrderForm.class, set);
		
		set = new HashSet<String>();
		set.add("id");
		set.add("goods_list");
		includeMap.put(Store.class, set);
		
		set = new HashSet<String>();
		set.add("id");
		set.add("goods_name");
		includeMap.put(Goods.class, set);
		
		jsonConfig.setJsonPropertyFilter(new ComplexOutPropertyPreFilter(includeMap));

		JSONArray json = JSONArray.fromObject(pList, jsonConfig);
		SignFilter.printNoCheck(request, response, json);
		System.out.println(json);
	}
    
}

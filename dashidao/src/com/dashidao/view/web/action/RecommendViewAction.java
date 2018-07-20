package com.dashidao.view.web.action;

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
import com.dashidao.foundation.domain.Accessory;
import com.dashidao.foundation.domain.Recommend;
import com.dashidao.foundation.domain.User;
import com.dashidao.foundation.domain.query.RecommendQueryObject;
import com.dashidao.foundation.service.IRecommendService;
import com.dashidao.foundation.service.IUserService;
import com.dashidao.jwt.filter.SignFilter;
import com.dashidao.jwt.util.ComplexOutPropertyPreFilter;
import com.dashidao.jwt.util.DateJsonValueProcessor;

import io.jsonwebtoken.Claims;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 推荐服务
 */
@Controller
public class RecommendViewAction {
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IRecommendService recommendService;
	

	  /**
	    * 推荐服务  及链接
	    * @param request
	    * @param response
	    * @param type 推荐类型
	    * @throws IOException
	    */
		@RequestMapping({ "/wap/recommend.htm" })
		@ResponseBody
		public void recommend(HttpServletRequest request, HttpServletResponse response,String recommend_name) throws IOException {
			String type = request.getParameter("type");
			Map<String,Object> params = new HashMap<>();
			params.put("state", 0);
			Claims claims = SignFilter.checkSign(request, response);
			if (claims != null) {
				String id = claims.getId();
				
				Recommend recommend = new Recommend();
				
				recommend.setAddTime(new Date());
				recommend.setRecommend_type(type);
				recommend.setRecommend_name(recommend_name);
				recommend.setUser(this.userService.getObjById(Long.parseLong(id)));
				recommend.setRecommend_state("0");
				this.recommendService.save(recommend);
				params.put("url", "../../.../*.htm?type=YUNKE&uid=0");
				params.put("state", 1);
			  }
				JSONArray json = JSONArray.fromObject(params);
				SignFilter.printNoCheck(request, response, params);
				System.out.println(json);
		}
		 /**
		    * 服务统计
		    * @param request
		    * @param response
		    * @param type 推荐类型
		    * @throws IOException
		    */
			@RequestMapping({ "/wap/totalrecommend.htm" })
			@ResponseBody
			public void totalrecommend(HttpServletRequest request, HttpServletResponse response) throws IOException {
				String type = request.getParameter("type");
				Map<String,Object> whereMap = new HashMap<>();
				whereMap.put("state", 0);
				Claims claims = SignFilter.checkSign(request, response);
				if (claims != null) {
					String id = claims.getId();
					Map params = new HashMap();
					 params.put("recommend_type", type);
					 params.put("user_id", Long.parseLong(id));
//					 params.put("user_id", Long.parseLong("1"));
				        List<Recommend> recommend_list = this.recommendService
				                                 .query(
				                                     "select obj.id from Recommend obj where obj.recommend_type =:recommend_type and obj.user.id=:user_id",
				                                     params, -1, -1);
				        
				        int count = recommend_list.size();
				        whereMap.put("count", count);
				        whereMap.put("state", 1);
				}
				JSONArray json = JSONArray.fromObject(whereMap);
				SignFilter.printNoCheck(request, response, whereMap);
				System.out.println(json);
			}
			
			
			/**推荐列表数据
			 * @param request
			 * @param response
			 * @param type 推荐类型  0 栈代  1 商家  2 云客
			 * @throws IOException 
			 */
			@RequestMapping({ "/wap/recommendList.htm" })
			@ResponseBody
			public void recommendList(HttpServletRequest request, HttpServletResponse response,String type) throws IOException 
			{
				Claims claims = SignFilter.checkSign(request, response);
				if (claims != null) 
				{
					String id = claims.getId();
					Map params = new HashMap();
					String currentPage="1";
					/*params.put("user_id", Long.parseLong(id));*/
					RecommendQueryObject obj = new RecommendQueryObject(currentPage, params, "recommend_type", "desc");
					obj.addQuery("obj.user.id", new SysMap("user_id", Long.parseLong(id)), "=");
					if(type!=null && !"".equals(type)) 
					{
						obj.addQuery("obj.recommend_type", new SysMap("recommend_type", type), "=");
					}
					IPageList pList = this.recommendService.list(obj);
					
					JsonConfig jsonConfig = new JsonConfig(); 
					jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
					// 需要过滤的类 + 属性
					Map<Class<?>, Set<String>> includeMap = new HashMap<Class<?>, Set<String>>();
					Set<String> set = new HashSet<String>();
					set.add("addTime");
					set.add("recommend_state");
					set.add("recommend_type");
					set.add("recommend_name");
					set.add("user");
					set.add("acc");
					includeMap.put(Recommend.class, set);
					
					set = new HashSet<String>();
					set.add("userName");
					includeMap.put(User.class, set);
					
					set = new HashSet<String>();
					set.add("name");
					set.add("path");
					includeMap.put(Accessory.class, set);
					
					jsonConfig.setJsonPropertyFilter(new ComplexOutPropertyPreFilter(includeMap));
					JSONArray json = JSONArray.fromObject(pList.getResult(), jsonConfig);
					SignFilter.print(request, response, json);
					System.out.println(json);
				}
			}
			
			
			
			
			
}

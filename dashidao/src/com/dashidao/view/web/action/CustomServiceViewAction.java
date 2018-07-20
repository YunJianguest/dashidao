package com.dashidao.view.web.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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
import com.dashidao.foundation.domain.Article;
import com.dashidao.foundation.domain.ArticleClass;
import com.dashidao.foundation.domain.KeFuPhoto;
import com.dashidao.foundation.domain.query.ArticleClassQueryObject;
import com.dashidao.foundation.domain.query.ArticleQueryObject;
import com.dashidao.foundation.domain.query.KeFuPhotoQueryObject;
import com.dashidao.foundation.service.IAccessoryService;
import com.dashidao.foundation.service.IArticleClassService;
import com.dashidao.foundation.service.IArticleService;
import com.dashidao.foundation.service.IKeFuPhotoService;
import com.dashidao.foundation.service.IUserService;
import com.dashidao.jwt.filter.SignFilter;
import com.dashidao.jwt.util.ComplexOutPropertyPreFilter;
import com.dashidao.jwt.util.DateJsonValueProcessor;

import io.jsonwebtoken.Claims;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * 客服中心
 * @author Administrator
 *
 */
@Controller
public class CustomServiceViewAction {
	
	@Autowired
    private IUserService userService;
	
	@Autowired
    private IKeFuPhotoService keFuPhotoService;
	
	@Autowired
    private IAccessoryService accessoryService;
	
	@Autowired
    private IArticleClassService articleClassService; 
	
	@Autowired
    private IArticleService articleService;
	
	 /**
     * 客服中心轮播图列表 
     * @param request
     * @param response
     * @param currentPage
     * @throws IOException
     */
    @RequestMapping({ "/wap/customService_list.htm" })
    @ResponseBody
    public void customService_list(HttpServletRequest request, HttpServletResponse response,String currentPage) throws IOException 
    {
    	Map params = new HashMap();
    	Claims claims = SignFilter.checkSign(request, response);
    	if (claims != null) {
			String id = claims.getId();
			KeFuPhotoQueryObject qo = new KeFuPhotoQueryObject(currentPage, params,"addTime", "desc");
			IPageList pList = this.keFuPhotoService.list(qo);
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("MM-dd"));
			// 需要过滤的类 + 属性
			Map<Class<?>, Set<String>> includeMap = new HashMap<Class<?>, Set<String>>();
			Set<String> set = new HashSet<String>();
			set.add("id");
			set.add("title");
			set.add("photourl");
			set.add("acc");
			includeMap.put(KeFuPhoto.class, set);
			
			set = new HashSet<String>();
			set.add("name");
			set.add("path");
			includeMap.put(Accessory.class, set);
			
			jsonConfig.setJsonPropertyFilter(new ComplexOutPropertyPreFilter(includeMap));
			JSONArray json = JSONArray.fromObject(pList.getResult(), jsonConfig);
			SignFilter.printNoCheck(request, response, json);
			System.out.println(json);
			
    	}
    }
    
    
    /**
     * 客服中心 常见问题列表
     * @param request
     * @param response
     * @param currentPage
     * @throws IOException 
     */
    @RequestMapping({ "/wap/fenLei_List.htm" })
    @ResponseBody
    public void fenLei_List(HttpServletRequest request, HttpServletResponse response,String currentPage) throws IOException 
    {
    	Map params = new HashMap();
    	Claims claims = SignFilter.checkSign(request, response);
    	if (claims != null) {
			String id = claims.getId();
			ArticleClassQueryObject qo = new ArticleClassQueryObject(currentPage, params,"sequence", "desc");
			IPageList pList = this.articleClassService.list(qo);
			
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("MM-dd"));
			// 需要过滤的类 + 属性
			Map<Class<?>, Set<String>> includeMap = new HashMap<Class<?>, Set<String>>();
			Set<String> set = new HashSet<String>();
			set.add("id");
			set.add("className");
			set.add("sequence");
			includeMap.put(ArticleClass.class, set);
			
			jsonConfig.setJsonPropertyFilter(new ComplexOutPropertyPreFilter(includeMap));
			JSONArray json = JSONArray.fromObject(pList.getResult(), jsonConfig);
			SignFilter.printNoCheck(request, response, json);
			System.out.println(json);
			
    	}
    }
    
    /**
     * 根据分类 查询文章列表
     * @param request
     * @param response
     * @param currentPage
     * @param fenLeiId
     * @throws IOException
     */
    @RequestMapping({ "/wap/articleByFenLeiId.htm" })
    @ResponseBody
    public void articleByFenLeiId(HttpServletRequest request, HttpServletResponse response,String currentPage,String fenLeiId,String artId) throws IOException 
    {
    	Map params = new HashMap();
    	Claims claims = SignFilter.checkSign(request, response);
    	if (claims != null) {
			String id = claims.getId();
			ArticleQueryObject qo = new ArticleQueryObject(currentPage, params,"sequence", "desc");
			if(fenLeiId!=null && !"".equals(fenLeiId)) {
				qo.addQuery("obj.articleClass.id", new SysMap("articleClassId",Long.parseLong(fenLeiId)), "=");
			}
			else if(artId!=null && !"".equals(artId)) 
			{
				qo.addQuery("obj.id", new SysMap("id",Long.parseLong(artId)), "=");
			}
			qo.addQuery("obj.display", new SysMap("display",true), "=");
			IPageList pList = this.articleService.list(qo);
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
			// 需要过滤的类 + 属性
			Map<Class<?>, Set<String>> includeMap = new HashMap<Class<?>, Set<String>>();
			Set<String> set = new HashSet<String>();
			set.add("id");
			set.add("title");
			set.add("addTime");
			set.add("url");
			set.add("mark");
			set.add("content");
			includeMap.put(Article.class, set);
			
			jsonConfig.setJsonPropertyFilter(new ComplexOutPropertyPreFilter(includeMap));
			JSONArray json = JSONArray.fromObject(pList.getResult(), jsonConfig);
			SignFilter.printNoCheck(request, response, json);
			System.out.println(json);
			
    	}
    }
    
    
    

}

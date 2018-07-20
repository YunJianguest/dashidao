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
import com.dashidao.foundation.domain.SuCai;
import com.dashidao.foundation.domain.SuCaiPhoto;
import com.dashidao.foundation.domain.TopPhoto;
import com.dashidao.foundation.domain.User;
import com.dashidao.foundation.domain.query.SuCaiQueryObject;
import com.dashidao.foundation.domain.query.TopPhotoQueryObject;
import com.dashidao.foundation.service.IAccessoryService;
import com.dashidao.foundation.service.ISuCaiPhotoService;
import com.dashidao.foundation.service.ISuCaiService;
import com.dashidao.foundation.service.ISysConfigService;
import com.dashidao.foundation.service.ITopPhotoService;
import com.dashidao.foundation.service.IUserConfigService;
import com.dashidao.foundation.service.IUserService;
import com.dashidao.jwt.filter.SignFilter;
import com.dashidao.jwt.util.ComplexOutPropertyPreFilter;
import com.dashidao.jwt.util.DateJsonValueProcessor;

import io.jsonwebtoken.Claims;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * 素材管理
 * @author Administrator
 *
 */
@Controller
public class MaterialViewAction {
	
	@Autowired
    private ISysConfigService configService;

    @Autowired
    private IUserConfigService userConfigService;
    
    @Autowired
    private ISuCaiService suCaiService;
    
    @Autowired
    private IUserService userService;
    
    @Autowired
    private ITopPhotoService topPhotoService;
    
    @Autowired
    private IAccessoryService accessoryService;
    
    @Autowired
    private ISuCaiPhotoService suCaiPhotoService;
    /**
     * 素材列表 
     * @param request
     * @param response
     * @param currentPage
     * @param type
     * @throws IOException
     */
    @RequestMapping({ "/wap/suCai_list.htm" })
    @ResponseBody
    public void suCai_list(HttpServletRequest request, HttpServletResponse response,String currentPage,String type) throws IOException 
    {
    	Map params = new HashMap();
    	Claims claims = SignFilter.checkSign(request, response);
    	if (claims != null) {
			String id = claims.getId();
			SuCaiQueryObject qo = new SuCaiQueryObject(currentPage, params,"addTime", "desc");
			if(Integer.parseInt(type) ==1) // 我的素材
			{
				qo.addQuery("obj.user.id", new SysMap("user_id",Long.parseLong(id)), "=");
//				qo.addQuery("obj.type", new SysMap("type",type), "=");
			}else if(Integer.parseInt(type) ==2)//我的收藏
			{
				qo.addQuery("obj.user.id", new SysMap("user_id",Long.parseLong(id)), "=");
				qo.addQuery("obj.type", new SysMap("type",type), "=");
			}
			else if(Integer.parseInt(type) ==3)//精选
			{
//				qo.addQuery("obj.user.id", new SysMap("user_id",Long.parseLong(id)), "=");
				qo.addQuery("obj.type", new SysMap("type",type), "=");
			}
			IPageList pList = this.suCaiService.list(qo);
			
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("MM-dd"));
			// 需要过滤的类 + 属性
			Map<Class<?>, Set<String>> includeMap = new HashMap<Class<?>, Set<String>>();
			Set<String> set = new HashSet<String>();
			set.add("id");
			set.add("content");
			set.add("addTime");
			set.add("user");
			set.add("type");
			set.add("photo");
			includeMap.put(SuCai.class, set);
			
			set = new HashSet<String>();
			set.add("id");
			set.add("userName");
			set.add("trueName");
			includeMap.put(User.class, set);
			
			set = new HashSet<String>();
			set.add("acc");
			includeMap.put(SuCaiPhoto.class, set);
			
			
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
     * 素材表处理
     * @param request
     * @param response
     * @param currentPage
     * @param suCaiid
     * @param type
     * @throws IOException
     */
    @RequestMapping({ "/wap/xiaZaiTuWen.htm" })
    @ResponseBody
    public void xiaZaiTuWen(HttpServletRequest request, HttpServletResponse response,String currentPage,String suCaiid,String type) throws IOException 
    {
    	Map params = new HashMap();
    	params.put("state", "0");
    	Claims claims = SignFilter.checkSign(request, response);
    	if (claims != null) {
			String id = claims.getId();
//			SuCaiQueryObject qo = new SuCaiQueryObject(currentPage, params,"addTime", "desc");
			SuCai sucai = this.suCaiService.getObjById(Long.parseLong(suCaiid));
			if(type!=null && !"".equals(type)) 
			{
				sucai.setType(type);
			}else {
				sucai.setUser(this.userService.getObjById(Long.parseLong(id)));
			}
			boolean istrue = this.suCaiService.update(sucai);
			if(istrue) 
			{
				params.put("state", 1);
			}
    	}
    	JSONArray json = JSONArray.fromObject(params);
		SignFilter.printNoCheck(request, response, params);
		System.out.println(json);
    }
    
    /**
     * 上传素材 保存
     * @param request
     * @param response
     * @param currentPage
     * @throws IOException
     */
    @RequestMapping({ "/wap/suCai_save.htm" })
    @ResponseBody
    public void suCai_save(HttpServletRequest request, HttpServletResponse response,String currentPage) throws IOException 
    {
    	Map params = new HashMap();
    	params.put("state", "0");
    	Claims claims = SignFilter.checkSign(request, response);
    	if (claims != null) {
			String id = claims.getId();
			//素材
			SuCai suCaiEntity = new SuCai();
			String content = request.getParameter("content");//内容
			suCaiEntity.setAddTime(new Date());
			suCaiEntity.setContent(content);
			suCaiEntity.setDeleteStatus(false);
			suCaiEntity.setUser(this.userService.getObjById(Long.parseLong(id)));
			
			boolean b = this.suCaiService.save(suCaiEntity);
			
			String up_picture = request.getParameter("up_picture");//图片
			if(up_picture!=null && !"".equals(up_picture)) 
			{
				String[] picArr = up_picture.split(",");
				for(String pic : picArr) 
				{
					Accessory acc = new Accessory();
					acc.setAddTime(new Date());
					acc.setDeleteStatus(false);
					acc.setName(pic);
					acc.setPath("upload/img");
					acc.setUser(this.userService.getObjById(Long.parseLong(id)));
					b = this.accessoryService.save(acc);
					if(b) 
					{
						SuCaiPhoto photoEntity = new SuCaiPhoto();
						photoEntity.setAcc(acc);
						photoEntity.setAddTime(new Date());
						photoEntity.setDeleteStatus(false);
						photoEntity.setSucai(suCaiEntity);
						b = this.suCaiPhotoService.save(photoEntity);
					}
					
				}
			}
			if(b) 
			{
				params.put("state", 1);
			}
    	}
    	
    	JSONArray json = JSONArray.fromObject(params);
		SignFilter.printNoCheck(request, response, params);
		System.out.println(json);
    }
    
    /**
     * 会员列表轮播图
     * @param request
     * @param response
     * @param currentPage
     * @throws IOException
     */
    @RequestMapping({ "/wap/huiYuanPhoto_list.htm" })
    @ResponseBody
    public void huiYuanPhoto_list(HttpServletRequest request, HttpServletResponse response,String currentPage) throws IOException 
    {
    	Map params = new HashMap();
    	Claims claims = SignFilter.checkSign(request, response);
    	if (claims != null) {
			String id = claims.getId();
			TopPhotoQueryObject qo = new TopPhotoQueryObject(currentPage, params,"addTime", "desc");
			IPageList pList = this.topPhotoService.list(qo);
			
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("MM-dd"));
			// 需要过滤的类 + 属性
			Map<Class<?>, Set<String>> includeMap = new HashMap<Class<?>, Set<String>>();
			Set<String> set = new HashSet<String>();
			set.add("id");
			set.add("title");
			set.add("addTime");
			set.add("top_url");
			set.add("acc");
			includeMap.put(TopPhoto.class, set);
			
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
    
    

}

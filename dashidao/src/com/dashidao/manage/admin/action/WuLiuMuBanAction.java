package com.dashidao.manage.admin.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dashidao.core.annotation.SecurityMapping;
import com.dashidao.core.mv.JModelAndView;
import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.tools.CommUtil;
import com.dashidao.core.tools.WebForm;
import com.dashidao.foundation.domain.Accessory;
import com.dashidao.foundation.domain.Advert;
import com.dashidao.foundation.domain.GoodsSpecification;
import com.dashidao.foundation.domain.KeFuPhoto;
import com.dashidao.foundation.domain.TopPhoto;
import com.dashidao.foundation.domain.WuLiuMuBan;
import com.dashidao.foundation.domain.query.KeFuPhotoQueryObject;
import com.dashidao.foundation.domain.query.TopPhotoQueryObject;
import com.dashidao.foundation.domain.query.WuLiuMuBanQueryObject;
import com.dashidao.foundation.service.IAccessoryService;
import com.dashidao.foundation.service.IKeFuPhotoService;
import com.dashidao.foundation.service.ISysConfigService;
import com.dashidao.foundation.service.ITopPhotoService;
import com.dashidao.foundation.service.IUserConfigService;
import com.dashidao.foundation.service.IWuLiuMuBanService;

/**物流模板设置
 * @author Administrator
 *
 */
@Controller
public class WuLiuMuBanAction {
	 @Autowired
	    private ISysConfigService configService;

	    @Autowired
	    private IUserConfigService userConfigService;
	    @Autowired
	    private IWuLiuMuBanService wuLiuMuBanService;
	    @Autowired
	    private ITopPhotoService topPhotoService;
	    
	    @Autowired
	    private IAccessoryService accessoryService;
	    
	    @Autowired
	    private IKeFuPhotoService keFuPhotoServic;
	
	    @SecurityMapping(display = false, rsequence = 0, title = "物流模板列表", value = "/admin/wuliumuban_list.htm*", rtype = "admin", rname = "运费区域", rcode = "admin_wuliumuban", rgroup = "设置")
	    @RequestMapping({"/admin/wuliumuban_list.htm"})
	    public ModelAndView wuliumuban_list(HttpServletRequest request, HttpServletResponse response, String currentPage, String id, String orderBy, String orderType){
	       ModelAndView mv = new JModelAndView("admin/blue/wuliumuban_list.html",
	                                            this.configService.getSysConfig(), this.userConfigService
	                                            .getUserConfig(), 0, request, response);
	        
	        String url = this.configService.getSysConfig().getAddress();
	        if ((url == null) || (url.equals(""))){
	            url = CommUtil.getURL(request);
	        }
	        WuLiuMuBanQueryObject qo = new WuLiuMuBanQueryObject(currentPage, mv,
	        		orderBy, orderType);
	       /* WebForm wf = new WebForm();
	        wf.toQueryPo(request, qo, WuLiuMuBan.class, mv);*/
	        IPageList pList = this.wuLiuMuBanService.list(qo);
	        CommUtil.saveIPageList2ModelAndView(
	            url + "/admin/wuliumuban_list.htm", "", "", pList, mv);
	        return mv;
	    }

	 
	 
	 @SecurityMapping(display = false, rsequence = 0, title = "物流模板列表", value = "/admin/wuliumuban_add.htm*", rtype = "admin", rname = "运费区域", rcode = "admin_wuliumuban", rgroup = "设置")
	    @RequestMapping({"/admin/wuliumuban_add.htm"})
	    public ModelAndView wuliumuban_add(HttpServletRequest request, HttpServletResponse response, String currentPage, String id, String orderBy, String orderType){
	        ModelAndView mv = new JModelAndView("admin/blue/wuliumuban_add.html",
	                                            this.configService.getSysConfig(), this.userConfigService
	                                            .getUserConfig(), 0, request, response);
	        return mv;
	    }
	    
	    
	    @SecurityMapping(display = false, rsequence = 0, title = "物流模板列表", value = "/admin/wuliumuban_save.htm*", rtype = "admin", rname = "运费区域", rcode = "admin_wuliumuban", rgroup = "设置")
	    @RequestMapping({"/admin/wuliumuban_save.htm"})
	    public ModelAndView wuliumuban_save(HttpServletRequest request, HttpServletResponse response, String currentPage,String list_url,String add_url, String id, String orderBy, String orderType){
	        WebForm wf = new WebForm();
	        WuLiuMuBan mubanEntity = null;
	        if(id.equals("")) 
	        {
	        	mubanEntity = wf.toPo(request, WuLiuMuBan.class);
	        	mubanEntity.setAddTime(new Date());
	        	mubanEntity.setStatus(0);
	        }else 
	        {
	        	WuLiuMuBan obj = this.wuLiuMuBanService
                        .getObjById(Long.valueOf(Long.parseLong(id)));
	        	mubanEntity = (WuLiuMuBan)wf.toPo(request, obj);
	        }
	        if (id.equals(""))
	        {
	        	this.wuLiuMuBanService.save(mubanEntity);
	        }else 
	        {
	        	this.wuLiuMuBanService.update(mubanEntity);
	        }
	        
	        ModelAndView mv = new JModelAndView("admin/blue/success.html",
                    this.configService.getSysConfig(), this.userConfigService
                    .getUserConfig(), 0, request, response);
			mv.addObject("list_url", list_url + "?currentPage=" + currentPage);
			mv.addObject("op_title", "保存运费模板设置成功");
			if (add_url != null){
			mv.addObject("add_url", add_url);
			}
	        
	        return mv;
	    }
	    
	    @SecurityMapping(display = false, rsequence = 0, title = "物流模板列表", value = "/admin/wuliumuban_edit.htm*", rtype = "admin", rname = "运费区域", rcode = "admin_wuliumuban", rgroup = "设置")
	    @RequestMapping({"/admin/wuliumuban_edit.htm"})
	    public ModelAndView wuliumuban_edit(HttpServletRequest request, HttpServletResponse response, String currentPage, String id, String orderBy, String orderType){
	        ModelAndView mv = new JModelAndView("admin/blue/wuliumuban_add.html",
	                                            this.configService.getSysConfig(), this.userConfigService
	                                            .getUserConfig(), 0, request, response);
	        
	        if ((id != null) && (!id.equals(""))){
	        	WuLiuMuBan mubanEntity = this.wuLiuMuBanService
                        .getObjById(Long.valueOf(Long.parseLong(id)));
				mv.addObject("obj", mubanEntity);
				mv.addObject("currentPage", currentPage);
				mv.addObject("edit", Boolean.valueOf(true));
	        	
	        }
	        return mv;
	      
	    }
	    
	    @SecurityMapping(display = false, rsequence = 0, title = "物流模板删除", value = "/admin/wuliumuban_del.htm*", rtype = "admin", rname = "运费区域", rcode = "admin_wuliumuban", rgroup = "设置")
	    @RequestMapping({"/admin/wuliumuban_del.htm"})
	    public String wuliumuban_del(HttpServletRequest request, HttpServletResponse response, String mulitId, String currentPage){
	        String[] ids = mulitId.split(",");
	        for (String id : ids){
	            if (!id.equals("")){
	            	WuLiuMuBan mubanEntity = this.wuLiuMuBanService.getObjById(
	                                    Long.valueOf(Long.parseLong(id)));
	                if (mubanEntity.getStatus() != 1){
//	                    CommUtil.del_acc(request, advert.getAd_acc());
	                    this.wuLiuMuBanService.delete(Long.valueOf(Long.parseLong(id)));
	                }
	            }
	        }

	        return "redirect:wuliumuban_list.htm?currentPage=" + currentPage;
	    }
	    
	    
	    
	    
	    @SecurityMapping(display = false, rsequence = 0, title = "消息列表", value = "/admin/xiaoxitongzhi_list.htm*", rtype = "admin", rname = "消息", rcode = "admin_xiaoxitongzhi", rgroup = "设置")
	    @RequestMapping({"/admin/xiaoxitongzhi_list.htm"})
	    public ModelAndView xiaoxitongzhi_list(HttpServletRequest request, HttpServletResponse response, String currentPage, String id, String orderBy, String orderType){
	       ModelAndView mv = new JModelAndView("admin/blue/xiaoxitongzhi_list.html",
	                                            this.configService.getSysConfig(), this.userConfigService
	                                            .getUserConfig(), 0, request, response);
	        
	        return mv;
	    }
	    
	    @SecurityMapping(display = false, rsequence = 0, title = "聊天页面", value = "/admin/liaotianpage.htm*", rtype = "admin", rname = "聊天", rcode = "admin_liaotianpage", rgroup = "设置")
	    @RequestMapping({"/admin/liaotianpage.htm"})
	    public ModelAndView tiaotianpage(HttpServletRequest request, HttpServletResponse response, String currentPage, String id, String orderBy, String orderType){
	       ModelAndView mv = new JModelAndView("admin/blue/liaotianpage.html",
	                                            this.configService.getSysConfig(), this.userConfigService
	                                            .getUserConfig(), 0, request, response);
	        
	        return mv;
	    }
	    
	    
	    
	    /**
	     * @param request
	     * @param response
	     * @param currentPage
	     * @param id
	     * @param orderBy
	     * @param orderType
	     * @return
	     */
	    @SecurityMapping(display = false, rsequence = 0, title = "顶部图片", value = "/admin/top_photo_list.htm*", rtype = "admin", rname = "顶部图片", rcode = "admin_top_photo", rgroup = "设置")
	    @RequestMapping({"/admin/top_photo_list.htm"})
	    public ModelAndView top_photo_list(HttpServletRequest request, HttpServletResponse response, String currentPage, String id, String orderBy, String orderType){
	       ModelAndView mv = new JModelAndView("admin/blue/top_photo_list.html",
	                                            this.configService.getSysConfig(), this.userConfigService
	                                            .getUserConfig(), 0, request, response);
	        
	        String url = this.configService.getSysConfig().getAddress();
	        if ((url == null) || (url.equals(""))){
	            url = CommUtil.getURL(request);
	        }
	        TopPhotoQueryObject qo = new TopPhotoQueryObject();
	       /* WebForm wf = new WebForm();
	        wf.toQueryPo(request, qo, WuLiuMuBan.class, mv);*/
	        IPageList pList = this.topPhotoService.list(qo);
	        CommUtil.saveIPageList2ModelAndView(
	            url + "/admin/top_photo_list.htm", "", "", pList, mv);
	        return mv;
	    }
	    
	    @SecurityMapping(display = false, rsequence = 0, title = "顶部图片列表", value = "/admin/top_photo_add.htm*", rtype = "admin", rname = "顶部图片", rcode = "admin_top_photo", rgroup = "设置")
	    @RequestMapping({"/admin/top_photo_add.htm"})
	    public ModelAndView top_photo_add(HttpServletRequest request, HttpServletResponse response, String currentPage, String id, String orderBy, String orderType){
	        ModelAndView mv = new JModelAndView("admin/blue/top_photo_add.html",
	                                            this.configService.getSysConfig(), this.userConfigService
	                                            .getUserConfig(), 0, request, response);
	        return mv;
	    }
	    
	    
	    @SecurityMapping(display = false, rsequence = 0, title = "顶部图片", value = "/admin/top_photo_save.htm*", rtype = "admin", rname = "顶部图片", rcode = "admin_top_photo", rgroup = "设置")
	    @RequestMapping({"/admin/top_photo_save.htm"})
	    public ModelAndView top_photo_save(HttpServletRequest request, HttpServletResponse response, String currentPage,String list_url,String add_url, String id, String orderBy, String orderType){
	        WebForm wf = new WebForm();
	        String title = request.getParameter("title");//标题
	        String top_url = request.getParameter("top_url");//链接
	        String up_picture = request.getParameter("up_picture");//图片名
	        String accid = request.getParameter("accid");//图片id
	        String path ="upload/img"; 
	        TopPhoto topPhotoEntity = null;
	        if(id.equals("")) 
	        {
	        	topPhotoEntity = wf.toPo(request, TopPhoto.class);
	        	topPhotoEntity.setAddTime(new Date());
	        	topPhotoEntity.setDeleteStatus(false);
	        	topPhotoEntity.setTitle(title);
	        	topPhotoEntity.setTop_url(top_url);
	        	if(up_picture!=null && !"".equals(up_picture)) 
	        	{
	        		Accessory acc= new Accessory();
		        	acc.setPath(path);
		        	acc.setName(up_picture);
		        	acc.setAddTime(new Date());
		        	acc.setDeleteStatus(false);
		        	this.accessoryService.save(acc);
		        	topPhotoEntity.setAcc(acc);
	        	}
	        }else 
	        {
	        	TopPhoto obj = this.topPhotoService
                        .getObjById(Long.valueOf(Long.parseLong(id)));
	        	topPhotoEntity = (TopPhoto)wf.toPo(request, obj);
	        	topPhotoEntity.setTitle(title);
	        	topPhotoEntity.setTop_url(top_url);
	        	if(up_picture!=null && !"".equals(up_picture)) 
	        	{
	        		Accessory acc= new Accessory();
	        		if(accid!=null && !"".equals(accid)) 
	        		{
	        			acc= this.accessoryService.getObjById(Long.parseLong(accid));
	        		}
	        		acc.setPath(path);
		        	acc.setName(up_picture);
		        	if(accid!=null && !"".equals(accid)) 
	        		{
		        		this.accessoryService.update(acc);
	        		}else 
	        		{
	        			acc.setAddTime(new Date());
			        	acc.setDeleteStatus(false);
	        			this.accessoryService.save(acc);
	        		}
		        	topPhotoEntity.setAcc(acc);
	        	}
	        }
	        if (id.equals(""))
	        {
	        	this.topPhotoService.save(topPhotoEntity);
	        }else 
	        {
	        	
	        	this.topPhotoService.update(topPhotoEntity);
	        }
	        
	        ModelAndView mv = new JModelAndView("admin/blue/success.html",
                    this.configService.getSysConfig(), this.userConfigService
                    .getUserConfig(), 0, request, response);
			mv.addObject("list_url", list_url + "?currentPage=" + currentPage);
			mv.addObject("op_title", "保存图片设置成功");
			if (add_url != null){
			mv.addObject("add_url", add_url);
			}
	        
	        return mv;
	    }
	    
	    @SecurityMapping(display = false, rsequence = 0, title = "顶部图片表", value = "/admin/top_photo_edit.htm*", rtype = "admin", rname = "运费区域", rcode = "admin_top_photo", rgroup = "设置")
	    @RequestMapping({"/admin/top_photo_edit.htm"})
	    public ModelAndView top_photo_edit(HttpServletRequest request, HttpServletResponse response, String currentPage, String id,String accid, String orderBy, String orderType){
	        ModelAndView mv = new JModelAndView("admin/blue/top_photo_add.html",
	                                            this.configService.getSysConfig(), this.userConfigService
	                                            .getUserConfig(), 0, request, response);
	        
	        if ((id != null) && (!id.equals(""))){
	        	TopPhoto entity = this.topPhotoService
                        .getObjById(Long.valueOf(Long.parseLong(id)));
				mv.addObject("obj", entity);
				mv.addObject("currentPage", currentPage);
				mv.addObject("edit", Boolean.valueOf(true));
	        	
	        }
	        return mv;
	      
	    }
	    
	    @SecurityMapping(display = false, rsequence = 0, title = "顶部图片删除", value = "/admin/top_photo_del.htm*", rtype = "admin", rname = "运费区域", rcode = "admin_top_photo", rgroup = "设置")
	    @RequestMapping({"/admin/top_photo_del.htm"})
	    public String top_photo_del(HttpServletRequest request, HttpServletResponse response, String mulitId,String accid, String currentPage){
	    	String[] ids = mulitId.split(",");
	        for (String id : ids){
	            if (!id.equals("")){
	            	TopPhoto entity = this.topPhotoService.getObjById(
	                                    Long.valueOf(Long.parseLong(id)));
	            	 this.topPhotoService.delete(Long.valueOf(Long.parseLong(id)));
	            }
	        }
	         if(accid!=null && !"".equals(accid)) 
	        {
	        	this.accessoryService.delete(Long.parseLong(accid));
	        }
	        return "redirect:top_photo_list.htm?currentPage=" + currentPage;
	    }
	    
	    
	    
	    
	    
	    /************客服中心轮播图设置 list add udpate del******************/
	    @SecurityMapping(display = false, rsequence = 0, title = "图片", value = "/admin/kefu_photo_list.htm*", rtype = "admin", rname = "图片", rcode = "admin_kefu_photo", rgroup = "设置")
	    @RequestMapping({"/admin/kefu_photo_list.htm"})
	    public ModelAndView kefu_photo_list(HttpServletRequest request, HttpServletResponse response, String currentPage, String id, String orderBy){
	       ModelAndView mv = new JModelAndView("admin/blue/kefu_photo_list.html",
	                                            this.configService.getSysConfig(), this.userConfigService
	                                            .getUserConfig(), 0, request, response);
	        
	        String url = this.configService.getSysConfig().getAddress();
	        if ((url == null) || (url.equals(""))){
	            url = CommUtil.getURL(request);
	        }
	        KeFuPhotoQueryObject qo = new KeFuPhotoQueryObject();
	        IPageList pList = this.keFuPhotoServic.list(qo);
	        CommUtil.saveIPageList2ModelAndView(
	            url + "/admin/kefu_photo_list.htm", "", "", pList, mv);
	        return mv;
	    }
	    
	    
	    @SecurityMapping(display = false, rsequence = 0, title = "图片列表", value = "/admin/kefu_photo_add.htm*", rtype = "admin", rname = "图片", rcode = "admin_kefu_photo", rgroup = "设置")
	    @RequestMapping({"/admin/kefu_photo_add.htm"})
	    public ModelAndView kefu_photo_add(HttpServletRequest request, HttpServletResponse response, String currentPage, String id, String orderBy, String orderType){
	        ModelAndView mv = new JModelAndView("admin/blue/kefu_photo_add.html",
	                                            this.configService.getSysConfig(), this.userConfigService
	                                            .getUserConfig(), 0, request, response);
	        return mv;
	    }
	    
	    
	    @SecurityMapping(display = false, rsequence = 0, title = "顶部图片", value = "/admin/kefu_photo_save.htm*", rtype = "admin", rname = "顶部图片", rcode = "admin_kefu_photo", rgroup = "设置")
	    @RequestMapping({"/admin/kefu_photo_save.htm"})
	    public ModelAndView kefu_photo_save(HttpServletRequest request, HttpServletResponse response, String currentPage,String list_url,String add_url, String id, String orderBy, String orderType){
	        WebForm wf = new WebForm();
	        String title = request.getParameter("title");//标题
	        String top_url = request.getParameter("photourl");//链接
	        String up_picture = request.getParameter("up_picture");//图片名
	        String accid = request.getParameter("accid");//图片id
	        String path ="upload/img"; 
	        KeFuPhoto kefuPhotoEntity =null;
	        if(id.equals("")) 
	        {
	        	kefuPhotoEntity = wf.toPo(request, KeFuPhoto.class);
	        	kefuPhotoEntity.setAddTime(new Date());
	        	kefuPhotoEntity.setDeleteStatus(false);
	        	kefuPhotoEntity.setTitle(title);
	        	kefuPhotoEntity.setPhotourl(top_url);
	        	if(up_picture!=null && !"".equals(up_picture)) 
	        	{
	        		Accessory acc= new Accessory();
		        	acc.setPath(path);
		        	acc.setName(up_picture);
		        	acc.setAddTime(new Date());
		        	acc.setDeleteStatus(false);
		        	this.accessoryService.save(acc);
		        	kefuPhotoEntity.setAcc(acc);
	        	}
	        }else 
	        {
	        	KeFuPhoto obj = this.keFuPhotoServic
                        .getObjById(Long.valueOf(Long.parseLong(id)));
	        	kefuPhotoEntity = (KeFuPhoto)wf.toPo(request, obj);
	        	kefuPhotoEntity.setTitle(title);
	        	kefuPhotoEntity.setPhotourl(top_url);
	        	
	        	
	        	if(up_picture!=null && !"".equals(up_picture)) 
	        	{
	        		Accessory acc= new Accessory();
	        		if(accid!=null && !"".equals(accid)) 
		        	{
		        		acc= this.accessoryService.getObjById(Long.parseLong(accid));
		        		acc.setPath(path);
			        	acc.setName(up_picture);
		        		this.accessoryService.update(acc);
		        	}else 
		        	{
		        		acc.setPath(path);
			        	acc.setName(up_picture);
		        		acc.setAddTime(new Date());
			        	acc.setDeleteStatus(false);
			        	this.accessoryService.save(acc);
		        	}
		        	kefuPhotoEntity.setAcc(acc);
	        	}
	        }
	        if (id.equals(""))
	        {
	        	this.keFuPhotoServic.save(kefuPhotoEntity);
	        }else 
	        {
	        	
	        	this.keFuPhotoServic.update(kefuPhotoEntity);
	        }
	        
	        ModelAndView mv = new JModelAndView("admin/blue/success.html",
                    this.configService.getSysConfig(), this.userConfigService
                    .getUserConfig(), 0, request, response);
			mv.addObject("list_url", list_url + "?currentPage=" + currentPage);
			mv.addObject("op_title", "保存图片设置成功");
			if (add_url != null){
			mv.addObject("add_url", add_url);
			}
	        
	        return mv;
	    }
	    
	    @SecurityMapping(display = false, rsequence = 0, title = "图片表", value = "/admin/kefu_photo_edit.htm*", rtype = "admin", rname = "图片", rcode = "admin_kefu_photo", rgroup = "设置")
	    @RequestMapping({"/admin/kefu_photo_edit.htm"})
	    public ModelAndView kefu_photo_edit(HttpServletRequest request, HttpServletResponse response, String currentPage, String id,String accid, String orderBy, String orderType){
	        ModelAndView mv = new JModelAndView("admin/blue/kefu_photo_add.html",
	                                            this.configService.getSysConfig(), this.userConfigService
	                                            .getUserConfig(), 0, request, response);
	        
	        if ((id != null) && (!id.equals(""))){
	        	KeFuPhoto entity = this.keFuPhotoServic
                        .getObjById(Long.valueOf(Long.parseLong(id)));
				mv.addObject("obj", entity);
				mv.addObject("currentPage", currentPage);
				mv.addObject("edit", Boolean.valueOf(true));
	        	
	        }
	        return mv;
	      
	    }
	    
	    @SecurityMapping(display = false, rsequence = 0, title = "图片删除", value = "/admin/kefu_photo_del.htm*", rtype = "admin", rname = "图片", rcode = "admin_kefu_photo", rgroup = "设置")
	    @RequestMapping({"/admin/kefu_photo_del.htm"})
	    public String kefu_photo_del(HttpServletRequest request, HttpServletResponse response, String mulitId,String accid, String currentPage){
	    	String[] ids = mulitId.split(",");
	        for (String id : ids){
	            if (!id.equals("")){
	            	KeFuPhoto entity = this.keFuPhotoServic.getObjById(
	                                    Long.valueOf(Long.parseLong(id)));
	            	 this.keFuPhotoServic.delete(Long.valueOf(Long.parseLong(id)));
	            }
	        }
	         if(accid!=null && !"".equals(accid)) 
	        {
	        	this.accessoryService.delete(Long.parseLong(accid));
	        }
	        return "redirect:kefu_photo_list.htm?currentPage=" + currentPage;
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
}

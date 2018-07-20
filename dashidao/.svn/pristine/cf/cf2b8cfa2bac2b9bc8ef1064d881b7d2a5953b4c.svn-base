package com.dashidao.manage.admin.action;

import com.dashidao.core.annotation.SecurityMapping;
import com.dashidao.core.domain.virtual.SysMap;
import com.dashidao.core.mv.JModelAndView;
import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.tools.CommUtil;
import com.dashidao.core.tools.WebForm;
import com.dashidao.foundation.domain.*;
import com.dashidao.foundation.domain.query.ChannelRateQueryObject;
import com.dashidao.foundation.domain.query.GoodsClassQueryObject;
import com.dashidao.foundation.service.*;
import com.dashidao.jwt.config.ResponseMsg;
import com.easyjf.beans.BeanUtils;
import com.easyjf.beans.BeanWrapper;
import com.sun.org.apache.bcel.internal.generic.NEW;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 分率设置控制器
 */
@Controller
public class ChannelRateManageAction {
    @Autowired
    private ISysConfigService configService;

    @Autowired
    private IUserConfigService userConfigService;

    @Autowired
    private IGoodsClassService goodsClassService;

    @Autowired
    private IGoodsTypeService goodsTypeService;

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IGoodsClassStapleService goodsClassStapleService;

    @Autowired
    private IAccessoryService accessoryService;
    @Autowired
    private IChannelRateService channelRateService;

    @SecurityMapping(display = false, rsequence = 0, title = "费率设置", value = "/admin/flsz_admin.htm*", rtype = "admin", rname = "费率设置", rcode = "flsz_admin", rgroup = "系统设置")
    @RequestMapping({"/admin/flsz_admin.htm"})
    public ModelAndView jcsz_admin(HttpServletRequest request, HttpServletResponse response, String id,String currentPage){
        
        ModelAndView mv = new JModelAndView("admin/blue/dodot/flsz_admin.html",
                this.configService.getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        ChannelRateQueryObject paramIQueryObject=new ChannelRateQueryObject(currentPage, mv, "addTime", "desc");
        paramIQueryObject.addQuery("obj.gc.id", new SysMap("gc_id", CommUtil.null2Long(id)),"=");
        IPageList pList= channelRateService.list(paramIQueryObject); 
        String url = this.configService.getSysConfig().getAddress();
        CommUtil.saveIPageList2ModelAndView(
                url + "/admin/flsz_admin.htm", "", "", pList, mv);

        mv.addObject("id",id);
        return mv;
    } 
    /**
     * 添加
     * @param request
     * @param response
     * @param id
     * @throws IOException 
     * @throws ParseException 
     */
    @RequestMapping({"/admin/flsz_save_admin.htm"})
    public void save(HttpServletRequest request, HttpServletResponse response, String gcid) throws IOException, ParseException{
    	 
    	String msString="添加失败！";
    	String starttime=request.getParameter("starttime");
    	String endtime=request.getParameter("endtime");
    	String fl_value=request.getParameter("fl_value");  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        if (StringUtils.isNotEmpty(endtime)&&StringUtils.isNotEmpty(starttime)&&StringUtils.isNotEmpty(fl_value)) { 
        	ChannelRate channelRate=new ChannelRate();
        	channelRate.setStartdate(sdf.parse(starttime));
        	channelRate.setEnddate(sdf.parse(endtime));
        	channelRate.setAddTime(new Date()); 
        	channelRate.setGc(goodsClassService.getObjById(Long.parseLong(gcid))); 
        	channelRate.setValue(Integer.parseInt(fl_value)); 
        	channelRateService.save(channelRate);
        	msString="添加成功！";
		} 
    	PrintWriter printWriter2 = response.getWriter();

		printWriter2.print(ResponseMsg.successWithData(msString));
		printWriter2.flush();
		printWriter2.close();
        
    }
    /**
     * 删除
     * @param request
     * @param response
     * @param className
     * @param id
     * @param pid
     * @throws IOException 
     */
    @RequestMapping({"/admin/flsz_del_admin.htm"})
    public void del(HttpServletRequest request, HttpServletResponse response,String id) throws IOException{
    	 
    	String msString="删除失败！";
    	channelRateService.delete(Long.parseLong(id));
    	msString="删除成功！";
    	PrintWriter printWriter2 = response.getWriter();

		printWriter2.print(ResponseMsg.successWithData(msString));
		printWriter2.flush();
		printWriter2.close();
    }
}





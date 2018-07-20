package com.dashidao.manage.admin.action;

import com.dashidao.core.annotation.SecurityMapping;
import com.dashidao.core.domain.virtual.SysMap;
import com.dashidao.core.mv.JModelAndView;
import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.security.SecurityManager;
import com.dashidao.core.security.support.SecurityUserHolder;
import com.dashidao.core.tools.CommUtil;
import com.dashidao.core.tools.Md5Encrypt;
import com.dashidao.core.tools.WebForm;
import com.dashidao.core.tools.database.DatabaseTools;
import com.dashidao.foundation.domain.Res;
import com.dashidao.foundation.domain.Role;
import com.dashidao.foundation.domain.RoleGroup;
import com.dashidao.foundation.domain.User;
import com.dashidao.foundation.domain.query.UserQueryObject;
import com.dashidao.foundation.service.*;
import com.dashidao.manage.buyer.action.BaseBuyerAction;
import com.dashidao.manage.seller.action.BaseSellerAction;
import com.dashidao.view.web.action.CartViewAction;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 平台管理控制器
 */
@Controller
public class PlatformManageAction implements ServletContextAware {
    private ServletContext servletContext;

    @Autowired
    private IUserService userService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private IUserConfigService userConfigService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IOrderFormService orderFormService;

    @Autowired
    private IRoleGroupService roleGroupService;

    @Autowired
    private DatabaseTools databaseTools;

    @Autowired
    SecurityManager securityManager;

    @Autowired
    private IResService resService; 
    @SecurityMapping(display = false, rsequence = 0, title = "平台活动审核", value = "/admin/pthdsh.htm*", rtype = "admin", rname = "平台活动审核", rcode = "platform_manage", rgroup = "审核")
    @RequestMapping({ "/admin/pthdsh.htm" })
    //平台活动审核
    public ModelAndView pthdsh(HttpServletRequest request, HttpServletResponse response,String id){
        ModelAndView mv = new JModelAndView("admin/blue/dodot/pthdsh.html", this.configService.getSysConfig(), this.userConfigService.getUserConfig(), 0, request, response);  
        mv.addObject("id",id);
        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "广告设置审核", value = "/admin/ggszsh.htm*", rtype = "admin", rname = "广告设置审核", rcode = "platform_manage", rgroup = "审核")
    @RequestMapping({ "/admin/ggszsh.htm" })
    //广告设置审核
    public ModelAndView ggsh(HttpServletRequest request, HttpServletResponse response,String id){
        ModelAndView mv = new JModelAndView("admin/blue/dodot/ggszsh.html", this.configService.getSysConfig(), this.userConfigService.getUserConfig(), 0, request, response);  
        mv.addObject("id",id);
        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "分配设置审核", value = "/admin/fpszsh.htm*", rtype = "admin", rname = "分配设置审核", rcode = "platform_manage", rgroup = "审核")
    @RequestMapping({ "/admin/fpszsh.htm" })
    //分配设置审核
    public ModelAndView fpszsh(HttpServletRequest request, HttpServletResponse response,String id){
        ModelAndView mv = new JModelAndView("admin/blue/dodot/fpszsh.html", this.configService.getSysConfig(), this.userConfigService.getUserConfig(), 0, request, response);  
        mv.addObject("id",id);
        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "资金流入流出详情", value = "/admin/zjlrlcxq.htm*", rtype = "admin", rname = "资金流入流出详情", rcode = "platform_manage", rgroup = "详情")
    @RequestMapping({ "/admin/zjlrlcxq.htm" })
    //资金流入流出详情
    public ModelAndView zjlrlcxq(HttpServletRequest request, HttpServletResponse response,String id){
        ModelAndView mv = new JModelAndView("admin/blue/dodot/zjlrlcxq.html", this.configService.getSysConfig(), this.userConfigService.getUserConfig(), 0, request, response);  
        mv.addObject("id",id);
        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "盈亏管理详情", value = "/admin/ykglxq.htm*", rtype = "admin", rname = "盈亏管理详情", rcode = "platform_manage", rgroup = "详情")
    @RequestMapping({ "/admin/ykglxq.htm" })
    //盈亏管理详情
    public ModelAndView ykglxq(HttpServletRequest request, HttpServletResponse response,String id){
        ModelAndView mv = new JModelAndView("admin/blue/dodot/ykglxq.html", this.configService.getSysConfig(), this.userConfigService.getUserConfig(), 0, request, response);  
        mv.addObject("id",id);
        return mv;
    }
 
    public void setServletContext(ServletContext servletContext){
        this.servletContext = servletContext;
    }
}

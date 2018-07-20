package com.dashidao.manage.poster.action;

import com.dashidao.core.annotation.SecurityMapping;
import com.dashidao.core.mv.JModelAndView;
import com.dashidao.core.security.support.SecurityUserHolder;
import com.dashidao.core.tools.CommUtil;
import com.dashidao.foundation.domain.Store;
import com.dashidao.foundation.domain.User;
import com.dashidao.foundation.service.*;
import com.dashidao.manage.seller.tools.MenuTools;
import com.dashidao.view.web.tools.AreaViewTools;
import com.dashidao.view.web.tools.OrderViewTools;
import com.dashidao.view.web.tools.StoreViewTools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 栈代中心控制器
 */
@Controller
public class BasePosterAction {
    @Autowired
    private ISysConfigService configService;

    @Autowired
    private IUserConfigService userConfigService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IMessageService messageService;

    @Autowired
    private IStoreService storeService;

    @Autowired
    private IArticleService articleService;

    @Autowired
    private StoreViewTools storeViewTools;

    @Autowired
    private OrderViewTools orderViewTools;

    @Autowired
    private AreaViewTools areaViewTools;

    @Autowired
    private MenuTools menuTools;

    @SecurityMapping(display = false, rsequence = 0, title = "栈代中心", value = "/poster/index.htm*", rtype = "buyer", rname = "栈代中心", rcode = "user_center", rgroup = "用户中心")
    @RequestMapping({"/poster/index.htm"})
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
            "user/default/usercenter/poster_index.html", this.configService.getSysConfig(),
            this.userConfigService.getUserConfig(), 0, request, response);
        User user = this.userService.getObjById(SecurityUserHolder.getCurrentUser().getId());
        List msgs = new ArrayList();
        Map params = new HashMap();
        params.put("status", Integer.valueOf(0));
        params.put("user_id", SecurityUserHolder.getCurrentUser().getId());
        msgs = this.messageService.query(
                   "select obj from Message obj where obj.status=:status and obj.toUser.id=:user_id and obj.parent.id is null",
                   params, -1, -1);
        params.clear();
        params.put("class_mark", "notice");
        params.put("display", Boolean.valueOf(true));
        List articles = this.articleService.query(
                            "select obj from Article obj where obj.articleClass.mark=:class_mark and obj.display=:display order by obj.addTime desc",
                            params, 0, 5);
        mv.addObject("articles", articles);
        mv.addObject("user", user);
        mv.addObject("store", user.getStore());
        mv.addObject("msgs", msgs);
        mv.addObject("storeViewTools", this.storeViewTools);
        mv.addObject("orderViewTools", this.orderViewTools);
        mv.addObject("areaViewTools", this.areaViewTools);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "栈代中心导航", value = "/poster/nav.htm*", rtype = "buyer", rname = "栈代中心导航", rcode = "user_center", rgroup = "用户中心")
    @RequestMapping({"/poster/nav.htm"})
    public ModelAndView nav(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView("user/default/usercenter/poster_nav.html", this.configService.getSysConfig(),
                                            this.userConfigService.getUserConfig(), 0, request, response);
        int store_status = 0;
        Store store = this.storeService.getObjByProperty("user.id", SecurityUserHolder.getCurrentUser().getId());
        if (store != null){
            store_status = store.getStore_status();
        }
        String op = CommUtil.null2String(request.getAttribute("op"));
        mv.addObject("op", op);
        mv.addObject("store_status", Integer.valueOf(store_status));
        mv.addObject("user", this.userService.getObjById(SecurityUserHolder.getCurrentUser().getId()));

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "栈代中心导航", value = "/poster/head.htm*", rtype = "buyer", rname = "栈代中心导航", rcode = "user_center", rgroup = "用户中心")
    @RequestMapping({"/poster/nav_head.htm"})
    public ModelAndView nav_head(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
            "user/default/usercenter/poster_head.html", this.configService
            .getSysConfig(),
            this.userConfigService.getUserConfig(), 0, request, response);
        String type = CommUtil.null2String(request.getAttribute("type"));
        mv.addObject("type", type.equals("") ? "goods" : type);
        mv.addObject("menuTools", this.menuTools);
        mv.addObject("user", this.userService.getObjById(
                         SecurityUserHolder.getCurrentUser().getId()));

        return mv;
    }
    
    @SecurityMapping(display = false, rsequence = 0, title = "栈代中心导航", value = "/poster/adv.htm*", rtype = "buyer", rname = "栈代中心导航", rcode = "user_center", rgroup = "用户中心")
    @RequestMapping({"/poster/adv.htm"})
    public ModelAndView adv(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
            "user/default/usercenter/zhandai/adv.html", this.configService
            .getSysConfig(),
            this.userConfigService.getUserConfig(), 0, request, response);

        return mv;
    }

}





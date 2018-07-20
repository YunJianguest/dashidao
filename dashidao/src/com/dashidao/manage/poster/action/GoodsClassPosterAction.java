package com.dashidao.manage.poster.action;

import com.dashidao.core.annotation.SecurityMapping;
import com.dashidao.core.domain.virtual.SysMap;
import com.dashidao.core.mv.JModelAndView;
import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.security.support.SecurityUserHolder;
import com.dashidao.core.tools.CommUtil;
import com.dashidao.core.tools.WebForm;
import com.dashidao.foundation.domain.UserGoodsClass;
import com.dashidao.foundation.domain.query.UserGoodsClassQueryObject;
import com.dashidao.foundation.service.ISysConfigService;
import com.dashidao.foundation.service.IUserConfigService;
import com.dashidao.foundation.service.IUserGoodsClassService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 栈代商品分类控制器
 */
@Controller
public class GoodsClassPosterAction {
    @Autowired
    private ISysConfigService configService;

    @Autowired
    private IUserConfigService userConfigService;

    @Autowired
    private IUserGoodsClassService usergoodsclassService;

    @SecurityMapping(display = false, rsequence = 0, title = "栈代商品分类列表", value = "/poster/usergoodsclass_list.htm*", rtype = "poster", rname = "商品分类", rcode = "usergoodsclass_poster", rgroup = "商品管理")
    @RequestMapping({"/poster/usergoodsclass_list.htm"})
    public ModelAndView usergoodsclass_list(HttpServletRequest request, HttpServletResponse response, String currentPage, String orderBy, String orderType){
        ModelAndView mv = new JModelAndView(
            "user/default/usercenter/usergoodsclass_list.html",
            this.configService.getSysConfig(), this.userConfigService
            .getUserConfig(), 0, request, response);
        String url = this.configService.getSysConfig().getAddress();
        if ((url == null) || (url.equals(""))){
            url = CommUtil.getURL(request);
        }
        String params = "";
        UserGoodsClassQueryObject qo = new UserGoodsClassQueryObject(
            currentPage, mv, orderBy, orderType);
        qo.setPageSize(Integer.valueOf(20));
        WebForm wf = new WebForm();
        wf.toQueryPo(request, qo, UserGoodsClass.class, mv);
        qo.addQuery("obj.parent.id is null", null);
        qo.addQuery("obj.user.id",
                    new SysMap("user_id",
                               SecurityUserHolder.getCurrentUser().getId()), "=");
        qo.setOrderBy("sequence");
        qo.setOrderType("asc");
        IPageList pList = this.usergoodsclassService.list(qo);
        CommUtil.saveIPageList2ModelAndView(url +
                                            "/poster/usergoodsclass_list.htm", "", params, pList, mv);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "栈代商品分类保存", value = "/poster/usergoodsclass_save.htm*", rtype = "poster", rname = "商品分类", rcode = "usergoodsclass_poster", rgroup = "商品管理")
    @RequestMapping({"/poster/usergoodsclass_save.htm"})
    public String usergoodsclass_save(HttpServletRequest request, HttpServletResponse response, String id, String pid){
        WebForm wf = new WebForm();
        UserGoodsClass usergoodsclass = null;
        if (id.equals("")){
            usergoodsclass = (UserGoodsClass)wf.toPo(request, UserGoodsClass.class);
            usergoodsclass.setAddTime(new Date());
        }else{
            UserGoodsClass obj = this.usergoodsclassService.getObjById(
                                     Long.valueOf(Long.parseLong(id)));
            usergoodsclass = (UserGoodsClass)wf.toPo(request, obj);
        }
        usergoodsclass.setUser(SecurityUserHolder.getCurrentUser());
        if (!pid.equals("")){
            UserGoodsClass parent = this.usergoodsclassService.getObjById(
                                        Long.valueOf(Long.parseLong(pid)));
            usergoodsclass.setParent(parent);
        }
        boolean ret = true;
        if (id.equals(""))
            ret = this.usergoodsclassService.save(usergoodsclass);
        else
            ret = this.usergoodsclassService.update(usergoodsclass);

        return "redirect:usergoodsclass_list.htm";
    }

    @SecurityMapping(display = false, rsequence = 0, title = "栈代商品分类删除", value = "/poster/usergoodsclass_del.htm*", rtype = "poster", rname = "商品分类", rcode = "usergoodsclass_poster", rgroup = "商品管理")
    @RequestMapping({"/poster/usergoodsclass_del.htm"})
    public String usergoodsclass_del(HttpServletRequest request, String mulitId){
        String[] ids = mulitId.split(",");
        for (String id : ids){
            if (!id.equals("")){
                UserGoodsClass usergoodsclass = this.usergoodsclassService
                                                .getObjById(Long.valueOf(Long.parseLong(id)));
                this.usergoodsclassService.delete(Long.valueOf(Long.parseLong(id)));
            }
        }

        return "redirect:usergoodsclass_list.htm";
    }

    @SecurityMapping(display = false, rsequence = 0, title = "新增栈代商品分类", value = "/poster/address_add.htm*", rtype = "poster", rname = "商品分类", rcode = "usergoodsclass_poster", rgroup = "商品管理")
    @RequestMapping({"/poster/usergoodsclass_add.htm"})
    public ModelAndView usergoodsclass_add(HttpServletRequest request, HttpServletResponse response, String currentPage, String pid){
        ModelAndView mv = new JModelAndView(
            "user/default/usercenter/usergoodsclass_add.html",
            this.configService.getSysConfig(), this.userConfigService
            .getUserConfig(), 0, request, response);
        Map map = new HashMap();
        map.put("uid", SecurityUserHolder.getCurrentUser().getId());
        List ugcs = this.usergoodsclassService
                    .query(
                        "select obj from UserGoodsClass obj where obj.parent.id is null and obj.user.id = :uid order by obj.sequence asc",
                        map, -1, -1);
        if (!CommUtil.null2String(pid).equals("")){
            UserGoodsClass parent = this.usergoodsclassService
                                    .getObjById(CommUtil.null2Long(pid));
            UserGoodsClass obj = new UserGoodsClass();
            obj.setParent(parent);
            mv.addObject("obj", obj);
        }
        mv.addObject("ugcs", ugcs);
        mv.addObject("currentPage", currentPage);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "编辑栈代商品分类", value = "/poster/usergoodsclass_edit.htm*", rtype = "poster", rname = "商品分类", rcode = "usergoodsclass_poster", rgroup = "商品管理")
    @RequestMapping({"/poster/usergoodsclass_edit.htm"})
    public ModelAndView usergoodsclass_edit(HttpServletRequest request, HttpServletResponse response, String currentPage, String id){
        ModelAndView mv = new JModelAndView(
            "user/default/usercenter/usergoodsclass_add.html",
            this.configService.getSysConfig(), this.userConfigService
            .getUserConfig(), 0, request, response);
        List ugcs = this.usergoodsclassService
                    .query(
                        "select obj from UserGoodsClass obj where obj.parent.id is null order by obj.sequence asc",
                        null, -1, -1);
        UserGoodsClass obj = this.usergoodsclassService.getObjById(
                                 CommUtil.null2Long(id));
        mv.addObject("obj", obj);
        mv.addObject("ugcs", ugcs);
        mv.addObject("currentPage", currentPage);

        return mv;
    }
}




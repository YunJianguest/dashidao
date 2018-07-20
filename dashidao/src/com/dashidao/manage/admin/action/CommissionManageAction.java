package com.dashidao.manage.admin.action;

import com.dashidao.core.annotation.SecurityMapping;
import com.dashidao.core.mv.JModelAndView;
import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.tools.CommUtil;
import com.dashidao.core.tools.WebForm;
import com.dashidao.foundation.domain.Commission;
import com.dashidao.foundation.domain.OrderForm;
import com.dashidao.foundation.domain.SysConfig;
import com.dashidao.foundation.domain.query.CommissionQueryObject;
import com.dashidao.foundation.service.ICommissionService;
import com.dashidao.foundation.service.IOrderFormService;
import com.dashidao.foundation.service.ISysConfigService;
import com.dashidao.foundation.service.IUserConfigService;
import com.easyjf.beans.BeanUtils;
import com.easyjf.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 佣金管理控制器
 */
@Controller
public class CommissionManageAction {
    @Autowired
    private ISysConfigService configService;

    @Autowired
    private IUserConfigService userConfigService;

    @Autowired
    private ICommissionService commissionService;

    @Autowired
    private IOrderFormService orderFormService;

   /* @SecurityMapping(display = false, rsequence = 0, title = "快递设置", value = "/admin/set_kuaidi.htm*", rtype = "admin", rname = "快递设置", rcode = "admin_set_kuaidi", rgroup = "设置")
    @RequestMapping({"/admin/set_kuaidi.htm"})
    public ModelAndView set_kuaidi(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView("admin/blue/set_kuaidi.html",
                                            this.configService.getSysConfig(), this.userConfigService
                                            .getUserConfig(), 0, request, response);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "保存快递设置", value = "/admin/set_kuaidi_save.htm*", rtype = "admin", rname = "快递设置", rcode = "admin_set_kuaidi", rgroup = "设置")
    @RequestMapping({"/admin/set_kuaidi_save.htm"})
    public ModelAndView set_kuaidi_save(HttpServletRequest request, HttpServletResponse response, String id, String kuaidi_id){
        SysConfig obj = this.configService.getSysConfig();
        WebForm wf = new WebForm();
        SysConfig config = null;
        if (id.equals("")){
            config = (SysConfig)wf.toPo(request, SysConfig.class);
            config.setAddTime(new Date());
        }else{
            config = (SysConfig)wf.toPo(request, obj);
        }
        config.setKuaidi_id(kuaidi_id);
        if (id.equals(""))
            this.configService.save(config);
       else{
            this.configService.update(config);
        }
        ModelAndView mv = new JModelAndView("admin/blue/success.html",
                                            this.configService.getSysConfig(), this.userConfigService
                                            .getUserConfig(), 0, request, response);
        mv.addObject("op_title", "快递设置成功");
        mv.addObject("list_url", CommUtil.getURL(request) +
                     "/admin/set_kuaidi.htm");

        return mv;
    }
*/
    @SecurityMapping(display = false, rsequence = 0, title = "佣金列表", value = "/admin/commission_list.htm*", rtype = "admin", rname = "佣金列表", rcode = "admin_commission", rgroup = "设置")
    @RequestMapping({"/admin/commission_list.htm"})
    public ModelAndView commission_list(HttpServletRequest request, HttpServletResponse response, String currentPage, String orderBy, String orderType){
        ModelAndView mv = new JModelAndView(
            "admin/blue/commission_list.html", this.configService
            .getSysConfig(),
            this.userConfigService.getUserConfig(), 0, request, response);
        String url = this.configService.getSysConfig().getAddress();
        if ((url == null) || (url.equals(""))){
            url = CommUtil.getURL(request);
        }
        String params = "";
        CommissionQueryObject qo = new CommissionQueryObject(
            currentPage, mv, "id", "asc");
        IPageList pList = this.commissionService.list(qo);
        CommUtil.saveIPageList2ModelAndView(url +
                                            "/admin/commission_list.htm", "", params, pList, mv);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "添加佣金添加", value = "/admin/commission_add.htm*", rtype = "admin", rname = "佣金配置", rcode = "admin_commission", rgroup = "设置")
    @RequestMapping({"/admin/commission_add.htm"})
    public ModelAndView commission_add(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
            "admin/blue/commission_add.html", this.configService
            .getSysConfig(),
            this.userConfigService.getUserConfig(), 0, request, response);

        return mv;
    }
    
    @SecurityMapping(display = false, rsequence = 0, title = "佣金保存", value = "/admin/commission_save.htm*", rtype = "admin", rname = "佣金配置", rcode = "admin_commission", rgroup = "设置")
    @RequestMapping({"/admin/commission_save.htm"})
    public ModelAndView commission_save(HttpServletRequest request, HttpServletResponse response, String id, String currentPage){
        WebForm wf = new WebForm();
        Commission commission = null;
        if (id.equals("")){
            commission = (Commission)wf.toPo(request, Commission.class);
            commission.setAddTime(new Date());
        }else{
            Commission obj = this.commissionService.getObjById(
                                     Long.valueOf(Long.parseLong(id)));
            commission = (Commission)wf.toPo(request, obj);
        }
        if (id.equals(""))
            this.commissionService.save(commission);
        else
            this.commissionService.update(commission);
        ModelAndView mv = new JModelAndView("admin/blue/success.html",
                                            this.configService.getSysConfig(), this.userConfigService
                                            .getUserConfig(), 0, request, response);
        mv.addObject("list_url", CommUtil.getURL(request) +
                     "/admin/commission_list.htm");
        mv.addObject("op_title", "保存佣金配置成功");
        mv.addObject("add_url", CommUtil.getURL(request) +
                     "/admin/commission_add.htm?currentPage=" + currentPage);

        return mv;
    }

    
    
    
    @SecurityMapping(display = false, rsequence = 0, title = "佣金编辑", value = "/admin/commission_edit.htm*", rtype = "admin", rname = "佣金配置", rcode = "admin_commission", rgroup = "设置")
    @RequestMapping({"/admin/commission_edit.htm"})
    public ModelAndView commission_edit(HttpServletRequest request, HttpServletResponse response, String id, String currentPage){
        ModelAndView mv = new JModelAndView(
            "admin/blue/commission_add.html", this.configService
            .getSysConfig(),
            this.userConfigService.getUserConfig(), 0, request, response);
        if ((id != null) && (!id.equals(""))){
            Commission commission = this.commissionService
                                            .getObjById(Long.valueOf(Long.parseLong(id)));
            mv.addObject("obj", commission);
            mv.addObject("currentPage", currentPage);
            mv.addObject("edit", Boolean.valueOf(true));
        }

        return mv;
    }
    
   
    @SecurityMapping(display = false, rsequence = 0, title = "佣金配置删除", value = "/admin/commission_del.htm*", rtype = "admin", rname = "佣金配置", rcode = "admin_commission", rgroup = "设置")
    @RequestMapping({"/admin/commission_del.htm"})
    public String commission_del(HttpServletRequest request, HttpServletResponse response, String mulitId, String currentPage){
        String[] ids = mulitId.split(",");
        for (String id : ids){
            if (!id.equals("")){
                Commission ec = this.commissionService.getObjById(
                                        Long.valueOf(Long.parseLong(id)));
                this.commissionService.delete(Long.valueOf(Long.parseLong(id)));
            }
        }

        return "redirect:commission_list.htm?currentPage=" + currentPage;
    }

    /*
    @SecurityMapping(display = false, rsequence = 0, title = "佣金配置Ajax更新数据", value = "/admin/commission_ajax.htm*", rtype = "admin", rname = "佣金配置", rcode = "admin_commission", rgroup = "设置")
    @RequestMapping({"/admin/commission_ajax.htm"})
    public void commission_ajax(HttpServletRequest request, HttpServletResponse response, String id, String fieldName, String value) throws ClassNotFoundException {
        commission obj = this.commissionService.getObjById(
                                 Long.valueOf(Long.parseLong(id)));
        Field[] fields = commission.class.getDeclaredFields();
        BeanWrapper wrapper = new BeanWrapper(obj);
        Object val = null;
        for (Field field : fields){
            if (field.getName().equals(fieldName)){
                Class clz = Class.forName("java.lang.String");
                if (field.getType().getName().equals("int")){
                    clz = Class.forName("java.lang.Integer");
                }
                if (field.getType().getName().equals("boolean")){
                    clz = Class.forName("java.lang.Boolean");
                }
                if (!value.equals(""))
                    val = BeanUtils.convertType(value, clz);
               else{
                    val = Boolean.valueOf(
                              !CommUtil.null2Boolean(wrapper
                                                     .getPropertyValue(fieldName)));
                }
                wrapper.setPropertyValue(fieldName, val);
            }
        }
        this.commissionService.update(obj);
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter writer = response.getWriter();
            writer.print(val.toString());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @RequestMapping({"/admin/commission_mark.htm"})
    public void commission_mark(HttpServletRequest request, HttpServletResponse response, String company_mark, String id){
        Map params = new HashMap();
        params.put("company_mark", company_mark.trim());
        params.put("id", CommUtil.null2Long(id));
        List ecs = this.commissionService
                   .query(
                       "select obj from commission obj where obj.company_mark=:company_mark and obj.id!=:id",
                       params, -1, -1);
        boolean ret = true;
        if (ecs.size() > 0){
            ret = false;
        }
        response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter writer = response.getWriter();
            writer.print(ret);
        } catch (IOException e){
            e.printStackTrace();
        }
    }*/
}





package com.dashidao.manage.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
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
import org.springframework.web.servlet.ModelAndView;

import com.dashidao.core.annotation.SecurityMapping;
import com.dashidao.core.domain.virtual.SysMap;
import com.dashidao.core.mv.JModelAndView;
import com.dashidao.core.query.support.IPageList;
import com.dashidao.foundation.domain.Accessory;
import com.dashidao.foundation.domain.Chatting;
import com.dashidao.foundation.domain.ChattingLog;
import com.dashidao.foundation.domain.Goods;
import com.dashidao.foundation.domain.GoodsCart;
import com.dashidao.foundation.domain.GoodsSpecProperty;
import com.dashidao.foundation.domain.GoodsSpecification;
import com.dashidao.foundation.domain.Store;
import com.dashidao.foundation.domain.User;
import com.dashidao.foundation.domain.query.GoodsQueryObject;
import com.dashidao.foundation.service.IChattingLogService;
import com.dashidao.foundation.service.IChattingService;
import com.dashidao.foundation.service.IGoodsService;
import com.dashidao.foundation.service.ISysConfigService;
import com.dashidao.foundation.service.IUserConfigService;
import com.dashidao.foundation.service.IUserService;
import com.dashidao.jwt.filter.SignFilter;
import com.dashidao.jwt.util.ComplexOutPropertyPreFilter;
import com.dashidao.jwt.util.DateJsonValueProcessor;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
/**
 * 卖家活动控制器
 */
@Controller
public class SaleManageAction {
    @Autowired
    private ISysConfigService configService;

    @Autowired
    private IUserConfigService userConfigService;
    
    @Autowired
	private IChattingService chattingService;
	@Autowired
	private IChattingLogService chattingLogService;
	
	 @Autowired
	 private IUserService userService;
	 
	 @Autowired
	 private IGoodsService goodsService;


    @SecurityMapping(display = false, rsequence = 0, title = "续签合同审核", value = "/admin/continue_check.htm*", rtype = "admin", rname = "续签合同审核", rcode = "continue_check_admin", rgroup = "审核管理")
    @RequestMapping({"/admin/continue_check.htm"})
    public ModelAndView continue_check(HttpServletRequest request, HttpServletResponse response){
        System.out.println("111111111111111111111111111111111");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/continue_check.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "续签合同审核详情", value = "/admin/xqht_details.htm*", rtype = "admin", rname = "续签合同审核详情", rcode = "xqht_details", rgroup = "审核管理")
    @RequestMapping({"/admin/xqht_details.htm"})
    public ModelAndView xqht_details(HttpServletRequest request, HttpServletResponse response){

        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/xqht_details.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "歇业审核", value = "/admin/end_check.htm*", rtype = "admin", rname = "歇业审核", rcode = "end_check_admin", rgroup = "审核管理")
    @RequestMapping({"/admin/end_check.htm"})
    public ModelAndView end_check(HttpServletRequest request, HttpServletResponse response){

        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/end_check.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }




    @SecurityMapping(display = false, rsequence = 0, title = "商家活动审核", value = "/admin/seller_activity_check.htm*", rtype = "admin", rname = "商家活动审核", rcode = "seller_activity_check", rgroup = "审核管理")
    @RequestMapping({"/admin/seller_activity_check.htm"})
    public ModelAndView seller_activity_check(HttpServletRequest request, HttpServletResponse response){
        System.out.println("333333333333333333333333333333");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/seller_activity_check.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "商家调价审核", value = "/admin/seller_adjust_admin.htm*", rtype = "admin", rname = "商家调价审核", rcode = "seller_adjust_admin", rgroup = "审核管理")
    @RequestMapping({"/admin/seller_adjust_admin.htm"})
    public ModelAndView seller_adjust_admin(HttpServletRequest request, HttpServletResponse response){
        System.out.println("444444444444444444444444444");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/seller_adjust_admin.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "商家调价审核详情", value = "/admin/tj_details.htm*", rtype = "admin", rname = "商家调价审核详情", rcode = "tj_details", rgroup = "审核管理")
    @RequestMapping({"/admin/tj_details.htm"})
    public ModelAndView tj_details(HttpServletRequest request, HttpServletResponse response){
        System.out.println("wwwwwwwwwwwwwwww");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/tj_details.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "商家消保审核", value = "/admin/sell_consume_check.htm*", rtype = "admin", rname = "商家消保审核", rcode = "sell_consume_check", rgroup = "审核管理")
    @RequestMapping({"/admin/sell_consume_check.htm"})
    public ModelAndView sell_consume_check(HttpServletRequest request, HttpServletResponse response){
        System.out.println("5555555555555555");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/sell_consume_check.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "商家消保审核详情", value = "/admin/xb_details.htm*", rtype = "admin", rname = "商家消保审核详情", rcode = "xb_details", rgroup = "审核管理")
    @RequestMapping({"/admin/xb_details.htm"})
    public ModelAndView xb_details(HttpServletRequest request, HttpServletResponse response){
        System.out.println("5555555555555555"+request.getParameter("id"));

        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/xb_details.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
                mv.addObject("states",request.getParameter("states"));
        mv.addObject("id",request.getParameter("id"));
        mv.addObject("shenheId",request.getParameter("shenheId"));
        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "退出审核", value = "/admin/quite_check.htm*", rtype = "admin", rname = "退出审核", rcode = "quite_check", rgroup = "审核管理")
    @RequestMapping({"/admin/quite_check.htm"})
    public ModelAndView quite_check(HttpServletRequest request, HttpServletResponse response){
        System.out.println("5555555555555555");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/quite_check.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        //id是商家ID

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "退出审核详情", value = "/admin/tcsq_details.htm*", rtype = "admin", rname = "退出审核详情", rcode = "tcsq_details", rgroup = "审核管理")
    @RequestMapping({"/admin/tcsq_details.htm"})
    public ModelAndView tcsq_details(HttpServletRequest request, HttpServletResponse response){
        System.out.println("5555555555555555"+request.getParameter("id")+request.getParameter("shenheId"));
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/tcsq_details.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        mv.addObject("id",request.getParameter("id"));
        mv.addObject("shenheId",request.getParameter("shenheId"));
        mv.addObject("states",request.getParameter("states"));
        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "会员提现审核", value = "/admin/member_cash_check.htm*", rtype = "admin", rname = "会员提现审核", rcode = "member_cash_check", rgroup = "审核管理")
    @RequestMapping({"/admin/member_cash_check.htm"})
    public ModelAndView member_cash_check(HttpServletRequest request, HttpServletResponse response){
        System.out.println("66666666666666666");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/member_cash_check.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "佣金退税审核", value = "/admin/charges_drawback_check.htm*", rtype = "admin", rname = "佣金退税审核", rcode = "charges_drawback_check", rgroup = "审核管理")
    @RequestMapping({"/admin/charges_drawback_check.htm"})
    public ModelAndView charges_drawback_check(HttpServletRequest request, HttpServletResponse response){
        System.out.println("777777777777777777777");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/charges_drawback_check.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "商品详情", value = "/admin/good_detail_admin.htm*", rtype = "admin", rname = "商品详情", rcode = "good_detail_admin", rgroup = "审核管理")
    @RequestMapping({"/admin/good_detail_admin.htm"})
    public ModelAndView good_detail_admin(HttpServletRequest request, HttpServletResponse response){
        System.out.println("88888888888888888888888");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/good_detail_admin.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "商品详情2", value = "/admin/good_detail_admin2.htm*", rtype = "admin", rname = "商品详情2", rcode = "good_detail_admin2", rgroup = "审核管理")
    @RequestMapping({"/admin/good_detail_admin2.htm"})
    public ModelAndView good_detail_admin2(HttpServletRequest request, HttpServletResponse response){
        System.out.println("999999999999999999");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/good_detail_admin2.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }


    @SecurityMapping(display = false, rsequence = 0, title = "退出审核详情", value = "/admin/quite_check_detail.htm*", rtype = "admin", rname = "退出审核详情", rcode = "quite_check_detail", rgroup = "审核管理")
    @RequestMapping({"/admin/quite_check_detail.htm"})
    public ModelAndView quite_check_detail(HttpServletRequest request, HttpServletResponse response){
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaa");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/quite_check_detail.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "歇业审核详情", value = "/admin/close_business.htm*", rtype = "admin", rname = "歇业审核详情", rcode = "close_business", rgroup = "审核管理")
    @RequestMapping({"/admin/close_business.htm"})
    public ModelAndView close_business(HttpServletRequest request, HttpServletResponse response){
        System.out.println("bbbbbbbbbbbbbbbbbbbb");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/close_business.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }


    @SecurityMapping(display = false, rsequence = 0, title = "佣金退税审核详情", value = "/admin/charges_drawback_check_info.htm*", rtype = "admin", rname = "佣金退税审核详情", rcode = "charges_drawback_check_info", rgroup = "审核管理")
    @RequestMapping({"/admin/charges_drawback_check_info.htm"})
    public ModelAndView charges_drawback_check_info(HttpServletRequest request, HttpServletResponse response){
        System.out.println("cccccccccccccccccccccccc");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/charges_drawback_check_info.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "佣金退税详情", value = "/admin/yjts_details.htm*", rtype = "admin", rname = "佣金退税详情", rcode = "yjts_details", rgroup = "审核管理")
    @RequestMapping({"/admin/yjts_details.htm"})
    public ModelAndView yjts_details(HttpServletRequest request, HttpServletResponse response){
        System.out.println("cccccccccccccccccccccccc");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/yjts_details.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "商家活动审核详情", value = "/admin/seller_activity_check_detail.htm*", rtype = "admin", rname = "商家活动审核详情", rcode = "seller_activity_check_detail", rgroup = "审核管理")
    @RequestMapping({"/admin/seller_activity_check_detail.htm"})
    public ModelAndView seller_activity_check_detail(HttpServletRequest request, HttpServletResponse response){
        System.out.println("dddddddddddddddddddddddd");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/seller_activity_check_detail.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "营销活动", value = "/admin/yxhd_h.htm*", rtype = "admin", rname = "营销活动", rcode = "yxhd_h", rgroup = "运营管理")
    @RequestMapping({"/admin/yxhd_h.htm"})
    public ModelAndView yxhd_h(HttpServletRequest request, HttpServletResponse response){
        System.out.println("eeeeeeeeeeeeeeeee");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/yxhd_h.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "营销工具", value = "/admin/yxgj_h.htm*", rtype = "admin", rname = "营销工具", rcode = "yxgj_h", rgroup = "运营管理")
    @RequestMapping({"/admin/yxgj_h.htm"})
    public ModelAndView yxgj_h(HttpServletRequest request, HttpServletResponse response){
        System.out.println("fffffffffffffffffffffffffffff");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/yxgj_h.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "宣传品管理", value = "/admin/xcpgl_h.htm*", rtype = "admin", rname = "宣传品管理", rcode = "xcpgl_h", rgroup = "运营管理")
    @RequestMapping({"/admin/xcpgl_h.htm"})
    public ModelAndView xcpgl_h(HttpServletRequest request, HttpServletResponse response){
        System.out.println("gggggggggggggggggggggggg");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/xcpgl_h.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "资金流入流出", value = "/admin/zjlrlc.htm*", rtype = "admin", rname = "资金流入流出", rcode = "zjlrlc", rgroup = "资金管理")
    @RequestMapping({"/admin/zjlrlc.htm"})
    public ModelAndView zjlrlc(HttpServletRequest request, HttpServletResponse response){
        System.out.println("hhhhhhhhhhhhhhhhhhhh");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/zjlrlc.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }


    @SecurityMapping(display = false, rsequence = 0, title = "平台收费", value = "/admin/ptsf.htm*", rtype = "admin", rname = "平台收费", rcode = "ptsf", rgroup = "资金管理")
    @RequestMapping({"/admin/ptsf.htm"})
    public ModelAndView ptsf(HttpServletRequest request, HttpServletResponse response){
        System.out.println("iiiiiiiiiiiiiiiiiiiiiii");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/ptsf.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "盈亏管理", value = "/admin/ykgl.htm*", rtype = "admin", rname = "盈亏管理", rcode = "ykgl", rgroup = "资金管理")
    @RequestMapping({"/admin/ykgl.htm"})
    public ModelAndView ykgl(HttpServletRequest request, HttpServletResponse response){
        System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjj");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/ykgl.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }


    @SecurityMapping(display = false, rsequence = 0, title = "对账中心", value = "/admin/dzzx.htm*", rtype = "admin", rname = "对账中心", rcode = "dzzx", rgroup = "资金管理")
    @RequestMapping({"/admin/dzzx.htm"})
    public ModelAndView dzzx(HttpServletRequest request, HttpServletResponse response){
        System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkk");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/dzzx.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "对账中心查看明细", value = "/admin/account_details.htm*", rtype = "admin", rname = "对账中心查看明细", rcode = "account_details", rgroup = "资金管理")
    @RequestMapping({"/admin/account_details.htm"})
    public ModelAndView account_details(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/account_details.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "佣金退税", value = "/admin/yjts.htm*", rtype = "admin", rname = "佣金退税", rcode = "yjts", rgroup = "资金管理")
    @RequestMapping({"/admin/yjts.htm"})
    public ModelAndView yjts(HttpServletRequest request, HttpServletResponse response){
        System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkk");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/yjts.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "会员", value = "/admin/hy.htm*", rtype = "admin", rname = "会员", rcode = "hy", rgroup = "资金管理")
    @RequestMapping({"/admin/hy.htm"})
    public ModelAndView hy(HttpServletRequest request, HttpServletResponse response){
        System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkk");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/hy.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }


    @SecurityMapping(display = false, rsequence = 0, title = "云客", value = "/admin/yk.htm*", rtype = "admin", rname = "云客", rcode = "yk", rgroup = "资金管理")
    @RequestMapping({"/admin/yk.htm"})
    public ModelAndView yk(HttpServletRequest request, HttpServletResponse response){
        System.out.println("LLLLLLLLLLLLLLLLLLLLLLLLLLLL");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/yk.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "商家", value = "/admin/sj.htm*", rtype = "admin", rname = "商家", rcode = "sj", rgroup = "资金管理")
    @RequestMapping({"/admin/sj.htm"})
    public ModelAndView sj(HttpServletRequest request, HttpServletResponse response){
        System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmm");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/sj.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }
//500
    @SecurityMapping(display = false, rsequence = 0, title = "栈代", value = "/admin/zhandai.htm*", rtype = "admin", rname = "栈代", rcode = "zhandai", rgroup = "资金管理")
    @RequestMapping({"/admin/zhandai.htm"})
    public ModelAndView zhandai(HttpServletRequest request, HttpServletResponse response){
        System.out.println("mmmmmmmmmmmmmmmmmmmmmmmmmm");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/zhandai.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "栈代", value = "/admin/zhandai_admin.htm*", rtype = "admin", rname = "栈代", rcode = "zhandai_admin", rgroup = "基础管理")
    @RequestMapping({"/admin/zhandai_admin.htm"})
    public ModelAndView zhandai_admin(HttpServletRequest request, HttpServletResponse response){
        System.out.println("daassaassaassk");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/zhandai_admin.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }
//404
    @SecurityMapping(display = false, rsequence = 0, title = "订单", value = "/admin/order.htm*", rtype = "admin", rname = "订单", rcode = "order", rgroup = "资金管理")
    @RequestMapping({"/admin/order.htm"})
    public ModelAndView order(HttpServletRequest request, HttpServletResponse response){
        System.out.println("nnnnnnnnnnnnnnnnnnnnnn");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/order.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }


    @SecurityMapping(display = false, rsequence = 0, title = "商品", value = "/admin/product.htm*", rtype = "admin", rname = "商品", rcode = "product", rgroup = "资金管理")
    @RequestMapping({"/admin/product.htm"})
    public ModelAndView product(HttpServletRequest request, HttpServletResponse response){
        System.out.println("nnnnnnnnnnnnnnnnnnnnnn");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/product.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }


    @SecurityMapping(display = false, rsequence = 0, title = "营销活动", value = "/admin/yingxiao.htm*", rtype = "admin", rname = "营销活动", rcode = "yingxiao", rgroup = "资金管理")
    @RequestMapping({"/admin/yingxiao.htm"})
    public ModelAndView yingxiao(HttpServletRequest request, HttpServletResponse response){
        System.out.println("ooooooooooooooooooo");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/yingxiao.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }



    @SecurityMapping(display = false, rsequence = 0, title = "售后服务", value = "/admin/shouhou.htm*", rtype = "admin", rname = "售后服务", rcode = "shouhou", rgroup = "资金管理")
    @RequestMapping({"/admin/shouhou.htm"})
    public ModelAndView shouhou(HttpServletRequest request, HttpServletResponse response){
        System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/shouhou.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        
        return mv;
    }


    @SecurityMapping(display = false, rsequence = 0, title = "商品警察", value = "/admin/spjc.htm*", rtype = "admin", rname = "商品警察", rcode = "spjc", rgroup = "交易警察")
    @RequestMapping({"/admin/spjc.htm"})
    public ModelAndView spjc(HttpServletRequest request, HttpServletResponse response) throws IOException{
        System.out.println("sssssssssssssssssssssss");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/spjc.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        
        return mv;
    }
    
    @SecurityMapping(display = false, rsequence = 0, title = "商品警察", value = "/admin/spjc_goods.htm*", rtype = "admin", rname = "商品警察", rcode = "spjc_goods", rgroup = "交易警察")
    @RequestMapping({"/admin/spjc_goods.htm"})
    public void goodsList(HttpServletRequest request, HttpServletResponse response) throws IOException 
    {
    	JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
		GoodsQueryObject obj = new GoodsQueryObject();
		obj.addQuery("obj.deleteStatus", new SysMap("deleteStatus", Long.parseLong("0")), "=");
		IPageList list = goodsService.list(obj);
		
		// 需要过滤的类 + 属性
		Map<Class<?>, Set<String>> includeMap = new HashMap<Class<?>, Set<String>>();
		Set<String> set = new HashSet<String>();
		set.add("id");
		set.add("store");
		set.add("gcs");
		set.add("goods_name");
		set.add("goods_price");
		set.add("goods_serial");
		set.add("seo_keywords");
		set.add("seo_description");
		set.add("goods_main_photo");
		set.add("goods_specs");
		includeMap.put(Goods.class, set);
		
		set = new HashSet<String>();
		set.add("name");
		set.add("path");
		includeMap.put(Accessory.class, set);
		
		set = new HashSet<String>();
		set.add("value");
		set.add("spec");
		set.add("specImage");
		set.add("sequence");
		includeMap.put(GoodsSpecProperty.class, set);

		set = new HashSet<String>();
		set.add("name");
		set.add("sequence");
		set.add("type");
		includeMap.put(GoodsSpecification.class, set);
		
		jsonConfig.setJsonPropertyFilter(new ComplexOutPropertyPreFilter(includeMap));

		JSONArray json = JSONArray.fromObject(list.getResult(), jsonConfig);
		SignFilter.printNoCheck(request, response, json);
		System.out.println(json);
		
    	
    }
    
    public void saveChatting(String userid1,String userid2,String spbh,String spmc,String jyid,String content) 
    {
    	User user1 =this.userService.getObjById(Long.valueOf(Long.parseLong(userid1)));
        User user2 =this.userService.getObjById(Long.valueOf(Long.parseLong(userid2)));
        Chatting chatting=new Chatting();
        chatting.setAddTime(new Date());
		chatting.setType(1);
		chatting.setUser1(user1);//发送
		chatting.setUser2(user2);//接受
		
		ChattingLog chattingLog=new ChattingLog();
		chattingLog.setAddTime(new Date());
		chattingLog.setChatting(chatting);
		//String content = "总部通知商品编号:"+spbh+",名称是"+spmc+"违规,"+jyid;
		chattingLog.setContent(content);
		chattingLog.setMark(0);
		chattingLog.setUser(user1);
		chattingService.save(chatting);
		chattingLogService.save(chattingLog);
    }
    
    
    @SecurityMapping(display = false, rsequence = 0, title = "价格监督", value = "/admin/jgjdC_save.htm*", rtype = "admin", rname = "价格监督", rcode = "jgjdC", rgroup = "价格监督")
    @RequestMapping({"/admin/jgjdC_save.htm"})
    public ModelAndView jgjdC_save(HttpServletRequest request, HttpServletResponse response,String userid1,String userid2,String spbh,String spmc,String jyid){
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/jgjd.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        
		String content = "总部通知商品编号:"+spbh+",名称是"+spmc+"价格违规,"+jyid;
		saveChatting(userid1,userid2,spbh,spmc,jyid,content) ;
        return mv;
    }
    
    @SecurityMapping(display = false, rsequence = 0, title = "法务监督", value = "/admin/fwjdC_save.htm*", rtype = "admin", rname = "法务监督", rcode = "fwjdC", rgroup = "法务监督")
    @RequestMapping({"/admin/fwjdC_save.htm"})
    public ModelAndView fwjdC_save(HttpServletRequest request, HttpServletResponse response,String userid1,String userid2,String spbh,String spmc,String jyid){
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/fwjd.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        
		String content = "总部通知商品编号:"+spbh+",名称是"+spmc+"违规,"+jyid;
		saveChatting(userid1,userid2,spbh,spmc,jyid,content) ;
        return mv;
    }
    
    
    @SecurityMapping(display = false, rsequence = 0, title = "商品警察", value = "/admin/spjc_save.htm*", rtype = "admin", rname = "商品警察", rcode = "spjc", rgroup = "交易警察")
    @RequestMapping({"/admin/spjc_save.htm"})
    public ModelAndView spjc_save(HttpServletRequest request, HttpServletResponse response,String userid1,String userid2,String spbh,String spmc,String jyid){
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/spjc.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        
        User user1 =this.userService.getObjById(Long.valueOf(Long.parseLong(userid1)));
        User user2 =this.userService.getObjById(Long.valueOf(Long.parseLong(userid2)));
        Chatting chatting=new Chatting();
        chatting.setAddTime(new Date());
		chatting.setType(1);
		chatting.setUser1(user1);//发送
		chatting.setUser2(user2);//接受
		
		ChattingLog chattingLog=new ChattingLog();
		chattingLog.setAddTime(new Date());
		chattingLog.setChatting(chatting);
		String content = "总部通知商品编号:"+spbh+",名称是"+spmc+"违规,"+jyid;
		chattingLog.setContent(content);
		chattingLog.setMark(0);
		chattingLog.setUser(user1);
		chattingService.save(chatting);
		chattingLogService.save(chattingLog);
        return mv;
    }
    
    @SecurityMapping(display = false, rsequence = 0, title = "聊天", value = "/admin/liaotian_save.htm*", rtype = "admin", rname = "聊天", rcode = "spjc", rgroup = "交易警察")
    @RequestMapping({"/admin/liaotian_save.htm"})
    public void liaotian_save(HttpServletRequest request, HttpServletResponse response,String content,String fromId,String toId) 
    {
    	/*String content = request.getParameter("content");
    	String fromid = request.getParameter("fromid");
    	String toId = request.getParameter("toId");*/
    	
    	
    	Chatting chatting1=null;
		/*Map map =new HashMap<>();
		map.put("user1_id", Long.parseLong(fromId));
		map.put("user2_id",Long.parseLong(toId));*/
		if (chatting1==null) {
			chatting1=new Chatting();
		}
		chatting1.setAddTime(new Date());
		chatting1.setType(0);
		User user1= this.userService.getObjById(Long.parseLong(fromId));
		User user2=  this.userService.getObjById(Long.parseLong(toId));
		chatting1.setUser1(user1);
		chatting1.setUser2(user2);
		ChattingLog chattingLog=new ChattingLog();
		chattingLog.setAddTime(new Date());
		chattingLog.setChatting(chatting1);
		chattingLog.setContent(content);
		chattingLog.setMark(0);
		chattingLog.setUser(user1);
		List et=chatting1.getLogs();
		et.add(chattingLog);
		chatting1.setLogs(et);
		chattingService.save(chatting1);
		chattingLogService.save(chattingLog);
		/*Chatting chatting2=null;
		map =new HashMap<>();
		map.put("user2_id", Long.parseLong(fromid));
		map.put("user1_id",Long.parseLong(toId));
		//ls=this.chattingService.query("select obj from Chatting obj where obj.user1.id=:user1_id and obj.user2.id=:user2_id ", map, -1, -1); 
		if (chatting2==null) {
			chatting2=new Chatting();
		}
		
		chatting2.setAddTime(new Date());
		chatting2.setType(0);
		User user3= this.userService.getObjById(Long.parseLong(fromid));
		User user4= this.userService.getObjById(Long.parseLong(toId));
		chatting2.setUser1(user4);
		chatting2.setUser2(user3);
		chattingLog=new ChattingLog();
		chattingLog.setAddTime(new Date());
		chattingLog.setChatting(chatting2);
		chattingLog.setContent(content);
		chattingLog.setMark(0);
		chattingLog.setUser(user3);
		et=chatting2.getLogs();
		et.add(chattingLog);
		chatting1.setLogs(et);
		chattingService.save(chatting2);
		chattingLogService.save(chattingLog);*/
		 response.setContentType("text/plain");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        Object val = new Object();
        try {
            PrintWriter writer = response.getWriter();
            writer.print(val.toString());
        } catch (IOException e){
            e.printStackTrace();
        }
    	
    	
    }
    
    
    @SecurityMapping(display = false, rsequence = 0, title = "商品警察详情", value = "/admin/spjc_details.htm*", rtype = "admin", rname = "商品警察", rcode = "spjc", rgroup = "交易警察")
    @RequestMapping({"/admin/spjc_details.htm"})
    public ModelAndView spjc_details(HttpServletRequest request, HttpServletResponse response){
        System.out.println("sssssssssssssssssssssss"+request.getParameter("id"));
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/spjc_details.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        mv.addObject("id",request.getParameter("id"));
        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "价格监督", value = "/admin/jgjd.htm*", rtype = "admin", rname = "价格监督", rcode = "jgjd", rgroup = "交易警察")
    @RequestMapping({"/admin/jgjd.htm"})
    public ModelAndView jgjd(HttpServletRequest request, HttpServletResponse response){
        System.out.println("ttttttttttttttttttttttttt");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/jgjd.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "价格监督详情", value = "/admin/jgjd_details.htm*", rtype = "admin", rname = "价格监督", rcode = "jgjd", rgroup = "交易警察")
    @RequestMapping({"/admin/jgjd_details.htm"})
    public ModelAndView jgjd_details(HttpServletRequest request, HttpServletResponse response){
        System.out.println("ttttttttttttttttttttttttt"+request.getParameter("id"));
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/jgjd_details.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        mv.addObject("id",request.getParameter("id"));
        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "法务监督", value = "/admin/fwjd.htm*", rtype = "admin", rname = "法务监督", rcode = "fwjd", rgroup = "交易警察")
    @RequestMapping({"/admin/fwjd.htm"})
    public ModelAndView fwjd(HttpServletRequest request, HttpServletResponse response){
        System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/fwjd.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "法务监督详情", value = "/admin/fwjd_details.htm*", rtype = "admin", rname = "法务监督", rcode = "fwjd", rgroup = "交易警察")
    @RequestMapping({"/admin/fwjd_details.htm"})
    public ModelAndView fwjd_details(HttpServletRequest request, HttpServletResponse response){
        System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu"+request.getParameter("id"));
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/fwjd_details.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        mv.addObject("id",request.getParameter("id"));
        return mv;
    }


    @SecurityMapping(display = false, rsequence = 0, title = "日志管理", value = "/admin/rzgl.htm*", rtype = "admin", rname = "日志管理", rcode = "rzgl", rgroup = "平台管理")
    @RequestMapping({"/admin/rzgl.htm"})
    public ModelAndView rzgl(HttpServletRequest request, HttpServletResponse response){
        System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvv");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/rzgl.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "日志管理详情", value = "/admin/rzgl_details.htm*", rtype = "admin", rname = "日志管理", rcode = "rzgl", rgroup = "平台管理")
    @RequestMapping({"/admin/rzgl_details.htm"})
    public ModelAndView rzgl_details(HttpServletRequest request, HttpServletResponse response){
        System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvv"+request.getParameter("id"));
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/rzgl_details.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        mv.addObject("id",request.getParameter("id"));
        return mv;
    }


    @SecurityMapping(display = false, rsequence = 0, title = "总部业务审核", value = "/admin/zbywshh.htm*", rtype = "admin", rname = "总部业务审核", rcode = "zbywshh", rgroup = "平台管理")
    @RequestMapping({"/admin/zbywshh.htm"})
    public ModelAndView zbywshh(HttpServletRequest request, HttpServletResponse response){
        System.out.println("wwwwwwwwwwwwwwwwwwwwww");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/zbywshh.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "总部业务审核详情", value = "/admin/headquarters_details.htm*", rtype = "admin", rname = "总部业务审核详情", rcode = "headquarters_details", rgroup = "平台管理")
    @RequestMapping({"/admin/headquarters_details.htm"})
    public ModelAndView headquarters_details(HttpServletRequest request, HttpServletResponse response){

        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/headquarters_details.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "分配设置", value = "/admin/fpsz_admin.htm*", rtype = "admin", rname = "分配设置", rcode = "fpsz_admin", rgroup = "系统设置")
    @RequestMapping({"/admin/fpsz_admin.htm"})
    public ModelAndView fpsz_admin(HttpServletRequest request, HttpServletResponse response){
        System.out.println("wwwwwwwwwwwwwwwwwwwwww");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/fpsz_admin.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }



    @SecurityMapping(display = false, rsequence = 0, title = "活动设置", value = "/admin/hdsz_admin.htm*", rtype = "admin", rname = "活动设置", rcode = "hdsz_admin", rgroup = "系统设置")
    @RequestMapping({"/admin/hdsz_admin.htm"})
    public ModelAndView hdsz_admin(HttpServletRequest request, HttpServletResponse response){
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxx");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/hdsz_admin.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }


    @SecurityMapping(display = false, rsequence = 0, title = "平台收款账户", value = "/admin/ptskzhh.htm*", rtype = "admin", rname = "平台收款账户", rcode = "ptskzhh", rgroup = "系统设置")
    @RequestMapping({"/admin/ptskzhh.htm"})
    public ModelAndView ptskzhh(HttpServletRequest request, HttpServletResponse response){
        System.out.println("yyyyyyyyyyyyyyyyyyyyyyyy");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/ptskzhh.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "消保服务设置", value = "/admin/xbfwsz_admin.htm*", rtype = "admin", rname = "消保服务设置", rcode = "xbfwsz_admin", rgroup = "系统设置")
    @RequestMapping({"/admin/xbfwsz_admin.htm"})
    public ModelAndView xbfwsz_admin(HttpServletRequest request, HttpServletResponse response){
        System.out.println("zzzzzzzzzzzzzzzzzzzz");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/xbfwsz_admin.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "云客", value = "/admin/yk_admin.htm*", rtype = "admin", rname = "云客", rcode = "yk_admin", rgroup = "基础管理")
    @RequestMapping({"/admin/yk_admin.htm"})
    public ModelAndView yk_admin(HttpServletRequest request, HttpServletResponse response){
        System.out.println("bbbbbbbbbbbbbbbbbbbbbbbb");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/yk_admin.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "云客", value = "/admin/yk_edit.htm*", rtype = "admin", rname = "云客", rcode = "yk_edit", rgroup = "基础管理")
    @RequestMapping({"/admin/yk_edit.htm"})
    public ModelAndView yk_edit(HttpServletRequest request, HttpServletResponse response){
        System.out.println("bbbbbbbbbbbbbbbbbbbbbbbb");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/yk_edit.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }



    @SecurityMapping(display = false, rsequence = 0, title = "支付方式", value = "/admin/zhifu_admin.htm*", rtype = "admin", rname = "支付方式", rcode = "zhifu_admin", rgroup = "系统设置")
    @RequestMapping({"/admin/zhifu_admin.htm"})
    public ModelAndView zhifu_admin(HttpServletRequest request, HttpServletResponse response){
        System.out.println("asdfasdfasdfasdfasdf");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/zhifu_admin.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "申诉管理", value = "/admin/shensu_admin.htm*", rtype = "admin", rname = "申诉管理", rcode = "shensu_admin", rgroup = "客户服务")
    @RequestMapping({"/admin/shensu_admin.htm"})
    public ModelAndView shensu_admin(HttpServletRequest request, HttpServletResponse response){
        System.out.println("asdfasdfasdfasdfasdf");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/shensu_admin.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }



    @SecurityMapping(display = false, rsequence = 0, title = "渠道设置", value = "/admin/classfy_admin.htm*", rtype = "admin", rname = "渠道设置", rcode = "classfy_admin", rgroup = "基础设置")
    @RequestMapping({"/admin/classfy_admin.htm"})
    public ModelAndView classfy_admin(HttpServletRequest request, HttpServletResponse response){
        System.out.println("渠道设置");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/classfy_admin.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "渠道设置一级", value = "/admin/sl_list.htm*", rtype = "admin", rname = "渠道设置一级", rcode = "sl_list", rgroup = "基础设置")
    @RequestMapping({"/admin/sl_list.htm"})
    public ModelAndView sl_list(HttpServletRequest request, HttpServletResponse response){
        System.out.println("渠道设置");
        System.out.println("渠道设置一级"+request.getParameter("a"));
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/sl_list.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "栈代注册", value = "/admin/zd_stack_register.htm*", rtype = "admin", rname = "栈代注册", rcode = "zd_stack_register", rgroup = "基础管理")
    @RequestMapping({"/admin/zd_stack_register.htm"})
    public ModelAndView zd_stack_register(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/zd_stack_register.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "购物卡", value = "/admin/shoppingcard.htm*", rtype = "admin", rname = "购物卡", rcode = "shoppingcard", rgroup = "基础管理")
    @RequestMapping({"/admin/shoppingcard.htm"})
    public ModelAndView shoppingcard(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/shoppingcard.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }
    
    @SecurityMapping(display = false, rsequence = 0, title = "添加购物卡", value = "/admin/add_card.htm*", rtype = "admin", rname = "添加购物卡", rcode = "add_card", rgroup = "基础管理")
    @RequestMapping({"/admin/add_card.htm"})
    public ModelAndView add_card(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/add_card.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }
    
    @SecurityMapping(display = false, rsequence = 0, title = "购物卡详情", value = "/admin/cardxiangqing.htm*", rtype = "admin", rname = "购物卡详情", rcode = "cardxiangqing", rgroup = "基础管理")
    @RequestMapping({"/admin/cardxiangqing.htm"})
    public ModelAndView cardxiangqing(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/cardxiangqing.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }
}

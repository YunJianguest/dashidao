package com.dashidao.view.web.action;

import com.dashidao.core.mv.JModelAndView;
import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.security.support.SecurityUserHolder;
import com.dashidao.core.tools.CommUtil;
import com.dashidao.core.tools.Md5Encrypt;
import com.dashidao.foundation.domain.*;
import com.dashidao.foundation.domain.query.DeliveryGoodsQueryObject;
import com.dashidao.foundation.domain.query.YunkeAuditQueryObject;
import com.dashidao.foundation.service.*;
import com.dashidao.jwt.filter.SignFilter;
import com.dashidao.jwt.util.ComplexOutPropertyPreFilter;
import com.dashidao.jwt.util.DateJsonValueProcessor;
import com.dashidao.manage.admin.tools.MsgTools;
import com.dashidao.view.web.tools.GoodsFloorViewTools;
import com.dashidao.view.web.tools.GoodsViewTools;
import com.dashidao.view.web.tools.NavViewTools;
import com.dashidao.view.web.tools.StoreViewTools;

import io.jsonwebtoken.Claims;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * 商城个人中心制器
 */
@Controller
public class PersonalCenterViewAction {
    @Autowired
    private ISysConfigService configService;

    @Autowired
    private IUserConfigService userConfigService;

    @Autowired
    private IGoodsClassService goodsClassService;

    @Autowired
    private IGoodsBrandService goodsBrandService;

    @Autowired
    private IPartnerService partnerService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IArticleClassService articleClassService;

    @Autowired
    private IArticleService articleService;

    @Autowired
    private IAccessoryService accessoryService;

    @Autowired
    private IMessageService messageService;

    @Autowired
    private IStoreService storeService;

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private INavigationService navigationService;

    @Autowired
    private IGroupGoodsService groupGoodsService;

    @Autowired
    private IGroupService groupService;

    @Autowired
    private IGoodsFloorService goodsFloorService;

    @Autowired
    private IBargainGoodsService bargainGoodsService;

    @Autowired
    private IDeliveryGoodsService deliveryGoodsService;

    @Autowired
    private IStoreCartService storeCartService;

    @Autowired
    private IGoodsCartService goodsCartService;
    @Autowired
    private IAdvertService advertservice;

    @Autowired
    private NavViewTools navTools;

    @Autowired
    private GoodsViewTools goodsViewTools;

    @Autowired
    private StoreViewTools storeViewTools;

    @Autowired
    private MsgTools msgTools;

    @Autowired
    private GoodsFloorViewTools gf_tools;

    @Autowired
    private IOrderFormService orderformService;
    @Autowired
    private IYunkeAuditService yunkeAuditService;
    
    //个人中心（我的）
    @RequestMapping({ "/wap/buyer/personalcenter.htm" })
    public ModelAndView buyerallclass(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView("/wap/buyer/personalcenter.html", this.configService.getSysConfig(), this.userConfigService.getUserConfig(), 1, request, response);
       
        mv.addObject("navTools", this.navTools);

        return mv;
    }
  //个人中心（我的）
    @RequestMapping({ "/wap/yunke/personalcenter.htm" })
    public ModelAndView yunkeallclass(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView("/wap/yunke/personalcenter.html", this.configService.getSysConfig(), this.userConfigService.getUserConfig(), 1, request, response);
       
        mv.addObject("navTools", this.navTools);

        return mv;
    }

	/**
	 * 云客
	 * @param request
	 * @param response 
	 * @throws IOException
	 */
	@RequestMapping({ "/wap/yunkedetail.htm" })
	@ResponseBody
	public void yunkedetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		Claims claims = SignFilter.checkSign(request, response);
		if (claims!=null) {
			String id=claims.getId(); 
		}
		
		List ls1=this.orderformService.query(
				"select SUM(obj.totalPrice) from OrderForm obj where 1=1", null, -1,
				-1);
		Map map=new HashMap<>();
		map.put("dqxf", ls1.get(0));
		
		List ls2=this.orderformService.query(
				"select obj from Infrastructure obj where 1=1", null, -1,
				-1); 
		Infrastructure infrastructure=(Infrastructure) ls2.get(0);
		map.put("mbxf",infrastructure.getYk_jj_tj());
		SignFilter.printNoCheck(request, response, map);

	}
	/**
	 * 云客申请
	 * @param request
	 * @param response 
	 * @throws IOException
	 */
	@RequestMapping({ "/wap/yunkeapply.htm" })
	@ResponseBody
	public void yunkeapply(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		Claims claims = SignFilter.checkSign(request, response);
		String msString="申请失败";
		if (claims!=null) {
			String id=claims.getId(); 
			List ls1=this.orderformService.query(
					"select SUM(obj.totalPrice) from OrderForm obj where 1=1", null, -1,
					-1);
			Map map=new HashMap<>();
			map.put("obj", ls1.get(0));
			
			List ls2=this.orderformService.query(
					"select obj from Infrastructure obj where 1=1", null, -1,
					-1); 
			Infrastructure infrastructure=(Infrastructure) ls2.get(0); 
			if(Double.parseDouble(infrastructure.getYk_jj_tj())>=Double.parseDouble(ls1.get(0).toString())) {
				YunkeAudit audit=new YunkeAudit();
				audit.setCreateDate(new Date());
				audit.setApply(userService.getObjById(Long.parseLong(id)));
				audit.setState(0);
				boolean bl=yunkeAuditService.save(audit); 
				if(bl) {
					msString="申请成功";
				}
			}
		}
		
		
		SignFilter.printNoCheck(request, response, msString);

	}
    
}

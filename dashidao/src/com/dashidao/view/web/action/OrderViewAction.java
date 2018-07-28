package com.dashidao.view.web.action;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Struct;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dashidao.core.domain.virtual.SysMap;
import com.dashidao.core.mv.JModelAndView;
import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.security.support.SecurityUserHolder;
import com.dashidao.core.tools.CommUtil;
import com.dashidao.foundation.domain.Accessory;
import com.dashidao.foundation.domain.Address;
import com.dashidao.foundation.domain.Area;
import com.dashidao.foundation.domain.Goods;
import com.dashidao.foundation.domain.GoodsCart;
import com.dashidao.foundation.domain.GoodsSpecProperty;
import com.dashidao.foundation.domain.GoodsSpecification;
import com.dashidao.foundation.domain.JiChuSheZhi;
import com.dashidao.foundation.domain.Location;
import com.dashidao.foundation.domain.OrderCom;
import com.dashidao.foundation.domain.OrderComPhoto;
import com.dashidao.foundation.domain.OrderForm;
import com.dashidao.foundation.domain.OrderFormLog;
import com.dashidao.foundation.domain.Store;
import com.dashidao.foundation.domain.StoreCart;
import com.dashidao.foundation.domain.StoreGoods;
import com.dashidao.foundation.domain.User;
import com.dashidao.foundation.domain.query.GoodsQueryObject;
import com.dashidao.foundation.domain.query.OrderComQueryObject;
import com.dashidao.foundation.domain.query.OrderFormQueryObject;
import com.dashidao.foundation.domain.query.UserQueryObject;
import com.dashidao.foundation.service.IAccessoryService;
import com.dashidao.foundation.service.IAddressService;
import com.dashidao.foundation.service.IAdvertService;
import com.dashidao.foundation.service.IArticleClassService;
import com.dashidao.foundation.service.IArticleService;
import com.dashidao.foundation.service.IBargainGoodsService;
import com.dashidao.foundation.service.IDeliveryGoodsService;
import com.dashidao.foundation.service.IGoodsBrandService;
import com.dashidao.foundation.service.IGoodsCartService;
import com.dashidao.foundation.service.IGoodsClassService;
import com.dashidao.foundation.service.IGoodsFloorService;
import com.dashidao.foundation.service.IGoodsService;
import com.dashidao.foundation.service.IGroupGoodsService;
import com.dashidao.foundation.service.IGroupService;
import com.dashidao.foundation.service.IJiChuSheZhiService;
import com.dashidao.foundation.service.IMessageService;
import com.dashidao.foundation.service.INavigationService;
import com.dashidao.foundation.service.IOrderComPhotoService;
import com.dashidao.foundation.service.IOrderComService;
import com.dashidao.foundation.service.IOrderFormLogService;
import com.dashidao.foundation.service.IOrderFormService;
import com.dashidao.foundation.service.IPartnerService;
import com.dashidao.foundation.service.IRoleService;
import com.dashidao.foundation.service.IStoreCartService;
import com.dashidao.foundation.service.IStoreGoodsService;
import com.dashidao.foundation.service.IStoreService;
import com.dashidao.foundation.service.ISysConfigService;
import com.dashidao.foundation.service.IUserConfigService;
import com.dashidao.foundation.service.IUserService;
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
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 商城订单制器
 * 
 * @author lsp
 *
 */
@Controller
public class OrderViewAction {
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
	private IStoreGoodsService storegoodsService;
	
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
	private IOrderFormService orderFormService;
	@Autowired
	private GoodsViewTools goodsViewTools;

	@Autowired
	private StoreViewTools storeViewTools;

	@Autowired
	private MsgTools msgTools;

	@Autowired
	private GoodsFloorViewTools gf_tools;
	@Autowired
	private IAddressService addressService;
	@Autowired
	private IOrderFormLogService orderFormLogService;
	@Autowired
	private IOrderComService orderComService; 
	
	
	@Autowired
	private IOrderComPhotoService orderComPhotoService;
	
	@Autowired
	private IJiChuSheZhiService jiChuSheZhiService;

	// 会员订单列表
	@RequestMapping({ "/wap/buyer/order.htm" })
	public ModelAndView buyerorder(HttpServletRequest request, HttpServletResponse response, String currentPage,
			String order_status) {
		ModelAndView mv = new JModelAndView("wap/buyer/order.html", this.configService.getSysConfig(),
				this.userConfigService.getUserConfig(), 0, request, response);
		if (SecurityUserHolder.getCurrentUser() == null) {
			mv = new JModelAndView("wap/buyer/login.html", this.configService.getSysConfig(),
					this.userConfigService.getUserConfig(), 0, request, response);
			return mv;
		}
		OrderFormQueryObject ofqo = new OrderFormQueryObject(currentPage, mv, "addTime", "desc");
		ofqo.addQuery("obj.user.id", new SysMap("user_id", SecurityUserHolder.getCurrentUser().getId()), "=");
		if (!CommUtil.null2String(order_status).equals("")) {
			if (order_status.equals("order_submit")) {
				ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(10)), "=");
			}
			if (order_status.equals("order_pay")) {
				ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(20)), "=");
			}
			if (order_status.equals("order_shipping")) {
				ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(30)), "=");
			}
			if (order_status.equals("order_receive")) {
				ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(40)), "=");
			}
			if (order_status.equals("order_evaluate")) {
				ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(50)), "=");
			}
			if (order_status.equals("order_finish")) {
				ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(60)), "=");
			}
			if (order_status.equals("order_cancel")) {
				ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(0)), "=");
			}
			if (order_status.equals("order_tosendgoods")) {
				ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(70)), "=");
			}
			if (order_status.equals("order_fotgoods")) {
				ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(80)), "=");
			}
		}

		IPageList pList = this.orderFormService.list(ofqo);
		CommUtil.saveIPageList2ModelAndView("", "", "", pList, mv);
		mv.addObject("storeViewTools", this.storeViewTools);
		mv.addObject("order_status", order_status == null ? "all" : order_status);

		return mv;
	}

	// 云客订单列表
	@RequestMapping({ "/wap/yunke/order.htm" })
	public ModelAndView yunkeorder(HttpServletRequest request, HttpServletResponse response, String currentPage,
			String order_status) {
		ModelAndView mv = new JModelAndView("wap/yunke/order.html", this.configService.getSysConfig(),
				this.userConfigService.getUserConfig(), 0, request, response);
		OrderFormQueryObject ofqo = new OrderFormQueryObject(currentPage, mv, "addTime", "desc");
		ofqo.addQuery("obj.user.id", new SysMap("user_id", SecurityUserHolder.getCurrentUser().getId()), "=");
		if (!CommUtil.null2String(order_status).equals("")) {
			if (order_status.equals("order_submit")) {
				ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(10)), "=");
			}
			if (order_status.equals("order_pay")) {
				ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(20)), "=");
			}
			if (order_status.equals("order_shipping")) {
				ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(30)), "=");
			}
			if (order_status.equals("order_receive")) {
				ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(40)), "=");
			}
			if (order_status.equals("order_evaluate")) {
				ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(50)), "=");
			}
			if (order_status.equals("order_finish")) {
				ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(60)), "=");
			}
			if (order_status.equals("order_cancel")) {
				ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(0)), "=");
			}
			if (order_status.equals("order_tosendgoods")) {
				ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(70)), "=");
			}
			if (order_status.equals("order_fotgoods")) {
				ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(80)), "=");
			}
		}

		IPageList pList = this.orderFormService.list(ofqo);
		CommUtil.saveIPageList2ModelAndView("", "", "", pList, mv);
		mv.addObject("storeViewTools", this.storeViewTools);
		mv.addObject("order_status", order_status == null ? "all" : order_status);

		return mv;
	}

	// 云客订单详情
	@RequestMapping({ "/wap/yunke/orderdetail.htm" })
	public ModelAndView yunkeorderdetail(HttpServletRequest request, HttpServletResponse response, String currentPage,
			String order_id) {
		ModelAndView mv = new JModelAndView("wap/yunke/orderdetail.html", this.configService.getSysConfig(),
				this.userConfigService.getUserConfig(), 0, request, response);
		mv.addObject("obj", this.goodsService.getObjById(Long.parseLong(order_id)));
		mv.addObject("storeViewTools", this.storeViewTools);

		return mv;
	}

	// 会员订单详情
	@RequestMapping({ "/wap/buyer/orderdetail.htm" })
	public ModelAndView buyerorderdetail(HttpServletRequest request, HttpServletResponse response, String currentPage,
			String order_id) {
		ModelAndView mv = new JModelAndView("wap/buyer/orderdetail.html", this.configService.getSysConfig(),
				this.userConfigService.getUserConfig(), 0, request, response);
		mv.addObject("obj", this.goodsService.getObjById(Long.parseLong(order_id)));
		mv.addObject("storeViewTools", this.storeViewTools);

		return mv;
	}

	// 会员查看物流
	@RequestMapping({ "/wap/buyer/logistics.htm" })
	public ModelAndView buyerlogistics(HttpServletRequest request, HttpServletResponse response, String currentPage,
			String order_id) {
		ModelAndView mv = new JModelAndView("wap/buyer/orderdetail.html", this.configService.getSysConfig(),
				this.userConfigService.getUserConfig(), 0, request, response);
		mv.addObject("obj", this.goodsService.getObjById(Long.parseLong(order_id)));
		mv.addObject("storeViewTools", this.storeViewTools);

		return mv;
	}

	// 云客查看物流
	@RequestMapping({ "/wap/yunke/logistics.htm" })
	public ModelAndView yunkelogistics(HttpServletRequest request, HttpServletResponse response, String currentPage,
			String order_id) {
		ModelAndView mv = new JModelAndView("wap/yunke/orderdetail.html", this.configService.getSysConfig(),
				this.userConfigService.getUserConfig(), 0, request, response);
		mv.addObject("obj", this.goodsService.getObjById(Long.parseLong(order_id)));
		mv.addObject("storeViewTools", this.storeViewTools);

		return mv;
	}

	// 订单列表
	@RequestMapping({ "/wap/order.htm" })
	@ResponseBody
	public void order(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String currentPage = request.getParameter("currentPage");
		String order_status = request.getParameter("order_status");
		Map params = new HashMap();
		Claims claims = SignFilter.checkSign(request, response);
		if (claims != null) {
			String id = claims.getId();
			System.out.println(id);
			OrderFormQueryObject ofqo = new OrderFormQueryObject(currentPage, params, "addTime", "desc");
			ofqo.addQuery("obj.user.id", new SysMap("user_id", Long.parseLong(id)), "=");
			
			String order_id = request.getParameter("order_id");
			if(order_id!=""&&order_id!=null){
				ofqo.addQuery("obj.order_id", new SysMap("order_id", "%"+order_id+"%"), "like");
			}
			
			if (!CommUtil.null2String(order_status).equals("")) {
				if (order_status.equals("order_submit")) {
					ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(10)), "=");
				}
				if (order_status.equals("order_pay")) {
					ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(20)), "=");
				}
				if (order_status.equals("order_shipping")) {
					ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(30)), "=");
				}
				if (order_status.equals("order_receive")) {
					ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(40)), "=");
				}
				if (order_status.equals("order_evaluate")) {
					ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(50)), "=");
				}
				if (order_status.equals("order_finish")) {
					ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(60)), "=");
				}
				if (order_status.equals("order_cancel")) {
					ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(0)), "=");
				}
				if (order_status.equals("order_tosendgoods")) {
					ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(70)), "=");
				}
				if (order_status.equals("order_fotgoods")) {
					ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(80)), "=");
				}
				if (order_status.equals("order_waitSend")) {
					ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(12)), "=");
				}
				if (order_status.equals("order_waitReceive")) {
					ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(13)), "=");
				}
				if (order_status.equals("order_waitEvaluate")) {
					ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(14)), "=");
				}
			}
			
			IPageList pList = this.orderFormService.list(ofqo);
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
			// 需要过滤的类 + 属性
			Map<Class<?>, Set<String>> includeMap = new HashMap<Class<?>, Set<String>>();
			Set<String> set = new HashSet<String>();
			set.add("id");
			set.add("addTime");
			set.add("store");
			set.add("gcs");
			set.add("order_status");
			set.add("user");
			set.add("totalPrice");
			set.add("goods_amount");
			set.add("order_id");
			set.add("invoice");
			includeMap.put(OrderForm.class, set);

			set = new HashSet<String>();
			set.add("store_name");
			includeMap.put(Store.class, set);

			set = new HashSet<String>();
			set.add("userName");
			includeMap.put(User.class, set);

			set = new HashSet<String>();
			set.add("goods");
			set.add("count");
			set.add("price");
			includeMap.put(GoodsCart.class, set);

			set = new HashSet<String>();
			set.add("seo_keywords");
			set.add("seo_description");
			set.add("goods_name");
			set.add("goods_price");
			set.add("goods_main_photo");
			set.add("goods_specs");
			set.add("id");
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

			JSONArray json = JSONArray.fromObject(pList.getResult(), jsonConfig);
			SignFilter.printNoCheck(request, response, json);
			System.out.println(json);
		}

	}
	/**
	 * 云客下的会员订单列表
	 * @param request
	 * @param response
	 * @throws IOException
	 */
		@RequestMapping({ "/wap/member_order.htm" })
		@ResponseBody
		public void member_order(HttpServletRequest request, HttpServletResponse response) throws IOException {
			String currentPage = request.getParameter("currentPage");
			String order_status = request.getParameter("order_status");
			Map params = new HashMap();
			Claims claims = SignFilter.checkSign(request, response);
			if (claims != null) {
				String id = claims.getId();
				System.out.println(id);
				OrderFormQueryObject ofqo = new OrderFormQueryObject(currentPage, params, "addTime", "desc");
				ofqo.addQuery("obj.user.parent.id", new SysMap("user_id", Long.parseLong(id)), "=");
				if (!CommUtil.null2String(order_status).equals("")) {
					if (order_status.equals("order_submit")) {
						ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(10)), "=");
					}
					if (order_status.equals("order_pay")) {
						ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(20)), "=");
					}
					if (order_status.equals("order_shipping")) {
						ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(30)), "=");
					}
					if (order_status.equals("order_receive")) {
						ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(40)), "=");
					}
					if (order_status.equals("order_evaluate")) {
						ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(50)), "=");
					}
					if (order_status.equals("order_finish")) {
						ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(60)), "=");
					}
					if (order_status.equals("order_cancel")) {
						ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(0)), "=");
					}
					if (order_status.equals("order_tosendgoods")) {
						ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(70)), "=");
					}
					if (order_status.equals("order_fotgoods")) {
						ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(80)), "=");
					}
					if (order_status.equals("order_waitSend")) {
						ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(12)), "=");
					}
					if (order_status.equals("order_waitReceive")) {
						ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(13)), "=");
					}
					if (order_status.equals("order_waitEvaluate")) {
						ofqo.addQuery("obj.order_status", new SysMap("order_status", Integer.valueOf(14)), "=");
					}
				}

				IPageList pList = this.orderFormService.list(ofqo);

				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
				// 需要过滤的类 + 属性
				Map<Class<?>, Set<String>> includeMap = new HashMap<Class<?>, Set<String>>();
				Set<String> set = new HashSet<String>();
				set.add("id");
				set.add("addTime");
				set.add("store");
				set.add("gcs");
				set.add("order_status");
				set.add("user");
				set.add("totalPrice");
				set.add("goods_amount");
				set.add("order_id");
				includeMap.put(OrderForm.class, set);

				set = new HashSet<String>();
				set.add("store_name");
				includeMap.put(Store.class, set);

				set = new HashSet<String>();
				set.add("userName");
				includeMap.put(User.class, set);

				set = new HashSet<String>();
				set.add("goods");
				set.add("count");
				set.add("price");
				includeMap.put(GoodsCart.class, set);

				set = new HashSet<String>();
				set.add("seo_keywords");
				set.add("seo_description");
				set.add("goods_name");
				set.add("goods_price");
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

				JSONArray json = JSONArray.fromObject(pList.getResult(), jsonConfig);
				SignFilter.printNoCheck(request, response, json);
				System.out.println(json);
			}

		}

	// 订单详情
	@RequestMapping({ "/wap/orderdetail.htm" })
	@ResponseBody
	public void orderdetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String currentPage = request.getParameter("currentPage");
		String order_status = request.getParameter("order_status");
		Map params = new HashMap();
		Claims claims = SignFilter.checkSign(request, response);
		if (claims != null) {
			String id = claims.getId();
			String orderid = request.getParameter("orderid");
			OrderForm orderForm = this.orderFormService.getObjById(Long.parseLong(orderid));
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
			// 需要过滤的类 + 属性
			Map<Class<?>, Set<String>> includeMap = new HashMap<Class<?>, Set<String>>();
			Set<String> set = new HashSet<String>();
			set.add("id");
			set.add("addTime");//下单时间
			set.add("payTime");//支付时间
			set.add("store");
			set.add("gcs");
			set.add("order_status");
			set.add("user");
			set.add("totalPrice");
			set.add("order_id");
			set.add("addr");
			includeMap.put(OrderForm.class, set);
			
			set = new HashSet<String>();
			set.add("trueName");
			set.add("area_info");
			set.add("mobile");
			includeMap.put(Address.class, set);
			
			set = new HashSet<String>();
			set.add("store_name");
			set.add("id");
			includeMap.put(Store.class, set);

			set = new HashSet<String>();
			set.add("userName");
			includeMap.put(User.class, set);

			set = new HashSet<String>();
			set.add("goods");
			set.add("count");
			set.add("price");
			includeMap.put(GoodsCart.class, set);

			set = new HashSet<String>();
			set.add("seo_keywords");
			set.add("seo_description");
			set.add("goods_name");
			set.add("goods_price");
			set.add("goods_main_photo");
			set.add("goods_specs");
			set.add("id");
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

			JSONObject json = JSONObject.fromObject(orderForm, jsonConfig);
			SignFilter.printNoCheck(request, response, json);
			System.out.println(json);
		}

	}

	// 移动端下单
	@RequestMapping({ "/wap/ordersave.htm" })
	@ResponseBody
	public void ordersave(HttpServletRequest request, HttpServletResponse response,String goodscart_ids,String storecart_ids,String store_id,String address_id,String totalPrice) throws IOException {

		Claims claims = SignFilter.checkSign(request, response);
		if (claims != null) {
			String id = claims.getId();
			String orderno = "";
			OrderForm orderForm = new OrderForm(); // goods_cart3.
			orderForm.setAddr(addressService.getObjById(CommUtil.null2Long(address_id)));
			orderForm.setAddTime(new Date());
			orderForm.setOrder_status(10);
			orderForm.setOrder_id( CommUtil.formatTime("yyyyMMddHHmmss", new Date())+id);
			orderForm.setStore(this.storeService.getObjById(CommUtil.null2Long(store_id)));
			orderForm.setOrder_type("wap");
			orderForm.setUser(userService.getObjById(CommUtil.null2Long(id)));
			orderForm.setTotalPrice(new BigDecimal(totalPrice));
			//orderForm.setBalance(request.getParameter("balance"));
			orderForm.setPay_password(request.getParameter("pay_password"));
			orderForm.setBalance(new BigDecimal(request.getParameter("deduction")));
			orderForm.setPay_password(request.getParameter("pay_password"));
			boolean bl = this.orderFormService.save(orderForm);
			
			String[] carid = storecart_ids.split(",");
			String[] gcarid = goodscart_ids.split(",");
			/**
			 * 云客ID
			 */
			String ykid=request.getParameter("ykid");
			//订单获取地址ID
			Address address=orderForm.getAddr();
			Area area=address.getArea().getParent();
			//根据商家获取区域栈代
			Map params = new HashMap();
			UserQueryObject ofqo = new UserQueryObject("0", params, "addTime", "desc");
			ofqo.addQuery("obj.area.id", new SysMap("area_id", area.getId()), "="); 
			List<User>lsuser=(List<User>) userService.list(ofqo);
			User fhUser=null;
			int  hwsl=0; 
			// 获取选中的购物车列表
			for (String string : carid) {
				if (StringUtils.isNotEmpty(string)) {
					StoreCart storeCart = storeCartService.getObjById(CommUtil.null2Long(string));
					if (storeCart != null) {
						 
						Iterator<GoodsCart> lsg = storeCart.getGcs().iterator();
						while (lsg.hasNext()) {
							GoodsCart goodsCart = lsg.next();
							
							//获取商家
							User sjuser=storeCart.getUser(); 
							//检索货最全的栈代
							for (User user : lsuser) {
								 GoodsQueryObject goodsQueryObject=new GoodsQueryObject("0", params, "addTime", "desc");
								 ofqo.addQuery("obj.store.user.id", new SysMap("user_id", user.getId()), "=");
								 List<Goods>goods=(List<Goods>) userService.list(ofqo);
								 int count=0;
								 for (Goods goods2 : goods) {
									if(goods2.getId()==goodsCart.getId()){
										count++;
									}
								 }
								 if(count>hwsl){
									 hwsl=count;
									 fhUser=user;
								 }
							}  
							for (String string2 : gcarid) {
								if (StringUtils.isNotEmpty(string2)) {
									if (goodsCart.getId() == Long.parseLong(string2)) {
										goodsCart.setOf(orderForm);
										goodsCart.setStatus(1);
										this.goodsCartService.update(goodsCart);
										// 移除购物车内容
										lsg.remove();
									}
								}

							}

						}
						// 更新店铺购物车
						List<GoodsCart> lsg1 = IteratorUtils.toList(lsg);
						storeCart.setGcs(lsg1);
						this.storeCartService.update(storeCart);
					}

				}

			}
			
			
			if(fhUser==null){
				//检索距离
			      //1.获取定位
				String longitude=request.getParameter("longitude");
				String latitude=request.getParameter("latitude");
				
				Location location=new Location();
				location.setLatitude(Double.parseDouble(latitude));  
				location.setLongitude(Double.parseDouble(longitude));
				  double juli=0;
				  //2.获取栈代商家的定位进行比较
				  for (User user : lsuser) {
					  Location ls=user.getLoc();
					  //距离计算
					  double jl=Distance(ls.getLongitude(), ls.getLatitude(),location.getLongitude(), location.getLatitude()); 
					  if(jl<juli){
						  fhUser=user; 
					  }
				  }
				
				
			}
			 //处理订单
			 GoodsQueryObject goodsQueryObject=new GoodsQueryObject("0", params, "addTime", "desc");
			 ofqo.addQuery("obj .store.user.id", new SysMap("user_id", fhUser.getId()), "=");
			 List<Goods>goods=(List<Goods>) userService.list(ofqo);
			 
			
			
			OrderFormLog ofl = new OrderFormLog();
			ofl.setAddTime(new Date());
			ofl.setOf(orderForm);
			ofl.setLog_info("提交订单");
			ofl.setLog_user(userService.getObjById(CommUtil.null2Long(id)));
			this.orderFormLogService.save(ofl);

			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
			
			Map<Class<?>, Set<String>> includeMap = new HashMap<Class<?>, Set<String>>();
			Set<String> set = new HashSet<String>();
			set.add("id");
			set.add("order_id");
			set.add("totalPrice");
			includeMap.put(OrderForm.class, set);

			
			jsonConfig.setJsonPropertyFilter(new ComplexOutPropertyPreFilter(includeMap));

			JSONObject json = JSONObject.fromObject(orderForm, jsonConfig);
			SignFilter.print(request, response, json);
			if (bl) {
				SignFilter.print(request, response, json);
			} else {
				SignFilter.print(request, response, "下单失败，请稍后再试");
			}
			System.out.println(json);

		}

	}

	/**
	 * 评论列表
	 * 
	 * @param request
	 * @param response
	 * @param sid（店铺ID）
	 * @param oid（订单id）
	 * @throws IOException
	 */
	@RequestMapping({ "/wap/ordercom.htm" })
	@ResponseBody
	public void ordercom(HttpServletRequest request, HttpServletResponse response, String sid)
			throws IOException {
		String currentPage = request.getParameter("currentPage"); 
		Map params = new HashMap();

		OrderComQueryObject gcqo = new OrderComQueryObject(currentPage, params, "addTime", "desc");


		if (StringUtils.isNotEmpty(sid)) {
			gcqo.addQuery("obj.store.id", new SysMap("store_id", Long.parseLong(sid)), "=");
		}

		IPageList pList = this.orderComService.list(gcqo);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
		// 需要过滤的类 + 属性
		Map<Class<?>, Set<String>> includeMap = new HashMap<Class<?>, Set<String>>();
		Set<String> set = new HashSet<String>();
		set.add("id");
		set.add("orderForm");
		set.add("user");
		set.add("store");
		set.add("content");
		set.add("lschild");
		set.add("state");
		set.add("addTime");
		includeMap.put(OrderCom.class, set);

		set = new HashSet<String>();
		set.add("store_name");
		includeMap.put(Store.class, set);

		set = new HashSet<String>();
		set.add("userName");
		set.add("photo");
		includeMap.put(User.class, set);

		set = new HashSet<String>();
		set.add("id");
		set.add("order_id");
		includeMap.put(OrderForm.class, set);

		set = new HashSet<String>();
		set.add("name");
		set.add("path");
		includeMap.put(Accessory.class, set);

		jsonConfig.setJsonPropertyFilter(new ComplexOutPropertyPreFilter(includeMap));

		JSONArray json = JSONArray.fromObject(pList.getResult(), jsonConfig);
		SignFilter.print(request, response, json);
		System.out.println(json);

	}

	/**
	 * 添加评论
	 * 
	 * @param request
	 * @param response
	 * @param oid订单id
	 * @param content评论内容
	 * @param sid店铺id
	 * @param pid父类id
	 * @throws IOException
	 */
	@RequestMapping({ "/wap/ordercomsave.htm" })
	@ResponseBody
	public void ordercomsave(HttpServletRequest request, HttpServletResponse response, String oid, String content,
			String sid, String pid) throws IOException {
		Claims claims = SignFilter.checkSign(request, response);
		if (claims != null) {
			String id = claims.getId();
			//订单
			OrderForm orderForm = this.orderFormService.getObjById(Long.parseLong(oid));
			String wuliupinglun = request.getParameter("wuliupinglun");
			String shangpinglun = request.getParameter("shangpinglun");
			String tiyanpinglun = request.getParameter("tiyanpinglun");
			String zhaopian = request.getParameter("zhaopian");
			String fuwupinglun = request.getParameter("fuwupinglun");
			String fenshu = request.getParameter("fenshu");
			String attitude = request.getParameter("attitude");
			
			//商品id
			String goods_id = request.getParameter("goods_id");
			String[] goodArr = goods_id.split(",");
			String[] storeArr = sid.split(",");
			
			for(int i=0;i<goodArr.length;i++) 
			{
				Store store = this.storeService.getObjById(Long.parseLong(storeArr[i]));
				OrderCom obj = null;
				if (StringUtils.isNotEmpty(pid)) {
					obj = this.orderComService.getObjById(Long.parseLong(pid));
				}
				
				if (store != null && orderForm != null) {
					OrderCom orderCom = new OrderCom();
					orderCom.setAddTime(new Date());
					orderCom.setContent(content);
					orderCom.setStore(store);
					orderCom.setUser(this.userService.getObjById(Long.parseLong(id)));
					orderCom.setOrderForm(orderForm);
					
					orderCom.setGoods(this.goodsService.getObjById(Long.parseLong(goodArr[i])));
					orderCom.setWuliupinglun(wuliupinglun);
					orderCom.setShangpinglun(shangpinglun);
					orderCom.setTiyanpinglun(tiyanpinglun);
					orderCom.setFuwupinglun(fuwupinglun);
//					orderCom.setZhaopian(zhaopian);
					orderCom.setFenshu(fenshu);
					orderCom.setTaidu(attitude);
					
					if (obj != null) {
						orderCom.setParent(obj);
					}
					this.orderComService.save(orderCom);
					
					//评价附件
					String[] picArr = zhaopian.split(",");
					for(String pic : picArr) 
					{
						Accessory acc = new Accessory();
						acc.setAddTime(new Date());
						acc.setDeleteStatus(false);
						acc.setName(pic);
						acc.setPath("upload/img");
						acc.setUser(this.userService.getObjById(Long.parseLong(id)));
						this.accessoryService.save(acc);
						
						OrderComPhoto photoEntity = new OrderComPhoto();
						photoEntity.setAcc(acc);
						photoEntity.setAddTime(new Date());
						photoEntity.setDeleteStatus(false);
						photoEntity.setOrderCom(orderCom);
						this.orderComPhotoService.save(photoEntity);
					}
				}
				
			}
			
			SignFilter.printNoCheck(request, response, "添加成功");
			SignFilter.printNoCheck(request, response, "店铺或者订单不存在！");
		}
	}

	/**
	 * 删除评论
	 * 
	 * @param request
	 * @param response
	 * @param sids（多个评论id用“，”隔开）
	 * @throws IOException
	 */
	@RequestMapping({ "/wap/ordercomdel.htm" })
	@ResponseBody
	public void ordercomdel(HttpServletRequest request, HttpServletResponse response, String sids) throws IOException {
		Claims claims = SignFilter.checkSign(request, response);
		if (claims != null) {
			List<Serializable> list = new ArrayList<>();
			for (String string : sids.split(",")) {
				list.add(string);
			}
			this.orderComService.batchDelete(list);
			SignFilter.printNoCheck(request, response, "删除成功");
		}

	}
	
	// 云客销售统计
	@RequestMapping({ "/wap/ordertwo.htm" })
	@ResponseBody
	public void ordertwo(HttpServletRequest request, HttpServletResponse response, String sid) throws IOException {
		String currentPage = request.getParameter("currentPage");
		String order_status = request.getParameter("order_status");
		Map params = new HashMap();
		Claims claims = SignFilter.checkSign(request, response);
		if (claims != null) {
			params.put("stroeid", Long.parseLong(sid));
			
			// 需要过滤的类 + 属性
			List glist = this.goodsCartService.query(""
					+ "select c.goods.goods_name,c.of.order_status,c.goods.goods_store.id,c.goods.id,c.goods.goods_main_photo.name,c.goods.goods_main_photo.path,sum(c.count) as count from GoodsCart c "
					+"where c.of.order_status=42 and c.of.store.id =:stroeid "
					+ "group by c.goods.goods_name,c.of.order_status,c.goods.goods_store.id,c.goods.id,c.goods.goods_main_photo.name,c.goods.goods_main_photo.path order by sum(c.count) desc", 	
					
					params, -1, -1);

			
			System.out.println(glist.size()+"***************");

			JSONArray json = JSONArray.fromObject(glist);
			SignFilter.printNoCheck(request, response, json);
			System.out.println(json);
		}

	}
	
	// 云客代言销售统计
	@RequestMapping({ "/wap/orderthere.htm" })
	@ResponseBody
	public void orderthere(HttpServletRequest request, HttpServletResponse response, String sid) throws IOException {
		String currentPage = request.getParameter("currentPage");
		String order_status = request.getParameter("order_status");
		Map params = new HashMap();
		//params.put("storeid", sid);
		Claims claims = SignFilter.checkSign(request, response);
		if (claims != null) {
			params.put("storeid", Long.parseLong(sid));
			
			List list = new ArrayList();
			Map map;
			List<StoreGoods> clist = this.storegoodsService.query("select obj from StoreGoods obj where obj.state=1 and obj.store.id=:storeid ",
					params, -1, -1);
			for(int i=0;i<clist.size();i++) {
				map = new HashMap();
				long goodsid=clist.get(i).getGoods().getId();
				Date addTime = clist.get(i).getAddTime();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateString = formatter.format(addTime);
				params.put("goodsid", goodsid);
				List glist = this.goodsCartService.query(""
						+ "select c.goods.goods_name,c.of.order_status,c.goods.goods_store.id,c.goods.id,c.goods.goods_main_photo.name,c.goods.goods_main_photo.path,sum(c.count) as count from GoodsCart c "
						+"where c.of.order_status=42 and c.of.store.id =:storeid and c.goods.id=:goodsid "
						+ "group by c.goods.goods_name,c.of.order_status,c.goods.goods_store.id,c.goods.id,c.goods.goods_main_photo.name,c.goods.goods_main_photo.path order by sum(c.count) desc", 	
						params, -1, -1);
				//System.out.println(glist.get(0).toString());
				map.put("goods_id",goodsid);
				map.put("addTime", dateString);
				map.put("list", glist);
				
				list.add(map);
			}
			
			
			

			JSONArray json = JSONArray.fromObject(list);
			SignFilter.printNoCheck(request, response, json);
			
			System.out.println(json);
		}

	}
	
	/**
	 * 判断商品是否已评价
	 * @param request
	 * @param response
	 * @param currentPage
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping({ "/wap/ajaxGetOrdercomByGid.htm" })
    @ResponseBody
	public void ajaxGetOrdercomByGid(HttpServletRequest request, HttpServletResponse response,String currentPage) throws IOException {
		Map params = new HashMap();
		params.put("state", -1);
    	Claims claims = SignFilter.checkSign(request, response);
		if (claims != null) {
			String id = claims.getId();
			String goodsid = request.getParameter("goodsid");//商品id
			String orderformid = request.getParameter("orderformid");
			
			Map paramMap = new HashMap();
			paramMap.put("goodsid", Long.parseLong(goodsid));
			paramMap.put("userid", Long.parseLong(id));
			List list = this.orderComService.query("select obj from OrderCom obj where obj.goods.id=:goodsid and obj.parent.id is null "
					+ " and obj.user.id=:userid order by obj.addTime asc ", paramMap, -1, -1);
			
			//获取设置中商品评价天数
			int zb_sppjts =0;
			List sheZhiList =jiChuSheZhiService.query("select obj from JiChuSheZhi obj ", null, -1, -1);
			if(sheZhiList!=null && sheZhiList.size()>0) {
				JiChuSheZhi shezhi =(JiChuSheZhi)sheZhiList.get(0);
				zb_sppjts = shezhi.getZb_sppjts();
			}else {
				zb_sppjts =15;
			}
			
			if(list!=null && list.size()>0) {
				OrderCom ordercom =(OrderCom) list.get(0);
				Date addTime = ordercom.getAddTime();
				Calendar cd = Calendar.getInstance();
				cd.setTime(addTime);
				cd.add(Calendar.DATE, zb_sppjts);
				Date  dat = cd.getTime();
				////表示dat小于new Date()
				if (dat.before(new Date())){ 
				 params.put("state", 1);
				}else {
				 params.put("state", 0);
				}
			}
		}
		JSONArray json = JSONArray.fromObject(params);
		SignFilter.printNoCheck(request, response, params);
		System.out.println(json);
		
	}
	

	/**
	 * 支付 修改订单状态
	 */
	@RequestMapping({ "/wap/orderpaybalance.htm" })
	@ResponseBody
	public void orderpaybalance(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map params = new HashMap();
		String orderid = request.getParameter("orderid");
		Claims claims = SignFilter.checkSign(request, response);
		if (claims != null) {
			OrderForm orderForm = this.orderFormService.getObjById(CommUtil.null2Long(orderid));
			orderForm.setOrder_status(20);
			
		}
	}
	 /** 
     * 计算地球上任意两点(经纬度)距离 
     *  
     * @param long1 
     *            第一点经度 
     * @param lat1 
     *            第一点纬度 
     * @param long2 
     *            第二点经度 
     * @param lat2 
     *            第二点纬度 
     * @return 返回距离 单位：米 
     */  
    public static double Distance(double long1, double lat1, double long2,  
            double lat2) {  
        double a, b, R;  
        R = 6378137; // 地球半径  
        lat1 = lat1 * Math.PI / 180.0;  
        lat2 = lat2 * Math.PI / 180.0;  
        a = lat1 - lat2;  
        b = (long1 - long2) * Math.PI / 180.0;  
        double d;  
        double sa2, sb2;  
        sa2 = Math.sin(a / 2.0);  
        sb2 = Math.sin(b / 2.0);  
        d = 2* R* Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)* Math.cos(lat2) * sb2 * sb2));  
        return d;  
    }
}

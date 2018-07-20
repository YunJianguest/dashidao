package com.dashidao.view.web.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.dashidao.core.domain.virtual.SysMap;
import com.dashidao.core.query.support.IPageList;
import com.dashidao.foundation.domain.Accessory;
import com.dashidao.foundation.domain.ChongZhiJiLu;
import com.dashidao.foundation.domain.Goods;
import com.dashidao.foundation.domain.GoodsCart;
import com.dashidao.foundation.domain.GoodsSpecProperty;
import com.dashidao.foundation.domain.GoodsSpecification;
import com.dashidao.foundation.domain.JiChuSheZhi;
import com.dashidao.foundation.domain.OrderForm;
import com.dashidao.foundation.domain.Setting;
import com.dashidao.foundation.domain.Store;
import com.dashidao.foundation.domain.TuiKuanShouHou;
import com.dashidao.foundation.domain.User;
import com.dashidao.foundation.domain.XiaoFeiJiLu;
import com.dashidao.foundation.domain.YunkeAudit;
import com.dashidao.foundation.domain.query.ChongZhiJiLuQueryObject;
import com.dashidao.foundation.domain.query.JiChuSheZhiQueryObject;
import com.dashidao.foundation.domain.query.SettingQueryObject;
import com.dashidao.foundation.domain.query.TuiKuanShouHouQueryObject;
import com.dashidao.foundation.domain.query.XiaoFeiJiLuQueryObject;
import com.dashidao.foundation.domain.query.YunkeAuditQueryObject;
import com.dashidao.foundation.service.IAccessoryService;
import com.dashidao.foundation.service.IChongZhiJiLuService;
import com.dashidao.foundation.service.IGoodsService;
import com.dashidao.foundation.service.IJiChuSheZhiService;
import com.dashidao.foundation.service.IOrderFormService;
import com.dashidao.foundation.service.ISettingService;
import com.dashidao.foundation.service.ISysConfigService;
import com.dashidao.foundation.service.ITuiKuanShouHouService;
import com.dashidao.foundation.service.IUserConfigService;
import com.dashidao.foundation.service.IUserService;
import com.dashidao.foundation.service.IXiaoFeiJiLuService;
import com.dashidao.foundation.service.IYunkeAuditService;
import com.dashidao.jwt.filter.SignFilter;
import com.dashidao.jwt.util.ComplexOutPropertyPreFilter;
import com.dashidao.jwt.util.DateJsonValueProcessor;

import io.jsonwebtoken.Claims;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * 消费记录和充值
 * @author Administrator
 *
 */
@Controller
public class ConsumeViewAciton {
	 	@Autowired
	    private ISysConfigService configService;

	    @Autowired
	    private IUserConfigService userConfigService;
	    @Autowired
	    private IXiaoFeiJiLuService xiaoFeiJiLuService;
	    @Autowired
	    private IChongZhiJiLuService chongZhiJiLuService;
	    @Autowired
	    private IJiChuSheZhiService jiChuSheZhiService;
	    @Autowired
	    private IUserService userService;
	    
	    @Autowired
	    private IYunkeAuditService yunkeAuditService;
	    @Autowired
	    private ITuiKuanShouHouService tuiKuanShouHouService;
	    
	    @Autowired
	    private IGoodsService goodsService;
	    
	    @Autowired
	    private IOrderFormService orderFormService; 
	    
	    @Autowired
	    private IAccessoryService accessoryService;
	    
	    @Autowired
	    private ISettingService settingService;
	    
	    /**
	     * 消费记录
	     * @param request
	     * @param response
	     * @param currentPage
	     * @throws IOException
	     */
	    @RequestMapping({ "/wap/xiaoFeiJiLu_list.htm" })
	    @ResponseBody
	    public void xiaoFeiJiLu_list(HttpServletRequest request, HttpServletResponse response,String currentPage) throws IOException 
	    {
	    	Map params = new HashMap();
	    	Claims claims = SignFilter.checkSign(request, response);
			if (claims != null) {
				String id = claims.getId();
				XiaoFeiJiLuQueryObject qo = new XiaoFeiJiLuQueryObject(currentPage, params,"addTime", "desc");
				qo.addQuery("obj.orderForm.user.id", new SysMap("user_id", Long.parseLong(id)), "=");
				IPageList pList = this.xiaoFeiJiLuService.list(qo);
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
				// 需要过滤的类 + 属性
				Map<Class<?>, Set<String>> includeMap = new HashMap<Class<?>, Set<String>>();
				Set<String> set = new HashSet<String>();
				set.add("xiaofeiname");
				set.add("addTime");
				set.add("order_bh");
				set.add("jineE");
				includeMap.put(XiaoFeiJiLu.class, set);
				jsonConfig.setJsonPropertyFilter(new ComplexOutPropertyPreFilter(includeMap));
				JSONArray json = JSONArray.fromObject(pList.getResult(), jsonConfig);
				SignFilter.printNoCheck(request, response, json);
				System.out.println(json);
				
			}
	    }
	    
	    /**
	     * 充值记录
	     * @param request
	     * @param response
	     * @param currentPage
	     * @throws IOException
	     */
	    @RequestMapping({ "/wap/chongZhiJiLu_list.htm" })
	    @ResponseBody
	    public void chongZhiJiLu_list(HttpServletRequest request, HttpServletResponse response,String currentPage) throws IOException 
	    {
	    	Map params = new HashMap();
	    	Claims claims = SignFilter.checkSign(request, response);
			if (claims != null) {
				String id = claims.getId();
				ChongZhiJiLuQueryObject qo = new ChongZhiJiLuQueryObject(currentPage, params,"addTime", "desc");
				qo.addQuery("obj.user.id", new SysMap("user_id", Long.parseLong(id)), "=");
				IPageList pList = this.chongZhiJiLuService.list(qo);
				
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
				// 需要过滤的类 + 属性
				Map<Class<?>, Set<String>> includeMap = new HashMap<Class<?>, Set<String>>();
				Set<String> set = new HashSet<String>();
				set.add("cz_type");
				set.add("zf_type");
				set.add("addTime");
				set.add("cz_jinE");
				includeMap.put(ChongZhiJiLu.class, set);
				jsonConfig.setJsonPropertyFilter(new ComplexOutPropertyPreFilter(includeMap));
				JSONArray json = JSONArray.fromObject(pList.getResult(), jsonConfig);
				SignFilter.printNoCheck(request, response, json);
				System.out.println(json);
			}
	    }
	    
	    /**
	     * 会员升级云客累计消费金额标准
	     * @param request
	     * @param response
	     * @param currentPage
	     * @throws IOException
	     */
	    @RequestMapping({ "/wap/jiChuSheZhi_yunKe.htm" })
	    @ResponseBody
	    public void jiChuSheZhi_yunKe(HttpServletRequest request, HttpServletResponse response,String currentPage) throws IOException 
	    {
	    	Map params = new HashMap();
	    	Claims claims = SignFilter.checkSign(request, response);
			if (claims != null) {
				String id = claims.getId();
				JiChuSheZhiQueryObject qo = new JiChuSheZhiQueryObject(currentPage, params,"addTime", "desc");
				IPageList pList = this.jiChuSheZhiService.list(qo);
				
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
				// 需要过滤的类 + 属性
				Map<Class<?>, Set<String>> includeMap = new HashMap<Class<?>, Set<String>>();
				Set<String> set = new HashSet<String>();
				set.add("zb_jifendixiao");//会员 升级云客消费标准
				/**
				 * 云客考核标准
				 */
				set.add("zb_cjkhbz");
				set.add("zb_zjkhbz");
				includeMap.put(JiChuSheZhi.class, set);
				jsonConfig.setJsonPropertyFilter(new ComplexOutPropertyPreFilter(includeMap));
				JSONArray json = JSONArray.fromObject(pList.getResult(), jsonConfig);
				SignFilter.printNoCheck(request, response, json);
				System.out.println(json);
			}
	    }
	    
	    /**
	     * 查询消费金额
	     * @param request
	     * @param response
	     * @param currentPage
	     * @param userType 类型
	     * @throws IOException
	     */
	    @RequestMapping({ "/wap/xiaoFeiJinEBy.htm" })
	    @ResponseBody
	    public void xiaoFeiJinEBy(HttpServletRequest request, HttpServletResponse response,String currentPage,String userType) throws IOException 
	    {
	    	Map params = new HashMap();
	    	params.put("state", 0);
	    	BigDecimal zongJinE= new BigDecimal("0");
	    	Claims claims = SignFilter.checkSign(request, response);
			if (claims != null) {
				String id = claims.getId();
				Map paramMap = new HashMap();
				paramMap.put("addTime", new Date());
				String paramString ="";
				if(userType!=null && !"".equals(userType)) 
				{
					if(userType.equals("day")) //日收益
					{
						paramString ="select SUM(obj.jineE) as jineE from XiaoFeiJiLu obj where TO_DAYS(obj.addTime) = TO_DAYS(:addTime)";
					}else if(userType.equals("month"))// 月收益
					{
						paramString ="select SUM(obj.jineE) as jineE from XiaoFeiJiLu obj where MONTH(obj.addTime) = MONTH(:addTime)";
					}else if(userType.equals("year")) //年收益
					{
						paramString ="select SUM(obj.jineE) as jineE from XiaoFeiJiLu obj where YEAR(obj.addTime) = YEAR(:addTime)";
					}
					
				}
				List list = this.xiaoFeiJiLuService.query(paramString, paramMap, -1, -1);
				params.put("state", 1);
				params.put("list", list);
			}
			JSONArray json = JSONArray.fromObject(params);
			SignFilter.printNoCheck(request, response, params);
			System.out.println(json);
	    }
	    
	    
	    /**
	     * 消费总金额
	     * @param request
	     * @param response
	     * @param currentPage
	     * @throws IOException
	     * @throws ParseException 
	     */
	    @RequestMapping({ "/wap/xiaoFeiZongE.htm" })
	    @ResponseBody
	    public void xiaoFeiZongE(HttpServletRequest request, HttpServletResponse response,String currentPage,String userType) throws IOException, ParseException 
	    {
	    	Map params = new HashMap();
	    	params.put("state", 0);
	    	BigDecimal zongJinE= new BigDecimal("0");
	    	Claims claims = SignFilter.checkSign(request, response);
			if (claims != null) {
				String id = claims.getId();
				XiaoFeiJiLuQueryObject qo = new XiaoFeiJiLuQueryObject(currentPage, params,"addTime", "desc");
				if(userType!=null && !"".equals(userType)) 
				{
					if(userType.equals("user")) //会员查询消费记录
					{
						qo.addQuery("obj.orderForm.user.id", new SysMap("user_id", Long.parseLong(id)), "=");
					}else if(userType.equals("store")) {//云客累计收益
						qo.addQuery("obj.orderForm.store.id", new SysMap("user_id", Long.parseLong(id)), "=");
					}
				}
				IPageList pList = this.xiaoFeiJiLuService.list(qo);
				if(pList!=null && pList.getResult()!=null) 
				{
					for(int i=0;i<pList.getResult().size();i++) 
					{
						XiaoFeiJiLu entity = (XiaoFeiJiLu)pList.getResult().get(i);
						BigDecimal jineE= entity.getJineE();
						zongJinE = zongJinE.add(jineE);
					}
				}
				params.put("state", 1);
				params.put("zongJinE", zongJinE);
			}
			JSONArray json = JSONArray.fromObject(params);
			SignFilter.printNoCheck(request, response, params);
			System.out.println(json);
	    }
	    
	    
	    /**
	     * 统计 日、月、年收益
	     * @param request
	     * @param response
	     * @param currentPage
	     * @param userType 类型
	     * @throws IOException
	     */
	    @RequestMapping({ "/wap/tongJiShouYi_yunKe.htm" })
	    @ResponseBody
	    public void tongJiShouYi_yunKe(HttpServletRequest request, HttpServletResponse response,String currentPage,String userType) throws IOException 
	    {
	    	Map params = new HashMap();
	    	params.put("state", 0);
	    	BigDecimal zongJinE= new BigDecimal("0");
	    	Claims claims = SignFilter.checkSign(request, response);
			if (claims != null) {
				String id = claims.getId();
				Map paramMap = new HashMap();
				String paramString ="";
				paramString ="select obj.addTime,SUM(obj.jineE) as jineE from XiaoFeiJiLu obj group by TO_DAYS(obj.addTime) order by obj.addTime desc ";
				List list = this.xiaoFeiJiLuService.query(paramString, paramMap, -1, -1);
				params.put("list1", list);
				paramString ="select obj.addTime,SUM(obj.jineE) as jineE from XiaoFeiJiLu obj group by MONTH(obj.addTime) order by obj.addTime desc";
				list = this.xiaoFeiJiLuService.query(paramString, paramMap, -1, -1);
				params.put("list2", list);
				paramString ="select obj.addTime,SUM(obj.jineE) as jineE from XiaoFeiJiLu obj group by YEAR(obj.addTime) order by obj.addTime desc";
				list = this.xiaoFeiJiLuService.query(paramString, paramMap, -1, -1);
				params.put("list3", list);
				params.put("state", 1);
			}
			JSONArray json = JSONArray.fromObject(params);
			SignFilter.printNoCheck(request, response, params);
			System.out.println(json);
	    }
	    
	    
	    /**
	     * 会员申请为云客
	     * @param request
	     * @param response
	     * @param currentPage
	     * @throws IOException
	     */
	    @RequestMapping({ "/wap/shenQingYunKe.htm" })
	    @ResponseBody
	    public void shenQingYunKe(HttpServletRequest request, HttpServletResponse response,String currentPage) throws IOException 
	    {
	    	Map params = new HashMap();
	    	params.put("state", 0);
	    	Claims claims = SignFilter.checkSign(request, response);
			if (claims != null) 
			{
				String id = claims.getId();
				User user = this.userService.getObjById(Long.parseLong(id));//申请人
				
				YunkeAudit yunKeEntity = new YunkeAudit();
				yunKeEntity.setApply(user);
				yunKeEntity.setCreateDate(new Date());
				yunKeEntity.setAddTime(new Date());
				yunKeEntity.setState(0);//状态0未审核1审核成功2审核失败
				yunKeEntity.setDeleteStatus(false);
				this.yunkeAuditService.save(yunKeEntity);
				params.put("state", 1);
			}
			JSONArray json = JSONArray.fromObject(params);
			SignFilter.printNoCheck(request, response, params);
			System.out.println(json);
	    }
	    
	    /**
	     * 会员申请成为云客情况
	     * @param request
	     * @param response
	     * @param currentPage
	     * @throws IOException
	     */
	    @RequestMapping({ "/wap/yunKeShenHeInfo.htm" })
	    @ResponseBody
	    public void yunKeShenHeInfo(HttpServletRequest request, HttpServletResponse response,String currentPage) throws IOException 
	    {
	    	Map params = new HashMap();
	    	Claims claims = SignFilter.checkSign(request, response);
			if (claims != null) 
			{
				String id = claims.getId();
				YunkeAuditQueryObject qo= new YunkeAuditQueryObject(currentPage, params,"createDate", "desc");
				qo.addQuery("obj.apply.id", new SysMap("user_id",Long.parseLong(id)), "=");
				IPageList pList= this.yunkeAuditService.list(qo);
				List list = null;
				if(pList.getResult()!=null) {
					list =(List) pList.getResult().get(0);
				}
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy年MM月dd日 "));
				// 需要过滤的类 + 属性
				Map<Class<?>, Set<String>> includeMap = new HashMap<Class<?>, Set<String>>();
				Set<String> set = new HashSet<String>();
				set.add("createDate");
				set.add("state");
				set.add("apply");
				includeMap.put(YunkeAudit.class, set);
				
				set = new HashSet<String>();
				set.add("userName");
				set.add("id");
				includeMap.put(User.class, set);
				jsonConfig.setJsonPropertyFilter(new ComplexOutPropertyPreFilter(includeMap));
				JSONArray json = JSONArray.fromObject(list, jsonConfig);
				SignFilter.printNoCheck(request, response, json);
				System.out.println(json);
			}
	    }
	    
	    
	    /**
	     * 售后申请记录list
	     * @param request
	     * @param response
	     * @param currentPage
	     * @param orderType
	     * @throws IOException
	     */
	    @RequestMapping({ "/wap/shouHouJiLu_list.htm" })
	    @ResponseBody
	    public void shouHouJiLu_list(HttpServletRequest request, HttpServletResponse response,String currentPage,String orderType) throws IOException 
	    {
	    	Map params = new HashMap();
	    	params.put("state", 0);
	    	Claims claims = SignFilter.checkSign(request, response);
			if (claims != null) 
			{
				String id = claims.getId();
				TuiKuanShouHouQueryObject qo = new TuiKuanShouHouQueryObject(currentPage, params,"addTime", "desc");
				if(orderType!=null && !"".equals(orderType)) 
				{
					if(orderType.equals("1")) 
					{
						qo.addQuery("obj.orderForm.user.id", new SysMap("user_id", Long.parseLong(id)), "=");
					}else 
					{
						qo.addQuery("obj.orderForm.user.parent.id", new SysMap("user_id", Long.parseLong(id)), "=");
					}
				}
				
				//服务编号
				String dingdanbianhao = request.getParameter("dingdanbianhao");
				if(dingdanbianhao!=null && !"".equals(dingdanbianhao)) 
				{
					qo.addQuery("obj.zd_dingdanbianhao", new SysMap("zd_dingdanbianhao", "%"+dingdanbianhao+"%"), "like");
					qo.addQuery("obj.orderForm.order_id", new SysMap("orderFormorder_id","%"+dingdanbianhao+"%"), "like", "or");
				}
				
				IPageList pList = this.tuiKuanShouHouService.list(qo);
				
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
				// 需要过滤的类 + 属性
				Map<Class<?>, Set<String>> includeMap = new HashMap<Class<?>, Set<String>>();
				Set<String> set = new HashSet<String>();
				set.add("id");
				set.add("addTime");
				set.add("orderForm");
				set.add("goods");
				set.add("zd_dingdanbianhao");
				includeMap.put(TuiKuanShouHou.class, set);
				
				set = new HashSet<String>();
				set.add("id");
				set.add("addTime");
				set.add("order_id");
				includeMap.put(OrderForm.class, set);

				set = new HashSet<String>();
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

				jsonConfig.setJsonPropertyFilter(new ComplexOutPropertyPreFilter(includeMap));
				JSONArray json = JSONArray.fromObject(pList.getResult(), jsonConfig);
				SignFilter.printNoCheck(request, response, json);
				System.out.println(json);
			}
	    }
	    
	    /**
	     * 订单售后申请
	     * @param request
	     * @param response
	     * @param currentPage
	     * @param orderFormId 订单id
	     * @throws IOException
	     */
	    @RequestMapping({ "/wap/shouShouShenQing_save.htm" })
	    @ResponseBody
	    public void shouShouShenQing_save(HttpServletRequest request, HttpServletResponse response,String currentPage,String orderFormId) throws IOException 
	    {
	    	Map params = new HashMap();
	    	params.put("state", 0);
	    	Claims claims = SignFilter.checkSign(request, response);
			if (claims != null) 
			{
				String id = claims.getId();
				//订单
				OrderForm orderFormEntity= this.orderFormService.getObjById(Long.parseLong(orderFormId));
				//商品IDS
				String goodIds = request.getParameter("goodIds");
				String[] idsArr= goodIds.split(",");
				String leixing = request.getParameter("leixing");
				String yuanyin = request.getParameter("yuanyin");
				String shuLiang = request.getParameter("shuLiang");
				String order_price = request.getParameter("order_price");
				String shuoMing = request.getParameter("shuoming");
				String up_picture = request.getParameter("up_picture");
				for(String goodsId : idsArr) 
				{
					//商品
					Goods goodsEntity= this.goodsService.getObjById(Long.parseLong(goodsId));
					TuiKuanShouHou shouHouEntity = new TuiKuanShouHou();
					shouHouEntity.setAddTime(new Date());
					shouHouEntity.setCreate_date(new Date());
					shouHouEntity.setCreate_datezd_riqi(new Date());
					shouHouEntity.setDeleteStatus(false);
					shouHouEntity.setStates(0);
					shouHouEntity.setZd_num(Integer.parseInt(shuLiang));
					shouHouEntity.setZd_shenqingliyou(yuanyin);
					shouHouEntity.setZd_shouhouleixing(leixing);
					shouHouEntity.setZd_shouhoushuoming(shuoMing);
					shouHouEntity.setGoods(goodsEntity);
					shouHouEntity.setOrderForm(orderFormEntity);
					//上传图片
					if(up_picture!=null && !"".equals(up_picture)) 
					{
						Accessory acc= new Accessory();
						acc.setAddTime(new Date());
						acc.setDeleteStatus(false);
						acc.setName(up_picture);
						acc.setPath("upload/img");
						acc.setUser(this.userService.getObjById(Long.parseLong(id)));
						this.accessoryService.save(acc);
						shouHouEntity.setAccess(acc);
					}
					
					//编号 ： 时间 + 序号
		            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		            String dateStr = df.format(new Date());
		            List<TuiKuanShouHou> listc= this.tuiKuanShouHouService.query("select obj from TuiKuanShouHou obj ", null, -1, -1);
		            String bh = dateStr + listc.size();
		            shouHouEntity.setZd_dingdanbianhao(bh);
					
					boolean b =this.tuiKuanShouHouService.save(shouHouEntity);
					if(b) 
					{
						params.put("state", 1);
					}
				}
				
			}
			JSONArray json = JSONArray.fromObject(params);
			SignFilter.printNoCheck(request, response, params);
			System.out.println(json);
	    }
	    
	    
	    /**
	     * 基础设置 --推荐商家奖
	     * @param request
	     * @param response
	     * @param currentPage
	     * @throws IOException
	     */
	    @RequestMapping({ "/wap/shangJiaSetting.htm" })
	    @ResponseBody
	    public void shangJiaSetting(HttpServletRequest request, HttpServletResponse response,String currentPage) throws IOException 
	    {
	    	Map params = new HashMap();
	    	Claims claims = SignFilter.checkSign(request, response);
	    	if (claims != null) {
				String id = claims.getId();
				List<Setting> list= this.settingService.query("select obj from Setting obj where DATE_FORMAT(obj.zb_enddate,'yyyy-MM-dd') >= DATE_FORMAT(NOW(),'yyyy-MM-dd') order by obj.zb_enddate ", null, -1, -1);
				
				JsonConfig jsonConfig = new JsonConfig();
				jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
				// 需要过滤的类 + 属性
				Map<Class<?>, Set<String>> includeMap = new HashMap<Class<?>, Set<String>>();
				Set<String> set = new HashSet<String>();
				set.add("id");
				set.add("zb_one_bigDecimal_attr");
				includeMap.put(Setting.class, set);
				
				jsonConfig.setJsonPropertyFilter(new ComplexOutPropertyPreFilter(includeMap));
				JSONArray json = JSONArray.fromObject(list, jsonConfig);
				SignFilter.printNoCheck(request, response, json);
				System.out.println(json);
	    	}
	    }
	    
	    

}

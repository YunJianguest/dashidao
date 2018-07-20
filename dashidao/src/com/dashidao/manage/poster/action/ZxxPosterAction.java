package com.dashidao.manage.poster.action;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dashidao.core.annotation.SecurityMapping;
import com.dashidao.core.domain.virtual.SysMap;
import com.dashidao.core.mv.JModelAndView;
import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.security.support.SecurityUserHolder;
import com.dashidao.core.tools.CommUtil;
import com.dashidao.foundation.domain.Accessory;
import com.dashidao.foundation.domain.AgentStore;
import com.dashidao.foundation.domain.Goods;
import com.dashidao.foundation.domain.GoodsCart;
import com.dashidao.foundation.domain.GoodsSpecProperty;
import com.dashidao.foundation.domain.GoodsSpecification;
import com.dashidao.foundation.domain.OrderForm;
import com.dashidao.foundation.domain.OrderFormLog;
import com.dashidao.foundation.domain.PoSunBuJiGl;
import com.dashidao.foundation.domain.QingCangShangPin;
import com.dashidao.foundation.domain.Qingcanghuodong;
import com.dashidao.foundation.domain.ShouHouXiangQing;
import com.dashidao.foundation.domain.Store;
import com.dashidao.foundation.domain.StoreGoods;
import com.dashidao.foundation.domain.TuiHuanHuoGl;
import com.dashidao.foundation.domain.TuiKuanShouHou;
import com.dashidao.foundation.domain.User;
import com.dashidao.foundation.domain.query.AgentGoodsQueryObject;
import com.dashidao.foundation.domain.query.GoodsCartQueryObject;
import com.dashidao.foundation.domain.query.GoodsQueryObject;
/*import com.dashidao.foundation.domain.query.ZxxPosterFormQueryObject;*/
import com.dashidao.foundation.domain.query.OrderFormQueryObject;
import com.dashidao.foundation.domain.query.QingCangHuoDongQueryObject;
import com.dashidao.foundation.domain.query.QingCangShangPinQueryObject;
import com.dashidao.foundation.domain.query.StoreQueryObject;
import com.dashidao.foundation.domain.query.TuiKuanShouHouQueryObject;
import com.dashidao.foundation.service.IAddressService;
import com.dashidao.foundation.service.IAgentGoodsService;
import com.dashidao.foundation.service.IAgentStoreService;
import com.dashidao.foundation.service.IGoodsCartService;
import com.dashidao.foundation.service.IGoodsReturnItemService;
import com.dashidao.foundation.service.IGoodsReturnService;
import com.dashidao.foundation.service.IGoodsService;
import com.dashidao.foundation.service.IOrderFormLogService;
import com.dashidao.foundation.service.IOrderFormService;
import com.dashidao.foundation.service.IQingCangHuoDongService;
import com.dashidao.foundation.service.IQingCangShangPinServiceImpl;
import com.dashidao.foundation.service.IStoreCartService;
import com.dashidao.foundation.service.IStoreService;
import com.dashidao.foundation.service.ISysConfigService;
import com.dashidao.foundation.service.ITuiKuanShouHouService;
import com.dashidao.foundation.service.IUserConfigService;
import com.dashidao.foundation.service.IUserService;
import com.dashidao.jwt.config.ResponseMsg;
import com.dashidao.jwt.filter.SignFilter;
import com.dashidao.jwt.util.ComplexOutPropertyPreFilter;
import com.dashidao.jwt.util.DateJsonValueProcessor;
import com.dashidao.manage.admin.tools.StoreTools;
import com.dashidao.view.web.tools.StoreViewTools;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 卖家退货控制器
 */
@Controller


public class ZxxPosterAction {

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private IUserConfigService userConfigService;
    
    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderFormService orderFormService;

    @Autowired
    private IGoodsReturnService goodsReturnService;
    @Autowired
    private StoreViewTools storeViewTools;

    @Autowired
    private IGoodsReturnItemService goodsReturnItemService;
    
    @Autowired
    private IQingCangHuoDongService qingCangHuoDongService;
    
    @Autowired
    private IQingCangShangPinServiceImpl qingCangShangPinServiceImpl;
    
    @Autowired
    private IGoodsService goodsService;
    
    @Autowired
    private StoreTools storeTools;

    @Autowired
    private IAgentGoodsService agentgoodsService;
    
    @Autowired
    private IAgentStoreService agentstoreService;
    
    @Autowired
	private IAddressService addressService;
	@Autowired
	private IOrderFormLogService orderFormLogService;
	@Autowired
	private IStoreCartService storeCartService;
	@Autowired
	private IStoreService storeService;
	@Autowired
	private IGoodsCartService goodsCartService;
	
	@Autowired
	private ITuiKuanShouHouService tuiKuanShouHouService;
    

    @SecurityMapping(display = false, rsequence = 0, title = "栈代删除列表", value = "/poster/zhandaisc.htm*", rtype = "poster", rname = "栈代删除", rcode = "zhandaisc_poster", rgroup = "商品管理")
    @RequestMapping({"/poster/zhandaisc.htm"})
    public ModelAndView zhandaisc(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandaisc.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "栈代信息列表", value = "/poster/zhandaixx.htm*", rtype = "poster", rname = "栈代信息", rcode = "zhandaixx_poster", rgroup = "栈代管理")
    @RequestMapping({"/poster/zhandaixx.htm"})
    public ModelAndView zhandaixx(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/zhandaixx.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "栈代合同列表", value = "/poster/zhandaiht.htm*", rtype = "poster", rname = "合同信息", rcode = "zhandaiht_poster", rgroup = "栈代管理")
    @RequestMapping({"/poster/zhandaiht.htm"})
    public ModelAndView zhandaiht(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/zhandaiht.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "栈代资金列表", value = "/poster/duizhangzhongxin.htm*", rtype = "poster", rname = "对账中心", rcode = "duizhangzhongxin_poster", rgroup = "资金管理")
    @RequestMapping({"/poster/duizhangzhongxin.htm"})
    public ModelAndView duizhangzhongxin(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/duizhangzhongxin.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "银行卡设置", value = "/poster/yinhangka.htm*", rtype = "poster", rname = "银行卡管理", rcode = "yinhangka_poster", rgroup = "资金管理")
    @RequestMapping({"/poster/yinhangka.htm"})
    public ModelAndView yinhangka(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/yinhangka.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }

    /**
     * 销售明细
     * @param request
     * @param response
     * @param id
     * @return
     */
    @SecurityMapping(display = false, rsequence = 0, title = "销售统计表", value = "/poster/xiaoshoumingxi.htm*", rtype = "poster", rname = "销售明细一览表", rcode = "xiaoshoumingxi_poster", rgroup = "统计管理")
    @RequestMapping({"/poster/xiaoshoumingxi.htm"})
    public ModelAndView xiaoshoumingxi(HttpServletRequest request, HttpServletResponse response,String currentPage, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/xiaoshoumingxi.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
//        OrderFormQueryObject qo = new OrderFormQueryObject(currentPage, mv,"addTime", "desc");
        GoodsCartQueryObject qo = new GoodsCartQueryObject(currentPage, mv,"addTime", "desc");
        qo.addQuery("obj.of.user.id", new SysMap("userid",SecurityUserHolder.getCurrentUser().getId()), "=");
        qo.addQuery("obj.status", new SysMap("status",Integer.valueOf(1)), "=");
        IPageList plist = this.goodsCartService.list(qo);
        
       Map params = new HashMap();
        params.put("status", Integer.valueOf(1));
        String paramString ="select sum(obj.count) AS count,SUM(obj.price) AS price,obj.goods.id,obj.goods.goods_name,obj.goods.goods_serial from GoodsCart obj ";
         	   paramString +="  WHERE obj.status=:status ";
         	   paramString +="  GROUP BY obj.goods.id,obj.goods.goods_name,obj.goods.goods_serial ";
         	   paramString +="  order by obj.addTime ";
        List list = this.goodsCartService.query(paramString, params, -1, -1);
        mv.addObject("list", list);
        CommUtil.saveIPageList2ModelAndView("", "", "", plist, mv);
        return mv;
    }
    /**
     * 根据商品id 查询订单中的商品信息
     * @param request
     * @param response
     * @param currentPage
     * @param goodsid
     * @throws IOException
     */
    @SecurityMapping(display = false, rsequence = 0, title = "明细一览表", value = "/poster/ajaxOrderDetail.htm*", rtype = "poster", rname = "明细一览表", rcode = "xiaoshoumingxi_poster", rgroup = "统计管理")
    @RequestMapping({"/poster/ajaxOrderDetail.htm"})
    public void ajaxOrderDetail(HttpServletRequest request,HttpServletResponse response,String currentPage,String goodsid) throws IOException {
    	Map map = new HashMap();  
    	GoodsCartQueryObject qo = new GoodsCartQueryObject(currentPage, map,"addTime", "desc");
    	qo.addQuery("obj.of.user.id", new SysMap("userid",SecurityUserHolder.getCurrentUser().getId()), "=");
         qo.addQuery("obj.status", new SysMap("status",Integer.valueOf(1)), "=");
         qo.addQuery("obj.goods.id", new SysMap("goodsid",Long.parseLong(goodsid)), "=");
        IPageList plist = this.goodsCartService.list(qo);
         
        JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
		// 需要过滤的类 + 属性
		Map<Class<?>, Set<String>> includeMap = new HashMap<Class<?>, Set<String>>();
		Set<String> set = new HashSet<String>();
		set.add("id");
		set.add("count");
		set.add("price");
		set.add("of");
		set.add("goods");
		set.add("addTime");
		includeMap.put(GoodsCart.class, set);
		
		set = new HashSet<String>();
		set.add("id");
		set.add("order_id");
		set.add("user");
		set.add("addTime");
		includeMap.put(OrderForm.class, set);
		
		set = new HashSet<String>();
		set.add("id");
		set.add("goods_name");
		set.add("goods_serial");
		set.add("goods_price");
		includeMap.put(Goods.class, set);
		
		set = new HashSet<String>();
		set.add("userRole");
		includeMap.put(User.class, set);
		jsonConfig.setJsonPropertyFilter(new ComplexOutPropertyPreFilter(includeMap));

		JSONArray json = JSONArray.fromObject(plist.getResult(), jsonConfig);
		SignFilter.print(request, response, json);
		System.out.println(json);
    }
    /**
     * 根据商品id 查询订单中的商品信息
     * @param request
     * @param response
     * @param currentPage
     * @param goodsid
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
	@RequestMapping({ "/poster/ajaxOrderFormBygoodsid.htm" })
   	@ResponseBody
   	public void ajaxOrderFormBygoodsid(HttpServletRequest request,HttpServletResponse response,String currentPage,String goodsid) throws IOException 
    {
    	Map params = new HashMap();
        params.put("status", Integer.valueOf(1));
        params.put("userid", SecurityUserHolder.getCurrentUser().getId());
        params.put("goodsid", Long.parseLong(goodsid));
        params.put("order_status", Integer.valueOf(42));
        
        String paramString ="select obj.count,obj.price,obj.of.order_id,DATE_FORMAT(obj.of.addTime,'%Y-%m-%d'),obj.goods.goods_price,obj.of.user.userRole from GoodsCart obj ";
   	   paramString +="  WHERE obj.status=:status  and obj.goods.id=:goodsid and obj.of.user.id =:userid and obj.of.order_status=:order_status ";
   	   paramString +="  order by obj.addTime ";
       List list = this.goodsCartService.query(paramString, params, -1, -1);
       JSONArray json = JSONArray.fromObject(list);
       SignFilter.printNoCheck(request, response, json);
       System.out.println(json);
    }
    /***
     * 销售日报表
     * @param request
     * @param response
     * @param currentPage
     * @throws IOException
     */
    @RequestMapping({ "/poster/ajaxOrderFormList.htm" })
   	@ResponseBody
   	public void ajaxOrderFormList (HttpServletRequest request,HttpServletResponse response,String currentPage) throws IOException 
    {
    	 Map params = new HashMap();
         params.put("status", Integer.valueOf(1));
         params.put("userid", SecurityUserHolder.getCurrentUser().getId());
         params.put("order_status", Integer.valueOf(42));
         String paramString ="select sum(obj.count) AS count,SUM(obj.price) AS price,obj.goods.id,obj.goods.goods_name,obj.goods.goods_serial from GoodsCart obj ";
          	   paramString +="  WHERE obj.status=:status and obj.of.user.id =:userid and obj.of.order_status=:order_status ";
          	   paramString +="  GROUP BY obj.goods.id,obj.goods.goods_name,obj.goods.goods_serial ";
          	   paramString +="  order by obj.addTime ";
         List list = this.goodsCartService.query(paramString, params, -1, -1);
         JSONArray json = JSONArray.fromObject(list);
		 SignFilter.printNoCheck(request, response, json);
		 System.out.println(json);
    }
    
    /***
     * 销售日报表
     * @param request
     * @param response
     * @param currentPage
     * @throws IOException
     */
    @RequestMapping({ "/poster/ajaxOrdeFormDayList.htm" })
   	@ResponseBody
   	public void ajaxOrdeFormDayList (HttpServletRequest request,HttpServletResponse response,String currentPage,String type) throws IOException 
    {
    	 Map params = new HashMap();
         params.put("status", Integer.valueOf(1));
         params.put("userid", SecurityUserHolder.getCurrentUser().getId());
         params.put("order_status", Integer.valueOf(42));
         String paramString ="select sum(obj.count) AS count,SUM(obj.price) AS price,obj.goods.id,obj.goods.goods_name,obj.goods.goods_serial,obj.goods.goods_price from GoodsCart obj ";
          	   paramString +="  WHERE obj.status=:status and obj.of.user.id =:userid and obj.of.order_status=:order_status ";
          	   if(type.equals("1")) 
          	   {
          		   //日报表
          		 paramString +="  AND TO_DAYS(obj.addTime)=  TO_DAYS(NOW()) ";
          	   }else if(type.equals("2")) {
          		   //月报表
          		 paramString +="  AND MONTH(obj.addTime)=  MONTH(NOW()) ";
          	   }
          	   paramString +="  GROUP BY obj.goods.id,obj.goods.goods_name,obj.goods.goods_serial ";
          	   paramString +="  order by sum(obj.count) DESC ";
         List list = this.goodsCartService.query(paramString, params, -1, -1);
         JSONArray json = JSONArray.fromObject(list);
		 SignFilter.printNoCheck(request, response, json);
		 System.out.println(json);
    }
    
    
    @SecurityMapping(display = false, rsequence = 0, title = "明细一览表", value = "/poster/exportExcel.htm*", rtype = "poster", rname = "明细一览表", rcode = "xiaoshoumingxi_poster", rgroup = "统计管理")
    @RequestMapping({"/poster/exportExcel.htm"})
    public void exportExcel(HttpServletRequest request,HttpServletResponse response,String currentPage,String type) 
    {
         
        // 创建Excel的工作书册 Workbook,对应到一个excel文档
         HSSFWorkbook wb = new HSSFWorkbook();
      
         // 创建Excel的工作sheet,对应到一个excel文档的tab
         HSSFSheet sheet = wb.createSheet("sheet1");
      
         // 设置excel每列宽度
         sheet.setColumnWidth(0, 4000);
         sheet.setColumnWidth(1, 3500);
      
         // 创建字体样式
         HSSFFont font = wb.createFont();
         font.setFontName("Verdana");
         font.setBoldweight((short) 100);
         font.setFontHeight((short) 300);
         font.setColor(HSSFColor.BLUE.index);
      
         // 创建单元格样式
         HSSFCellStyle style = wb.createCellStyle();
         style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
         style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
         style.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
         style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
      
         // 设置边框
         style.setBottomBorderColor(HSSFColor.RED.index);
         style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
         style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
         style.setBorderRight(HSSFCellStyle.BORDER_THIN);
         style.setBorderTop(HSSFCellStyle.BORDER_THIN);
      
         style.setFont(font);// 设置字体
      
         // 创建Excel的sheet的一行
         HSSFRow row = sheet.createRow(0);
         row.setHeight((short) 500);// 设定行的高度
         // 创建一个Excel的单元格
         HSSFCell cell = row.createCell(0);
         cell = row.createCell(0);
         cell.setCellValue("行号");
         cell = row.createCell(1);
         cell.setCellValue("商品编号");
         cell = row.createCell(2);
         cell.setCellValue("商品名称");
         cell = row.createCell(3);
         cell.setCellValue("单价");
         cell = row.createCell(4);
         cell.setCellValue("销量");
         cell = row.createCell(5);
         cell.setCellValue("销售额");
         
         String filename="";
         Map params = new HashMap();
         params.put("status", Integer.valueOf(1));
         params.put("userid", SecurityUserHolder.getCurrentUser().getId());
         params.put("order_status", Integer.valueOf(42));
         String paramString ="select sum(obj.count) AS count,SUM(obj.price) AS price,obj.goods.id,obj.goods.goods_name,obj.goods.goods_serial,obj.goods.goods_price from GoodsCart obj ";
          	   paramString +="  WHERE obj.status=:status and obj.of.user.id =:userid and obj.of.order_status=:order_status  ";
          	   if(type.equals("1")) 
          	   {
          		 filename="销售日报表";
          		   //日报表
          		 paramString +="  AND TO_DAYS(obj.addTime)=  TO_DAYS(NOW()) ";
          	   }else if(type.equals("2")) {
          		   //月报表
          		 filename="销售月报表";
          		 paramString +="  AND MONTH(obj.addTime)=  MONTH(NOW()) ";
          	   }
          	   paramString +="  GROUP BY obj.goods.id,obj.goods.goods_name,obj.goods.goods_serial ";
          	   paramString +="  order by sum(obj.count) DESC ";
         List<GoodsCart> list = this.goodsCartService.query(paramString, params, -1, -1);
         JSONArray json = JSONArray.fromObject(list);
         if(list!=null) {
        	 for(int i=0;i<json.size();i++) {
        		 row = sheet.createRow(i + 1);
        		 JSONArray array = json.getJSONArray(i);
        		 row.createCell(0).setCellValue((i+1));
        		 row.createCell(1).setCellValue(String.valueOf(array.getString(4)));
        		 row.createCell(2).setCellValue(array.getString(3));
        		 row.createCell(3).setCellValue(String.valueOf(array.get(5)));
        		 row.createCell(4).setCellValue(array.getInt(0));
        		 row.createCell(5).setCellValue(String.valueOf(array.get(1)));
        		 
        	 }
         }
         try {
			/*FileOutputStream os = new FileOutputStream("e:\\workbook.xls");
			 wb.write(os);
			 os.close();*/
        	 this.setResponseHeader(response, filename);
        	 OutputStream os = response.getOutputStream();
        	 wb.write(os);
        	 os.flush();
        	 os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //发送响应流方法
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    @SecurityMapping(display = false, rsequence = 0, title = "申请退出", value = "/poster/sqtc.htm*", rtype = "poster", rname = "申请退出", rcode = "yinhangka_poster", rgroup = "基础管理")
    @RequestMapping({"/poster/sqtc.htm"})
    public ModelAndView sqtc(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/sqtc.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);

        return mv;
    }
    
    
    @SecurityMapping(display = false, rsequence = 0, title = "歇业请假申请", value = "/poster/xyqjsq.htm*", rtype = "poster", rname = "歇业请假申请", rcode = "xyqjsq_poster", rgroup = "基础管理")
    @RequestMapping({"/poster/xyqjsq.htm"})
    public ModelAndView xyqjsq(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/xyqjsq.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }
    
    @SecurityMapping(display = false, rsequence = 0, title = "申请歇业", value = "/poster/sqxy.htm*", rtype = "poster", rname = "申请歇业", rcode = "sqxy_poster", rgroup = "基础管理")
    @RequestMapping({"/poster/sqxy.htm"})
    public ModelAndView sqxy(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/sqxy.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }
    
    
    @SecurityMapping(display = false, rsequence = 0, title = "申请歇业详情", value = "/poster/sqxydetails.htm*", rtype = "poster", rname = "申请歇业详情", rcode = "sqxydetails_poster", rgroup = "基础管理")
    @RequestMapping({"/poster/sqxydetails.htm"})
    public ModelAndView sqxydetails(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/sqxydetails.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }
    

    @SecurityMapping(display = false, rsequence = 0, title = "销售月报", value = "/poster/xiaoshouyuebao.htm*", rtype = "poster", rname = "销售月报", rcode = "xiaoshouyuebao_poster", rgroup = "统计管理")
    @RequestMapping({"/poster/xiaoshouyuebao.htm"})
    public ModelAndView xiaoshouyuebao(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/xiaoshouyuebao.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "销售日报", value = "/poster/xiaoshouribao.htm*", rtype = "poster", rname = "销售月报", rcode = "xiaoshouribao_poster", rgroup = "统计管理")
    @RequestMapping({"/poster/xiaoshouribao.htm"})
    public ModelAndView xiaoshouribao(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/xiaoshouribao.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }


    @SecurityMapping(display = false, rsequence = 0, title = "资金服务", value = "/poster/zijinfuwu.htm*", rtype = "poster", rname = "资金服务", rcode = "zijinfuwu_poster", rgroup = "资金管理")
    @RequestMapping({"/poster/zijinfuwu.htm"})
    public ModelAndView zijinfuwu(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/zijinfuwu.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }
    
    @SecurityMapping(display = false, rsequence = 0, title = "资金服务", value = "/poster/zijinfuwudetail.htm*", rtype = "poster", rname = "资金服务", rcode = "zijinfuwudetail_poster", rgroup = "资金管理")
    @RequestMapping({"/poster/zijinfuwudetail.htm"})
    public ModelAndView zijinfuwudetail(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/zijinfuwudetail.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }
    

    /**
     * 代理商家列表
     * @param request
     * @param response
     * @param id
     * @param currentPage
     * @return
     */
    @SecurityMapping(display = false, rsequence = 0, title = "代理商品", value = "/poster/agentcommodity.htm*", rtype = "poster", rname = "代理商品", rcode = "agentcommodity_poster", rgroup = "商品管理")
    @RequestMapping({"/poster/agentcommodity.htm"})
    public ModelAndView agentcommodity(HttpServletRequest request, HttpServletResponse response, String id,String currentPage){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/agentcommodity.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        //加载代理商品信息
        StoreQueryObject goodsQueryObject=new StoreQueryObject(currentPage, mv, "addTime", "desc");
        System.out.println("888888888");
        System.out.println(SecurityUserHolder.getCurrentUser().getId());
        Map params = new HashMap();
        params.put("user_id", SecurityUserHolder.getCurrentUser().getId());
        List<AgentStore> store_list = this.agentstoreService
                .query(
                    "select obj from AgentStore obj where obj.user.id=:user_id group by obj.store.id",
                    params, -1, -1);
        
        mv.addObject("objs", store_list);
       
        return mv;
    }
    
    
    /**
     * 添加代理商家
     * @param request
     * @param response
     * @param id
     * @throws IOException 
     * @throws ParseException 
     */
    @RequestMapping({"/poster/agentcommodity_save.htm"})
    public void save(HttpServletRequest request, HttpServletResponse response, String gids) throws IOException, ParseException{
    	 
    	String msString="添加失败！";
    	if (StringUtils.isNotEmpty(gids)) {
			String []idStrings=gids.split(",");
			for (String string : idStrings) {
				if (StringUtils.isNoneEmpty(string)) {
					AgentStore agentStore=new AgentStore();
					agentStore.setAddTime(new Date());
					User user=new User();
					user.setId(SecurityUserHolder.getCurrentUser().getId());
					
					Store store=new Store();
					store.setId(SecurityUserHolder.getCurrentUser().getStore().getId());
					agentStore.setStore(store);
					agentStore.setUser(user);
					agentstoreService.save(agentStore);
					msString="添加成功！";
				}
				
			}
			
		}
    	PrintWriter printWriter2 = response.getWriter();

		printWriter2.print(ResponseMsg.successWithData(msString));
		printWriter2.flush();
		printWriter2.close();
        
    }
    /**
     * 删除代理商品
     * @param request
     * @param response
     * @param className
     * @param id
     * @param pid
     * @throws IOException 
     */
    @RequestMapping({"/poster/agentcommodity_del.htm"})
    public void del(HttpServletRequest request, HttpServletResponse response,String id) throws IOException{
    	 
    	String msString="删除失败！";
    	agentgoodsService.delete(Long.parseLong(id));
    	msString="删除成功！";
    	PrintWriter printWriter2 = response.getWriter();

		printWriter2.print(ResponseMsg.successWithData(msString));
		printWriter2.flush();
		printWriter2.close();
    } 
    
    @SecurityMapping(display = false, rsequence = 0, title = "破损补寄", value = "/poster/breakmaili.htm*", rtype = "poster", rname = "破损补寄管理", rcode = "breakmaili_poster", rgroup = "客户服务")
    @RequestMapping({"/poster/breakmaili.htm"})
    public ModelAndView breakmaili(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/breakmaili.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "破损补寄详情查看", value = "/poster/breakmailidetail.htm*", rtype = "poster", rname = "破损补寄详情查看", rcode = "breakmailidetail_poster", rgroup = "客户服务")
    @RequestMapping({"/poster/breakmailidetail.htm"})
    public ModelAndView breakmailidetail(HttpServletRequest request, HttpServletResponse response, Long id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/breakmailidetail.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        
		Map params = new HashMap();
       params.put("id", id);
        List<PoSunBuJiGl> list=this.goodsReturnItemService.queryBysql(
                "select obj from PoSunBuJiGl obj where obj.id=:id",
                params, -1, -5);
        mv.addObject("map",list.get(0));
        
        return mv;
    } 
    /**
     * 选择商品
     * @param request
     * @param response
     * @param id
     * @return
     */
    @SecurityMapping(display = false, rsequence = 0, title = "选择商品", value = "/poster/choicegoods.htm*", rtype = "poster", rname = "选择商品", rcode = "choicegoods_poster", rgroup = "商品管理")
    @RequestMapping({"/poster/choicegoods.htm"})
    public ModelAndView choicegoods(HttpServletRequest request, HttpServletResponse response, String id,String currentPage){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/choicegoods.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        //加载全部商品信息
        GoodsQueryObject goodsQueryObject=new GoodsQueryObject(currentPage, mv, "addTime", "desc");
        IPageList pList=goodsService.list(goodsQueryObject);
        String url = this.configService.getSysConfig().getAddress();
        CommUtil.saveIPageList2ModelAndView(
                url + "/admin/flsz_admin.htm", "", "", pList, mv); 
        return mv;
    }
    
    /**
     * 代理管理
     * @param request
     * @param response
     * @param id
     * @return
     */
    @SecurityMapping(display = false, rsequence = 0, title = "代理管理", value = "/poster/choicegoods_agency.htm*", rtype = "poster", rname = "选择商品", rcode = "choicegoods_poster", rgroup = "商品管理")
    @RequestMapping({"/poster/choicegoods_agency.htm"})
    public ModelAndView choicegoods_agency(HttpServletRequest request, HttpServletResponse response, String id,String currentPage){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/choicegoods_agency.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        
       
         StoreQueryObject storeQueryObject=new StoreQueryObject(currentPage, mv, "addTime", "desc");
         IPageList pList=storeService.list(storeQueryObject);
         String url = this.configService.getSysConfig().getAddress();
         CommUtil.saveIPageList2ModelAndView(
                 url + "/admin/flsz_admin.htm", "", "", pList, mv); 
        
        return mv;
    }
    
    @SecurityMapping(display = false, rsequence = 0, title = "商品详情", value = "/poster/choicegoodsdetail.htm*", rtype = "poster", rname = "选择商品", rcode = "choicegoodsdetail_poster", rgroup = "商品管理")
    @RequestMapping({"/poster/choicegoodsdetail.htm"})
    public ModelAndView choicegoodsdetail(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/choicegoodsdetail.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        

        mv.addObject("obj",goodsService.getObjById(CommUtil.null2Long(id)));

        return mv;
    }
    
    
    @SecurityMapping(display = false, rsequence = 0, title = "清仓活动", value = "/poster/cleargood.htm*", rtype = "poster", rname = "清仓活动", rcode = "cleargood_poster", rgroup = "营销活动")
    @RequestMapping({"/poster/cleargood.htm"})
    public ModelAndView cleargood(HttpServletRequest request, HttpServletResponse response, String id, String currentPage,String orderBy, String orderType ){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/cleargood.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        return mv;
    }
    
    @SecurityMapping(display = false, rsequence = 0, title = "清仓活动", value = "/poster/cleargood_list.htm*", rtype = "poster", rname = "清仓活动", rcode = "cleargood_poster", rgroup = "营销活动")
    @RequestMapping({"/poster/cleargood_list.htm"})
    public ModelAndView cleargood_list(HttpServletRequest request, HttpServletResponse response, String id, String currentPage,String orderBy, String orderType,String liNum ){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/cleargood_list.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        QingCangHuoDongQueryObject qo = new QingCangHuoDongQueryObject();
//        qo.addQuery("", "");
        
        if(liNum !=null && liNum!="") 
        {
        	String states="";
        	 int index = Integer.parseInt(liNum);
             if(index ==0) //全部
             {
             	
             }else if(index ==1) { //待审核
            	 states="0";
             	qo.addQuery("states", new SysMap("states", states),"=");
             }else if(index ==2) { //即将开始
            	 states="3";
             	qo.addQuery("states", new SysMap("states", states),"=");
             }else if(index ==3) { //进行中
            	 states="4";
             	qo.addQuery("states", new SysMap("states", states),"=");
             }else if(index ==4) { //已结束
            	 states="5";
             	qo.addQuery("states", new SysMap("states", states),"=");
             }
             mv.addObject("liNum", liNum);
        }
        IPageList pList = this.qingCangHuoDongService.list(qo);
        QingCangShangPinQueryObject spqo = new QingCangShangPinQueryObject();
       /* IPageList goodsList = this.qingCangShangPinServiceImpl.list(spqo);
        mv.addObject("spObj", goodsList.getResult());*/
        CommUtil.saveIPageList2ModelAndView("", "", "", pList, mv);
        mv.addObject("storeTools", this.storeTools);
        return mv;
    }
    
    
    @SecurityMapping(display = false, rsequence = 0, title = "清仓活动", value = "/poster/cleargood_save.htm*", rtype = "poster", rname = "清仓活动", rcode = "cleargood_poster", rgroup = "营销活动")
    @RequestMapping({"/poster/cleargood_save.htm"})
    public void cleargood_save(HttpServletRequest request, HttpServletResponse response, String id, String zd_huodongname,String zd_dazhelidu,String zd_dazhemenkan,String zd_huodongday,String zd_huodongdayend,
    			String zd_canjiashuliang, String shangPinIds,String userid) throws ParseException, IOException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Map<String,Object> whereMap = new HashMap<>();
        whereMap.put("state", 0);
        Qingcanghuodong huoDongEntity = new Qingcanghuodong();
        huoDongEntity.setAddTime(new Date());
        huoDongEntity.setDeleteStatus(true);
        huoDongEntity.setStates("0");
        huoDongEntity.setZd_canjiashangping(shangPinIds);
        huoDongEntity.setZd_canjiashuliang(zd_canjiashuliang);
        huoDongEntity.setZd_dazhelidu(zd_dazhelidu);
        huoDongEntity.setZd_dazhemenkan(zd_dazhemenkan);
        huoDongEntity.setZd_huodongday(sdf.parse(zd_huodongday));
        huoDongEntity.setZd_huodongdayend(sdf.parse(zd_huodongdayend));
        huoDongEntity.setZd_huodongname(zd_huodongname);
        huoDongEntity.setZd_shangjiaid(userid);
        huoDongEntity.setZd_time(new Date());
        huoDongEntity.setZd_zhandaiid(userid);
        this.qingCangHuoDongService.save(huoDongEntity);
        List<QingCangShangPin> list = new ArrayList<QingCangShangPin>();
        String[] ids = shangPinIds.split(",");
        for(int i=0;i<ids.length;i++) 
        {
        	QingCangShangPin shangPinEntity = new QingCangShangPin();
        	shangPinEntity.setAddTime(new Date());
        	shangPinEntity.setDeleteStatus(true);
//        	shangPinEntity.setGoos_id(Long.parseLong(ids[i]));
        	shangPinEntity.setGoods(this.goodsService.getObjById(Long.parseLong(ids[i])));
        	shangPinEntity.setQingcanghuodong(huoDongEntity);
        	list.add(shangPinEntity);
        	this.qingCangShangPinServiceImpl.save(shangPinEntity);
        }
        
       /* ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/cleargood_list.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);*/
        JSONArray json = JSONArray.fromObject(whereMap);
		SignFilter.printNoCheck(request, response, whereMap);
		System.out.println(json);
    }
    
    

    @SecurityMapping(display = false, rsequence = 0, title = "申述管理", value = "/poster/Complaintm.htm*", rtype = "poster", rname = "申述管理", rcode = "Complaintm_poster", rgroup = "客户服务")
    @RequestMapping({"/poster/Complaintm.htm"})
    public ModelAndView Complaintm(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/Complaintm.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "维修管理", value = "/poster/maintmanag.htm*", rtype = "poster", rname = "维修管理", rcode = "maintmanag_poster", rgroup = "客户服务")
    @RequestMapping({"/poster/maintmanag.htm"})
    public ModelAndView maintmanag(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/maintmanag.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "投诉管理", value = "/poster/tsmanage.htm*", rtype = "poster", rname = "投诉管理", rcode = "tsmanage_poster", rgroup = "客户服务")
    @RequestMapping({"/poster/tsmanage.htm"})
    public ModelAndView tsmanage(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/tsmanage.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);

        return mv;
    }
    
    
    
    
    @SecurityMapping(display = false, rsequence = 0, title = "退换货管理", value = "/poster/returnmanage.htm*", rtype = "poster", rname = "退换货管理", rcode = "returnmanage_poster", rgroup = "客户服务")
    @RequestMapping({"/poster/returnmanage.htm"})
    public ModelAndView returnmanage(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/returnmanage.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }
    
    /**售后
     * @param request
     * @param response
     * @param id
     * @return
     * @throws IOException 
     */
    @SecurityMapping(display = false, rsequence = 0, title = "售后", value = "/poster/shouhou.htm*", rtype = "poster", rname = "售后", rcode = "shouhou_poster", rgroup = "客户服务")
    @RequestMapping({"/poster/shouhou.htm"})
    public ModelAndView shouhou(HttpServletRequest request, HttpServletResponse response, String id,String currentPage,String orderBy,String orderType) throws IOException{
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/shouhou.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        String url = this.configService.getSysConfig().getAddress();
        if ((url == null) || (url.equals(""))){
            url = CommUtil.getURL(request);
        }
        User user = this.userService.getObjById(SecurityUserHolder.getCurrentUser().getId());
        TuiKuanShouHouQueryObject qo = new TuiKuanShouHouQueryObject(currentPage, mv, orderBy, orderType);
        qo.addQuery("obj.goods.goods_store.id", new SysMap("storeId",user.getStore().getId()), "=");
        qo.addQuery("obj.states", new SysMap("states",0), "=");
        
        IPageList pList = this.tuiKuanShouHouService.list(qo);
        CommUtil.saveIPageList2ModelAndView(url +
                "/poster/shouhou.htm", "", null, pList, mv);
        return mv;
    }
    
    /**
     * 售后记录
     * @param request
     * @param response
     * @param id
     * @param currentPage
     * @param orderBy
     * @param orderType
     * @return
     * @throws IOException
     */
    @SecurityMapping(display = false, rsequence = 0, title = "售后", value = "/poster/shouhoujilu.htm*", rtype = "poster", rname = "售后", rcode = "shouhou_poster", rgroup = "客户服务")
    @RequestMapping({"/poster/shouhoujilu.htm"})
    public ModelAndView shouhoujilu(HttpServletRequest request, HttpServletResponse response, String id,String currentPage,String orderBy,String orderType) throws IOException{
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/shouhoujilu.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        String url = this.configService.getSysConfig().getAddress();
        if ((url == null) || (url.equals(""))){
            url = CommUtil.getURL(request);
        }
        User user = this.userService.getObjById(SecurityUserHolder.getCurrentUser().getId());
        TuiKuanShouHouQueryObject qo = new TuiKuanShouHouQueryObject(currentPage, mv, orderBy, orderType);
        qo.addQuery("obj.goods.goods_store.id", new SysMap("storeId",user.getStore().getId()), "=");
        qo.addQuery("obj.states", new SysMap("states",0), "!=");
        
        IPageList pList = this.tuiKuanShouHouService.list(qo);
        CommUtil.saveIPageList2ModelAndView(url +
                "/poster/shouhoujilu.htm", "", null, pList, mv);
        return mv;
    }
    
    
    /**退换货管理详情查看 zizhanghugl
     * @param request
     * @param response
     * @param id
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@SecurityMapping(display = false, rsequence = 0, title = "售后详情查看", value = "/poster/shouhoudetail.htm*", rtype = "poster", rname = "售后详情查看", rcode = "shouhoudetail_poster", rgroup = "客户服务")
    @RequestMapping({"/poster/shouhoudetail.htm"})
    public ModelAndView shouhoudetail(HttpServletRequest request, HttpServletResponse response, Long id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/shouhoudetail.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        
		Map params = new HashMap();
       params.put("id", id);
        List<TuiKuanShouHou> list=this.goodsReturnItemService.queryBysql(
                "select obj from TuiKuanShouHou obj where obj.id=:id",
                params, -1, -5);
        mv.addObject("map",list.get(0));
        return mv;
    }
    
    
    @SecurityMapping(display = false, rsequence = 0, title = "子账号管理", value = "/poster/zizhanghugl.htm*", rtype = "poster", rname = "子账号管理", rcode = "zizhanghugl_poster", rgroup = "基础设置")
    @RequestMapping({"/poster/zizhanghugl.htm"})
    public ModelAndView zizhanghugl(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/zizhanghugl.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }
    
    /**退换货管理详情查看 zizhanghugl
     * @param request
     * @param response
     * @param id
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@SecurityMapping(display = false, rsequence = 0, title = "退换货管理详情查看", value = "/poster/returnmanageDetail.htm*", rtype = "poster", rname = "退换货管理详情查看", rcode = "returnmanageDetail_poster", rgroup = "客户服务")
    @RequestMapping({"/poster/returnmanageDetail.htm"})
    public ModelAndView returnmanageDetail(HttpServletRequest request, HttpServletResponse response, Long id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/returnmanageDetail.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        
		Map params = new HashMap();
       params.put("id", id);
        List<TuiHuanHuoGl> list=this.goodsReturnItemService.queryBysql(
                "select obj from TuiHuanHuoGl obj where obj.id=:id",
                params, -1, -5);
        mv.addObject("map",list.get(0));
        return mv;
    }
    
    
    @SecurityMapping(display = false, rsequence = 0, title = "进货管理", value = "/poster/importbill.htm*", rtype = "poster", rname = "进货管理", rcode = "importbill_poster", rgroup = "客户服务")
    @RequestMapping({"/poster/importbill.htm"})
    public ModelAndView importbill(HttpServletRequest request, HttpServletResponse response, String id, String currentPage, String order_status, String order_id, String beginTime, String endTime, String buyer_userName,String urlStr){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/importbill.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        OrderFormQueryObject ofqo = new OrderFormQueryObject(currentPage, mv,
                "addTime", "desc");
        ofqo.addQuery("obj.user.id",
                      new SysMap("user_id",
                                 SecurityUserHolder.getCurrentUser().getId()), "="); 
        if (!CommUtil.null2String(order_status).equals("")){ 
            if (order_status.equals("order_submit")){
                ofqo.addQuery("obj.order_status",
                              new SysMap("order_status", Integer.valueOf(10)), "=");
            }
            if (order_status.equals("order_pay")){
                ofqo.addQuery("obj.order_status",
                              new SysMap("order_status", Integer.valueOf(20)), "=");
            }
            if (order_status.equals("order_shipping")){
                ofqo.addQuery("obj.order_status",
                              new SysMap("order_status", Integer.valueOf(30)), "=");
            }
            if (order_status.equals("order_receive")){
                ofqo.addQuery("obj.order_status",
                              new SysMap("order_status", Integer.valueOf(40)), "=");
            }
            if (order_status.equals("order_evaluate")){
                ofqo.addQuery("obj.order_status",
                              new SysMap("order_status", Integer.valueOf(50)), "=");
            }
            if (order_status.equals("order_finish")){
                ofqo.addQuery("obj.order_status",
                              new SysMap("order_status", Integer.valueOf(60)), "=");
            }
            if (order_status.equals("order_cancel")){
                ofqo.addQuery("obj.order_status",
                              new SysMap("order_status", Integer.valueOf(0)), "=");
            }
            if (order_status.equals("order_tosendgoods")){
                ofqo.addQuery("obj.order_status",
                              new SysMap("order_status", Integer.valueOf(70)), "=");
            }
            if (order_status.equals("order_fotgoods")){
                ofqo.addQuery("obj.order_status",
                              new SysMap("order_status", Integer.valueOf(80)), "=");
            }
        }
        if (!CommUtil.null2String(order_id).equals("")){
            ofqo.addQuery("obj.order_id",
                          new SysMap("order_id", "%" + order_id +
                                     "%"), "like");
        }
        if (!CommUtil.null2String(beginTime).equals("")){
            ofqo.addQuery("obj.addTime",
                          new SysMap("beginTime", CommUtil.formatDate(beginTime)),
                          ">=");
        }
        if (!CommUtil.null2String(endTime).equals("")){
            ofqo.addQuery("obj.addTime",
                          new SysMap("endTime", CommUtil.formatDate(endTime)), ">=");
        }
        if (!CommUtil.null2String(buyer_userName).equals("")){
            ofqo.addQuery("obj.user.userName",
                          new SysMap("userName",
                                     buyer_userName), "=");
        }
        IPageList pList = this.orderFormService.list(ofqo);  
        CommUtil.saveIPageList2ModelAndView("", "", "", pList, mv);
        mv.addObject("storeViewTools", this.storeViewTools);
        mv.addObject("order_id", order_id);
        mv.addObject("order_status", order_status == null ? "all" :
                     order_status);
        mv.addObject("beginTime", beginTime);
        mv.addObject("endTime", endTime);
        mv.addObject("buyer_userName", buyer_userName); 
   
//        System.out.println("urlStr========="+urlStr);
        mv.addObject("urlc",urlStr);
        System.out.println(mv);
        
        //加载代理商家信息
        StoreQueryObject goodsQueryObject=new StoreQueryObject(currentPage, mv, "addTime", "desc");
        
        Map params = new HashMap();
        params.put("user_id", SecurityUserHolder.getCurrentUser().getId());
        List<AgentStore> store_list = this.agentstoreService
                .query(
                    "select obj from AgentStore obj where obj.user.id=:user_id group by obj.store.id",
                    params, -1, -1);
        
        mv.addObject("store", store_list);

        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "库存管理", value = "/poster/stockmanage.htm*", rtype = "poster", rname = "库存管理", rcode = "stockmanage_poster", rgroup = "客户服务")
    @RequestMapping({"/poster/stockmanage.htm"})
    public ModelAndView stockmanage(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/stockmanage.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "退款售后", value = "/poster/refundsale.htm*", rtype = "poster", rname = "退款售后", rcode = "refundsale_poster", rgroup = "商品管理")
    @RequestMapping({"/poster/refundsale.htm"})
    public ModelAndView refundsale(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/refundsale.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }
    /**
     * 退款售后详情
     * @param request
     * @param response
     * @param id
     * @return
     */
    @SecurityMapping(display = false, rsequence = 0, title = "退款售后详情", value = "/poster/tuiKuanShouHouInfo.htm*", rtype = "poster", rname = "退款售后详情", rcode = "refundsale_poster", rgroup = "商品管理")
    @RequestMapping({"/poster/tuiKuanShouHouInfo.htm"})
    public ModelAndView tuiKuanShouHouInfo(HttpServletRequest request, HttpServletResponse response, Long id) 
    {
    	 ModelAndView mv = new JModelAndView(
                 "user/default/usercenter/zhandai/tuiKuanShouHouInfo.html", this.configService
                 .getSysConfig(),
                 this.userConfigService.getUserConfig(), 0, request, response);
    	 
    	 Map params = new HashMap();
    	 Long ids= (long) id;
         params.put("id", ids);
         List<ShouHouXiangQing> list=this.goodsReturnItemService.queryBysql(
                 "select obj from ShouHouXiangQing obj where obj.id=:id",
                 params, -1, -5);
         mv.addObject("map",list.get(0));
         return mv;
    }
    
    
    @SecurityMapping(display = false, rsequence = 0, title = "售后服务看板", value = "/poster/shouhouwufukanban.htm*", rtype = "poster", rname = "售后服务看板", rcode = "shouhouwufukanban_poster", rgroup = "商品管理")
    @RequestMapping({"/poster/shouhouwufukanban.htm"})
    public ModelAndView shouhouwufukanban(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/shouhouwufukanban.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }



    /**
     * 跳转页面
     */
    @SecurityMapping(display = false, rsequence = 0, title = "商品详情", value = "/poster/agentdetail.htm*", rtype = "poster", rname = "代理商品详情", rcode = "agentdetail_poster", rgroup = "商品管理")
    @RequestMapping({"/poster/agentdetail.htm"})
    public ModelAndView agentdetail(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/agentdetail.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "选择商家", value = "/poster/choicebusiness.htm*", rtype = "poster", rname = "选择商家", rcode = "choicebusiness_poster", rgroup = "商品管理")
    @RequestMapping({"/poster/choicebusiness.htm"})
    public ModelAndView choicebusiness(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/choicebusiness.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "申诉管理详情", value = "/poster/Complaintdetal.htm*", rtype = "poster", rname = "申诉管理", rcode = "Complaintdetal_poster", rgroup = "客户服务")
    @RequestMapping({"/poster/Complaintdetal.htm"})
    public ModelAndView Complaintdetal(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/Complaintdetal.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "进货订单详情", value = "/poster/entrysheetdetail.htm*", rtype = "poster", rname = "进货管理", rcode = "entrysheetdetail_poster", rgroup = "商品管理")
    @RequestMapping({"/poster/entrysheetdetail.htm"})
    public ModelAndView entrysheetdetail(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/entrysheetdetail.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "发起诉讼详情", value = "/poster/fqqs.htm*", rtype = "poster", rname = "投诉管理", rcode = "fqqs_poster", rgroup = "客服管理")
    @RequestMapping({"/poster/fqqs.htm"})
    public ModelAndView fqqs(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/fqqs.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "申请退款", value = "/poster/refundapplication.htm*", rtype = "poster", rname = "进货管理", rcode = "refundapplication_poster", rgroup = "商品管理")
    @RequestMapping({"/poster/refundapplication.htm"})
    public ModelAndView refundapplication(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/refundapplication.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "售后详情", value = "/poster/refunddetail.htm*", rtype = "poster", rname = "进货管理", rcode = "refunddetail_poster", rgroup = "商品管理")
    @RequestMapping({"/poster/refunddetail.htm"})
    public ModelAndView refunddetail(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/refunddetail.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "结算", value = "/poster/settle.htm*", rtype = "poster", rname = "进货管理", rcode = "settle_poster", rgroup = "商品管理")
    @RequestMapping({"/poster/settle.htm"})
    public ModelAndView settle(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/settle.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "换货申请", value = "/poster/transferapplication.htm*", rtype = "poster", rname = "进货管理", rcode = "transferapplication_poster", rgroup = "商品管理")
    @RequestMapping({"/poster/transferapplication.htm"})
    public ModelAndView transferapplication(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/transferapplication.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "库存调整", value = "/poster/storeadjuslist.htm*", rtype = "poster", rname = "库存调整", rcode = "storeadjuslist_poster", rgroup = "商品管理")
    @RequestMapping({"/poster/storeadjuslist.htm"})
    public ModelAndView storeadjuslist(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/storeadjuslist.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "库存调整", value = "/poster/storeadjus.htm*", rtype = "poster", rname = "库存调整", rcode = "storeadjus_poster", rgroup = "商品管理")
    @RequestMapping({"/poster/storeadjus.htm"})
    public ModelAndView storeadjus(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/storeadjus.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "投诉详情", value = "/poster/tsdetails.htm*", rtype = "poster", rname = "投诉管理", rcode = "tsdetails_poster", rgroup = "客服服务")
    @RequestMapping({"/poster/tsdetails.htm"})
    public ModelAndView tsdetails(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/tsdetails.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "添加银行卡", value = "/admin/add_bank.htm*", rtype = "poster", rname = "平台收款", rcode = "add_bank_poster", rgroup = "基础设置")
    @RequestMapping({"/admin/add_bank.htm"})
    public ModelAndView add_bank(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/add_bank.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "银行卡管理", value = "/seller/add_bank2.htm*", rtype = "seller", rname = "银行卡管理", rcode = "tuih_seller", rgroup = "资金管理")
    @RequestMapping({"/seller/add_bank2.htm"})
    public ModelAndView add_bank2(HttpServletRequest request, HttpServletResponse response, String id){
        System.out.println("*********************************"+1);
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/ziJinGuanLi/add_bank2.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);

        System.out.println("*********************************"+2);
        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "修改项", value = "/admin/tuih.htm*", rtype = "poster", rname = "消保服务", rcode = "tuih_poster", rgroup = "基础设置")
    @RequestMapping({"/admin/tuih.htm"})
    public ModelAndView tuih(HttpServletRequest request, HttpServletResponse response, String id){
        System.out.println("ccccccccccccccccccccccccccc");
        System.out.println(id);
        System.out.println("ccccccccccccccccccccccccccc");
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/tuih.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "添加项", value = "/admin/add_tuih.htm*", rtype = "poster", rname = "消保服务", rcode = "add_tuih_poster", rgroup = "基础设置")
    @RequestMapping({"/admin/add_tuih.htm"})
    public ModelAndView add_tuih(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/add_tuih.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "评价管理", value = "/seller/evaluate.htm*", rtype = "seller", rname = "评价管理", rcode = "tuih_seller", rgroup = "资金管理")
    @RequestMapping({"/seller/evaluate.htm"})
    public ModelAndView evaluate(HttpServletRequest request, HttpServletResponse response, String id){
        System.out.println("*********************************"+11111);
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhiYingDianGuanli/evaluate.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);

        System.out.println("*********************************"+22222);
        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "添加项", value = "/admin/zhandaixq.htm*", rtype = "poster", rname = "消保服务", rcode = "zhandaixq_poster", rgroup = "基础设置")
    @RequestMapping({"/admin/zhandaixq.htm"})
    public ModelAndView zhandaixq(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/zhandaixq.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "推荐栈代", value = "/admin/tuijian_zhandai2.htm*", rtype = "poster", rname = "推荐栈代", rcode = "tuijian_zhandai2_poster", rgroup = "基础设置")
    @RequestMapping({"/admin/tuijian_zhandai2.htm"})
    public ModelAndView tuijian_zhandai2(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/tuijian_zhandai2.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "推荐栈代", value = "/admin/tuijian_zhandai3.htm*", rtype = "poster", rname = "推荐栈代", rcode = "tuijian_zhandai3_poster", rgroup = "基础设置")
    @RequestMapping({"/admin/tuijian_zhandai3.htm"})
    public ModelAndView tuijian_zhandai3(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "admin/blue/dodot/tuijian_zhandai3.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "银行卡管理", value = "/seller/sjcomplaintdetails.htm*", rtype = "seller", rname = "银行卡管理", rcode = "sjcomplaintdetails_seller", rgroup = "资金管理")
    @RequestMapping({"/seller/sjcomplaintdetails.htm"})
    public ModelAndView sjcomplaintdetails(HttpServletRequest request, HttpServletResponse response, String id){
        System.out.println("*********************************"+1);
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/sjcomplaintdetails.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);

        System.out.println("*********************************"+2);
        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "申诉管理", value = "/seller/sjappeal_details.htm*", rtype = "seller", rname = "申诉管理", rcode = "sjappeal_details_seller", rgroup = "申诉详情")
    @RequestMapping({"/seller/sjappeal_details.htm"})
    public ModelAndView sjappeal_details(HttpServletRequest request, HttpServletResponse response, String id){
        System.out.println("*********************************"+1);
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/sjappeal_details.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);

        System.out.println("*********************************"+2);
        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "申诉管理", value = "/seller/sjapply_appeal.htm*", rtype = "seller", rname = "申诉管理", rcode = "sjapply_appeal_seller", rgroup = "申请申诉")
    @RequestMapping({"/seller/sjapply_appeal.htm"})
    public ModelAndView sjapply_appeal(HttpServletRequest request, HttpServletResponse response, String id){
        System.out.println("*********************************"+1);
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/sjapply_appeal.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);

        System.out.println("*********************************"+2);
        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "维修管理", value = "/seller/sjseekhelp.htm*", rtype = "seller", rname = "维修管理", rcode = "sjseekhelp_seller", rgroup = "一键求助详情")
    @RequestMapping({"/seller/sjseekhelp.htm"})
    public ModelAndView sjseekhelp(HttpServletRequest request, HttpServletResponse response, String id){
        System.out.println("*********************************"+1);
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/sjseekhelp.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);

        System.out.println("*********************************"+2);
        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "维修管理", value = "/seller/sjguarantee.htm*", rtype = "seller", rname = "维修管理", rcode = "sjguarantee_seller", rgroup = "全国联保详情")
    @RequestMapping({"/seller/sjguarantee.htm"})
    public ModelAndView sjguarantee(HttpServletRequest request, HttpServletResponse response, String id){
        System.out.println("*********************************"+1);
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/sjguarantee.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);

        System.out.println("*********************************"+2);
        return mv;
    }
    
    @SecurityMapping(display = false, rsequence = 0, title = "维修管理", value = "/seller/sjsannianquality.htm*", rtype = "seller", rname = "维修管理", rcode = "sjsannianquality_seller", rgroup = "三年质保详情")
    @RequestMapping({"/seller/sjsannianquality.htm"})
    public ModelAndView sjsannianquality(HttpServletRequest request, HttpServletResponse response, String id){
        System.out.println("*********************************"+1);
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/sjsannianquality.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);

        System.out.println("*********************************"+2);
        return mv;
    }
    
    @SecurityMapping(display = false, rsequence = 0, title = "维修管理", value = "/seller/htxq.htm*", rtype = "seller", rname = "维修管理", rcode = "htxq_seller", rgroup = "合同详情")
    @RequestMapping({"/seller/htxq.htm"})
    public ModelAndView htxq(HttpServletRequest request, HttpServletResponse response, String id){
        System.out.println("*********************************"+1);
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/htxq.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);

        System.out.println("*********************************"+2);
        return mv;
    }
    
    @SecurityMapping(display = false, rsequence = 0, title = "栈代对账中心", value = "/poster/duizhang_details.htm*", rtype = "poster", rname = "对账中心", rcode = "duizhang_details_poster", rgroup = "栈代对账中心")
    @RequestMapping({"/poster/duizhang_details.htm"})
    public ModelAndView duizhang_details(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/duizhang_details.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "资金服务", value = "/seller/zjlrlcxq2.htm*", rtype = "seller", rname = "资金服务", rcode = "zjlrlcxq2_seller", rgroup = "资金流入流出详情")
    @RequestMapping({"/seller/zjlrlcxq2.htm"})
    public ModelAndView zjlrlcxq2(HttpServletRequest request, HttpServletResponse response, String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zijinGuanLi/zjlrlcxq2.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);


        return mv;
    }
    /**
     * 下单进货
     * @param request
     * @param response
     * @param id（店铺ID）
     * @param currentPage
     * @return
     */
    @SecurityMapping(display = false, rsequence = 0, title = "下单进货", value = "/poster/ordersave.htm*", rtype = "seller", rname = "下单进货", rcode = "ordersave_poster", rgroup = "下单进货")
    @RequestMapping({"/poster/ordersave.htm"})
    public ModelAndView ordersave(HttpServletRequest request, HttpServletResponse response, String id,String currentPage){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhandai/ordersave.html", this.configService
                .getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        AgentGoodsQueryObject agentGoodsQueryObject=new AgentGoodsQueryObject(currentPage,mv, "addTime","desc");
        agentGoodsQueryObject.addQuery("obj.user.id",new SysMap("user_id",SecurityUserHolder.getCurrentUser().getId()),"=");
        if (StringUtils.isNotEmpty(id)) {
			agentGoodsQueryObject.addQuery("obj.store.id",new SysMap("store_id",Long.parseLong(id)),"=");
		}
        IPageList pList=agentgoodsService.list(agentGoodsQueryObject);
        String url = this.configService.getSysConfig().getAddress();
        CommUtil.saveIPageList2ModelAndView(
                url + "/poster/ordersave.htm", "", "", pList, mv);  
        return mv;
    }
    /**
     * 代理添加商品到订单
     * @param request
     * @param response
     * @param gids（商品Id）
     * @throws IOException 
     * @throws ParseException 
     */
    @RequestMapping({"/poster/ajaxsaveorder.htm"})
    public void ajaxsaveorder(HttpServletRequest request, HttpServletResponse response, String gids) throws IOException, ParseException{
    	 
    	String msString="添加失败！";
    	if (StringUtils.isNotEmpty(gids)) {
    		User user=new User();
    		user.setId(SecurityUserHolder.getCurrentUser().getId());
    		Store store=new Store();
    		store.setId(SecurityUserHolder.getCurrentUser().getStore().getId());
    		
    		OrderForm orderForm = new OrderForm(); // goods_cart3.  
			orderForm.setAddTime(new Date());
			orderForm.setOrder_status(10);
			orderForm.setOrder_id(CommUtil.formatTime("yyyyMMddHHmmss", new Date())+user.getId());
			orderForm.setStore(store);
			orderForm.setOrder_type("Pc");
			orderForm.setUser(user);
			orderForm.setChargeAddress(request.getParameter("chargeAddress"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			orderForm.setChargeDate(sdf.parse(request.getParameter("chargeDate")));
			orderForm.setChargeName(request.getParameter("chargeName"));
			orderForm.setChargeTel(request.getParameter("chargeTel"));
			orderForm.setMsg(request.getParameter("msg"));
			boolean bl = this.orderFormService.save(orderForm);
			JSONArray array=JSONArray.fromObject(gids);
			for (Object object : array) {
				JSONObject jsonObject=JSONObject.fromObject(object);
				String gid=jsonObject.getString("gid");
				String count=jsonObject.getString("count");
				if (StringUtils.isNotEmpty(gid)&&StringUtils.isNotEmpty(count)) {
					Goods goods=goodsService.getObjById(Long.parseLong(gid));
					GoodsCart goodsCart=new GoodsCart();
					goodsCart.setAddTime(new Date());
					goodsCart.setGoods(goods);
					goodsCart.setCount(Integer.parseInt(count));
					goodsCart.setGsps(goods.getGoods_specs());
					goodsCart.setOf(orderForm); 
					goodsCartService.save(goodsCart);
				}
				
				
			}  
			OrderFormLog ofl = new OrderFormLog();
			ofl.setAddTime(new Date());
			ofl.setOf(orderForm);
			ofl.setLog_info("提交订单");
			ofl.setLog_user(user);
			this.orderFormLogService.save(ofl);
			msString="下单成功！";
    	 
			
		}
    	PrintWriter printWriter2 = response.getWriter();

		printWriter2.print(ResponseMsg.successWithData(msString));
		printWriter2.flush();
		printWriter2.close();
        
    }
    
    /**
     * 代理添加商品到订单(打样)
     * @param request
     * @param response
     * @param gids（商品Id）
     * @throws IOException 
     * @throws ParseException 
     */
    @RequestMapping({"/poster/ajaxsaveorderdy.htm"})
    public void ajaxsaveorderdy(HttpServletRequest request, HttpServletResponse response, String gids) throws IOException, ParseException{
    	 
    	String msString="添加失败！";
    	if (StringUtils.isNotEmpty(gids)) {
    		User user=new User();
    		user.setId(SecurityUserHolder.getCurrentUser().getId());
    		Store store=new Store();
    		store.setId(SecurityUserHolder.getCurrentUser().getStore().getId());
    		
    		OrderForm orderForm = new OrderForm(); // goods_cart3.  
			orderForm.setAddTime(new Date());
			orderForm.setOrder_status(10);
			orderForm.setOrder_id(CommUtil.formatTime("yyyyMMddHHmmss", new Date())+user.getId());
			orderForm.setStore(store);
			orderForm.setOrder_type("Pc");
			orderForm.setUser(user);
			if (StringUtils.isNotEmpty(request.getParameter("chargeAddress"))) {
				orderForm.setChargeAddress(request.getParameter("chargeAddress"));
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			if (StringUtils.isNotEmpty(request.getParameter("chargeDate"))) {
				orderForm.setChargeDate(sdf.parse(request.getParameter("chargeDate")));
			}
			if (StringUtils.isNotEmpty(request.getParameter("chargeName"))) {
				orderForm.setChargeName(request.getParameter("chargeName"));
			}
			if (StringUtils.isNotEmpty(request.getParameter("chargeTel"))) {
				orderForm.setChargeTel(request.getParameter("chargeTel"));
			}
			if (StringUtils.isNotEmpty(request.getParameter("msg"))) {
				orderForm.setMsg(request.getParameter("msg"));
			}
			
			boolean bl = this.orderFormService.save(orderForm);
			String array[]=gids.split(",");
			for (String string : array) {
				if (StringUtils.isNotEmpty(string)) {
					Goods goods=goodsService.getObjById(Long.parseLong(string));
					GoodsCart goodsCart=new GoodsCart();
					goodsCart.setAddTime(new Date());
					goodsCart.setGoods(goods);
					goodsCart.setCount(1);
					//goodsCart.setGsps(goods.getGoods_specs());
					goodsCart.setOf(orderForm); 
					goodsCartService.save(goodsCart);
				}
			}  
			OrderFormLog ofl = new OrderFormLog();
			ofl.setAddTime(new Date());
			ofl.setOf(orderForm);
			ofl.setLog_info("提交订单");
			ofl.setLog_user(user);
			this.orderFormLogService.save(ofl);
			msString="下单成功！";
    	 
			
		}
    	PrintWriter printWriter2 = response.getWriter();

		printWriter2.print(ResponseMsg.successWithData(msString));
		printWriter2.flush();
		printWriter2.close();
        
    }
    
    
    /**
     * 下单进货
     * @param request
     * @param response
     * @param goods_ids（商品Id） goods_amounts(商品数量) name（收货人） total_price(总价) telphone(电话) address(地址) expect_date(送货日期)
     * @throws IOException 
     * @throws ParseException 
     */
    @RequestMapping({"/poster/ajaxsaveorderdy1.htm"})
    public void ajaxsaveorderdy1(HttpServletRequest request, HttpServletResponse response, String goods_ids,String goods_amounts,String total_price,String name,String telphone,String address,String expect_date) throws IOException, ParseException{
    	 
    	String msString="添加失败！";
    	if (StringUtils.isNotEmpty(goods_ids)) {
    		User user=new User();
    		user.setId(SecurityUserHolder.getCurrentUser().getId());
    		Store store=new Store();
    		store.setId(SecurityUserHolder.getCurrentUser().getStore().getId());
    		
    		OrderForm orderForm = new OrderForm(); // goods_cart3.  
			orderForm.setAddTime(new Date());
			orderForm.setOrder_status(10);
			orderForm.setOrder_id(CommUtil.formatTime("yyyyMMddHHmmss", new Date())+user.getId());
			orderForm.setStore(store);
			orderForm.setOrder_type("Pc");
			orderForm.setUser(user);
			BigDecimal b = new BigDecimal(total_price);   
			b=b.setScale(2, BigDecimal.ROUND_DOWN); //小数位 直接舍去，不会四舍五入  
			orderForm.setTotalPrice(b);
			if (StringUtils.isNotEmpty(address)) {
				orderForm.setChargeAddress(address);
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
			if (StringUtils.isNotEmpty(expect_date)) {
				orderForm.setChargeDate(sdf.parse(expect_date));
			}
			if (StringUtils.isNotEmpty(name)) {
				orderForm.setChargeName(name);
			}
			if (StringUtils.isNotEmpty(telphone)) {
				orderForm.setChargeTel(telphone);
			}
			if (StringUtils.isNotEmpty(request.getParameter("msg"))) {
				orderForm.setMsg(request.getParameter("msg"));
			}
			
			boolean bl = this.orderFormService.save(orderForm);
			String array[]=goods_ids.split(",");
			String arrays[]=goods_amounts.split(",");
			
			for(int i=0;i<array.length;i++){
				String str1 = array[i];
				String str2 = arrays[i];
				
				if (StringUtils.isNotEmpty(str1)) {
					Goods goods=goodsService.getObjById(Long.parseLong(str1));
					GoodsCart goodsCart=new GoodsCart();
					goodsCart.setAddTime(new Date());
					goodsCart.setGoods(goods);
					goodsCart.setCount(Integer.parseInt(str2));
					//goodsCart.setGsps(goods.getGoods_specs());
					goodsCart.setOf(orderForm); 
					goodsCartService.save(goodsCart);
				}
			}
			
			OrderFormLog ofl = new OrderFormLog();
			ofl.setAddTime(new Date());
			ofl.setOf(orderForm);
			ofl.setLog_info("提交订单");
			ofl.setLog_user(user);
			this.orderFormLogService.save(ofl);
			msString="下单成功！";
    	 
			
		}
    	PrintWriter printWriter2 = response.getWriter();

		printWriter2.print(ResponseMsg.successWithData(msString));
		printWriter2.flush();
		printWriter2.close();
        
    }
    
    /**
     * 获得商品
     * @param request
     * @param response
     * @param gids（商品Id）
     * @throws IOException 
     * @throws ParseException 
     */
    @RequestMapping({"/poster/getgoods.htm"})
    public void getgoods(HttpServletRequest request, HttpServletResponse response, String id) throws IOException, ParseException{
    	 
    	Goods goods=goodsService.getObjById(Long.parseLong(id));
    	JsonConfig jsonConfig = new JsonConfig();
 		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
 		// 需要过滤的类 + 属性
 		Map<Class<?>, Set<String>> includeMap = new HashMap<Class<?>, Set<String>>();
 		Set<String> set = new HashSet<String>();
 		set.add("id");
 		set.add("goods");
 		set.add("type");
 		set.add("state");
 		includeMap.put(StoreGoods.class, set);
 		set = new HashSet<String>();
 		set.add("id");
 		set.add("seo_keywords");
 		set.add("seo_description");
 		set.add("goods_name");
 		set.add("goods_price");
 		set.add("goods_inventory");
 		set.add("goods_weight");
 		set.add("goods_click");
 		set.add("store_recommend");
 		set.add("goods_details");
 		set.add("goods_recommend");
 		set.add("goods_collect");
 		set.add("goods_status");
 		set.add("goods_main_photo");
 		set.add("goods_photos");
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

 		JSONArray json = JSONArray.fromObject(goods, jsonConfig);
 		SignFilter.printNoCheck(request, response, json);
    	 
        
    }
 
 

}

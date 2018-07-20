package com.dashidao.manage.seller.action;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.springframework.web.servlet.ModelAndView;

import com.dashidao.core.annotation.SecurityMapping;
import com.dashidao.core.domain.virtual.SysMap;
import com.dashidao.core.mv.JModelAndView;
import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.security.support.SecurityUserHolder;
import com.dashidao.core.tools.CommUtil;
import com.dashidao.foundation.domain.Accessory;
import com.dashidao.foundation.domain.Goods;
import com.dashidao.foundation.domain.GoodsSpecProperty;
import com.dashidao.foundation.domain.GoodsSpecification;
import com.dashidao.foundation.domain.OrderCom;
import com.dashidao.foundation.domain.OrderForm;
import com.dashidao.foundation.domain.Store;
import com.dashidao.foundation.domain.User;
import com.dashidao.foundation.domain.query.ElectronicInvoiceQueryObject;
import com.dashidao.foundation.domain.query.EvaluateQueryObject;
import com.dashidao.foundation.domain.query.GoodsClassQueryObject;
import com.dashidao.foundation.domain.query.GoodsQueryObject;
import com.dashidao.foundation.domain.query.OrderComQueryObject;
import com.dashidao.foundation.domain.query.OrderFormQueryObject;
import com.dashidao.foundation.service.IAccessoryService;
import com.dashidao.foundation.service.IElectronicInvoiceService;
import com.dashidao.foundation.service.IEvaluateService;
import com.dashidao.foundation.service.IGoodsBrandService;
import com.dashidao.foundation.service.IGoodsService;
import com.dashidao.foundation.service.IOrderComService;
import com.dashidao.foundation.service.IOrderFormLogService;
import com.dashidao.foundation.service.IOrderFormService;
import com.dashidao.foundation.service.ISysConfigService;
import com.dashidao.foundation.service.IUserConfigService;
import com.dashidao.foundation.service.IUserService;
import com.dashidao.jwt.filter.SignFilter;
import com.dashidao.jwt.util.ComplexOutPropertyPreFilter;
import com.dashidao.jwt.util.DateJsonValueProcessor;
import com.dashidao.manage.admin.tools.StoreTools;
import com.dashidao.pay.bill.util.MD5Util;
import com.dashidao.view.web.tools.GoodsViewTools;
import com.dashidao.view.web.tools.StoreViewTools;
import com.sun.org.apache.bcel.internal.generic.NEW;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
/**
 * 资金管理
 * @author Administrator
 *
 */
@Controller
public class CuiJingSellerAction {


    @Autowired
    private ISysConfigService configService;

    @Autowired
    private IUserConfigService userConfigService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IGoodsService goodsService;
    
    @Autowired
    private IOrderComService orderComService;
    @Autowired
    private IEvaluateService evaluateService;
    @Autowired
    private StoreTools storeTools;

    @Autowired
    private GoodsViewTools goodsViewTools;
    @Autowired
    private IOrderFormService orderFormService;
    @Autowired
    private IOrderFormLogService orderFormLogService;
    @Autowired
    private StoreViewTools storeViewTools;

    @Autowired
    private IGoodsBrandService goodsBrandService;

    @Autowired
    private IAccessoryService accessoryService;
    @Autowired
    private IElectronicInvoiceService electronicInvoiceService;
    


    @SecurityMapping(display = false, rsequence = 0, title = "发票管理", value = "/seller/role_invoice_manager.htm*", rtype = "seller", rname = "发票管理", rcode = "role_invoice_manager_seller", rgroup = "客户服务")
    @RequestMapping({"/seller/role_invoice_manager.htm"})
    public ModelAndView role_invoice_manager(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/cuijingAdd/role_invoice_manager.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "维修管理", value = "/seller/role_maintain_manager.htm*", rtype = "seller", rname = "维修管理", rcode = "role_maintain_manager_seller", rgroup = "客户服务")
    @RequestMapping({"/seller/role_maintain_manager.htm"})
    public ModelAndView role_maintain_manager(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/cuijingAdd/role_maintain_manager.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "申诉管理", value = "/seller/role_appeal_manager.htm*", rtype = "seller", rname = "申诉管理", rcode = "role_appeal_manager_seller", rgroup = "客户服务")
    @RequestMapping({"/seller/role_appeal_manager.htm"})
    public ModelAndView role_appeal_manager(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/keHuFuWu/role_appeal_manager.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "服务数据看板", value = "/seller/role_service_data.htm*", rtype = "seller", rname = "服务数据看板", rcode = "role_service_data_seller", rgroup = "客户服务")
    @RequestMapping({"/seller/role_service_data.htm"})
    public ModelAndView role_service_data(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/cuijingAdd/role_service_data.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "破损补记管理", value = "/seller/destroy_product_manager.htm*", rtype = "seller", rname = "破损补记管理", rcode = "destroy_product_manager_seller", rgroup = "客户服务")
    @RequestMapping({"/seller/destroy_product_manager.htm"})
    public ModelAndView destroy_product_manager(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/keHuFuWu/destroy_product_manager.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "合同信息", value = "/seller/contract_information.htm*", rtype = "seller", rname = "合同信息", rcode = "contract_information_seller", rgroup = "基础管理")
    @RequestMapping({"/seller/contract_information.htm"})
    public ModelAndView contract_information(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/cuijingAdd/contract_information.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "商家考核信息", value = "/seller/bunisess_test_information.htm*", rtype = "seller", rname = "商家考核信息", rcode = "bunisess_test_information_seller", rgroup = "基础管理")
    @RequestMapping({"/seller/bunisess_test_information.htm"})
    public ModelAndView bunisess_test_information(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/cuijingAdd/bunisess_test_information.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "电子发票", value = "/seller/dianzifapiao.htm*", rtype = "seller", rname = "电子发票", rcode = "dianzifapiao_seller", rgroup = "基础管理")
    @RequestMapping({"/seller/dianzifapiao.htm"})
    public ModelAndView dianzifapiao(HttpServletRequest request, HttpServletResponse response,String currentPage){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/cuijingAdd/dianzifapiao.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);
        ElectronicInvoiceQueryObject electronicInvoiceQueryObject=new ElectronicInvoiceQueryObject(currentPage, mv, "addTime", "desc");
        electronicInvoiceQueryObject.addQuery("obj.user.id", new SysMap("user_id",SecurityUserHolder.getCurrentUser().getId()),"=");
        IPageList pList=this.electronicInvoiceService.list(electronicInvoiceQueryObject);
        String url = this.configService.getSysConfig().getAddress();
        CommUtil.saveIPageList2ModelAndView(
                url + "/seller/dianzifapiao.htm", "", "", pList, mv);
        return mv;
    }
    
    @SecurityMapping(display = false, rsequence = 0, title = "添加电子发票", value = "/seller/upload_dianzifapiao.htm*", rtype = "seller", rname = "电子发票", rcode = "upload_dianzifapiao_seller", rgroup = "基础管理")
    @RequestMapping({"/seller/upload_dianzifapiao.htm"})
    public ModelAndView upload_dianzifapiao(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/cuijingAdd/upload_dianzifapiao.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }
    
    @SecurityMapping(display = false, rsequence = 0, title = "电子发票下载", value = "/seller/download.htm*", rtype = "seller", rname = "电子发票", rcode = "download_seller", rgroup = "基础管理")
    @RequestMapping({"/seller/download.htm"})
    public void download(HttpServletRequest request, HttpServletResponse response,String pathFile) 
    {
    	 try {
             // path是指欲下载的文件的路径。
    		 String path ="E://"+ pathFile;
    		 //E://pictureurl/fap.jpg
             File file = new File(path);
             // 取得文件名。
             String filename = file.getName();
             // 取得文件的后缀名。
             String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

             // 以流的形式下载文件。
             InputStream fis = new BufferedInputStream(new FileInputStream(path));
             byte[] buffer = new byte[fis.available()];
             fis.read(buffer);
             fis.close();
             // 清空response
             response.reset();
             // 设置response的Header
             response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
             response.addHeader("Content-Length", "" + file.length());
             OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
             response.setContentType("application/octet-stream");
             toClient.write(buffer);
             toClient.flush();
             toClient.close();
         } catch (IOException ex) {
             ex.printStackTrace();
         }
    }
    
    
    
    @SecurityMapping(display = false, rsequence = 0, title = "电子发票", value = "/seller/ele_invoice.htm*", rtype = "seller", rname = "电子发票", rcode = "ele_invoice_seller", rgroup = "基础管理")
    @RequestMapping({"/seller/ele_invoice.htm"})
    public ModelAndView ele_invoice(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/cuijingAdd/ele_invoice.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "申请退出", value = "/seller/request_exit.htm*", rtype = "seller", rname = "申请退出", rcode = "request_exit_seller", rgroup = "基础管理")
    @RequestMapping({"/seller/request_exit.htm"})
    public ModelAndView request_exit(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/cuijingAdd/request_exit.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "供货商管理", value = "/seller/bunisess_produce_manager.htm*", rtype = "seller", rname = "供货商管理", rcode = "bunisess_produce_manager_seller", rgroup = "基础管理")
    @RequestMapping({"/seller/bunisess_produce_manager.htm"})
    public ModelAndView bunisess_produce_manager(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/cuijingAdd/bunisess_produce_manager.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "开设直营店", value = "/seller/open_store.htm*", rtype = "seller", rname = "开设直营店", rcode = "open_store_seller", rgroup = "基础管理")
    @RequestMapping({"/seller/open_store.htm"})
    public ModelAndView open_store(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/cuijingAdd/open_store.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }
    
    @SecurityMapping(display = false, rsequence = 0, title = "开设直营店", value = "/seller/open_store_add.htm*", rtype = "seller", rname = "开设直营店", rcode = "open_store_add_seller", rgroup = "基础管理")
    @RequestMapping({"/seller/open_store_add.htm"})
    public ModelAndView open_store_add(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/cuijingAdd/open_store_add.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }
    
    @SecurityMapping(display = false, rsequence = 0, title = "开设直营店", value = "/seller/open_store_edit.htm*", rtype = "seller", rname = "开设直营店", rcode = "open_store_edit_seller", rgroup = "基础管理")
    @RequestMapping({"/seller/open_store_edit.htm"})
    public ModelAndView open_store_edit(HttpServletRequest request, HttpServletResponse response,String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/cuijingAdd/open_store_edit.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);
        mv.addObject("id",id);
        return mv;
    }
    
    @SecurityMapping(display = false, rsequence = 0, title = "开设直营店", value = "/seller/open_store_detail.htm*", rtype = "seller", rname = "开设直营店", rcode = "open_store_detail_seller", rgroup = "基础管理")
    @RequestMapping({"/seller/open_store_detail.htm"})
    public ModelAndView open_store_detail(HttpServletRequest request, HttpServletResponse response,String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/cuijingAdd/open_store_detail.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);
        mv.addObject("id",id);
        System.out.println(MD5Util.md5Hex("e10adc3949ba59abbe56e057f20f883e"));
        return mv;
    }
    

    //统计管理
    @SecurityMapping(display = false, rsequence = 0, title = "销售明细一览表", value = "/seller/sel_list.htm*", rtype = "seller", rname = "销售明细一览表", rcode = "sel_list_seller", rgroup = "统计管理")
    @RequestMapping({"/seller/sel_list.htm"})
    public ModelAndView sel_list(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/tongJiGuanLi/sel_list.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "销售日报表", value = "/seller/sel_list_day.htm*", rtype = "seller", rname = "销售日报表", rcode = "sel_list_day_seller", rgroup = "统计管理")
    @RequestMapping({"/seller/sel_list_day.htm"})
    public ModelAndView sel_list_day(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/tongJiGuanLi/sel_list_day.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "销售月报表", value = "/seller/sel_list_month.htm*", rtype = "seller", rname = "销售月报表", rcode = "sel_list_month_seller", rgroup = "统计管理")
    @RequestMapping({"/seller/sel_list_month.htm"})
    public ModelAndView sel_list_month(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/tongJiGuanLi/sel_list_month.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "进销存一览表", value = "/seller/purchase_sell_stock_list.htm*", rtype = "seller", rname = "进销存一览表", rcode = "purchase_sell_stock_list_seller", rgroup = "统计管理")
    @RequestMapping({"/seller/purchase_sell_stock_list.htm"})
    public ModelAndView purchase_sell_stock_list(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/tongJiGuanLi/purchase_sell_stock_list.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "进销存月报表", value = "/seller/purchase_sell_stock_dayList.htm*", rtype = "seller", rname = "进销存月报表", rcode = "purchase_sell_stock_dayList_seller", rgroup = "统计管理")
    @RequestMapping({"/seller/purchase_sell_stock_dayList.htm"})
    public ModelAndView purchase_sell_stock_dayList(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/tongJiGuanLi/purchase_sell_stock_dayList.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "进销存月报表", value = "/seller/purchase_sell_stock_monthList.htm*", rtype = "seller", rname = "进销存月报表", rcode = "purchase_sell_stock_monthList_seller", rgroup = "统计管理")
    @RequestMapping({"/seller/purchase_sell_stock_monthList.htm"})
    public ModelAndView purchase_sell_stock_monthList(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/tongJiGuanLi/purchase_sell_stock_monthList.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    //销售分析
    @SecurityMapping(display = false, rsequence = 0, title = "商品分析", value = "/seller/product_analyze.htm*", rtype = "seller", rname = "商品分析", rcode = "product_analyze_seller", rgroup = "销售分析")
    @RequestMapping({"/seller/product_analyze.htm"})
    public ModelAndView product_analyze(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/xiaoShouFenXi/product_analyze.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "活动分析", value = "/seller/activity_analyze.htm*", rtype = "seller", rname = "活动分析", rcode = "activity_analyze_seller", rgroup = "销售分析")
    @RequestMapping({"/seller/activity_analyze.htm"})
    public ModelAndView activity_analyze(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/xiaoShouFenXi/activity_analyze.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "供货分析", value = "/seller/supply_analyze.htm*", rtype = "seller", rname = "供货分析", rcode = "supply_analyze_seller", rgroup = "销售分析")
    @RequestMapping({"/seller/supply_analyze.htm"})
    public ModelAndView supply_analyze(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/xiaoShouFenXi/supply_analyze.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    //直营店管理
    @SecurityMapping(display = false, rsequence = 0, title = "货物调拨", value = "/seller/product_commit.htm*", rtype = "seller", rname = "货物调拨", rcode = "product_commit_seller", rgroup = "直营店管理")
    @RequestMapping({"/seller/product_commit.htm"})
    public ModelAndView product_commit(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhiYingDianGuanli/product_commit.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "评价管理", value = "/seller/evaluate_manager.htm*", rtype = "seller", rname = "评价管理", rcode = "evaluate_manager_seller", rgroup = "直营店管理")
    @RequestMapping({"/seller/evaluate_manager.htm"})
    public ModelAndView evaluate_manager(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/goods_manager/evaluate_manager.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }
    
    @SecurityMapping(display = false, rsequence = 0, title = "评价管理详情", value = "/seller/evaluate_details.htm*", rtype = "seller", rname = "评价管理", rcode = "evaluate_details", rgroup = "商品管理")
    @RequestMapping({"/seller/evaluate_details.htm"})
    public ModelAndView evaluate_details(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/goods_manager/evaluate_details.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }
    
    @SecurityMapping(display = false, rsequence = 0, title = "评价管理回复", value = "/seller/evaluate_huifu.htm*", rtype = "seller", rname = "评价管理", rcode = "evaluate_huifu", rgroup = "商品管理")
    @RequestMapping({"/seller/evaluate_huifu.htm"})
    public ModelAndView evaluate_huifu(HttpServletRequest request, HttpServletResponse response,String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/goods_manager/evaluate_huifu.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);
        mv.addObject("id", id);
        return mv;
    }
    
    
    @RequestMapping({ "/seller/evaluate_save.htm" })
    @ResponseBody
    public void evaluate_save(HttpServletRequest request, HttpServletResponse response,String parentId,String content,String userid) throws IOException{
    	
    	Map params = new HashMap();
    	params.put("state", "0");
    	if(parentId!=null && !"".equals(parentId)) 
    	{
    		OrderCom ordercom = this.orderComService.getObjById(Long.parseLong(parentId));
    		User user = this.userService.getObjById(Long.parseLong(userid));
    		OrderCom entity = new OrderCom();
    		entity.setAddTime(new Date());
    		entity.setContent(content);
    		entity.setDeleteStatus(false);
    		entity.setStatus(0);
    		entity.setParent(ordercom);
    		entity.setUser(user);
    		entity.setContent(content);
    		boolean b =this.orderComService.save(entity);
    		if(b) {
    			params.put("state", "1");
    		}
    	}
    	JSONArray json = JSONArray.fromObject(params);
		SignFilter.printNoCheck(request, response, params);
		System.out.println(json);
    }
    
    
    
    @RequestMapping({ "/seller/sel_evaluate.htm" })
    @ResponseBody
    public void sel_evaluate(HttpServletRequest request, HttpServletResponse response,String currentPage,String user_id) throws IOException{
    	System.out.println(user_id);
    	System.out.println(SecurityUserHolder.getCurrentUser().getStore().getId());
    	
    	Map params = new HashMap();
//    	OrderComQueryObject eqo =new OrderComQueryObject(currentPage, params,
//                "addTime", "desc");
//    	
//    	eqo.addQuery("obj.store.id", new SysMap("store_id",SecurityUserHolder.getCurrentUser().getStore().getId()), "=");
    	params.put("storeId",Long.parseLong(user_id));
    	List pList = this.orderComService.query(
				"select obj from OrderCom obj where obj.parent.id is null and obj.store.id=:storeId ",
				params, -1, -1);
    	//IPageList pList = this.orderComService.list(eqo);
    	
    	JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
		
		// 需要过滤的类 + 属性
		Map<Class<?>, Set<String>> includeMap = new HashMap<Class<?>, Set<String>>();
		Set<String> set = new HashSet<String>();
		set.add("id");
		set.add("orderForm");
		set.add("user");
		set.add("goods");
		set.add("store");
		set.add("content");
		set.add("lschild");
		set.add("evaluate_user");
		set.add("addTime");
		includeMap.put(OrderCom.class, set);
		
		set = new HashSet<String>();
		set.add("id");
		set.add("userName");
		set.add("photo");
		includeMap.put(User.class, set);
		
		set = new HashSet<String>();
		set.add("id");
		set.add("goods_name");
		set.add("goods_details");
		set.add("seo_description");
		set.add("goods_main_photo");
		includeMap.put(Goods.class, set);
		
		set = new HashSet<String>();
		set.add("id");
		set.add("store_name");
		includeMap.put(Store.class, set);
		
		set = new HashSet<String>();
		set.add("name");
		set.add("path");
		set.add("ext");
		includeMap.put(Accessory.class, set);
		
		set = new HashSet<String>();
		set.add("id");
		set.add("totalPrice");
		includeMap.put(OrderForm.class, set);
		
		jsonConfig.setJsonPropertyFilter(new ComplexOutPropertyPreFilter(includeMap));

		JSONArray json = JSONArray.fromObject(pList, jsonConfig);
		SignFilter.printNoCheck(request, response, json);
		System.out.println(json);
	}
    
    @RequestMapping({ "/seller/sel_evaluateById.htm" })
    @ResponseBody
    public void sel_evaluateById(HttpServletRequest request, HttpServletResponse response,String currentPage,String id) throws IOException{
    	System.out.println(id);
    	System.out.println(SecurityUserHolder.getCurrentUser().getStore().getId());
    	
    	Map params = new HashMap();
//    	OrderComQueryObject eqo =new OrderComQueryObject(currentPage, params,
//                "addTime", "desc");
//    	
//    	eqo.addQuery("obj.store.id", new SysMap("store_id",SecurityUserHolder.getCurrentUser().getStore().getId()), "=");
    	params.put("id",Long.parseLong(id));
    	List pList = this.orderComService.query(
				"select obj from OrderCom obj where obj.parent.id=:id ",
				params, -1, -1);
    	//IPageList pList = this.orderComService.list(eqo);
    	
    	JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
		
		// 需要过滤的类 + 属性
		Map<Class<?>, Set<String>> includeMap = new HashMap<Class<?>, Set<String>>();
		Set<String> set = new HashSet<String>();
		set.add("id");
		set.add("orderForm");
		set.add("user");
		set.add("goods");
		set.add("store");
		set.add("content");
		set.add("lschild");
		set.add("evaluate_user");
		set.add("addTime");
		includeMap.put(OrderCom.class, set);
		
		set = new HashSet<String>();
		set.add("id");
		set.add("userName");
		set.add("photo");
		includeMap.put(User.class, set);
		
		set = new HashSet<String>();
		set.add("id");
		set.add("store_name");
		includeMap.put(Store.class, set);
		
		set = new HashSet<String>();
		set.add("id");
		set.add("goods_details");
		set.add("goods_name");
		set.add("seo_description");
		set.add("goods_main_photo");
		set.add("goods_price");
		includeMap.put(Goods.class, set);
		
		set = new HashSet<String>();
		set.add("name");
		set.add("path");
		set.add("ext");
		includeMap.put(Accessory.class, set);
		
		set = new HashSet<String>();
		set.add("id");
		set.add("totalPrice");
		includeMap.put(OrderForm.class, set);
		
		jsonConfig.setJsonPropertyFilter(new ComplexOutPropertyPreFilter(includeMap));

		JSONArray json = JSONArray.fromObject(pList, jsonConfig);
		SignFilter.printNoCheck(request, response, json);
		System.out.println(json);
	}
    
    @RequestMapping({ "/seller/goods_ruku_shenhe.htm" })
    @ResponseBody
    public void goods_ruku_shenhe(HttpServletRequest request, HttpServletResponse response,String currentPage,String id) throws IOException{
    	System.out.println(id);
    	Map params = new HashMap();
//    	OrderComQueryObject eqo =new OrderComQueryObject(currentPage, params,
//                "addTime", "desc");
//    	
//    	eqo.addQuery("obj.store.id", new SysMap("store_id",SecurityUserHolder.getCurrentUser().getStore().getId()), "=");
    	params.put("id",Long.parseLong(id));
    	List pList = this.goodsService.query(
				"select obj from Goods obj where obj.goods_store.id=:id ",
				params, 0, 15);
    	//IPageList pList = this.orderComService.list(eqo);
    	
    	JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
		
		// 需要过滤的类 + 属性
		Map<Class<?>, Set<String>> includeMap = new HashMap<Class<?>, Set<String>>();
		Set<String> set = new HashSet<String>();
		set.add("id");
		set.add("goods_name");
		set.add("seo_keywords");
		set.add("seo_description");
		set.add("goods_price");
		set.add("goods_inventory");
		set.add("goods_weight");
		set.add("goods_click");
		set.add("goods_main_photo");
		set.add("goods_specs");
		set.add("goods_serial");
		includeMap.put(Goods.class, set);
		
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
		
		set = new HashSet<String>();
		set.add("id");
		set.add("store_name");
		includeMap.put(Store.class, set);
		
		set = new HashSet<String>();
		set.add("name");
		set.add("path");
		set.add("ext");
		includeMap.put(Accessory.class, set);
		
		jsonConfig.setJsonPropertyFilter(new ComplexOutPropertyPreFilter(includeMap));

		JSONArray json = JSONArray.fromObject(pList, jsonConfig);
		SignFilter.printNoCheck(request, response, json);
		System.out.println(json);
	}

    //棧代管理
    @SecurityMapping(display = false, rsequence = 0, title = "推荐棧代", value = "/seller/tuijian_zhandai.htm*", rtype = "seller", rname = "推荐棧代", rcode = "tuijian_zhandai_seller", rgroup = "棧代管理")
    @RequestMapping({"/seller/tuijian_zhandai.htm"})
    public ModelAndView tuijian_zhandai(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhanDaiGuanli/tuijian_zhandai.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "棧代合同", value = "/seller/zhandai_hetong.htm*", rtype = "seller", rname = "评价管理", rcode = "zhandai_hetong_seller", rgroup = "直营店管理")
    @RequestMapping({"/seller/zhandai_hetong.htm"})
    public ModelAndView zhandai_hetong(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhanDaiGuanli/zhandai_hetong.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "棧代信息", value = "/seller/zhandai_xinxi.htm*", rtype = "seller", rname = "棧代信息", rcode = "zhandai_xinxi_seller", rgroup = "棧代管理")
    @RequestMapping({"/seller/zhandai_xinxi.htm"})
    public ModelAndView zhandai_xinxi(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhanDaiGuanli/zhandai_xinxi.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "棧代动态", value = "/seller/zhandai_dongtai.htm*", rtype = "seller", rname = "棧代动态", rcode = "zhandai_dongtai_seller", rgroup = "棧代管理")
    @RequestMapping({"/seller/zhandai_dongtai.htm"})
    public ModelAndView zhandai_dongtai(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/zhanDaiGuanli/zhandai_dongtai.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    //资金管理
    @SecurityMapping(display = false, rsequence = 0, title = "对账中心", value = "/seller/duizhang_center.htm*", rtype = "seller", rname = "对账中心", rcode = "duizhang_center_seller", rgroup = "资金管理")
    @RequestMapping({"/seller/duizhang_center.htm"})
    public ModelAndView duizhang_center(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/ziJinGuanLi/duizhang_center.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "对账中心详情", value = "/seller/duizhang_center_details.htm*", rtype = "seller", rname = "对账中心", rcode = "duizhang_center_details_seller", rgroup = "资金管理")
    @RequestMapping({"/seller/duizhang_center_details.htm"})
    public ModelAndView duizhang_center_details(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/ziJinGuanLi/duizhang_center_details.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "资金服务", value = "/seller/money_fuwu.htm*", rtype = "seller", rname = "资金服务", rcode = "money_fuwu_seller", rgroup = "资金管理")
    @RequestMapping({"/seller/money_fuwu.htm"})
    public ModelAndView money_fuwu(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/ziJinGuanLi/money_fuwu.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "银行卡管理", value = "/seller/yinhangka_manager.htm*", rtype = "seller", rname = "银行卡管理", rcode = "yinhangka_manager_seller", rgroup = "资金管理")
    @RequestMapping({"/seller/yinhangka_manager.htm"})
    public ModelAndView yinhangka_manager(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/ziJinGuanLi/yinhangka_manager.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "支出审核", value = "/seller/out_shenhe.htm*", rtype = "seller", rname = "支出审核", rcode = "out_shenhe_seller", rgroup = "资金管理")
    @RequestMapping({"/seller/out_shenhe.htm"})
    public ModelAndView out_shenhe(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/ziJinGuanLi/out_shenhe.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "安全设置", value = "/seller/sefaty_set.htm*", rtype = "seller", rname = "安全设置", rcode = "sefaty_set_seller", rgroup = "资金管理")
    @RequestMapping({"/seller/sefaty_set.htm"})
    public ModelAndView sefaty_set(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/ziJinGuanLi/sefaty_set.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    //商品管理
    @SecurityMapping(display = false, rsequence = 0, title = "招募棧代", value = "/seller/zhaomuzhandai.htm*", rtype = "seller", rname = "招募棧代", rcode = "zhaomuzhandai_seller", rgroup = "商品管理")
    @RequestMapping({"/seller/zhaomuzhandai.htm"})
    public ModelAndView zhaomuzhandai(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/goods_manager/zhaomuzhandai.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

   @SecurityMapping(display = false, rsequence = 0, title = "招募棧代详情", value = "/seller/zhaomuzhandaixiangqing.htm*", rtype = "seller", rname = "招募棧代详情", rcode = "zhaomuzhandaixiangqing_seller", rgroup = "商品管理")
    @RequestMapping({"/seller/zhaomuzhandaixiangqing.htm"})
    public ModelAndView zhaomuzhandaixiangqing(HttpServletRequest request, HttpServletResponse response,String id){
       System.out.println("2222222222222222222222");
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/goods_manager/zhaomuzhandaixiangqing.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);
       System.out.println("id="+id);
       if ((id != null) && (!id.equals(""))){
           /*GoodsBrand goodsBrand = this.goodsBrandService.getObjById(
                   Long.valueOf(Long.parseLong(id)));
           mv.addObject("obj", goodsBrand);*/
           System.out.println("1111111111111111111111");
           System.out.println(id);
       }

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "添加棧代", value = "/seller/tianjiazhandai.htm*", rtype = "seller", rname = "招募棧代", rcode = "tianjiazhandai_seller", rgroup = "商品管理")
    @RequestMapping({"/seller/tianjiazhandai.htm"})
    public ModelAndView tianjiazhandai(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/goods_manager/tianjiazhandai.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }


    @SecurityMapping(display = false, rsequence = 0, title = "商品库存", value = "/seller/goods_kucun.htm*", rtype = "seller", rname = "商品库存", rcode = "goods_kucun_seller", rgroup = "商品管理")
    @RequestMapping({"/seller/goods_kucun.htm"})
    public ModelAndView goods_kucun(HttpServletRequest request,
                                    HttpServletResponse response, String currentPage, String orderBy,
                                    String orderType, String goods_name, String user_class_id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/goods_manager/goods_kucun.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);
        String url = this.configService.getSysConfig().getAddress();
        if ((url == null) || (url.equals(""))){
            url = CommUtil.getURL(request);
        }
        User user = this.userService.getObjById(SecurityUserHolder
                .getCurrentUser().getId());
        String params = "";
        GoodsQueryObject qo = new GoodsQueryObject(currentPage, mv, orderBy,
                orderType);
        qo.addQuery("obj.goods_store.id", new SysMap("goods_store_id", user
                .getStore().getId()), "=");
        qo.setOrderBy("addTime");
        qo.setOrderType("desc");
        IPageList pList = this.goodsService.list(qo);// 根据条件查询商品
        CommUtil.saveIPageList2ModelAndView(url + "/seller/goods_kucun.htm", "",
                params, pList, mv);
        mv.addObject("storeTools", this.storeTools);
        mv.addObject("goodsViewTools", this.goodsViewTools);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "调价申请", value = "/seller/request_tiaojia.htm*", rtype = "seller", rname = "调价申请", rcode = "request_tiaojia_seller", rgroup = "商品管理")
    @RequestMapping({"/seller/request_tiaojia.htm"})
    public ModelAndView request_tiaojia(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/goods_manager/request_tiaojia.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "新增调价申请", value = "/seller/add_request_tiaojia.htm*", rtype = "seller", rname = "调价申请", rcode = "add_request_tiaojia_seller", rgroup = "商品管理")
    @RequestMapping({"/seller/add_request_tiaojia.htm"})
    public ModelAndView add_request_tiaojia(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/goods_manager/add_request_tiaojia.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }
    @SecurityMapping(display = false, rsequence = 0, title = "入库审核", value = "/seller/storage_shenhe.htm*", rtype = "seller", rname = "入库审核", rcode = "storage_shenhe_seller", rgroup = "商品管理")
    @RequestMapping({"/seller/storage_shenhe.htm"})
    public ModelAndView storage_shenhe(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/goods_manager/storage_shenhe.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "提货卡管理", value = "/seller/tihuoka_manager.htm*", rtype = "seller", rname = "提货卡管理", rcode = "tihuoka_manager_seller", rgroup = "商品管理")
    @RequestMapping({"/seller/tihuoka_manager.htm"})
    public ModelAndView tihuoka_manager(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/goods_manager/tihuoka_manager.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "提货卡管理——延期", value = "/seller/pick_up_card_yanqi.htm*", rtype = "seller", rname = "提货卡管理", rcode = "pick_up_card_yanqi_seller", rgroup = "商品管理")
    @RequestMapping({"/seller/pick_up_card_yanqi.htm"})
    public ModelAndView pick_up_card_yanqi(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/goods_manager/pick_up_card_yanqi.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "提货卡管理——新增", value = "/seller/add_pick_up_card.htm*", rtype = "seller", rname = "提货卡管理", rcode = "add_pick_up_card_seller", rgroup = "商品管理")
    @RequestMapping({"/seller/add_pick_up_card.htm"})
    public ModelAndView add_pick_up_card(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/goods_manager/add_pick_up_card.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);

        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "评价管理", value = "/seller/evaluated_manager.htm*", rtype = "seller", rname = "提货卡管理", rcode = "evaluated_manager_seller", rgroup = "商品管理")
    @RequestMapping({"/seller/evaluated_manager.htm"})
    public ModelAndView evaluated_manager(HttpServletRequest request, HttpServletResponse response,String currentPage, String orderBy, String orderType){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/goods_manager/evaluated_manager.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);
      /*  String url = this.configService.getSysConfig().getAddress();
        if ((url == null) || (url.equals(""))){
            url = CommUtil.getURL(request);
        }
        OrderComQueryObject qo = new OrderComQueryObject(currentPage, mv,orderBy, orderType);
        qo.addQuery("obj.parent.id is null", null,null);
        IPageList pList = this.orderComService.list(qo);
        CommUtil.saveIPageList2ModelAndView(
	            url + "/seller/evaluated_manager.htm", "", "", pList, mv);*/
        return mv;
    }

    @SecurityMapping(display = false, rsequence = 0, title = "订货单详情", value = "/seller/seller_support_sheet_details.htm*", rtype = "seller", rname = "订货单详情", rcode = "seller_support_sheet_details_seller", rgroup = "交易管理")
    @RequestMapping({"/seller/seller_support_sheet_details.htm"})
    public ModelAndView seller_support_sheet_details(HttpServletRequest request, HttpServletResponse response, String currentPage, String order_status, String order_id, String beginTime, String endTime, String buyer_userName,String id){
        ModelAndView mv = new JModelAndView(
                "user/default/usercenter/seller_support_sheet_details.html",
                this.configService.getSysConfig(), this.userConfigService
                .getUserConfig(), 0, request, response);
        OrderFormQueryObject ofqo = new OrderFormQueryObject(currentPage, mv,
                "addTime", "desc");

        System.out.println(id);
        ofqo.addQuery("obj.store.user.id",
                new SysMap("user_id",
                        SecurityUserHolder.getCurrentUser().getId()), "=");

        if (!CommUtil.null2String(id).equals("")){
            ofqo.addQuery("obj.id",
                    new SysMap("id", "%" + id +
                            "%"), "like");
        }
        IPageList pList = this.orderFormService.list(ofqo);
        CommUtil.saveIPageList2ModelAndView("", "", "", pList, mv);
        mv.addObject("storeViewTools", this.storeViewTools);
        mv.addObject("id", id);

        return mv;
    }
}

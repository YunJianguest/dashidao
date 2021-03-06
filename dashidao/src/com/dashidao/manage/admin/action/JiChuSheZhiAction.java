package com.dashidao.manage.admin.action;

import com.dashidao.core.annotation.SecurityMapping;
import com.dashidao.core.domain.virtual.SysMap;
import com.dashidao.core.mv.JModelAndView;
import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.tools.CommUtil;
import com.dashidao.core.tools.WebForm;
import com.dashidao.foundation.domain.Accessory;
import com.dashidao.foundation.domain.Activity;
import com.dashidao.foundation.domain.ActivityGoods;
import com.dashidao.foundation.domain.ChannelRate;
import com.dashidao.foundation.domain.Goods;
import com.dashidao.foundation.domain.GoodsBrand;
import com.dashidao.foundation.domain.JiChuSheZhi;
import com.dashidao.foundation.domain.Recommend;
import com.dashidao.foundation.domain.Setting;
import com.dashidao.foundation.domain.User;
import com.dashidao.foundation.domain.query.ActivityGoodsQueryObject;
import com.dashidao.foundation.domain.query.ActivityQueryObject;
import com.dashidao.foundation.domain.query.ChannelRateQueryObject;
import com.dashidao.foundation.domain.query.GoldRecordQueryObject;
import com.dashidao.foundation.domain.query.JiChuSheZhiQueryObject;
import com.dashidao.foundation.domain.query.SettingQueryObject;
import com.dashidao.foundation.domain.query.YunkeAuditQueryObject;
import com.dashidao.foundation.service.*;
import com.dashidao.foundation.service.impl.GoodsTypeServiceImpl;
import com.dashidao.jwt.config.ResponseMsg;
import com.dashidao.jwt.filter.SignFilter;
import com.dashidao.jwt.util.ComplexOutPropertyPreFilter;
import com.easyjf.beans.BeanUtils;
import com.easyjf.beans.BeanWrapper;

import io.jsonwebtoken.Claims;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 基础设置
 */
@Controller
public class JiChuSheZhiAction {
	
	 @Autowired
	 private ISysConfigService configService;

	 @Autowired
	 private IUserConfigService userConfigService;
	
    @Autowired
    private IJiChuSheZhiService jiChuSheZhiService;
    
    @Autowired
    private ISettingService settingService;
    
    @Autowired
    private IGoodsBrandService goodsBandService;

    //@SecurityMapping(display = false, rsequence = 0, title = "基础设置", value = "/admin/setting_admin.htm*", rtype = "admin", rname = "基础设置", rcode = "setting_admin", rgroup = "系统设置")
    @RequestMapping({"/admin/setting_admin.htm"})
    public ModelAndView jcsz_admin(HttpServletRequest request, HttpServletResponse response, String id,String currentPage){
        
        ModelAndView mv = new JModelAndView("admin/blue/dodot/setting_admin.html",
                this.configService.getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
        JiChuSheZhiQueryObject qo = new JiChuSheZhiQueryObject(currentPage, mv, null, null);
		IPageList pList = this.jiChuSheZhiService.list(qo);
		JiChuSheZhi jichushezhi = new JiChuSheZhi();
		if(pList.getResult()!=null&&pList.getResult().size()>0){
			jichushezhi = (JiChuSheZhi) pList.getResult().get(0);
		}
        mv.addObject("obj", jichushezhi);
        
        //JiChuSheZhiQueryObject qo = new JiChuSheZhiQueryObject(currentPage, mv, null, null);
        //qo.addQuery("obj.addTime", new SysMap("beginTime",  CommUtil.formatDate(beginTime)), ">=");
        //云客代金券规则 zb_type=1
        /*List<Setting> yunkeCash = this.settingService.query(
                "select obj from Setting obj where obj.zb_type = 1", null, -1, -1);*/
      //云客推荐商家佣金 zb_type=2
        List<Setting> yunkeRecommandStore = this.settingService.query(
                "select obj from Setting obj where obj.zb_type = 2", null, -1, -1);
      //云客推荐栈代佣金 zb_type=3
        List<Setting> yunkeRecommandCash = this.settingService.query(
                "select obj from Setting obj where obj.zb_type = 3", null, -1, -1);
      //佣金扣税税率
        List<Setting>tax = this.settingService.query(
                "select obj from Setting obj where obj.zb_type = 4", null, -1, -1);
      //提现规则
        List<Setting> withdrawCash = this.settingService.query(
                "select obj from Setting obj where obj.zb_type = 5", null, -1, -1);
        //mv.addObject("yunkeCash", yunkeCash);
        mv.addObject("yunkeRecommandStore", yunkeRecommandStore);
        mv.addObject("yunkeRecommandCash", yunkeRecommandCash);
        mv.addObject("tax", tax);
        mv.addObject("withdrawCash", withdrawCash);
        return mv;
    } 
    //@SecurityMapping(display = false, rsequence = 0, title = "基础设置", value = "/admin/setting_admin.htm*", rtype = "admin", rname = "基础设置", rcode = "setting_admin", rgroup = "系统设置")
    @RequestMapping({"/admin/sjbzj_admin.htm"})
    public ModelAndView sjbzj_admin(HttpServletRequest request, HttpServletResponse response, String id,String currentPage){
        
        ModelAndView mv = new JModelAndView("admin/blue/dodot/sjbzj_admin.html",
                this.configService.getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);
       
        //云客代金券规则 zb_type=1
        String brand_name = request.getParameter("brand_name");
        SettingQueryObject paramIQueryObject=new SettingQueryObject(currentPage, mv, "addTime", "desc");
        paramIQueryObject.addQuery("obj.zb_type", new SysMap("zb_type", 6),"=");
        if ((brand_name != null) && (!brand_name.equals(""))){
        	paramIQueryObject.addQuery("obj.goodsBrand.name",
                    new SysMap("obj_name", "%" +
                    		brand_name.trim() + "%"), "like");
        	mv.addObject("name", brand_name);
        }
        IPageList pList= settingService.list(paramIQueryObject);
        CommUtil.saveIPageList2ModelAndView("", "", "", pList, mv);
        
        JiChuSheZhiQueryObject qo = new JiChuSheZhiQueryObject(currentPage, mv, null, null);
		IPageList pList1 = this.jiChuSheZhiService.list(qo);
		JiChuSheZhi jichushezhi = new JiChuSheZhi();
		if(pList1.getResult()!=null&&pList1.getResult().size()>0){
			jichushezhi = (JiChuSheZhi) pList1.getResult().get(0);
		}
        mv.addObject("obj", jichushezhi);
        
        List<GoodsBrand> goodsBrand = this.goodsBandService.query(
                "select obj from GoodsBrand obj", null, -1, -1);
        mv.addObject("goodsBrand", goodsBrand);
        
        return mv;
    } 
    //@SecurityMapping(display = false, rsequence = 0, title = "基础设置", value = "/admin/setting_admin.htm*", rtype = "admin", rname = "基础设置", rcode = "setting_admin", rgroup = "系统设置")
    @RequestMapping({"/admin/zdbzj_admin.htm"})
    public ModelAndView zdbzj_admin(HttpServletRequest request, HttpServletResponse response, String id,String currentPage){
        
        ModelAndView mv = new JModelAndView("admin/blue/dodot/zdbzj_admin.html",
                this.configService.getSysConfig(),
                this.userConfigService.getUserConfig(), 0, request, response);

        SettingQueryObject paramIQueryObject=new SettingQueryObject(currentPage, mv, "addTime", "desc");
        paramIQueryObject.addQuery("obj.zb_type", new SysMap("zb_type", 7),"=");
        IPageList pList= settingService.list(paramIQueryObject);
        CommUtil.saveIPageList2ModelAndView("", "", "", pList, mv);
        
        JiChuSheZhiQueryObject qo = new JiChuSheZhiQueryObject(currentPage, mv, null, null);
		IPageList pList1 = this.jiChuSheZhiService.list(qo);
		JiChuSheZhi jichushezhi = new JiChuSheZhi();
		if(pList1.getResult()!=null&&pList1.getResult().size()>0){
			jichushezhi = (JiChuSheZhi) pList1.getResult().get(0);
		}
        mv.addObject("obj", jichushezhi);
        return mv;
    } 
    /**
     * 添加 修改
     * @param request
     * @param response
     * @param id
     * @throws IOException 
     * @throws ParseException 
     */
    @RequestMapping({"/admin/setting_save_admin.htm"})
    public void save(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException{
    	 
    	String msString="添加失败！";
    	
    	String id=request.getParameter("id");

    	String zb_shifujine=request.getParameter("zb_shifujine");
    	String zb_jifendixiao=request.getParameter("zb_jifendixiao");
    	String zb_dingdanshiyongjifen=request.getParameter("zb_dingdanshiyongjifen");

    	String zb_hygmJinE=request.getParameter("zb_hygmJinE");
    	String zb_huiyuandingdan=request.getParameter("zb_huiyuandingdan");
    	String zb_tuijianyunke=request.getParameter("zb_tuijianyunke");
    	
    	String zb_cjkhzq=request.getParameter("zb_cjkhzq");
    	String zb_cjkhbz=request.getParameter("zb_cjkhbz");
    	String zb_zjkhzq=request.getParameter("zb_zjkhzq");
    	String zb_zjkhbz=request.getParameter("zb_zjkhbz");
    	
    	String zb_sjkbzq=request.getParameter("zb_sjkbzq");
    	String zb_sjxsJinE=request.getParameter("zb_sjxsJinE");
    	String zb_syzssj=request.getParameter("zb_syzssj");
    	String zb_dysj=request.getParameter("zb_dysj");
    	
    	String zb_qrshqx=request.getParameter("zb_qrshqx");
    	String zb_qxfkqx=request.getParameter("zb_qxfkqx");
    	String zb_qrthsqqx=request.getParameter("zb_qrthsqqx");
    	String zb_qrthsshqx=request.getParameter("zb_qrthsshqx");
    	String zb_zdfhqx=request.getParameter("zb_zdfhqx");
    	String zb_fhxq=request.getParameter("zb_fhxq");
    	String zb_wfhfk=request.getParameter("zb_wfhfk");
    	String zb_wfhjg=request.getParameter("zb_wfhjg");
    	String zb_jgwfhfk=request.getParameter("zb_jgwfhfk");
    	String zb_wfhqx=request.getParameter("zb_wfhqx"); 
    	String zb_xfzycshsj=request.getParameter("zb_xfzycshsj"); 
    	String zb_xfzycshsqsj=request.getParameter("zb_xfzycshsqsj"); 
    	String zb_xfzycshsqcs=request.getParameter("zb_xfzycshsqcs"); 
    	String zb_zdgdjjfhcs=request.getParameter("zb_zdgdjjfhcs"); 
    	String zb_thhjzsj=request.getParameter("zb_thhjzsj"); 
    	//String zb_wfhqx=request.getParameter("zb_wfhqx"); 
    	
    	String zb_spcpljg=request.getParameter("zb_spcpljg");
    	String zb_spcplxj=request.getParameter("zb_spcplxj");
    	
    	String zb_htdqts=request.getParameter("zb_htdqts");
    	String zb_jsjinE=request.getParameter("zb_jsjinE");
    	
    	String zb_zdhtdqts=request.getParameter("zb_zdhtdqts"); 
    	
    	String zb_tjyksz=request.getParameter("zb_tjyksz"); 
    	String zb_hdjgsx=request.getParameter("zb_hdjgsx");
    	String zb_dcqjzcts=request.getParameter("zb_dcqjzcts");
    	
    	String zb_sfbls=request.getParameter("zb_sfbls");
    	String zb_jtbls=request.getParameter("zb_jtbls");
    	String zb_sfbl=request.getParameter("zb_sfbl");
    	String zb_jtbl=request.getParameter("zb_jtbl");
    	String zb_lsbzjthqx=request.getParameter("zb_lsbzjthqx");
    	String zb_pfbzjthqx=request.getParameter("zb_pfbzjthqx");
    	String zb_lsbzj=request.getParameter("zb_lsbzj");
    	String zb_pfbzj=request.getParameter("zb_pfbzj");
    	
    	String zb_sppjts=request.getParameter("zb_sppjts");
    	String zb_spischeck=request.getParameter("zb_spischeck");
    	
    	JiChuSheZhi jichushezhi = new JiChuSheZhi();

    	if (id==null||id.equals("")){
    		if(zb_shifujine!=null){
        		jichushezhi.setZb_shifujine(new BigDecimal(zb_shifujine));
        	}
        	if(zb_jifendixiao!=null){
        	    jichushezhi.setZb_jifendixiao(Double.parseDouble(zb_jifendixiao));
        	}
        	if(zb_dingdanshiyongjifen!=null){
        		jichushezhi.setZb_dingdanshiyongjifen(Integer.parseInt(zb_dingdanshiyongjifen));
        	}
        	
        	if(zb_hygmJinE!=null){
        		jichushezhi.setZb_hygmJinE(new BigDecimal(zb_hygmJinE));
        	}
        	if(zb_huiyuandingdan!=null){
        		jichushezhi.setZb_huiyuandingdan(new BigDecimal(zb_huiyuandingdan));
        	}
        	if(zb_tuijianyunke!=null){
        		jichushezhi.setZb_tuijianyunke(Integer.parseInt(zb_tuijianyunke));
        	}
        	
        	if(zb_cjkhzq!=null){
        		jichushezhi.setZb_cjkhzq(Integer.parseInt(zb_cjkhzq));
        	}
        	if(zb_cjkhbz!=null){
        		jichushezhi.setZb_cjkhbz(new BigDecimal(zb_cjkhbz));
        	}
        	if(zb_zjkhzq!=null){
        		jichushezhi.setZb_zjkhzq(Integer.parseInt(zb_zjkhzq));
        	}
        	if(zb_zjkhbz!=null){
        		jichushezhi.setZb_zjkhbz(new BigDecimal(zb_zjkhbz));
        	}
        	
        	
        	if(zb_sjkbzq!=null){
        		jichushezhi.setZb_sjkbzq(Double.parseDouble(zb_sjkbzq));
        	}
        	if(zb_sjxsJinE!=null){
        		jichushezhi.setZb_sjxsJinE(new BigDecimal(zb_sjxsJinE));
        	}
        	if(zb_syzssj!=null){
        		jichushezhi.setZb_syzssj(Double.parseDouble(zb_syzssj));
        	}
        	if(zb_dysj!=null){
        		jichushezhi.setZb_dysj(Double.parseDouble(zb_dysj));
        	}
        	
        	if(zb_dysj!=null){
        		jichushezhi.setZb_qrshqx(new BigDecimal(zb_qrshqx));
        	}
        	
        	if(zb_qxfkqx!=null){
        		jichushezhi.setZb_qxfkqx(new BigDecimal(zb_qxfkqx));
        	}
        	if(zb_qrthsqqx!=null){
        		jichushezhi.setZb_qrthsqqx(new BigDecimal(zb_qrthsqqx));
        	}
        	if(zb_qrthsshqx!=null){
        		jichushezhi.setZb_qrthsshqx(new BigDecimal(zb_qrthsshqx));
        	}
        	if(zb_zdfhqx!=null){
        		jichushezhi.setZb_zdfhqx(new BigDecimal(zb_zdfhqx));
        	}
        	if(zb_fhxq!=null){
        		jichushezhi.setZb_fhxq(new BigDecimal(zb_fhxq));
        	}
        	if(zb_wfhfk!=null){
        		jichushezhi.setZb_wfhfk(new BigDecimal(zb_wfhfk));
        	}
        	if(zb_wfhjg!=null){
        		jichushezhi.setZb_wfhjg(new BigDecimal(zb_wfhjg));
        	}
        	if(zb_jgwfhfk!=null){
        		jichushezhi.setZb_jgwfhfk(new BigDecimal(zb_jgwfhfk));
        	}
        	if(zb_wfhqx!=null){
        		jichushezhi.setZb_wfhqx(new BigDecimal(zb_wfhqx));
        	}
        	if(zb_xfzycshsj!=null){
        		jichushezhi.setZb_xfzycshsj(new BigDecimal(zb_xfzycshsj));
        	}
        	if(zb_xfzycshsqsj!=null){
        		jichushezhi.setZb_xfzycshsqsj(new BigDecimal(zb_xfzycshsqsj));
        	}
        	if(zb_xfzycshsqcs!=null){
        		jichushezhi.setZb_xfzycshsqcs(Integer.parseInt(zb_xfzycshsqcs));
        	}
        	if(zb_zdgdjjfhcs!=null){
        		jichushezhi.setZb_zdgdjjfhcs(Integer.parseInt(zb_zdgdjjfhcs));
        	}
        	if(zb_thhjzsj!=null){
        		jichushezhi.setZb_thhjzsj(Double.parseDouble(zb_thhjzsj));
        	}
        	
        	
        	if(zb_spcpljg!=null){
        		jichushezhi.setZb_spcpljg(new BigDecimal(zb_spcpljg));
        	}
        	if(zb_spcplxj!=null){
        		jichushezhi.setZb_spcplxj(new BigDecimal(zb_spcplxj));
        	}
        	
        	if(zb_htdqts!=null){
        		jichushezhi.setZb_htdqts(new BigDecimal(zb_htdqts));
        	}
        	if(zb_jsjinE!=null){
        		jichushezhi.setZb_jsjinE(new BigDecimal(zb_jsjinE));
        	}
        	
        	if(zb_zdhtdqts!=null){
        		jichushezhi.setZb_zdhtdqts(new BigDecimal(zb_zdhtdqts));
        	}
        	
        	if(zb_tjyksz!=null){
        		jichushezhi.setZb_tjyksz(Integer.parseInt(zb_tjyksz));
        	}
        	if(zb_hdjgsx!=null){
        		jichushezhi.setZb_hdjgsx(Double.parseDouble(zb_hdjgsx));
        	}
        	if(zb_dcqjzcts!=null){
        		jichushezhi.setZb_dcqjzcts(Integer.parseInt(zb_dcqjzcts));
        	}
        	
        	if(zb_sfbls!=null){
        		jichushezhi.setZb_sfbls(Double.parseDouble(zb_sfbls));
        	}
        	if(zb_jtbls!=null){
        		jichushezhi.setZb_jtbls(Double.parseDouble(zb_jtbls));
        	}
        	if(zb_sfbl!=null){
        		jichushezhi.setZb_sfbl(Double.parseDouble(zb_sfbl));
        	}
        	if(zb_jtbl!=null){
        		jichushezhi.setZb_jtbl(Double.parseDouble(zb_jtbl));
        	}
        	if(zb_lsbzjthqx!=null){
        		jichushezhi.setZb_lsbzjthqx(new BigDecimal(zb_lsbzjthqx));
        	}
        	if(zb_pfbzjthqx!=null){
        		jichushezhi.setZb_pfbzjthqx(new BigDecimal(zb_pfbzjthqx));
        	}
        	if(zb_lsbzj!=null){
        		jichushezhi.setZb_lsbzj(new BigDecimal(zb_lsbzj));
        	}
        	if(zb_pfbzj!=null){
        		jichushezhi.setZb_pfbzj(new BigDecimal(zb_pfbzj));
        	}
        	
        	if(zb_sppjts!=null && !"".equals(zb_sppjts)) {
        		jichushezhi.setZb_sppjts(Integer.parseInt(zb_sppjts));
        	}
        	if(zb_spischeck!=null && !"".equals(zb_spischeck)) {
        		jichushezhi.setZb_spischeck(Integer.parseInt(zb_spischeck));
        	}
        	
        	/*String zb_sppjts=request.getParameter("zb_sppjts");
        	String zb_spischeck=request.getParameter("zb_spischeck");*/
        	
    		jiChuSheZhiService.save(jichushezhi);
    		msString="操作成功！";
    	}else {
    	   jichushezhi = this.jiChuSheZhiService.getObjById(Long.parseLong(id));
    	    if(zb_shifujine!=null){
       		    jichushezhi.setZb_shifujine(new BigDecimal(zb_shifujine));
	       	}
	       	if(zb_jifendixiao!=null){
	       	    jichushezhi.setZb_jifendixiao(Double.parseDouble(zb_jifendixiao));
	       	}
	       	if(zb_dingdanshiyongjifen!=null){
	       		jichushezhi.setZb_dingdanshiyongjifen(Integer.parseInt(zb_dingdanshiyongjifen));
	       	}
	       	
	       	if(zb_hygmJinE!=null){
	       		jichushezhi.setZb_hygmJinE(new BigDecimal(zb_hygmJinE));
	       	}
	       	if(zb_huiyuandingdan!=null){
	       		jichushezhi.setZb_huiyuandingdan(new BigDecimal(zb_huiyuandingdan));
	       	}
	       	if(zb_tuijianyunke!=null){
	       		jichushezhi.setZb_tuijianyunke(Integer.parseInt(zb_tuijianyunke));
	       	}
	       	
	       	if(zb_cjkhzq!=null){
	       		jichushezhi.setZb_cjkhzq(Integer.parseInt(zb_cjkhzq));
	       	}
	       	if(zb_cjkhbz!=null){
	       		jichushezhi.setZb_cjkhbz(new BigDecimal(zb_cjkhbz));
	       	}
	       	if(zb_zjkhzq!=null){
	       		jichushezhi.setZb_zjkhzq(Integer.parseInt(zb_zjkhzq));
	       	}
	       	if(zb_zjkhbz!=null){
	       		jichushezhi.setZb_zjkhbz(new BigDecimal(zb_zjkhbz));
	       	}
	       	
	       	
	       	if(zb_sjkbzq!=null){
	       		jichushezhi.setZb_sjkbzq(Double.parseDouble(zb_sjkbzq));
	       	}
	       	if(zb_sjxsJinE!=null){
	       		jichushezhi.setZb_sjxsJinE(new BigDecimal(zb_sjxsJinE));
	       	}
	       	if(zb_syzssj!=null){
	       		jichushezhi.setZb_syzssj(Double.parseDouble(zb_syzssj));
	       	}
	       	if(zb_dysj!=null){
	       		jichushezhi.setZb_dysj(Double.parseDouble(zb_dysj));
	       	}
	       	
	       	if(zb_dysj!=null){
	       		jichushezhi.setZb_qrshqx(new BigDecimal(zb_qrshqx));
	       	}
	       	
	       	if(zb_qxfkqx!=null){
	       		jichushezhi.setZb_qxfkqx(new BigDecimal(zb_qxfkqx));
	       	}
	       	if(zb_qrthsqqx!=null){
	       		jichushezhi.setZb_qrthsqqx(new BigDecimal(zb_qrthsqqx));
	       	}
	       	if(zb_qrthsshqx!=null){
	       		jichushezhi.setZb_qrthsshqx(new BigDecimal(zb_qrthsshqx));
	       	}
	       	if(zb_zdfhqx!=null){
	       		jichushezhi.setZb_zdfhqx(new BigDecimal(zb_zdfhqx));
	       	}
	       	if(zb_fhxq!=null){
	       		jichushezhi.setZb_fhxq(new BigDecimal(zb_fhxq));
	       	}
	       	if(zb_wfhfk!=null){
	       		jichushezhi.setZb_wfhfk(new BigDecimal(zb_wfhfk));
	       	}
	       	if(zb_wfhjg!=null){
	       		jichushezhi.setZb_wfhjg(new BigDecimal(zb_wfhjg));
	       	}
	       	if(zb_jgwfhfk!=null){
	       		jichushezhi.setZb_jgwfhfk(new BigDecimal(zb_jgwfhfk));
	       	}
	       	if(zb_wfhqx!=null){
	       		jichushezhi.setZb_wfhqx(new BigDecimal(zb_wfhqx));
	       	}
	       	
	       	
	       	if(zb_spcpljg!=null){
	       		jichushezhi.setZb_spcpljg(new BigDecimal(zb_spcpljg));
	       	}
	       	if(zb_spcplxj!=null){
	       		jichushezhi.setZb_spcplxj(new BigDecimal(zb_spcplxj));
	       	}
	       	
	       	if(zb_htdqts!=null){
	       		jichushezhi.setZb_htdqts(new BigDecimal(zb_htdqts));
	       	}
	       	if(zb_jsjinE!=null){
	       		jichushezhi.setZb_jsjinE(new BigDecimal(zb_jsjinE));
	       	}
	       	
	       	if(zb_zdhtdqts!=null){
	       		jichushezhi.setZb_zdhtdqts(new BigDecimal(zb_zdhtdqts));
	       	}
	       	
	       	if(zb_tjyksz!=null){
        		jichushezhi.setZb_tjyksz(Integer.parseInt(zb_tjyksz));
        	}
	       	if(zb_hdjgsx!=null){
        		jichushezhi.setZb_hdjgsx(Double.parseDouble(zb_hdjgsx));
        	}
        	if(zb_dcqjzcts!=null){
        		jichushezhi.setZb_dcqjzcts(Integer.parseInt(zb_dcqjzcts));
        	}
        	if(zb_sfbls!=null){
        		jichushezhi.setZb_sfbls(Double.parseDouble(zb_sfbls));
        	}
        	if(zb_jtbls!=null){
        		jichushezhi.setZb_jtbls(Double.parseDouble(zb_jtbls));
        	}
        	
        	if(zb_sfbl!=null){
        		jichushezhi.setZb_sfbl(Double.parseDouble(zb_sfbl));
        	}
        	if(zb_jtbl!=null){
        		jichushezhi.setZb_jtbl(Double.parseDouble(zb_jtbl));
        	}
        	if(zb_lsbzjthqx!=null){
        		jichushezhi.setZb_lsbzjthqx(new BigDecimal(zb_lsbzjthqx));
        	}
        	if(zb_pfbzjthqx!=null){
        		jichushezhi.setZb_pfbzjthqx(new BigDecimal(zb_pfbzjthqx));
        	}
        	if(zb_lsbzj!=null){
        		jichushezhi.setZb_lsbzj(new BigDecimal(zb_lsbzj));
        	}
        	if(zb_pfbzj!=null){
        		jichushezhi.setZb_pfbzj(new BigDecimal(zb_pfbzj));
        	}
        	if(zb_sppjts!=null && !"".equals(zb_sppjts)) {
        		jichushezhi.setZb_sppjts(Integer.parseInt(zb_sppjts));
        	}
        	if(zb_spischeck!=null && !"".equals(zb_spischeck)) {
        		jichushezhi.setZb_spischeck(Integer.parseInt(zb_spischeck));
        	}
    	   jiChuSheZhiService.update(jichushezhi);
    	   msString="操作成功！";
    	}
    	PrintWriter printWriter2 = response.getWriter();

		printWriter2.print(ResponseMsg.successWithData(msString));
		printWriter2.flush();
		printWriter2.close(); 
    }
    
    
    /**
     * 添加 修改 列表
     * @param request
     * @param response
     * @param id
     * @throws IOException 
     * @throws ParseException 
     */
    @RequestMapping({"/admin/settingList_save_admin.htm"})
    public void saveList(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException{
    	 
    	String msString="添加失败！";
    	
    	String id=request.getParameter("id");
    	
    	String zb_one_int_attr=request.getParameter("zb_one_int_attr");
    	
    	String zb_one_bigDecimal_attr=request.getParameter("zb_one_bigDecimal_attr");
    	String zb_two_bigDecimal_attr=request.getParameter("zb_two_bigDecimal_attr");
    	String zb_three_bigDecimal_attr=request.getParameter("zb_three_bigDecimal_attr");
    	String zb_four_bigDecimal_attr=request.getParameter("zb_four_bigDecimal_attr");
    	
    	String zb_type=request.getParameter("zb_type");
    	
    	String goodsType_id=request.getParameter("goodsType_id");
    	
    	String zb_startdate=request.getParameter("zb_startdate");
    	String zb_enddate=request.getParameter("zb_enddate");
    	
    	Setting setting = new Setting();
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	if(zb_one_int_attr!=null){
    		setting.setZb_one_int_attr(Integer.parseInt(zb_one_int_attr));
    	}
    	if(zb_one_bigDecimal_attr!=null){
    		setting.setZb_one_bigDecimal_attr(new BigDecimal(zb_one_bigDecimal_attr));
    	}
    	if(zb_two_bigDecimal_attr!=null){
    		setting.setZb_two_bigDecimal_attr(new BigDecimal(zb_two_bigDecimal_attr));
    	}
    	if(zb_three_bigDecimal_attr!=null){
    		setting.setZb_three_bigDecimal_attr(new BigDecimal(zb_three_bigDecimal_attr));
    	}
    	if(zb_four_bigDecimal_attr!=null){
    		setting.setZb_four_bigDecimal_attr(new BigDecimal(zb_four_bigDecimal_attr));
    	}
    	if(zb_startdate!=null){
    		setting.setZb_startdate(df.parse(zb_startdate));
    	}
    	if(zb_enddate!=null){
    		setting.setZb_enddate(df.parse(zb_enddate));
    	}
    	setting.setZb_type(Integer.parseInt(zb_type));
        if(goodsType_id!=null&&!goodsType_id.equals("")){
        	setting.setGoodsBrand(this.goodsBandService.getObjById(Long.parseLong(goodsType_id)));
        }
    	
    	if (id==null||id.equals("")){
    		settingService.save(setting);
    		msString="操作成功！";
    	}else {
    	   setting.setId(Long.parseLong(id));
    	   settingService.update(setting);
    	   msString="操作成功！";
    	}
    	PrintWriter printWriter2 = response.getWriter();

		printWriter2.print(ResponseMsg.successWithData(msString));
		printWriter2.flush();
		printWriter2.close(); 
    }
    /**
     *  列表
     * @param request
     * @param response
     * @param id
     * @throws IOException 
     * @throws ParseException 
     */
    @RequestMapping({"/admin/showList_admin.htm"})
    public void showList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String type = request.getParameter("type");
    	Map<String,Object> whereMap = new HashMap<>();
		whereMap.put("state", 0);
		Claims claims = SignFilter.checkSign(request, response);
		if (claims != null) {
//			String id = claims.getId();
			Map params = new HashMap();
			 params.put("zb_type", type);
		        List<Setting> setting_list = this.settingService
		                                 .query(
		                                     "select obj from Setting obj where obj.zb_type =:zb_type",
		                                     params, -1, -1);
		        
		        JsonConfig jsonConfig = new JsonConfig(); 
				// 需要过滤的类 + 属性
				Map<Class<?>, Set<String>> includeMap = new HashMap<Class<?>, Set<String>>();
				Set<String> set = new HashSet<String>();
				set = new HashSet<String>();
				set.add("name");
				includeMap.put(GoodsBrand.class, set);
				
				jsonConfig.setJsonPropertyFilter(new ComplexOutPropertyPreFilter(includeMap));
				JSONArray json = JSONArray.fromObject(setting_list, jsonConfig);
				SignFilter.printNoCheck(request, response, json);
				
		}
		
	}
   
}





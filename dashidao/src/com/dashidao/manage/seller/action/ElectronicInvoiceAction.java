package com.dashidao.manage.seller.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dashidao.foundation.domain.ElectronicInvoice;
import com.dashidao.foundation.domain.Goods;
import com.dashidao.foundation.domain.GoodsCart;
import com.dashidao.foundation.domain.OrderForm;
import com.dashidao.foundation.domain.User;
import com.dashidao.foundation.service.IElectronicInvoiceService;
import com.dashidao.foundation.service.ISysConfigService;
import com.dashidao.foundation.service.IUserConfigService;
import com.dashidao.jwt.filter.SignFilter;

@Controller
public class ElectronicInvoiceAction {
	@Autowired
    private ISysConfigService configService;

    @Autowired
    private IUserConfigService userConfigService;
    
    @Autowired
    private IElectronicInvoiceService electronicInvoiceService;
    
    @RequestMapping({ "/seller/addEletronic.htm" })
	@ResponseBody
    public void addEletronic(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	String url=request.getParameter("url");
    	System.out.println(url);
    	System.out.println(request.getParameter("order_id"));
    	OrderForm order=new OrderForm();
    	order.setId(Long.parseLong(request.getParameter("order_id")));
    	
    	User user=new User();
    	user.setId(Long.parseLong(request.getParameter("user_id")));
    	
    	ElectronicInvoice ee=new ElectronicInvoice();
    	ee.setAddTime(new Date());
    	ee.setDeleteStatus(false);
    	ee.setUrl(url);
    	ee.setOrder(order);
    	ee.setUser(user);
    	
    	this.electronicInvoiceService.save(ee);
    	
    	SignFilter.printNoCheck(request, response, "添加成功！");
    }
    
}

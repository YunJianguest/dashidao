package com.dashidao.foundation.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.dashidao.core.domain.IdEntity;
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "dashidao_maintenanceManager")
public class MaintenanceManager extends IdEntity{
	//订单编号
	@ManyToOne
	private OrderForm order;
	//维修原因
	private String content;
	//上传凭证路径
	private String url;
	//状态
	private int states;
	//商品描述
	private  String goods_introduce;
	
	public String getGoods_introduce() {
		return goods_introduce;
	}
	public void setGoods_introduce(String goods_introduce) {
		this.goods_introduce = goods_introduce;
	}
	public OrderForm getOrder() {
		return order;
	}
	public void setOrder(OrderForm order) {
		this.order = order;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getStates() {
		return states;
	}
	public void setStates(int states) {
		this.states = states;
	}
	
	
	
	
	
}

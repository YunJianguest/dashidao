package com.dashidao.foundation.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.dashidao.core.domain.IdEntity;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "dashidao_electronicInvoice")
public class ElectronicInvoice extends IdEntity{
	//订单编号
	@OneToOne
	private OrderForm order;
	//发票路径
	private String url;
	//上传人
	@OneToOne
	private User user;
	public OrderForm getOrder() {
		return order;
	}
	public void setOrder(OrderForm order) {
		this.order = order;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}

package com.dashidao.foundation.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.dashidao.core.domain.IdEntity;
import javax.persistence.OneToOne;

/**
 * 消费记录
 * @author Administrator
 *
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "dashidao_xiaoFeiJiLu")
public class XiaoFeiJiLu extends IdEntity  {
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	private OrderForm orderForm;//订单
	private String order_bh;//订单编号
	private BigDecimal jineE;//金额
	private String xiaofeiname;//消费名称
	public OrderForm getOrderForm() {
		return orderForm;
	}
	public void setOrderForm(OrderForm orderForm) {
		this.orderForm = orderForm;
	}
	public String getOrder_bh() {
		return order_bh;
	}
	public void setOrder_bh(String order_bh) {
		this.order_bh = order_bh;
	}
	public BigDecimal getJineE() {
		return jineE;
	}
	public void setJineE(BigDecimal jineE) {
		this.jineE = jineE;
	}
	public String getXiaofeiname() {
		return xiaofeiname;
	}
	public void setXiaofeiname(String xiaofeiname) {
		this.xiaofeiname = xiaofeiname;
	}
	
	
	
	

}

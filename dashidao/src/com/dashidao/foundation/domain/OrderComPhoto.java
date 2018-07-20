package com.dashidao.foundation.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.dashidao.core.domain.IdEntity;

/**
 * 评价附件
 * @author Administrator
 *
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "dashidao_ordercomphoto")
public class OrderComPhoto extends IdEntity {
	 private static final long serialVersionUID = 8707872936870153411L;
	 	//附件
	 	@ManyToOne
	    private Accessory acc;
	 	//评价
	 	@ManyToOne
	 	private OrderCom orderCom;
		public Accessory getAcc() {
			return acc;
		}
		public void setAcc(Accessory acc) {
			this.acc = acc;
		}
		public OrderCom getOrderCom() {
			return orderCom;
		}
		public void setOrderCom(OrderCom orderCom) {
			this.orderCom = orderCom;
		}
	 	
	 	

}

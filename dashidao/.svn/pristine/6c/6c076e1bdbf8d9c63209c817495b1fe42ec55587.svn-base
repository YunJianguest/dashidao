package com.dashidao.foundation.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.dashidao.core.domain.IdEntity;

import javax.persistence.OneToOne;

/**
 * 充值记录
 * @author Administrator
 *
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "dashidao_chongZhiJiLu")
public class ChongZhiJiLu extends IdEntity {
	private static final long serialVersionUID = 1L;
	
	private String cz_type;//充值类型 0 在线充值 1 购物卡充值
	private String zf_type;//支付类型 0微信 1支付宝
	private BigDecimal cz_jinE;//充值金额
	@OneToOne
	private ShoppingCard shoppingCard;//购物卡id
	
	@ManyToOne
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getCz_type() {
		return cz_type;
	}
	public void setCz_type(String cz_type) {
		this.cz_type = cz_type;
	}
	public String getZf_type() {
		return zf_type;
	}
	public void setZf_type(String zf_type) {
		this.zf_type = zf_type;
	}
	public BigDecimal getCz_jinE() {
		return cz_jinE;
	}
	public void setCz_jinE(BigDecimal cz_jinE) {
		this.cz_jinE = cz_jinE;
	}
	public ShoppingCard getShoppingCard() {
		return shoppingCard;
	}
	public void setShoppingCard(ShoppingCard shoppingCard) {
		this.shoppingCard = shoppingCard;
	}
	
	

}

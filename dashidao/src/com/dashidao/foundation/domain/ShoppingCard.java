package com.dashidao.foundation.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.dashidao.core.domain.IdEntity;
import com.easyjf.dbo.annotation.OneToOne;

/**
 * 购物卡
 * @author Administrator
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "dashidao_shoppingcard")
public class ShoppingCard extends IdEntity {
	private static final long serialVersionUID = 1L;
	
	private String card_name;//购物卡名称
	private String type;//类型
	private Date endtime;//有效期至
	private String Account_number;//账号
	private BigDecimal menoy;//面值
	private String card_password;//密码
	private String card_photo;//图片
	private String states;//状态
	@OneToOne(column = "user")
	private User user;//用户
	public String getCard_name() {
		return card_name;
	}
	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getAccount_number() {
		return Account_number;
	}
	public void setAccount_number(String account_number) {
		Account_number = account_number;
	}
	public BigDecimal getMenoy() {
		return menoy;
	}
	public void setMenoy(BigDecimal menoy) {
		this.menoy = menoy;
	}
	public String getCard_password() {
		return card_password;
	}
	public void setCard_password(String card_password) {
		this.card_password = card_password;
	}
	public String getCard_photo() {
		return card_photo;
	}
	public void setCard_photo(String card_photo) {
		this.card_photo = card_photo;
	}
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	

}

package com.dashidao.foundation.domain;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.dashidao.core.domain.IdEntity;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "zd_tuikuanshouhou")
public class TuiKuanShouHou  extends IdEntity  {
	
	private static final long serialVersionUID = 1L;
	private String zd_zhandaiid;
	private String zd_shangjiaid;
	private String zd_dingdanbianhao;
	private Date create_datezd_riqi;
	private String zd_shangjia;
	private String zd_shopname;
	private String zd_chima;
	private String zd_color;
	private Double zd_shoujia;
	private Integer zd_num;
	private String zd_danwei;
	private Double zd_danjia;
	private String zd_shouhouleixing;
	private String zd_shenqingliyou;
	private String zd_liaotianjilu;
	private Date create_date;
	private Integer states;
	private String zd_huiyuan;
	private String zd_shouhoushuoming;
	@OneToOne
	private Goods goods;
	
	@OneToOne
	private OrderForm orderForm;
	
	@OneToOne
	private Accessory access;
	
	public Accessory getAccess() {
		return access;
	}
	public void setAccess(Accessory access) {
		this.access = access;
	}
	public OrderForm getOrderForm() {
		return orderForm;
	}
	public void setOrderForm(OrderForm orderForm) {
		this.orderForm = orderForm;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public String getZd_shouhoushuoming() {
		return zd_shouhoushuoming;
	}
	public void setZd_shouhoushuoming(String zd_shouhoushuoming) {
		this.zd_shouhoushuoming = zd_shouhoushuoming;
	}
	public String getZd_zhandaiid() {
		return zd_zhandaiid;
	}
	public void setZd_zhandaiid(String zd_zhandaiid) {
		this.zd_zhandaiid = zd_zhandaiid;
	}
	public String getZd_shangjiaid() {
		return zd_shangjiaid;
	}
	public void setZd_shangjiaid(String zd_shangjiaid) {
		this.zd_shangjiaid = zd_shangjiaid;
	}
	public String getZd_dingdanbianhao() {
		return zd_dingdanbianhao;
	}
	public void setZd_dingdanbianhao(String zd_dingdanbianhao) {
		this.zd_dingdanbianhao = zd_dingdanbianhao;
	}
	public Date getCreate_datezd_riqi() {
		return create_datezd_riqi;
	}
	public void setCreate_datezd_riqi(Date create_datezd_riqi) {
		this.create_datezd_riqi = create_datezd_riqi;
	}
	public String getZd_shangjia() {
		return zd_shangjia;
	}
	public void setZd_shangjia(String zd_shangjia) {
		this.zd_shangjia = zd_shangjia;
	}
	public String getZd_shopname() {
		return zd_shopname;
	}
	public void setZd_shopname(String zd_shopname) {
		this.zd_shopname = zd_shopname;
	}
	public String getZd_chima() {
		return zd_chima;
	}
	public void setZd_chima(String zd_chima) {
		this.zd_chima = zd_chima;
	}
	public String getZd_color() {
		return zd_color;
	}
	public void setZd_color(String zd_color) {
		this.zd_color = zd_color;
	}
	public Double getZd_shoujia() {
		return zd_shoujia;
	}
	public void setZd_shoujia(Double zd_shoujia) {
		this.zd_shoujia = zd_shoujia;
	}
	public Integer getZd_num() {
		return zd_num;
	}
	public void setZd_num(Integer zd_num) {
		this.zd_num = zd_num;
	}
	public String getZd_danwei() {
		return zd_danwei;
	}
	public void setZd_danwei(String zd_danwei) {
		this.zd_danwei = zd_danwei;
	}
	public Double getZd_danjia() {
		return zd_danjia;
	}
	public void setZd_danjia(Double zd_danjia) {
		this.zd_danjia = zd_danjia;
	}
	public String getZd_shouhouleixing() {
		return zd_shouhouleixing;
	}
	public void setZd_shouhouleixing(String zd_shouhouleixing) {
		this.zd_shouhouleixing = zd_shouhouleixing;
	}
	public String getZd_shenqingliyou() {
		return zd_shenqingliyou;
	}
	public void setZd_shenqingliyou(String zd_shenqingliyou) {
		this.zd_shenqingliyou = zd_shenqingliyou;
	}
	public String getZd_liaotianjilu() {
		return zd_liaotianjilu;
	}
	public void setZd_liaotianjilu(String zd_liaotianjilu) {
		this.zd_liaotianjilu = zd_liaotianjilu;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Integer getStates() {
		return states;
	}
	public void setStates(Integer states) {
		this.states = states;
	}
	public String getZd_huiyuan() {
		return zd_huiyuan;
	}
	public void setZd_huiyuan(String zd_huiyuan) {
		this.zd_huiyuan = zd_huiyuan;
	}
	
}

package com.dashidao.foundation.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.dashidao.core.domain.IdEntity;
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "dashidao_ordercom")
/**
 * 订单评论
 * @author lsp
 *
 */
public class OrderCom  extends IdEntity{

	/**
	 * 所属订单
	 */
	@ManyToOne
	private  OrderForm orderForm;
	/**
	 * 评论用户
	 */
	@ManyToOne
	private  User user;
	/**
	 * 所属商品
	 */
	@ManyToOne
	private  Goods goods;
	/**
	 * 所属店铺
	 */
	@ManyToOne
	private  Store store;
	/**
	 * 评论内容
	 */
	private  String content;
	/**
	 * 父级评论
	 */
	@ManyToOne
	private  OrderCom parent;
	/**
	 * 子级评论
	 */
	@OneToMany(mappedBy = "parent")
	private  List<OrderCom> lschild;
	
	private String wuliupinglun;//物流
	private String shangpinglun;//商品
	private String tiyanpinglun;//体验
	private String fuwupinglun;//服务
	private String zhaopian;//照片
	
	private String fenshu;//分数
	private String taidu;//态度
	private int status;//
	
	 //图片附件
    @OneToMany(mappedBy = "orderCom")
	private List<OrderComPhoto> ordercomPhoto = new ArrayList();
    
	
	public List<OrderComPhoto> getOrdercomPhoto() {
		return ordercomPhoto;
	}
	public void setOrdercomPhoto(List<OrderComPhoto> ordercomPhoto) {
		this.ordercomPhoto = ordercomPhoto;
	}
	public String getFenshu() {
		return fenshu;
	}
	public void setFenshu(String fenshu) {
		this.fenshu = fenshu;
	}
	public String getTaidu() {
		return taidu;
	}
	public void setTaidu(String taidu) {
		this.taidu = taidu;
	}
	public String getWuliupinglun() {
		return wuliupinglun;
	}
	public void setWuliupinglun(String wuliupinglun) {
		this.wuliupinglun = wuliupinglun;
	}
	public String getShangpinglun() {
		return shangpinglun;
	}
	public void setShangpinglun(String shangpinglun) {
		this.shangpinglun = shangpinglun;
	}
	public String getTiyanpinglun() {
		return tiyanpinglun;
	}
	public void setTiyanpinglun(String tiyanpinglun) {
		this.tiyanpinglun = tiyanpinglun;
	}
	public String getFuwupinglun() {
		return fuwupinglun;
	}
	public void setFuwupinglun(String fuwupinglun) {
		this.fuwupinglun = fuwupinglun;
	}
	public String getZhaopian() {
		return zhaopian;
	}
	public void setZhaopian(String zhaopian) {
		this.zhaopian = zhaopian;
	}
	/**
	 * 状态0默认1删除
	 */
	private int state;
	public OrderForm getOrderForm() {
		return orderForm;
	}
	public void setOrderForm(OrderForm orderForm) {
		this.orderForm = orderForm;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public OrderCom getParent() {
		return parent;
	}
	public void setParent(OrderCom parent) {
		this.parent = parent;
	}
	public List<OrderCom> getLschild() {
		return lschild;
	}
	public void setLschild(List<OrderCom> lschild) {
		this.lschild = lschild;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}

package com.dashidao.foundation.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.dashidao.core.domain.IdEntity;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "dashidao_storegoods")
/**
 * 店铺商品
 * @author lsp
 *
 */
public class StoreGoods extends IdEntity {

    //店铺
    @ManyToOne
    private Store store; 
   
    //用户
    @ManyToOne
    private User user;
    //用户
    @ManyToOne
    private Goods goods;
    /**
     * 1为云客代言,2活动精选，3为精品推荐
     */
    private int state;
    /**
     * 0店长推荐1店长代言
     */
    private int type; 
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	 
    
 
}





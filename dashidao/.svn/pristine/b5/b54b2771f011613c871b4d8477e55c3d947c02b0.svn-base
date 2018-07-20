package com.dashidao.foundation.domain;

import com.dashidao.core.domain.IdEntity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "dashidao_stackgoods")
public class StackGoods extends IdEntity {
    /**
     * 棧代商品库
     */
    private static final long serialVersionUID = -4611982955098188929L;
    
    //商品型号编码
    private String goods_code;
    //名称
    private String goods_name;
    //商品价格
    @Column(precision = 12, scale = 2)
    private BigDecimal goods_price;
    //商品佣金
    @Column(precision = 12, scale = 2)
    private BigDecimal goods_commission;
     //库存数量
    private int goods_nums;
    //棧代编号
    private Long stack_id;
      //商家编号 
    private Long trader_id;
    //商品状态
     private int status;
	public String getGoods_code() {
		return goods_code;
	}
	public void setGoods_code(String goods_code) {
		this.goods_code = goods_code;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public BigDecimal getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(BigDecimal goods_price) {
		this.goods_price = goods_price;
	}
	public BigDecimal getGoods_commission() {
		return goods_commission;
	}
	public void setGoods_commission(BigDecimal goods_commission) {
		this.goods_commission = goods_commission;
	}
	public int getGoods_nums() {
		return goods_nums;
	}
	public void setGoods_nums(int goods_nums) {
		this.goods_nums = goods_nums;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Long getStack_id() {
		return stack_id;
	}
	public void setStack_id(Long stack_id) {
		this.stack_id = stack_id;
	}
	public Long getTrader_id() {
		return trader_id;
	}
	public void setTrader_id(Long trader_id) {
		this.trader_id = trader_id;
	}
     
    

}

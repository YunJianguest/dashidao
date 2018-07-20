package com.dashidao.foundation.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.dashidao.core.domain.IdEntity;

/**
 * @author Administrator
 *  代理商家保证金设置
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "zb_dashidaoSetting")
public class Setting extends IdEntity  {
	
	private static final long serialVersionUID = 8026813053768023527L;
	
	private int zb_one_int_attr;
	
	private BigDecimal zb_one_bigDecimal_attr;
	private BigDecimal zb_two_bigDecimal_attr;
	private BigDecimal zb_three_bigDecimal_attr;
	private BigDecimal zb_four_bigDecimal_attr;
	
	private String zb_remark;
	
	/**
	 * 1-云客代金券规则  2-云客推荐商家佣金 3-云客推荐栈代佣金  4-佣金扣税税率  5-提现规则
	 * 6-商家保障金设置  7-栈代保证金设置
	 */
	private int zb_type;
	
	private Date zb_startdate;
	private Date zb_enddate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private GoodsBrand goodsBrand;

	public int getZb_one_int_attr() {
		return zb_one_int_attr;
	}

	public void setZb_one_int_attr(int zb_one_int_attr) {
		this.zb_one_int_attr = zb_one_int_attr;
	}

	public BigDecimal getZb_one_bigDecimal_attr() {
		return zb_one_bigDecimal_attr;
	}

	public void setZb_one_bigDecimal_attr(BigDecimal zb_one_bigDecimal_attr) {
		this.zb_one_bigDecimal_attr = zb_one_bigDecimal_attr;
	}

	public BigDecimal getZb_two_bigDecimal_attr() {
		return zb_two_bigDecimal_attr;
	}

	public void setZb_two_bigDecimal_attr(BigDecimal zb_two_bigDecimal_attr) {
		this.zb_two_bigDecimal_attr = zb_two_bigDecimal_attr;
	}

	public BigDecimal getZb_three_bigDecimal_attr() {
		return zb_three_bigDecimal_attr;
	}

	public void setZb_three_bigDecimal_attr(BigDecimal zb_three_bigDecimal_attr) {
		this.zb_three_bigDecimal_attr = zb_three_bigDecimal_attr;
	}

	public BigDecimal getZb_four_bigDecimal_attr() {
		return zb_four_bigDecimal_attr;
	}

	public void setZb_four_bigDecimal_attr(BigDecimal zb_four_bigDecimal_attr) {
		this.zb_four_bigDecimal_attr = zb_four_bigDecimal_attr;
	}

	public int getZb_type() {
		return zb_type;
	}

	public void setZb_type(int zb_type) {
		this.zb_type = zb_type;
	}

	public GoodsBrand getGoodsBrand() {
		return goodsBrand;
	}

	public void setGoodsBrand(GoodsBrand goodsBrand) {
		this.goodsBrand = goodsBrand;
	}

	public String getZb_remark() {
		return zb_remark;
	}

	public void setZb_remark(String zb_remark) {
		this.zb_remark = zb_remark;
	}

	public Date getZb_startdate() {
		return zb_startdate;
	}

	public void setZb_startdate(Date zb_startdate) {
		this.zb_startdate = zb_startdate;
	}

	public Date getZb_enddate() {
		return zb_enddate;
	}

	public void setZb_enddate(Date zb_enddate) {
		this.zb_enddate = zb_enddate;
	}
	
	
}
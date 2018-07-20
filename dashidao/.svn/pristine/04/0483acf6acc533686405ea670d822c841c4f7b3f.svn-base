package com.dashidao.foundation.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.dashidao.core.domain.IdEntity;

/**
 * 云客推荐商家佣金  推荐栈代佣金
 * @author Administrator
 *
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "zb_yunketuijianshangjianYj")
public class YunKeTuiJianShangJianYj extends IdEntity  {
	private static final long serialVersionUID = 8026813053768023527L;
	private String zb_nianXian;//年
	private BigDecimal zb_yongJinLv;//佣金率
	private String zb_type;// 1 云客推荐商家佣金 2云客推荐栈代佣金
	public String getZb_nianXian() {
		return zb_nianXian;
	}
	public void setZb_nianXian(String zb_nianXian) {
		this.zb_nianXian = zb_nianXian;
	}
	public BigDecimal getZb_yongJinLv() {
		return zb_yongJinLv;
	}
	public void setZb_yongJinLv(BigDecimal zb_yongJinLv) {
		this.zb_yongJinLv = zb_yongJinLv;
	}
	public String getZb_type() {
		return zb_type;
	}
	public void setZb_type(String zb_type) {
		this.zb_type = zb_type;
	}
	
	
	
	
	

}

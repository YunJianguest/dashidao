package com.dashidao.foundation.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.dashidao.core.domain.IdEntity;

/**
 * 提现规则
 * @author Administrator
 *
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "zb_tiXianGuiZe")
public class TiXianGuiZe extends IdEntity  {

	private static final long serialVersionUID = 8026813053768023527L;
	private BigDecimal zb_tiXianJine;//体现金额
	private BigDecimal zb_shouXuFei;//手续费
	public BigDecimal getZb_tiXianJine() {
		return zb_tiXianJine;
	}
	public void setZb_tiXianJine(BigDecimal zb_tiXianJine) {
		this.zb_tiXianJine = zb_tiXianJine;
	}
	public BigDecimal getZb_shouXuFei() {
		return zb_shouXuFei;
	}
	public void setZb_shouXuFei(BigDecimal zb_shouXuFei) {
		this.zb_shouXuFei = zb_shouXuFei;
	}
	
	
}

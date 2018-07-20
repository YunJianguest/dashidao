package com.dashidao.foundation.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.dashidao.core.domain.IdEntity;

/**
 * @author Administrator
 *云客代金券规则
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "zb_yunkedaijinquanguize")
public class YunKeDaiJinQuanGuiZe extends IdEntity  {
	
	private static final long serialVersionUID = 8026813053768023527L;
	private BigDecimal zb_JinE;//金额
	private BigDecimal zb_dmJinE;//单笔金额
	public BigDecimal getZb_JinE() {
		return zb_JinE;
	}
	public void setZb_JinE(BigDecimal zb_JinE) {
		this.zb_JinE = zb_JinE;
	}
	public BigDecimal getZb_dmJinE() {
		return zb_dmJinE;
	}
	public void setZb_dmJinE(BigDecimal zb_dmJinE) {
		this.zb_dmJinE = zb_dmJinE;
	}
	
	
	
	
	

}

package com.dashidao.foundation.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.dashidao.core.domain.IdEntity;

/**
 * @author Administrator
 *
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "dashidao_wuliumuban")
public class WuLiuMuBan extends IdEntity  {
	private static final long serialVersionUID = 1L;
	
	@Column(precision = 12, scale = 2)
	private BigDecimal szzl;//首重重量
	@Column(precision = 12, scale = 2)
	private BigDecimal szfy;//首重费用
	@Column(precision = 12, scale = 2)
	private BigDecimal xzjjzl;//续重计价重量
	@Column(precision = 12, scale = 2)
	private BigDecimal xzdj;//续重单价
	
	private Integer status;
	
//	private Date addTime;
	
	

	/*public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}*/

	public BigDecimal getSzzl() {
		return szzl;
	}

	public void setSzzl(BigDecimal szzl) {
		this.szzl = szzl;
	}

	public BigDecimal getSzfy() {
		return szfy;
	}

	public void setSzfy(BigDecimal szfy) {
		this.szfy = szfy;
	}

	public BigDecimal getXzjjzl() {
		return xzjjzl;
	}

	public void setXzjjzl(BigDecimal xzjjzl) {
		this.xzjjzl = xzjjzl;
	}

	public BigDecimal getXzdj() {
		return xzdj;
	}

	public void setXzdj(BigDecimal xzdj) {
		this.xzdj = xzdj;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	

}

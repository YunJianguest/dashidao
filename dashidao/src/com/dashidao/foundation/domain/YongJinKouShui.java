package com.dashidao.foundation.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.dashidao.core.domain.IdEntity;

/**
 * 佣金扣税税率
 * @author Administrator
 *
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "zb_yongJinKouShui")
public class YongJinKouShui  extends IdEntity {
	private static final long serialVersionUID = 8026813053768023527L;

	private BigDecimal zb_yongJinStart;//佣金
	private BigDecimal zb_yongJinEnd;//佣金
	private BigDecimal zb_shuolv;//说率
	public BigDecimal getZb_yongJinStart() {
		return zb_yongJinStart;
	}
	public void setZb_yongJinStart(BigDecimal zb_yongJinStart) {
		this.zb_yongJinStart = zb_yongJinStart;
	}
	public BigDecimal getZb_yongJinEnd() {
		return zb_yongJinEnd;
	}
	public void setZb_yongJinEnd(BigDecimal zb_yongJinEnd) {
		this.zb_yongJinEnd = zb_yongJinEnd;
	}
	public BigDecimal getZb_shuolv() {
		return zb_shuolv;
	}
	public void setZb_shuolv(BigDecimal zb_shuolv) {
		this.zb_shuolv = zb_shuolv;
	}
	
	
		
}

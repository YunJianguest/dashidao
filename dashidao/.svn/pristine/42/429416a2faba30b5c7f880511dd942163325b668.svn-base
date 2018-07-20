package com.dashidao.foundation.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
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
@Table(name = "zb_zhanDaiBaoZhengJinSheZhi")
public class ZhanDaiBaoZhengJinSheZhi extends IdEntity  {
	
	private static final long serialVersionUID = 8026813053768023527L;
	private int zb_shangJianNum;//商家数量
	private BigDecimal zb_baoZhengJin;//栈代保证金
	public int getZb_shangJianNum() {
		return zb_shangJianNum;
	}
	public void setZb_shangJianNum(int zb_shangJianNum) {
		this.zb_shangJianNum = zb_shangJianNum;
	}
	public BigDecimal getZb_baoZhengJin() {
		return zb_baoZhengJin;
	}
	public void setZb_baoZhengJin(BigDecimal zb_baoZhengJin) {
		this.zb_baoZhengJin = zb_baoZhengJin;
	}
	
	
}

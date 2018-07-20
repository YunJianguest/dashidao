package com.dashidao.foundation.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.dashidao.core.domain.IdEntity;

/**云客审核
 * @author lsp
 *
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "dashidao_yunkeAudit")
public class YunkeAudit extends IdEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 申请人
	 */
	@OneToOne
	private User apply;
	/**
	 * 审核人
	 */
	@OneToOne
	private User audit;
	/**
	 * 申请时间
	 */
	private Date createDate;
	/**
	 * 审核时间
	 */
	private Date auditDate;
	/**
	 * 状态0未审核1审核成功2审核失败
	 */
	private int state;
	public User getApply() {
		return apply;
	}
	public void setApply(User apply) {
		this.apply = apply;
	}
	public User getAudit() {
		return audit;
	}
	public void setAudit(User audit) {
		this.audit = audit;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
}

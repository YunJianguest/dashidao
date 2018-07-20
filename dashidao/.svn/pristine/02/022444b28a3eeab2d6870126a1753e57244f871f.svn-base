package com.dashidao.foundation.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.dashidao.core.domain.IdEntity;
/**
 * 佣金
 * @author
 *
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "dashidao_commission")
public class Commission extends IdEntity {
    /**
     * UID
     */
    private static final long serialVersionUID = -3166365941305570434L;
    //佣金编号
    @Column(columnDefinition = "LongText")
    private String commission_code ;

    //佣金名称
    private String commission_name;
    //佣金说明
    @Column(columnDefinition = "LongText")
    private  String commission_notes;
    //佣金使用角色
    
    private String commission_role;
    //佣金值
  
    private double commission_value;
   //佣金状态
    @Column(columnDefinition = "int default 0")
    private int commission_status;
	public String getCommission_code() {
		return commission_code;
	}
	public void setCommission_code(String commission_code) {
		this.commission_code = commission_code;
	}
	public String getCommission_name() {
		return commission_name;
	}
	public void setCommission_name(String commission_name) {
		this.commission_name = commission_name;
	}
	public String getCommission_notes() {
		return commission_notes;
	}
	public void setCommission_notes(String commission_notes) {
		this.commission_notes = commission_notes;
	}
	public String getCommission_role() {
		return commission_role;
	}
	public void setCommission_role(String commission_role) {
		this.commission_role = commission_role;
	}
	public double getCommission_value() {
		return commission_value;
	}
	public void setCommission_value(double commission_value) {
		this.commission_value = commission_value;
	}
	public int getCommission_status() {
		return commission_status;
	}
	public void setCommission_status(int commission_status) {
		this.commission_status = commission_status;
	}
    
    
    
}

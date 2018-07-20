package com.dashidao.foundation.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.dashidao.core.domain.IdEntity;
/**
 *渠道费率
 * @author
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "dashidao_channelrate")
public class ChannelRate extends IdEntity {
    /**
     * UID
     */
    private static final long serialVersionUID = 8707872936870153411L;
    private int value;
    private Date startdate;
    private Date enddate;
    /**
     * 状态0正常1已失效
     */
    private int state;
    /**
     * 所属分类
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private GoodsClass gc;
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	 
	public GoodsClass getGc() {
		return gc;
	}
	public void setGc(GoodsClass gc) {
		this.gc = gc;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}

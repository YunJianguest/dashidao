package com.dashidao.foundation.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.dashidao.core.domain.IdEntity;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "zd_qingcanghuodong")
public class Qingcanghuodong extends IdEntity
{
	private static final long serialVersionUID = 1L;
    private String zd_huodongname;//清仓活动名称
    private String zd_dazhelidu;//打折力度
    private String zd_dazhemenkan;//打折门槛
    private Date zd_huodongday;//活动日期
    private String zd_canjiashangping;//参加商品
    private String zd_canjiashuliang;//参加商品数量
    private String states;//状态
    private Date zd_huodongdayend;
    private Date zd_time;
    private String zd_zhandaiid;
    private String zd_shangjiaid;
    
    @OneToMany(mappedBy = "qingcanghuodong")
    List<QingCangShangPin> list = new ArrayList<QingCangShangPin>();
    
    
    


    public List<QingCangShangPin> getList() {
		return list;
	}

	public void setList(List<QingCangShangPin> list) {
		this.list = list;
	}

	public Date getZd_time() {
		return zd_time;
	}

	public void setZd_time(Date zd_time) {
		this.zd_time = zd_time;
	}

	public String getZd_zhandaiid() {
		return zd_zhandaiid;
	}

	public void setZd_zhandaiid(String zd_zhandaiid) {
		this.zd_zhandaiid = zd_zhandaiid;
	}

	public String getZd_shangjiaid() {
		return zd_shangjiaid;
	}

	public void setZd_shangjiaid(String zd_shangjiaid) {
		this.zd_shangjiaid = zd_shangjiaid;
	}

	public Date getZd_huodongdayend() {
		return zd_huodongdayend;
	}

	public void setZd_huodongdayend(Date zd_huodongdayend) {
		this.zd_huodongdayend = zd_huodongdayend;
	}

	public String getZd_huodongname() {
        return zd_huodongname;
    }

    public String getZd_dazhelidu() {
        return zd_dazhelidu;
    }

    public String getZd_dazhemenkan() {
        return zd_dazhemenkan;
    }

    public Date getZd_huodongday() {
        return zd_huodongday;
    }

    public String getZd_canjiashangping() {
        return zd_canjiashangping;
    }

    public String getZd_canjiashuliang() {
        return zd_canjiashuliang;
    }

    public String getStates() {
        return states;
    }

    public void setZd_huodongname(String zd_huodongname) {
        this.zd_huodongname = zd_huodongname;
    }

    public void setZd_dazhelidu(String zd_dazhelidu) {
        this.zd_dazhelidu = zd_dazhelidu;
    }

    public void setZd_dazhemenkan(String zd_dazhemenkan) {
        this.zd_dazhemenkan = zd_dazhemenkan;
    }

    public void setZd_huodongday(Date zd_huodongday) {
        this.zd_huodongday = zd_huodongday;
    }

    public void setZd_canjiashangping(String zd_canjiashangping) {
        this.zd_canjiashangping = zd_canjiashangping;
    }

    public void setZd_canjiashuliang(String zd_canjiashuliang) {
        this.zd_canjiashuliang = zd_canjiashuliang;
    }

    public void setStates(String states) {
        this.states = states;
    }
}

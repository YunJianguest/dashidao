package com.dashidao.foundation.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.dashidao.core.domain.IdEntity;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "zd_huodong_goods")
public class QingCangShangPin  extends IdEntity  {
	
	private static final long serialVersionUID = 1L;
    @ManyToOne(fetch = FetchType.LAZY)
	private Qingcanghuodong qingcanghuodong;
	//private Long goos_id;
	//商品
    @OneToOne
    private Goods goods;
    
    
    
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public Qingcanghuodong getQingcanghuodong() {
		return qingcanghuodong;
	}
	public void setQingcanghuodong(Qingcanghuodong qingcanghuodong) {
		this.qingcanghuodong = qingcanghuodong;
	}
	
	
	

}

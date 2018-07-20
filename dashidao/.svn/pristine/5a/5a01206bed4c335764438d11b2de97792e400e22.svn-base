package com.dashidao.foundation.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.dashidao.core.domain.IdEntity;

/**
 * 会员顶部图片
 * @author Administrator
 *
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "dashidao_top_photo")
public class TopPhoto  extends IdEntity {
	   /**
     * UID
     */
    private static final long serialVersionUID = -4650388260362359406L;
    
    private String title;//标题
    private String top_url;//链接地址
    @OneToOne
    private Accessory acc;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTop_url() {
		return top_url;
	}
	public void setTop_url(String top_url) {
		this.top_url = top_url;
	}
	public Accessory getAcc() {
		return acc;
	}
	public void setAcc(Accessory acc) {
		this.acc = acc;
	}
    
    

}

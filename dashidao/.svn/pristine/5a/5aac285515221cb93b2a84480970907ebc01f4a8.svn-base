package com.dashidao.foundation.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.dashidao.core.domain.IdEntity;

/**
 * 云客客服中心轮播图 实体
 * @author Administrator
 *
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "dashidao_kefu_photo")
public class KeFuPhoto extends IdEntity {
	
	  /**
     * UID
     */
    private static final long serialVersionUID = -4650388260362359406L;
    
    private String title;//标题
    private String photourl;//链接地址
    @OneToOne
    private Accessory acc;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPhotourl() {
		return photourl;
	}
	public void setPhotourl(String photourl) {
		this.photourl = photourl;
	}
	public Accessory getAcc() {
		return acc;
	}
	public void setAcc(Accessory acc) {
		this.acc = acc;
	}
    
    
    
    

}

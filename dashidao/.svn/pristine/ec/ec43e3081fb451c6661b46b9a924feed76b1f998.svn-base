package com.dashidao.foundation.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.dashidao.core.domain.IdEntity;

/**
 * 素材
 * @author Administrator
 *
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "dashidao_sucai")
public class SuCai extends IdEntity  {
	 private static final long serialVersionUID = 8707872936870153411L;
	 
	 
	 //文字内容
	 @Column(columnDefinition = "LongText")
	 private String content;
	 @ManyToOne
	 private User user;
	 private String type;//类型 ： 1精选  2收藏  3精选
	 //图片附件
    @OneToMany(mappedBy = "sucai")
	private List<SuCaiPhoto> photo = new ArrayList();
	    
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<SuCaiPhoto> getPhoto() {
		return photo;
	}
	public void setPhoto(List<SuCaiPhoto> photo) {
		this.photo = photo;
	}
	
	
	
	 

}

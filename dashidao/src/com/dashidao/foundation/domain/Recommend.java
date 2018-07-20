package com.dashidao.foundation.domain;

import java.util.ArrayList;
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
 * 推荐服务
 * @author
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "dashidao_user_recommend")
public class Recommend extends IdEntity {
    /**
     * UID
     */
    private static final long serialVersionUID = 8707872936870153411L;

    //所属人
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    
    //推荐状态 0-未审核 1-审核
    private String recommend_state;
    //推荐类型 YUNKE：云客
    private String recommend_type;
    
    //
    private String recommend_name;//推荐名称
    
    @OneToOne
    private Accessory acc;
    
    
    public Accessory getAcc() {
		return acc;
	}

	public void setAcc(Accessory acc) {
		this.acc = acc;
	}

	public String getRecommend_name() {
		return recommend_name;
	}

	public void setRecommend_name(String recommend_name) {
		this.recommend_name = recommend_name;
	}

	public User getUser(){
        return this.user;
    }

    public void setUser(User user){
        this.user = user;
    }

	public String getRecommend_state() {
		return recommend_state;
	}

	public void setRecommend_state(String recommend_state) {
		this.recommend_state = recommend_state;
	}

	public String getRecommend_type() {
		return recommend_type;
	}

	public void setRecommend_type(String recommend_type) {
		this.recommend_type = recommend_type;
	}
    
}

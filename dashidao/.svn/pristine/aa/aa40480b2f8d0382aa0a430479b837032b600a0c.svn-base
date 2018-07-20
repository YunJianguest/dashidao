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
 * 代理商家
 * @author
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "dashidao_agent_store")
public class AgentStore extends IdEntity {
    /**
     * UID
     */
    private static final long serialVersionUID = 8707872936870153411L;
    @ManyToOne
    private User user;
    
    @ManyToOne
    private Store store;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	} 
	
    
}

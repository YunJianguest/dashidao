package com.dashidao.foundation.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.dashidao.core.domain.IdEntity;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "dashidao_sucaiphoto")
public class SuCaiPhoto  extends IdEntity {
	
	 private static final long serialVersionUID = 8707872936870153411L;
	 	//附件
	 	@ManyToOne
	    private Accessory acc;
	    //素材
	    @ManyToOne
	    private SuCai sucai;
		public Accessory getAcc() {
			return acc;
		}
		public void setAcc(Accessory acc) {
			this.acc = acc;
		}
		public SuCai getSucai() {
			return sucai;
		}
		public void setSucai(SuCai sucai) {
			this.sucai = sucai;
		}
	    
	    
	    
	    
	

}

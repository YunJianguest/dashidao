package com.dashidao.foundation.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.dashidao.core.domain.IdEntity;

/**
 * 实名认证
 * 
 * @author
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "dashidao_certification")
public class Certification extends IdEntity {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 8707872936870153411L;

	// 真实姓名
	private String name;

	// 身份证号码
	private String id_number;

	// 认证类型
	private int certification_type;

	// 认证状态
	private int certification_status;

	// 公司名称
	private String company_name;

	// 营业证号码
	private String business_license;

	// 身份证正面照
	private String id_card_front;

	// 身份证反面照
	private String id_card_reverse;

	// 营业执照照片
	private String business_phone;

	// 用户id
	private int user_id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId_number() {
		return id_number;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}

	public int getCertification_type() {
		return certification_type;
	}

	public void setCertification_type(int certification_type) {
		this.certification_type = certification_type;
	}

	public int getCertification_status() {
		return certification_status;
	}

	public void setCertification_status(int certification_status) {
		this.certification_status = certification_status;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getBusiness_license() {
		return business_license;
	}

	public void setBusiness_license(String business_license) {
		this.business_license = business_license;
	}

	public String getId_card_front() {
		return id_card_front;
	}

	public void setId_card_front(String id_card_front) {
		this.id_card_front = id_card_front;
	}

	public String getId_card_reverse() {
		return id_card_reverse;
	}

	public void setId_card_reverse(String id_card_reverse) {
		this.id_card_reverse = id_card_reverse;
	}

	public String getBusiness_phone() {
		return business_phone;
	}

	public void setBusiness_phone(String business_phone) {
		this.business_phone = business_phone;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}

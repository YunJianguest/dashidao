package com.dashidao.foundation.domain;

import com.dashidao.core.domain.IdEntity;
/**
 * 位置
 * @author lsp
 *
 */
public class Location extends IdEntity {

	/**
	 * 经度
	 */
	private  Double longitude;
	/**
	 * 纬度
	 */
	private  Double latitude;
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	
}
 
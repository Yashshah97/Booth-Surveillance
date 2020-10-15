package com.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity

@Table(name="booth")

public class BoothVO {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="boothId")
	private int boothId;
	
	@Column(name="boothNo")
	private String boothNo;
	
	@Column(name="requiredPerson")
	private String requiredPerson;
	
	@Column(name="boothAddress")
	private String boothAddress;
	
	
	@ManyToOne CityVO cityVO;
	
	@ManyToOne AreaVO areaVO;
	
	
	
	@Column
	private boolean status=true;
	
	
	
	

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	

	

	public int getBoothId() {
		return boothId;
	}

	public void setBoothId(int boothId) {
		this.boothId = boothId;
	}

	public String getBoothNo() {
		return boothNo;
	}

	public void setBoothNo(String boothNo) {
		this.boothNo = boothNo;
	}

	public String getRequiredPerson() {
		return requiredPerson;
	}

	public void setRequiredPerson(String requiredPerson) {
		this.requiredPerson = requiredPerson;
	}

	public String getBoothAddress() {
		return boothAddress;
	}

	public void setBoothAddress(String boothAddress) {
		this.boothAddress = boothAddress;
	}

	public CityVO getCityVO() {
		return cityVO;
	}

	public void setCityVO(CityVO cityVO) {
		this.cityVO = cityVO;
	}

	public AreaVO getAreaVO() {
		return areaVO;
	}

	public void setAreaVO(AreaVO areaVO) {
		this.areaVO = areaVO;
	}
	

}

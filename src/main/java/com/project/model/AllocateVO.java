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

@Table(name="allocate")
public class AllocateVO {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="allocateId")
	private int allocateId;
	
	@Column(name="startingDate")
	private String startingDate;
	
	@Column(name="endingDate")
	private String endingDate;
	
	
	@ManyToOne BoothVO boothVO;
	
	@ManyToOne StaffVO staffVO;
	
	
	@Column
	private boolean status=true;
	
	
	
	

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}


	public int getAllocateId() {
		return allocateId;
	}


	public void setAllocateId(int allocateId) {
		this.allocateId = allocateId;
	}


	public String getStartingDate() {
		return startingDate;
	}


	public void setStartingDate(String startingDate) {
		this.startingDate = startingDate;
	}


	public String getEndingDate() {
		return endingDate;
	}


	public void setEndingDate(String endingDate) {
		this.endingDate = endingDate;
	}


	public BoothVO getBoothVO() {
		return boothVO;
	}


	public void setBoothVO(BoothVO boothVO) {
		this.boothVO = boothVO;
	}


	public StaffVO getStaffVO() {
		return staffVO;
	}


	public void setStaffVO(StaffVO staffVO) {
		this.staffVO = staffVO;
	}
	
	

	

	
}

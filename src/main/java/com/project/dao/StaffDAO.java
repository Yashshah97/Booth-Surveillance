package com.project.dao;

import java.util.List;

import com.project.model.AreaVO;
import com.project.model.BoothVO;
import com.project.model.CityVO;
import com.project.model.StaffVO;


public interface StaffDAO {
	
	public void insertStaff(StaffVO StaffVO) ;
	public List searchStaff(StaffVO StaffVO);
	public void deleteStaff(StaffVO StaffVO);
	public List getStaff(StaffVO StaffVO);
	public void updateStaff(StaffVO StaffVO);

}

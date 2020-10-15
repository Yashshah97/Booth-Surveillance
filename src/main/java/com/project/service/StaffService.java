package com.project.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.AreaDAO;
import com.project.dao.BoothDAO;
import com.project.dao.CityDAO;
import com.project.dao.StaffDAO;
import com.project.model.AreaVO;
import com.project.model.BoothVO;
import com.project.model.CityVO;
import com.project.model.StaffVO;

@Service
public class StaffService {

	@Autowired StaffDAO staffDAO;
	@Transactional
	public void insertStaff(StaffVO StaffVO)
	{
		this.staffDAO.insertStaff(StaffVO);
	}
	@Transactional
	public List searchStaff(StaffVO StaffVO)
	{
		
		return this.staffDAO.searchStaff(StaffVO);
		
		
	}
	@Transactional
	public void deleteStaff(StaffVO StaffVO)
	{
		staffDAO.deleteStaff(StaffVO);
	}
	@Transactional
	public List getStaff(StaffVO StaffVO)
	{
		List ls=staffDAO.getStaff(StaffVO);
		return ls;
	}
	@Transactional
	public void updateStaff(StaffVO StaffVO)
	{
		staffDAO.updateStaff(StaffVO);
	}
	
	
}

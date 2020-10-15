package com.project.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.AllocateDAO;
import com.project.dao.AreaDAO;
import com.project.dao.CityDAO;
import com.project.model.AllocateVO;
import com.project.model.AreaVO;
import com.project.model.CityVO;

@Service
public class AllocateService {

	@Autowired AllocateDAO allocateDAO;
	
	@Transactional
	public void insertAllocate(AllocateVO allocateVO)
	{
		this.allocateDAO.insertAllocate(allocateVO);
	}
	@Transactional
	public List searchAllocate(AllocateVO allocateVO)
	{
		
		return this.allocateDAO.searchAllocate(allocateVO);
		
		
	}
	@Transactional
	public void deleteAllocate(AllocateVO allocateVO)
	{
		allocateDAO.deleteAllocate(allocateVO);
	}
	@Transactional
	public List getAllocate(AllocateVO allocateVO)
	{
		List ls=allocateDAO.getAllocate(allocateVO);
		return ls;
	}
	@Transactional
	public void updateAllocate(AllocateVO allocateVO)
	{
		allocateDAO.updateAllocate(allocateVO);
	}
	
	
}

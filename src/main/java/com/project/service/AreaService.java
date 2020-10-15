package com.project.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.AreaDAO;
import com.project.dao.CityDAO;
import com.project.model.AreaVO;
import com.project.model.CityVO;

@Service
public class AreaService {

	@Autowired AreaDAO areaDAO;
	
	@Transactional
	public void insertArea(AreaVO areaVO)
	{
		this.areaDAO.insertArea(areaVO);
	}
	@Transactional
	public List searchArea(AreaVO areaVO)
	{
		
		return this.areaDAO.searchArea(areaVO);
		
		
	}
	@Transactional
	public void deleteArea(AreaVO areaVO)
	{
		areaDAO.deleteArea(areaVO);
	}
	@Transactional
	public List getArea(AreaVO areaVO)
	{
		List ls=areaDAO.getArea(areaVO);
		return ls;
	}
	@Transactional
	public void updateArea(AreaVO areaVO)
	{
		areaDAO.updateArea(areaVO);
	}
	@Transactional
	public List getAreabycity(AreaVO areaVO)
	{
		List ls=areaDAO.getAreabycity(areaVO);
		return ls;
	
	}
	
}

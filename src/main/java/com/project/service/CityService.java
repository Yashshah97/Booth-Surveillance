package com.project.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.CityDAO;
import com.project.model.CityVO;

@Service
public class CityService {

	@Autowired CityDAO cityDAO;
	
	
	@Transactional
	public void insertCity(CityVO cityVO)
	{
		this.cityDAO.insertCity(cityVO);
	}
	
	@Transactional
	public List searchCity(CityVO cityVO)
	{
		
		return this.cityDAO.searchCity(cityVO);
		
		
	}
	@Transactional
	public void deleteCity(CityVO areaVO)
	{
		cityDAO.deleteCity(areaVO);
	}
	@Transactional
	public List getCity(CityVO areaVO)
	{
		List ls=cityDAO.getCity(areaVO);
		return ls;
	}
	@Transactional
	public void updateCity(CityVO areaVO)
	{
		cityDAO.updateCity(areaVO);
	}
	
}

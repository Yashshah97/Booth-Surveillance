package com.project.dao;

import java.util.List;

import com.project.model.CityVO;

public interface CityDAO {

	public void insertCity(CityVO cityVO) ;
	public List searchCity(CityVO AreaView);
	public void deleteCity(CityVO areaVO);
	public List getCity(CityVO AreaView);
	public void updateCity(CityVO areaVO);
	
}

package com.project.dao;

import java.util.List;

import com.project.model.AreaVO;
import com.project.model.BoothVO;
import com.project.model.CityVO;


public interface BoothDAO {
	public void insertBooth(BoothVO boothVO) ;
	public List searchBooth(BoothVO boothVO);
	public void deleteBooth(BoothVO boothVO);
	public List getBooth(BoothVO boothVO);
	public void updateBooth(BoothVO boothVO);
	

}

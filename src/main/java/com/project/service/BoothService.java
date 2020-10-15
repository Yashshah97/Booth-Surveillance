package com.project.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.AreaDAO;
import com.project.dao.BoothDAO;
import com.project.dao.CityDAO;
import com.project.model.AreaVO;
import com.project.model.BoothVO;
import com.project.model.CityVO;

@Service
public class BoothService {

	@Autowired BoothDAO boothDAO;
	@Transactional
	public void insertBooth(BoothVO boothVO)
	{
		this.boothDAO.insertBooth(boothVO);
	}
	@Transactional
	public List searchBooth(BoothVO boothVO)
	{
		return this.boothDAO.searchBooth(boothVO);
	}
	@Transactional
	public void deleteBooth(BoothVO boothVO)
	{
		boothDAO.deleteBooth(boothVO);
	}
	@Transactional
	public List getBooth(BoothVO boothVO)
	{
		List ls=boothDAO.getBooth(boothVO);
		return ls;
	}
	@Transactional
	public void updateBooth(BoothVO boothVO)
	{
		boothDAO.updateBooth(boothVO);
	}
	
	
}

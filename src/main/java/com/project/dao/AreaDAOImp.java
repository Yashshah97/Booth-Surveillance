package com.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.model.AreaVO;
import com.project.model.CityVO;

@Repository
public class AreaDAOImp implements AreaDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void insertArea(AreaVO areaVO) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(areaVO);
	}
	public List searchArea(AreaVO areaVO)
	{
		List ls=new ArrayList();
		try
			{
				
			Session session = this.sessionFactory.getCurrentSession();				
			Query q= session.createQuery("from AreaVO where status=true");
			ls=q.list();
				
			}
		catch(Exception ex)
			{
				ex.printStackTrace();
			}
		return ls;
	}
	public void deleteArea(AreaVO areaVO)
	{
		
		try
			{
			Session session = this.sessionFactory.getCurrentSession();		
			session.delete(areaVO);
				
			}
		catch(Exception ex)
			{
				ex.printStackTrace();
			}

	}
	public List getArea(AreaVO areaVO)
	{
		List ls=new ArrayList();
		try
			{
			Session session = this.sessionFactory.getCurrentSession();		
			Query q= session.createQuery("from AreaVO where areaId= '"+areaVO.getAreaId()+"'");
			ls=q.list();
			System.out.println(ls);
				
			}
		catch(Exception ex)
			{
				ex.printStackTrace();
			}
		return ls;
	}
	public List getAreabycity(AreaVO areaVO)
	{
		List ls=new ArrayList();
		try
			{
			Session session = this.sessionFactory.getCurrentSession();		
			Query q= session.createQuery("from AreaVO where cityVO_cityId= '"+areaVO.getCityVO().getCityId()+"'");
			ls=q.list();
			System.out.println(ls);
				
			}
		catch(Exception ex)
			{
				ex.printStackTrace();
			}
		return ls;
	}
	public void updateArea(AreaVO areaVO)
	{
		try
			{
			Session session = this.sessionFactory.getCurrentSession();		
			session.update(areaVO);
			}
		catch(Exception ex)
			{
				ex.printStackTrace();
			} 
	}
	

}

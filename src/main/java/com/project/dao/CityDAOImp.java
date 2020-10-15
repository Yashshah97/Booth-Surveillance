package com.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.model.CityVO;

@Repository
public class CityDAOImp implements CityDAO {
	
	@Autowired
	private SessionFactory sessionFactory;


	public void insertCity(CityVO cityVO) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(cityVO);
		
	}
	public List searchCity(CityVO cityVO)
	{
		List ls=new ArrayList();
		try
			{
				
			Session session = this.sessionFactory.getCurrentSession();				
			Query q= session.createQuery("from CityVO where status=true");
			ls=q.list();
				
			}
		catch(Exception ex)
			{
				ex.printStackTrace();
			}
		return ls;
	}
	public void deleteCity(CityVO areaVO)
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
	public List getCity(CityVO regVO)
	{
		List ls=new ArrayList();
		try
			{
			Session session = this.sessionFactory.getCurrentSession();		
			Query q= session.createQuery("from CityVO where cityId= '"+regVO.getCityId()+"'");
			ls=q.list();
			System.out.println(ls);
				
			}
		catch(Exception ex)
			{
				ex.printStackTrace();
			}
		return ls;
	}
	public void updateCity(CityVO cityVO)
	{
		try
			{
			Session session = this.sessionFactory.getCurrentSession();		
			session.update(cityVO);
			}
		catch(Exception ex)
			{
				ex.printStackTrace();
			} 
	}


}

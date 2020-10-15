package com.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.model.CityVO;
import com.project.model.LoginVO;
import com.project.model.StaffVO;

@Repository
public class LoginDAOImp implements LoginDAO {
	
	@Autowired
	private SessionFactory sessionFactory;


	public void insertLogin(LoginVO loginVO) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(loginVO);
		
	}
	/*public List searchCity(CityVO cityVO)
	{
		List ls=new ArrayList();
		try
			{
				
			Session session = this.sessionFactory.getCurrentSession();				
			Query q= session.createQuery("from CityVO");
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
	public void updateCity(CityVO areaVO)
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
	}*/


	@Override
	public List searchLoginID(LoginVO loginVO) {
		// TODO Auto-generated method stub
	
		Session session = this.sessionFactory.getCurrentSession();		
		Query q= session.createQuery("from LoginVO where username= '"+loginVO.getUsername()+"'");
		List ls=q.list();
		System.out.println(ls);
		
		return ls;
	}


	@Override
	public List searchRegister(StaffVO svo) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();		
		Query q= session.createQuery("from StaffVO where loginVO= '"+svo.getLoginVO().getLoginId()+"'");
		List ls=q.list();
		System.out.println(ls);
		
		return ls;
	}


}

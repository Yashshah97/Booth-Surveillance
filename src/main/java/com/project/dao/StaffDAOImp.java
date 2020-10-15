package com.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.model.AreaVO;
import com.project.model.BoothVO;
import com.project.model.CityVO;
import com.project.model.StaffVO;

@Repository
public class StaffDAOImp implements StaffDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void insertStaff(StaffVO StaffVO)  {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(StaffVO);
	}
	public List searchStaff(StaffVO StaffVO)
	{
		List ls=new ArrayList();
		try
			{
				
			Session session = this.sessionFactory.getCurrentSession();				
			Query q= session.createQuery("from StaffVO where status=true");
			ls=q.list();
				
			}
		catch(Exception ex)
			{
				ex.printStackTrace();
			}
		return ls;
	}
	public void deleteStaff(StaffVO StaffVO)
	{
		
		try
			{
			Session session = this.sessionFactory.getCurrentSession();		
			session.delete(StaffVO);
				
			}
		catch(Exception ex)
			{
				ex.printStackTrace();
			}

	}
	public List getStaff(StaffVO StaffVO)
	{
		List ls=new ArrayList();
		try
			{
			Session session = this.sessionFactory.getCurrentSession();		
			Query q= session.createQuery("from StaffVO where staffId= '"+StaffVO.getStaffId()+"'");
			ls=q.list();
			System.out.println(ls);
				
			}
		catch(Exception ex)
			{
				ex.printStackTrace();
			}
		return ls;
	}
	public void updateStaff(StaffVO StaffVO)
	{
		try
			{
			Session session = this.sessionFactory.getCurrentSession();		
			session.update(StaffVO);
			}
		catch(Exception ex)
			{
				ex.printStackTrace();
			} 
	}
	
	

}

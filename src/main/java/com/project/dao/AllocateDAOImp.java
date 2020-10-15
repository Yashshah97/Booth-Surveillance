package com.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.model.AllocateVO;
import com.project.model.AreaVO;
import com.project.model.CityVO;

@Repository
public class AllocateDAOImp implements AllocateDAO{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void insertAllocate(AllocateVO allocateVO) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(allocateVO);
	}
	public List searchAllocate(AllocateVO allocateVO)
	{
		List ls=new ArrayList();
		try
			{
				
			Session session = this.sessionFactory.getCurrentSession();				
			Query q= session.createQuery("from AllocateVO where status=true");
			ls=q.list();
				
			}
		catch(Exception ex)
			{
				ex.printStackTrace();
			}
		return ls;
	}
	public void deleteAllocate(AllocateVO allocateVO)
	{
		
		try
			{
			Session session = this.sessionFactory.getCurrentSession();		
			session.delete(allocateVO);
				
			}
		catch(Exception ex)
			{
				ex.printStackTrace();
			}

	}
	public List getAllocate(AllocateVO allocateVO)
	{
		List ls=new ArrayList();
		try
			{
			Session session = this.sessionFactory.getCurrentSession();		
			Query q= session.createQuery("from AllocateVO where allocateId= '"+allocateVO.getAllocateId()+"'");
			ls=q.list();
			System.out.println(ls);
				
			}
		catch(Exception ex)
			{
				ex.printStackTrace();
			}
		return ls;
	}
	
	public void updateAllocate(AllocateVO allocateVO)
	{
		try
			{
			Session session = this.sessionFactory.getCurrentSession();		
			session.update(allocateVO);
			}
		catch(Exception ex)
			{
				ex.printStackTrace();
			} 
	}
	

}

package com.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.model.BoothVO;
import com.project.model.CityVO;

@Repository
public class BoothDAOImp implements BoothDAO {
	
	@Autowired
	private SessionFactory sessionFactory;


	public void insertBooth(BoothVO boothVO) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(boothVO);
		
	}
	public List searchBooth(BoothVO boothVO)
	{
		List ls=new ArrayList();
		try
			{
				
			Session session = this.sessionFactory.getCurrentSession();				
			Query q= session.createQuery("from BoothVO where status=true");
			ls=q.list();
				
			}
		catch(Exception ex)
			{
				ex.printStackTrace();
			}
		return ls;
	}
	public void deleteBooth(BoothVO boothVO)
	{
		
		try
			{
			Session session = this.sessionFactory.getCurrentSession();		
			session.delete(boothVO);
				
			}
		catch(Exception ex)
			{
				ex.printStackTrace();
			}

	}
	public List getBooth(BoothVO boothVO)
	{
		List ls=new ArrayList();
		try
			{
			Session session = this.sessionFactory.getCurrentSession();		
			Query q= session.createQuery("from BoothVO where boothId= '"+boothVO.getBoothId()+"'");
			ls=q.list();
			System.out.println(ls);
				
			}
		catch(Exception ex)
			{
				ex.printStackTrace();
			}
		return ls;
	}
	public void updateBooth(BoothVO boothVO)
	{
		try
			{
			Session session = this.sessionFactory.getCurrentSession();		
			session.update(boothVO);
			}
		catch(Exception ex)
			{
				ex.printStackTrace();
			} 
	}


}

package com.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.model.CityVO;
import com.project.model.FeedbackVO;

@Repository
public class FeedbackDAOImp implements FeedbackDAO {
	
	@Autowired
	private SessionFactory sessionFactory;


	public void insertFeedback(FeedbackVO feedbackVO) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(feedbackVO);
		
	}
	public List searchFeedback(FeedbackVO feedbackVO)
	{
		List ls=new ArrayList();
		try
			{
				
			Session session = this.sessionFactory.getCurrentSession();				
			Query q= session.createQuery("from FeedbackVO where status=true");
			ls=q.list();
				
			}
		catch(Exception ex)
			{
				ex.printStackTrace();
			}
		return ls;
	}


	
	
	public void deleteFeedback(FeedbackVO feedbackVO)
	{
		
		try
			{
			Session session = this.sessionFactory.getCurrentSession();		
			session.delete(feedbackVO);
				
			}
		catch(Exception ex)
			{
				ex.printStackTrace();
			}

	}


}

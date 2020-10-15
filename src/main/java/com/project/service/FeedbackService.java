package com.project.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.CityDAO;
import com.project.dao.FeedbackDAO;
import com.project.model.CityVO;
import com.project.model.FeedbackVO;

@Service
public class FeedbackService {

	@Autowired FeedbackDAO feedbackDAO;
	
	
	@Transactional
		public void insertFeedback(FeedbackVO feedbackVO) {
		// TODO Auto-generated method stub
		this.feedbackDAO.insertFeedback(feedbackVO);
		
	}

	@Transactional
	public List searchFeedback(FeedbackVO feedbackVO) {
		// TODO Auto-generated method stub
		
		return this.feedbackDAO.searchFeedback(feedbackVO);
		
	}
	@Transactional
	public void deleteFeedback(FeedbackVO feedbackVO)
	{
		feedbackDAO.deleteFeedback(feedbackVO);
	}
	
	
}

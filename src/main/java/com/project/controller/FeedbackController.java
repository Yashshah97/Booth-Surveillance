package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.model.CityVO;
import com.project.model.FeedbackVO;
import com.project.model.LoginVO;
import com.project.model.StaffVO;
import com.project.service.CityService;
import com.project.service.FeedbackService;
import com.project.service.LoginService;

@Controller
public class FeedbackController {

	@Autowired
	FeedbackService feedbackService;
	

	@Autowired
	LoginService loginService;


	@RequestMapping(value = "staff/feedback")
	public ModelAndView feedback(HttpSession session,LoginVO loginVO) {
		
		
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		loginVO.setUsername(userName);
		List ls = this.loginService.searchLoginID(loginVO);
		LoginVO lVO= (LoginVO)ls.get(0);
		int loginId = lVO.getLoginId();
		lVO.setLoginId(loginId);
		System.out.println("loginID>>>>>>"+loginId);

		
	/*	LoginVO rlVO=  new LoginVO();
		rlVO.setLoginId(loginId);
		
		StaffVO svo = new StaffVO();*/
		
		
		session.setAttribute("loginId", loginId);

		return new ModelAndView("staff/feedback", "feedbackVO", new FeedbackVO());
	}

	@RequestMapping(value = "staff/insertFeedback", method = RequestMethod.POST)
	public ModelAndView insertFeedback(@ModelAttribute FeedbackVO feedbackVO) {
		
		
			
		System.out.println("id is"+feedbackVO.getLoginVO().getLoginId());
		this.feedbackService.insertFeedback(feedbackVO);
		return new ModelAndView("redirect:feedback");
	}
	
	@RequestMapping(value="admin/viewfeedback",method = RequestMethod.GET)
	public ModelAndView viewfeedback(@ModelAttribute FeedbackVO feedbackVO) {
		
		   List feedbackList = this.feedbackService.searchFeedback(feedbackVO);
		   System.out.println(feedbackList);
		   return new ModelAndView("admin/viewfeedback").addObject("feedbackList",feedbackList);
		   
	   }
	
	@RequestMapping(value="staff/viewfeedback",method = RequestMethod.GET)
	public ModelAndView viewfeedbackStaff(@ModelAttribute FeedbackVO feedbackVO) {
		
		   List feedbackList = this.feedbackService.searchFeedback(feedbackVO);
		   System.out.println(feedbackList);
		   return new ModelAndView("staff/viewfeedback").addObject("feedbackList",feedbackList);
		   
	   }
	@RequestMapping(value="staff/deleteFeedback",method=RequestMethod.GET)
	public ModelAndView deleteFeedback(@ModelAttribute FeedbackVO feedbackVO,@RequestParam("id1") int id)
		{	
		feedbackVO.setFeedbackId(id);
		
		
		
		this.feedbackService.deleteFeedback(feedbackVO);
		List feedbackList = this.feedbackService.searchFeedback(feedbackVO);
		return new ModelAndView("staff/viewfeedback").addObject("feedbackList",feedbackList);
		}
	
}

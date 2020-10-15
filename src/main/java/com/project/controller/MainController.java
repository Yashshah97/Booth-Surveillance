	package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.model.LoginVO;
import com.project.model.StaffVO;
import com.project.service.LoginService;

@Controller
public class MainController {
	
	@Autowired LoginService loginService;
	
	@RequestMapping(value = "/admin/viewDetection", method = RequestMethod.GET)
	public ModelAndView viewDetection() {

		return new ModelAndView("admin/viewDetection");
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView loadLogin() {

		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "/admin/index", method = RequestMethod.GET)
	public ModelAndView adminIndex(LoginVO loginVO,HttpSession session) {

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		loginVO.setUsername(userName);
		List ls = this.loginService.searchLoginID(loginVO);
		LoginVO lVO= (LoginVO)ls.get(0);
		int loginId = lVO.getLoginId();
		System.out.println("loginID>>>>>>"+loginId);

		
		LoginVO rlVO=  new LoginVO();
		rlVO.setLoginId(loginId);
		
		StaffVO svo = new StaffVO();
		svo.setLoginVO(rlVO);
		
		List rls = loginService.searchRegister(svo);
		StaffVO ssvo = (StaffVO) rls.get(0);
		
String fn = ssvo.getFirstName();
String fileName = ssvo.getFileName();

session.setAttribute("fname", fn);
session.setAttribute("fileName", fileName);



System.out.println("FNAM>>>>>>>"+fn+"<<<<<<<<filename>>>>>>"+fileName);
		
		
		return new ModelAndView("admin/index");
	}
	
	@RequestMapping(value = "/staff/index", method = RequestMethod.GET)
	public ModelAndView userIndex(LoginVO loginVO,HttpSession session) {

		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();
		loginVO.setUsername(userName);
		List ls = this.loginService.searchLoginID(loginVO);
		LoginVO lVO= (LoginVO)ls.get(0);
		int loginId = lVO.getLoginId();
		System.out.println("loginID>>>>>>"+loginId);

		
		LoginVO rlVO=  new LoginVO();
		rlVO.setLoginId(loginId);
		
		StaffVO svo = new StaffVO();
		svo.setLoginVO(rlVO);
		
		List rls = loginService.searchRegister(svo);
		StaffVO ssvo = (StaffVO) rls.get(0);
		
String fn = ssvo.getFirstName();
String fileName = ssvo.getFileName();

session.setAttribute("fname", fn);
session.setAttribute("fileName", fileName);



System.out.println("FNAM>>>>>>>"+fn+"<<<<<<<<filename>>>>>>"+fileName);
		
		
		return new ModelAndView("staff/index");
	}
	@RequestMapping(value = "/logout", method = {RequestMethod.POST, RequestMethod.GET})	
	public String viewUserDetails(ModelMap model,HttpServletResponse response,HttpServletRequest request) {

		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        if (auth != null) {
	            new SecurityContextLogoutHandler().logout(request, response, auth);
	            request.getSession().invalidate();
	            request.getSession().setAttribute("tempStatus", "success");
	            request.getSession().setAttribute("statusText", "Logout Successfully!");
	}
	        return "login";
	        }
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView load() {

		return new ModelAndView("login");
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView load403() {

		return new ModelAndView("login");
	}
	

	
}

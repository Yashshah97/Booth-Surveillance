package com.project.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.project.model.AllocateVO;
import com.project.model.AreaVO;
import com.project.model.BoothVO;
import com.project.model.CityVO;
import com.project.model.LoginVO;
import com.project.model.StaffVO;
import com.project.service.CityService;
import com.project.service.LoginService;
import com.project.service.StaffService;
@Controller
public class StaffController {
	
	@Autowired
	StaffService staffService;
	
	@Autowired
	LoginService loginService;
	
	
	
	@RequestMapping(value = "admin/loadStaff")
	public ModelAndView loadStaff() {
		
		return new ModelAndView("admin/addStaff", "StaffVO", new StaffVO());
	}
	
	@RequestMapping(value = "admin/insertStaff", method = RequestMethod.POST)
	public ModelAndView insertStaff(@ModelAttribute StaffVO staffVO,LoginVO loginvo,@RequestParam ("file")MultipartFile file,HttpSession session) 
	{
		
		String path = session.getServletContext().getRealPath("/");
		
		String finalPath = path + "document\\demo\\";
		
		String fileName = file.getOriginalFilename();
		
		try
		{
			byte b[] = file.getBytes();
			
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(finalPath+"/"+fileName));
			
			bufferedOutputStream.write(b);
			bufferedOutputStream.flush();
			bufferedOutputStream.close();
		}
		catch (Exception e) {
			System.out.println("Hey I'm Error");
		}
		
	    staffVO.setFileName(fileName);
	    staffVO.setFilePath(finalPath);
	   
	    loginvo.setUsername(staffVO.getLoginVO().getUsername());
	    loginvo.setPassword(staffVO.getLoginVO().getPassword());
	    loginvo.setEnabled("1");
	    loginvo.setRole("ROLE_STAFF");
 
	    
	    this.loginService.insertLogin(loginvo);
	    
	    staffVO.setLoginVO(loginvo);
		this.staffService.insertStaff(staffVO);
		return new ModelAndView("redirect:loadStaff");
	}
	@RequestMapping(value="admin/viewStaff",method = RequestMethod.GET)
	public ModelAndView viewStaff(@ModelAttribute StaffVO staffVO) {
		
		   List staffList = this.staffService.searchStaff(staffVO);
		   System.out.println(staffList);
		   return new ModelAndView("admin/viewStaff").addObject("staffList",staffList);
	}
	@RequestMapping(value="staff/viewStaff",method = RequestMethod.GET)
	public ModelAndView viewStaff2(@ModelAttribute StaffVO staffVO) {
		
		   List staffList = this.staffService.searchStaff(staffVO);
		   System.out.println(staffList);
		   return new ModelAndView("staff/viewStaff").addObject("staffList",staffList);
	}
	@RequestMapping(value="admin/deleteStaff",method=RequestMethod.GET)
	public ModelAndView deleteStaff(@ModelAttribute StaffVO staffVO,@RequestParam("id1") int id)
		{	
		staffVO.setStaffId(id);
		List staffList=this.staffService.getStaff(staffVO);
		
		staffVO  = (StaffVO)staffList.get(0);
		staffVO.setStatus(false); 
		this.staffService.updateStaff(staffVO);;
		
		return new ModelAndView("redirect:/admin/viewStaff");
		
		
		}
	@RequestMapping(value="admin/editStaff",method=RequestMethod.GET)
	public ModelAndView editStaff(@ModelAttribute StaffVO staffVO,@RequestParam("id1") int id)
		{	
		staffVO.setStaffId(id);
		
		List ls=this.staffService.getStaff(staffVO);
		System.out.println(ls.size());
		
		System.out.println((StaffVO)ls.get(0));
		return new ModelAndView("admin/updateStaff", "StaffVO",(StaffVO)ls.get(0));
		}
	@RequestMapping(value="admin/updateStaff",method=RequestMethod.POST)
	public ModelAndView updateStaff(@ModelAttribute StaffVO staffVO,@RequestParam ("file")MultipartFile file,HttpSession session)
		{
			String path = session.getServletContext().getRealPath("/");
			
			String finalPath = path + "document\\demo\\";
			
			String fileName = file.getOriginalFilename();
			
			try
			{
				byte b[] = file.getBytes();
				
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(finalPath+"/"+fileName));
				
				bufferedOutputStream.write(b);
				bufferedOutputStream.flush();
				bufferedOutputStream.close();
			}
			catch (Exception e) {
				System.out.println("Hey I'm Error");
			}
			
		    staffVO.setFileName(fileName);
		    staffVO.setFilePath(finalPath);
		   
			
			this.staffService.updateStaff(staffVO);
			return new ModelAndView("redirect:viewStaff");
		}
		   

}

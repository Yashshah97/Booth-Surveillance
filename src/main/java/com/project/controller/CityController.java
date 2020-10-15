package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.model.CityVO;
import com.project.service.CityService;

@Controller
public class CityController {

	@Autowired
	CityService cityService;

	@RequestMapping(value = "admin/loadCity")
	public ModelAndView loadCity() {

		return new ModelAndView("admin/addCity", "cityVO", new CityVO());
	}

	@RequestMapping(value = "admin/managecity2", method = RequestMethod.POST)
	public ModelAndView managecity2(@ModelAttribute CityVO cityVO) {
		this.cityService.insertCity(cityVO);
		return new ModelAndView("redirect:loadCity");
	}
	
	@RequestMapping(value="admin/viewcity",method = RequestMethod.GET)
	public ModelAndView viewcity(@ModelAttribute CityVO cityVO) {
		
		   List ls = this.cityService.searchCity(cityVO);
		   System.out.println(ls);
		   return new ModelAndView("admin/viewcity").addObject("k",ls);
		   
	   }
	@RequestMapping(value="staff/viewcity",method = RequestMethod.GET)
	public ModelAndView viewcityStaff(@ModelAttribute CityVO cityVO) {
		
		   List ls = this.cityService.searchCity(cityVO);
		   System.out.println(ls);
		   return new ModelAndView("staff/viewcity").addObject("k",ls);
		   
	   }
	@RequestMapping(value="admin/deletecontent",method=RequestMethod.GET)
	public ModelAndView deletecontent(@ModelAttribute CityVO cityVO,@RequestParam("id1") int id)
		{	
		cityVO.setCityId(id);
		List cityList=this.cityService.getCity(cityVO);
		cityVO  = (CityVO)cityList.get(0);
		cityVO.setStatus(false); 
		this.cityService.updateCity(cityVO);
		
		return new ModelAndView("redirect:/admin/viewcity");
		}
	@RequestMapping(value="admin/editcontent",method=RequestMethod.GET)
	public ModelAndView editcontent(@ModelAttribute CityVO cityVO,@RequestParam("id1") int id)
		{	
		cityVO.setCityId(id);
		
		List ls=this.cityService.getCity(cityVO);
		System.out.println(ls.size());
		
		System.out.println((CityVO)ls.get(0));
		return new ModelAndView("admin/updateCity", "cityVO",(CityVO)ls.get(0));
		}
	@RequestMapping(value="admin/updateCity",method=RequestMethod.POST)
	public ModelAndView newdataInsert(@ModelAttribute CityVO cityVO)
		{	System.out.println(cityVO.getCityId());
			this.cityService.updateCity(cityVO);
			return new ModelAndView("redirect:viewcity");
		}
}

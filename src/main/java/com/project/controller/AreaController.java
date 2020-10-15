package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.model.AreaVO;
import com.project.model.CityVO;
import com.project.service.AreaService;
import com.project.service.CityService;

@Controller
public class AreaController {

	@Autowired
	CityService cityService;
	
	@Autowired
	AreaService areaService;

	@RequestMapping(value = "admin/loadArea")
	public ModelAndView loadArea(CityVO cityVO) {
		List cityList = this.cityService.searchCity(cityVO);
		System.out.println(cityList);
		return new ModelAndView("admin/addArea", "areaVO", new AreaVO()).addObject("cityList",cityList);
	}

	@RequestMapping(value = "admin/insertArea", method = RequestMethod.POST)
	public ModelAndView insertArea(@ModelAttribute AreaVO areaVO) {
		this.areaService.insertArea(areaVO);
		return new ModelAndView("redirect:loadArea");
	}
	
	@RequestMapping(value="admin/viewarea",method = RequestMethod.GET)
	public ModelAndView viewarea(@ModelAttribute AreaVO areaVO) {
		
		   List areaList = this.areaService.searchArea(areaVO);
		   System.out.println(areaList);
		   return new ModelAndView("admin/viewarea").addObject("areaList",areaList);
		   
	   }
	@RequestMapping(value="staff/viewarea",method = RequestMethod.GET)
	public ModelAndView viewareaStaff(@ModelAttribute AreaVO areaVO) {
		
		   List areaList = this.areaService.searchArea(areaVO);
		   System.out.println(areaList);
		   return new ModelAndView("staff/viewarea").addObject("areaList",areaList);
		   
	   }
	@RequestMapping(value="admin/deleteArea",method=RequestMethod.GET)
	public ModelAndView deleteArea(@ModelAttribute AreaVO areaVO,@RequestParam("id1") int id)
		{	
		areaVO.setAreaId(id);
		List areaList=this.areaService.getArea(areaVO);
		
		areaVO  = (AreaVO)areaList.get(0);
		areaVO.setStatus(false); 
		this.areaService.updateArea(areaVO);
		return new ModelAndView("redirect:/admin/viewarea");
		
		}
	@RequestMapping(value="admin/editArea",method=RequestMethod.GET)
	public ModelAndView editcontent(@ModelAttribute AreaVO areaVO,@RequestParam("id1") int id)
		{	
		areaVO.setAreaId(id);
		
		List ls=this.areaService.getArea(areaVO);
		System.out.println(ls.size());
		
		System.out.println((AreaVO)ls.get(0));
		CityVO cityVO=new CityVO();
		List cityList = this.cityService.searchCity(cityVO);
		return new ModelAndView("admin/updateArea", "areaVO",(AreaVO)ls.get(0)).addObject("cityList",cityList);
		}
	@RequestMapping(value="admin/updateArea",method=RequestMethod.POST)
	public ModelAndView updateArea(@ModelAttribute AreaVO areaVO)
		{	System.out.println(areaVO.getAreaId());
			this.areaService.updateArea(areaVO);
			return new ModelAndView("redirect:viewarea");
		}
}

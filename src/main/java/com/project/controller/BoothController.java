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
import com.project.model.BoothVO;
import com.project.model.CityVO;
import com.project.service.AreaService;
import com.project.service.BoothService;
import com.project.service.CityService;

@Controller
public class BoothController {

	@Autowired
	CityService cityService;
	
	@Autowired
	AreaService areaService;
	
	@Autowired
	BoothService boothService;

	@RequestMapping(value = "admin/loadBooth")
	public ModelAndView loadBooth(CityVO cityVO) {
		AreaVO areaVO=new AreaVO();
		
		List cityList = this.cityService.searchCity(cityVO);
		//List areaList = this.areaService.searchArea(areaVO);
		System.out.println(cityList);
		return new ModelAndView("admin/addBoothdetails", "BoothVO", new BoothVO()).addObject("cityList",cityList);
	}
	@RequestMapping(value = "/getarea")
	public ModelAndView getarea(@ModelAttribute BoothVO boothVO,@RequestParam("city") int id) {
		System.out.println("HEy");
		System.out.println(id);
		
		CityVO cityVO=new CityVO();
		cityVO.setCityId(id);
		AreaVO areaVO=new AreaVO();
		areaVO.setCityVO(cityVO);
		List areaList=this.areaService.getAreabycity(areaVO);
		System.out.println(areaList);
		System.out.println(areaVO.getCityVO().getCityId());
		return new ModelAndView("admin/addBoothdetails", "BoothVO", new BoothVO()).addObject("areaList", areaList);
	}

	@RequestMapping(value = "admin/insertBooth", method = RequestMethod.POST)
	public ModelAndView insertArea(@ModelAttribute BoothVO boothVO) {
		this.boothService.insertBooth(boothVO);
		return new ModelAndView("redirect:loadBooth");
	}
	
	@RequestMapping(value="admin/viewbooth",method = RequestMethod.GET)
	public ModelAndView viewbooth(@ModelAttribute BoothVO boothVO) {
		
		   List boothList = this.boothService.searchBooth(boothVO);
		   System.out.println(boothList);
		   return new ModelAndView("admin/viewboothdetails").addObject("boothList",boothList);
		   
	   }
	@RequestMapping(value="staff/viewbooth",method = RequestMethod.GET)
	public ModelAndView viewboothStaff(@ModelAttribute BoothVO boothVO) {
		
		   List boothList = this.boothService.searchBooth(boothVO);
		   System.out.println(boothList);
		   return new ModelAndView("staff/viewboothdetails").addObject("boothList",boothList);
		   
	   }
	@RequestMapping(value="admin/deleteBooth",method=RequestMethod.GET)
	public ModelAndView deleteBooth(@ModelAttribute BoothVO boothVO,@RequestParam("id1") int id)
		{	
		boothVO.setBoothId(id);
		List boothList=this.boothService.getBooth(boothVO);
		boothVO  = (BoothVO)boothList.get(0);
		boothVO.setStatus(false); 
		this.boothService.updateBooth(boothVO);
		
		return new ModelAndView("redirect:/admin/viewbooth");
		
		}
	@RequestMapping(value="admin/editBooth",method=RequestMethod.GET)
	public ModelAndView editBooth(@ModelAttribute BoothVO boothVO,@RequestParam("id1") int id)
		{	
		boothVO.setBoothId(id);
		
		List ls=this.boothService.getBooth(boothVO);
		System.out.println(ls.size());
		
		System.out.println((BoothVO)ls.get(0));
		CityVO cityVO=new CityVO();
		List cityList = this.cityService.searchCity(cityVO);
		return new ModelAndView("admin/updateBooth", "BoothVO",(BoothVO)ls.get(0)).addObject("cityList",cityList);
		}
	@RequestMapping(value = "/getareaagain")
	public ModelAndView getareaagain(@ModelAttribute BoothVO boothVO,@RequestParam("city") int id) {
		System.out.println("HEy");
		System.out.println(id);
		
		CityVO cityVO=new CityVO();
		cityVO.setCityId(id);
		AreaVO areaVO=new AreaVO();
		areaVO.setCityVO(cityVO);
		List areaList=this.areaService.getAreabycity(areaVO);
		System.out.println(areaList);
		System.out.println(areaVO.getCityVO().getCityId());
		return new ModelAndView("admin/updateBooth", "BoothVO", new BoothVO()).addObject("areaList", areaList);
	}
	@RequestMapping(value="admin/updateBooth",method=RequestMethod.POST)
	public ModelAndView updateBooth(@ModelAttribute BoothVO boothVO)
		{	System.out.println(boothVO.getBoothId());
			this.boothService.updateBooth(boothVO);
			return new ModelAndView("redirect:viewbooth");
		}
	
}

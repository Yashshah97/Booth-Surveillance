package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.model.AllocateVO;
import com.project.model.AreaVO;
import com.project.model.BoothVO;
import com.project.model.CityVO;
import com.project.model.StaffVO;
import com.project.service.AllocateService;
import com.project.service.AreaService;
import com.project.service.BoothService;
import com.project.service.CityService;
import com.project.service.StaffService;

@Controller
public class AllocateController {

	@Autowired
	AllocateService allocateService;
	
	@Autowired
	StaffService staffService;
	
	
	@Autowired
	BoothService boothService;

	@RequestMapping(value = "admin/allocate")
	public ModelAndView allocate(BoothVO boothVO) {
		
		StaffVO staffVO=new StaffVO();
		List staffList = this.staffService.searchStaff(staffVO);
		List boothList = this.boothService.searchBooth(boothVO);
		System.out.println(boothList);
		return new ModelAndView("admin/allocate1", "allocateVO", new AllocateVO()).addObject("boothList",boothList).addObject("staffList", staffList);
	}

	@RequestMapping(value = "admin/insertAllocate", method = RequestMethod.POST)
	public ModelAndView insertAllocate(@ModelAttribute AllocateVO allocateVO) {
		this.allocateService.insertAllocate(allocateVO);
		return new ModelAndView("redirect:allocate");
	}
	
	@RequestMapping(value="admin/viewallocate",method = RequestMethod.GET)
	public ModelAndView viewallocate(@ModelAttribute AllocateVO allocateVO) {
		
		   List allocateList = this.allocateService.searchAllocate(allocateVO);
		   System.out.println(allocateList);
		   return new ModelAndView("admin/viewallocate").addObject("allocateList",allocateList);
		   
	   }
	@RequestMapping(value="staff/viewallocate",method = RequestMethod.GET)
	public ModelAndView viewallocateStaff(@ModelAttribute AllocateVO allocateVO) {
		
		   List allocateList = this.allocateService.searchAllocate(allocateVO);
		   System.out.println(allocateList);
		   return new ModelAndView("staff/viewallocate").addObject("allocateList",allocateList);
		   
	   }
	@RequestMapping(value="admin/deleteAllocate",method=RequestMethod.GET)
	public ModelAndView deleteAllocate(@ModelAttribute AllocateVO allocateVO,@RequestParam("id1") int id)
		{	
		allocateVO.setAllocateId(id);
		List allocateList=this.allocateService.getAllocate(allocateVO);
		
		allocateVO  = (AllocateVO)allocateList.get(0);
		allocateVO.setStatus(false); 
		this.allocateService.updateAllocate(allocateVO);;
		
		return new ModelAndView("redirect:/admin/viewallocate");
		
		
		}
	@RequestMapping(value="admin/editAllocate",method=RequestMethod.GET)
	public ModelAndView editAllocate(@ModelAttribute AllocateVO allocateVO,@RequestParam("id1") int id)
		{	
		allocateVO.setAllocateId(id);
		
		List ls=this.allocateService.getAllocate(allocateVO);
		System.out.println(ls.size());
		
		System.out.println((AllocateVO)ls.get(0));
		BoothVO boothVO=new BoothVO();
		StaffVO staffVO=new StaffVO();
		List staffList = this.staffService.searchStaff(staffVO);
		List boothList = this.boothService.searchBooth(boothVO);
		return new ModelAndView("admin/updateAllocate", "allocateVO",(AllocateVO)ls.get(0)).addObject("staffList",staffList).addObject("boothList",boothList).addObject("staffList", staffList);
		}
	@RequestMapping(value="admin/updateAllocate",method=RequestMethod.POST)
	public ModelAndView updateAllocate(@ModelAttribute AllocateVO allocateVO)
		{	System.out.println(allocateVO.getAllocateId());
			this.allocateService.updateAllocate(allocateVO);
			return new ModelAndView("redirect:viewallocate");
		}
}

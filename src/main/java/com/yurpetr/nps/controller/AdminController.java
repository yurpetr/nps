package com.yurpetr.nps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.yurpetr.nps.service.UsersService;

@RestController
@RequestMapping("/admin")
public class AdminController {

   @Autowired
	UsersService usersService;

	public AdminController(UsersService usersService) {
		this.usersService = usersService;
	}

	@GetMapping
	public ModelAndView getAllUsers() {
		ModelAndView mav = new ModelAndView("admin");
		mav.addObject("users", usersService.getAllUsers());
		return mav;
	}

}

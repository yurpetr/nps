package com.yurpetr.nps.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yurpetr.nps.service.UsersService;

@Controller
public class HomeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	UsersService usersService;

	@GetMapping("/")
	public ModelAndView home(Principal principal) {
		ModelAndView mav = new ModelAndView();
		if (principal != null) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {
				LOGGER.info("Admin logined");
				mav.setViewName("admin");
				mav.addObject("users", usersService.getAllUsers());
				return mav;
			}
			if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_POWERBI"))) {
				LOGGER.info("PowerBI user logined");
				mav.setViewName("powerbi");
				mav.addObject("url", usersService.findByLogin(authentication.getName()).get().getPowerBiUrl());
				return mav;
			}
			LOGGER.info("User logined");
			mav.setViewName("nps");
			mav.addObject("pos", usersService.findByLogin(authentication.getName()).get().getPointOfSale());
			return mav;

		}
		LOGGER.info("Not authenticated");
		mav.setViewName("login");
		return mav;

	}
}

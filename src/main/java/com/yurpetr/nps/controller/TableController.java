package com.yurpetr.nps.controller;

import java.util.Collection;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.yurpetr.nps.model.Role;
import com.yurpetr.nps.model.User;
import com.yurpetr.nps.repo.RoleRepository;
import com.yurpetr.nps.service.UserNotFoundException;
import com.yurpetr.nps.service.UsersService;

@RestController
@RequestMapping("/users")
public class TableController {

	@Autowired
	UsersService usersService;

	@Autowired
	RoleRepository roleRepository;

	@GetMapping("/addUserForm")
	public ModelAndView addUserForm() {
		ModelAndView mav = new ModelAndView();
		User newUser = new User();
		Collection<Role> roles = roleRepository.findAll();
		newUser.setRoles(roles);
		mav.setViewName("add-user-form");
		mav.addObject("user", newUser);
		return mav;
	}

	@PostMapping(value = "/create")
	public ModelAndView addUser(@ModelAttribute User usr) {
		ModelAndView mav = new ModelAndView();
		usersService.save(usr);
		mav.setViewName("redirect:/");
		mav.addObject("users", usersService.getAllUsers());
		return mav;
	}

	@GetMapping(value = "/delete/{id}")
	public ModelAndView deleteUser(@PathVariable("id") @Min(1) int id) {
		ModelAndView mav = new ModelAndView();
		User usr = usersService.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Користувача " + id + " не знайдено!"));
		usersService.deleteById(usr.getId());
		mav.setViewName("redirect:/");
		mav.addObject("users", usersService.getAllUsers());
		return mav;
	}

	@GetMapping(value = "/edit/{id}")
	public ModelAndView editUser(@PathVariable("id") @Min(1) int id) {
		ModelAndView mav = new ModelAndView();
		User usr = usersService.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Користувача " + id + " не знайдено!"));
		mav.setViewName("edit-user-form");
		Collection<Role> roles = roleRepository.findAll();
		mav.addObject("roles", roles);
		mav.addObject("user", usr);
		return mav;
	}

	@PostMapping(value = "/update/{id}")
	public ModelAndView updateUser(@PathVariable("id") @Min(1) int id, @ModelAttribute User usrU) {
		ModelAndView mav = new ModelAndView();
		User usr = usersService.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Користувача " + id + " не знайдено!"));
		usr.setLogin(usrU.getLogin());
		if ((usrU.getPassword() != "") && (usrU.getPassword() != null))
			usr.setPassword(usrU.getPassword());
		usr.setPointOfSale(usrU.getPointOfSale());
		usr.setPowerBiUrl(usrU.getPowerBiUrl());
		usr.setRoles(usrU.getRoles());
		usersService.save(usr);
		mav.setViewName("redirect:/");
		mav.addObject("users", usersService.getAllUsers());
		return mav;
	}
}

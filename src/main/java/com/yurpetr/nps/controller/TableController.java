package com.yurpetr.nps.controller;

import java.util.Arrays;
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
import org.springframework.web.servlet.view.RedirectView;

import com.yurpetr.nps.model.AdminAttribute;
import com.yurpetr.nps.model.Role;
import com.yurpetr.nps.model.User;
import com.yurpetr.nps.repo.RolesRepository;
import com.yurpetr.nps.service.UserNotFoundException;
import com.yurpetr.nps.service.UsersService;

@RestController
@RequestMapping("/users")
public class TableController {

	@Autowired
	UsersService usersService;

	@Autowired
	RolesRepository rolesRepository;

	@GetMapping("/addUserForm")
	public ModelAndView addUserForm() {
		ModelAndView mav = new ModelAndView("add-user-form");
		User newUser = new User();
		Collection<Role> roles = rolesRepository.findAll();
		newUser.setRoles(roles);
		mav.addObject("user", newUser);
		return mav;
	}

	@PostMapping(value = "/create")
	public ModelAndView addUser(@ModelAttribute User usr) {
		usersService.save(usr);
		ModelAndView mav = new ModelAndView(new RedirectView("/admin"));
		mav.addObject("users", usersService.getAllUsers());
		return mav;
	}

	@GetMapping(value = "/delete/{id}")
	public ModelAndView deleteUser(@PathVariable("id") @Min(1) int id) {
		User usr = usersService.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Пользователь " + id + " не найден!"));
		if (!usr.getLogin().equalsIgnoreCase("admin"))
			usersService.deleteById(usr.getId());
		ModelAndView mav = new ModelAndView(new RedirectView("/admin"));
		mav.addObject("users", usersService.getAllUsers());
		return mav;
	}
}

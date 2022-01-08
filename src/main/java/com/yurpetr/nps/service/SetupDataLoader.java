package com.yurpetr.nps.service;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.yurpetr.nps.model.Role;
import com.yurpetr.nps.model.User;
import com.yurpetr.nps.repo.RolesRepository;
import com.yurpetr.nps.repo.UsersRepository;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private RolesRepository roleRepository;

	/*
	 * @Autowired private PasswordEncoder passwordEncoder;
	 */

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (alreadySetup)
			return;
		createRoleIfNotFound("ROLE_ADMIN");
		createRoleIfNotFound("ROLE_USER");
		createRoleIfNotFound("ROLE_POWERBI");
		

		if (!userRepository.findByLogin("admin").isPresent()) {
			Role adminRole = roleRepository.findByName("ROLE_ADMIN").get();
			User user = new User();
			user.setLogin("admin");
			user.setPassword("$2a$12$R1zZBCdmJK2A7Y/qgHNY2.P3rpxrHZYBUlwziHFC/5oMekb7d/tWq");
			user.setPointOfSale("admin");
			user.setRoles(Arrays.asList(adminRole));
			userRepository.save(user);
		}
		/*
		 * if (!userRepository.findByLogin("user").isPresent()) { Role userRole =
		 * roleRepository.findByName("ROLE_USER").get(); User test = new User();
		 * test.setLogin("user"); test.setPassword(
		 * "$2a$12$pV85f1gXDLjP/vZBJVVBH.pysDGmroFgIgfdB3zNazSDwWeprnt0G");
		 * test.setPointOfSale("test"); test.setRoles(Arrays.asList(userRole));
		 * userRepository.save(test); }
		 */
		alreadySetup = true;

	}

	@Transactional
	Role createRoleIfNotFound(String name) {

		Role role = roleRepository.findByName(name).orElseGet(() -> {
			Role r = new Role();
			r.setName(name);
			roleRepository.save(r);
			return r;
		});
		return role;
	}
}
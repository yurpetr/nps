package com.yurpetr.nps.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.yurpetr.nps.model.Role;
import com.yurpetr.nps.model.User;
import com.yurpetr.nps.repo.RoleRepository;
import com.yurpetr.nps.repo.UserRepository;

import jakarta.transaction.Transactional;

@Component
public class SetupDataLoader
      implements ApplicationListener<ContextRefreshedEvent> {

   boolean alreadySetup = false;

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private RoleRepository roleRepository;

   @Override
   @Transactional
   public void onApplicationEvent(ContextRefreshedEvent event) {

      if (alreadySetup)
         return;
      createRoleIfNotFound("ROLE_ADMIN");
      createRoleIfNotFound("ROLE_USER");
      createRoleIfNotFound("ROLE_POWERBI");

      if (!userRepository.findByLogin("admin").isPresent()) {
         Role adminRole = roleRepository.findByName("ROLE_ADMIN")
               .get();
         User user = new User();
         user.setLogin("admin");
         user.setPassword(
               "$2a$12$R1zZBCdmJK2A7Y/qgHNY2.P3rpxrHZYBUlwziHFC/5oMekb7d/tWq");
         user.setPointOfSale("admin");
         user.setRoles(Arrays.asList(adminRole));
         userRepository.save(user);
      }
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
package com.yurpetr.nps.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginLogoutController {

   @GetMapping("/login")
   public ModelAndView login() {
		return new ModelAndView("login");
   }

   @GetMapping("/logoutpage")
   public ModelAndView logoutPage() {
      return new ModelAndView("logoutpage");
   }

   @GetMapping("/logout")
   public ModelAndView logout(HttpServletRequest request,
         HttpServletResponse response) {
      Authentication auth = SecurityContextHolder.getContext()
            .getAuthentication();
      if (auth != null) {
         new SecurityContextLogoutHandler().logout(request, response,
               auth);
      }
      return new ModelAndView("redirect:/");
   }
}
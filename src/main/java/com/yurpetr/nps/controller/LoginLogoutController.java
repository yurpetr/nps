package com.yurpetr.nps.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginLogoutController {

   @GetMapping("/login")
   public ModelAndView login() {
	   ModelAndView mav = new ModelAndView("login");
		return mav;
   }

   @GetMapping("/logoutpage")
   public String logoutPage() {
      return "logoutpage";
   }

   @GetMapping("/logout")
   public String logout(HttpServletRequest request,
         HttpServletResponse response) {
      Authentication auth = SecurityContextHolder.getContext()
            .getAuthentication();
      if (auth != null) {
         new SecurityContextLogoutHandler().logout(request, response,
               auth);
      }
      return "redirect:/";
   }
}
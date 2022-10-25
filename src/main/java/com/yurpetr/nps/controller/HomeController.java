package com.yurpetr.nps.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.yurpetr.nps.service.CreateLeadService;
import com.yurpetr.nps.service.UsersService;

@Controller
@RequestMapping("/")
public class HomeController {
   @Autowired
   UsersService usersService;

   @GetMapping
   public ModelAndView home(Principal principal) {
      if (principal != null) {
         Authentication authentication = SecurityContextHolder.getContext()
               .getAuthentication();
         if (authentication.getAuthorities().stream()
               .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {
            ModelAndView mav = new ModelAndView(new RedirectView("/admin"));
            return mav;
         }
         if (authentication.getAuthorities().stream()
               .anyMatch(r -> r.getAuthority().equals("ROLE_POWERBI"))) {
            ModelAndView mav = new ModelAndView("powerbi");
            mav.addObject("url", usersService
                  .findByLogin(authentication.getName()).get().getPowerBiUrl());
            return mav;
         }

         ModelAndView mav = new ModelAndView("nps");
         mav.addObject("pos", usersService
               .findByLogin(authentication.getName()).get().getPointOfSale());
         return mav;

      }
      ModelAndView mav = new ModelAndView(new RedirectView("/login"));
      return mav;
   }

   @PostMapping("/")
   public void submit(@RequestParam String id, String point) {

      CreateLeadService service = new CreateLeadService();
      service.createLead(id, point);

   }

   /*
    * @RequestMapping(value = "/justdoit") public String doStuffMethod(
    * 
    * @RequestParam(value = "id", required = false) String id,
    * 
    * @RequestParam(value = "point", required = false) String point) { //
    * CreateLeadService service = new CreateLeadService(); //
    * service.createLead(id, point); System.out.println("Success");
    * System.out.println("Button pressed: " + id);
    * System.out.println("Point of sale: " + point); return "congrats"; //
    * return "redirect:/"; }
    */

   @PostMapping("/justdoit")
   public String doStuffMethod(
         @RequestParam("id") String id,
         @RequestParam("pos") String point) {
      CreateLeadService service = new CreateLeadService();
//      service.createLead(id, point);
      System.out.println("Success");
      System.out.println("Button pressed: " + id);
      System.out.println("Point of sale: " + point);
      return "congrats";
      // return "redirect:/";
   }
}

package com.yurpetr.nps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/temp")
public class TempController {
   @GetMapping
   public String home() {
      return "index";
   }
}

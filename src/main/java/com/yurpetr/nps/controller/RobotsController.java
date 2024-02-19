package com.yurpetr.nps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
final class RobotsController {
 
    @GetMapping("robots.txt")
    @ResponseBody
    String returnNoRobotsTxt() {
       return null;
    }
}

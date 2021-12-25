package com.yurpetr.nps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
final class FaviconController {
 
    @GetMapping("favicon.ico")
    @ResponseBody
    String returnNoFavicon() {
       return null;
    }
}

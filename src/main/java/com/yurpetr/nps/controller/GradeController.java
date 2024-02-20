package com.yurpetr.nps.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yurpetr.nps.bitrix.CreateLeadService;

@Controller
public class GradeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(GradeController.class);

	@PostMapping("/justdoit")
	public String doStuffMethod(@RequestParam("id") String id, @RequestParam("pos") String point) {
		CreateLeadService service = new CreateLeadService();
		service.createLead(id, point);
		LOGGER.info("Success");
		LOGGER.info("Button pressed: " + id);
		LOGGER.info("Point of sale: " + point);

		return "congrats";
	}
}

package com.student.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CovidController {
	
	@Autowired
	DataService DataService;
	
	@GetMapping("/")
	public String home(Model model) {
		int totalDeath = DataService.getCovidData().stream().mapToInt(total -> Integer.parseInt(total.getTotalDeath())).sum();
		model.addAttribute("totalCount", totalDeath);
		model.addAttribute("covidData",DataService.getCovidData());
		return "home";
	}

}

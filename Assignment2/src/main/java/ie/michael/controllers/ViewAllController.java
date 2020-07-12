package ie.michael.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ie.michael.entities.Jobs;
import ie.michael.service.JobsService;


@Controller
public class ViewAllController {

	@Autowired
	private JobsService jobsService;
	
	@GetMapping("/view-all")
	public String showAll(Model model)
	{
		List<Jobs> jobs = jobsService.findAll();
		model.addAttribute("jobs", jobs);
		return "view-all";
	}
}

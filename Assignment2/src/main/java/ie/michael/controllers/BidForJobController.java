package ie.michael.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ie.michael.entities.Jobs;
import ie.michael.service.JobsService;

@Controller
public class BidForJobController {

	@Autowired
	private JobsService jobsService;
	
	
	@GetMapping("/bid-for-job")
	public String bidForJobPage(Model model, Principal loggeduser){
		List<Jobs> jobs = jobsService.findAll();
		model.addAttribute("jobs", jobs);
		model.addAttribute("email", loggeduser.getName());
		return "bid-for-job";
	}

}

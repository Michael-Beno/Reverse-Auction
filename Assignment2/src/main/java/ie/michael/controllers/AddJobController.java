package ie.michael.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ie.michael.entities.Jobs;
import ie.michael.entities.Users;
import ie.michael.forms.AddJobForm;
import ie.michael.service.JobsService;
import ie.michael.service.UsersService;

@Controller
public class AddJobController {

	@Autowired
	private JobsService jobsService;
	@Autowired
	private UsersService usersService;
	
	@GetMapping("/add-job")
	public String addJobPage(Model model){
		AddJobForm addJobForm = new AddJobForm();
		model.addAttribute("addJobForm",addJobForm);
		return "add-job";
	}


	@PostMapping("/add-job")
	public String addNewCountySave(@Valid @ModelAttribute("addJobForm") AddJobForm addJobForm, BindingResult binding, RedirectAttributes redirectAttributes, Principal user)
	{
		if (binding.hasErrors())
			return "add-job";
		Users creator = usersService.findByEmail(user.getName());
		Jobs job = new Jobs(addJobForm.getJobName(), addJobForm.getJobDescription(), creator);
		jobsService.save(job);
		
		return "redirect:view-all";
	}
	

}

package ie.michael.controllers;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ie.michael.entities.Bids;
import ie.michael.entities.Jobs;
import ie.michael.entities.Users;
import ie.michael.forms.AddBidForm;
import ie.michael.service.BidService;
import ie.michael.service.JobsService;
import ie.michael.service.UsersService;


@Controller
public class BidController {
	
	@Autowired
	private JobsService jobsService;
	@Autowired
	private UsersService usersService;
	@Autowired
	private BidService bidService;
	
	@GetMapping("/bid/{id}") 
	public String oneBid(@PathVariable(name="id") int id, Model model, Principal loggeduser){

		Users user = usersService.findByEmail(loggeduser.getName());
		
		if (jobsService.existsByJobId(id)) {
			Jobs job = jobsService.findJobsByJobId(id);
			model.addAttribute("addedBy", job.getAddedBy().getId());
			model.addAttribute("loggedId", user.getId());
			model.addAttribute("addBidForm", new AddBidForm());
			model.addAttribute("jobs", job);
			List<Bids> bids = bidService.findAllByJobJobId(id);
			model.addAttribute("bids", bids);
			String sLastBid = bidService.findLowestBid(id);
			int lastBid = 0;
			if(sLastBid != null)
				lastBid = Integer.parseInt(sLastBid);
			model.addAttribute("maximumBid", lastBid-1);
			
			return "bid";
		}
		
 		model.addAttribute("id", id);
		return "notfounderror";
	}
	
	
	@PostMapping("/bid")
	public String addNewBidSave(@Valid @ModelAttribute("addBidForm") AddBidForm addBidForm, Model model, 
			BindingResult binding, RedirectAttributes redirectAttributes, Principal loggeduser)
	{
		if (binding.hasErrors()) {
			System.out.println(binding.getFieldErrorCount());
			model.addAttribute("job", addBidForm.getJobID());

			return "bid";
		}

		int bid = addBidForm.getLowestBid();
		int maxBid = addBidForm.getMaximumBid();
		//System.out.println(addBidForm.getMaximumBid());
		Jobs job = jobsService.findJobsByJobId(addBidForm.getJobID());
		
		Users user = usersService.findByEmail(loggeduser.getName());
		if(maxBid == -1 || maxBid >= bid)
			bidService.saveAndUpdate( new Bids(bid, job, user) );
		else return "redirect:bid/"+job.getJobId();

		return "redirect:view-all";
	}

}


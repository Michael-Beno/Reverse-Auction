package ie.michael.controllers.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ie.michael.entities.Bids;
import ie.michael.entities.Jobs;
import ie.michael.entities.Users;
import ie.michael.service.BidService;
import ie.michael.service.JobsService;
import ie.michael.service.UsersService;



@RestController
@RequestMapping("/api")
public class RestControllersJobs {

	
	@Autowired
	private JobsService jobService;
	@Autowired
	private BidService  bidService;
	@Autowired
	private UsersService usersService;
	
	
	@PreAuthorize("hasRole('API')")
	@GetMapping("/job/{id}")
	public Jobs myRestJob(@PathVariable("id") int id){
		return jobService.findJobsByJobId(id);
	}

	@PreAuthorize("hasRole('API')")
	@GetMapping("/showjobs")
	public List<Jobs> myAllJobs(){
		return jobService.findAll();
	}
	
	@PreAuthorize("hasRole('API')")
	@GetMapping("/bid/{id}")
	public List<Bids> myRestBid(@PathVariable("id") int id){
		return bidService.findAllByJobJobId(id);
	}
	
	@PreAuthorize("hasRole('API')")
	@GetMapping("/users")
	public List<Users> myRestUser(){
		return usersService.getAllUsers();
	}
	
	
}

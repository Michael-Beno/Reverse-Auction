package ie.michael;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import ie.michael.service.BidService;
import ie.michael.service.JobsService;
import ie.michael.entities.ApiUser;
import ie.michael.entities.Bids;
import ie.michael.entities.Jobs;
import ie.michael.entities.Role;
import ie.michael.entities.Users;
import ie.michael.service.ApiUserService;
import ie.michael.service.RoleService;
import ie.michael.service.UsersService;


@SpringBootApplication
@EnableScheduling
public class Assignment2Application implements CommandLineRunner{

	//Log4J
	public static void main(String[] args) {
		SpringApplication.run(Assignment2Application.class, args);
	}
	
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private JobsService jobsService;
	
	@Autowired
	private BidService bidService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleService roleService;
	@Autowired 
	private ApiUserService myApiUserService;
	
	@Override
	public void run(String... args) throws Exception {
	    
		Role rFred = new Role("fred@email.com", "ROLE_USER");
		Role rPolk = new Role("polk@email.com", "ROLE_USER");
		Role rBob = new Role("bob@email.com", "ROLE_USER");
		Role roleApi = new Role("sam@email.com", "ROLE_API");
		roleService.save(rFred);
		roleService.save(rPolk);
		roleService.save(rBob);
		roleService.save(roleApi);
		
		Users fred = new Users("Fred", "fred@email.com", passwordEncoder.encode("password"), "0987654321",rFred,true);
		usersService.save(fred);
		Users polk = new Users("Polk", "polk@email.com", passwordEncoder.encode("password"), "01234567899",rPolk, true);
		usersService.save(polk);
		Users bob = new Users("Bob", "bob@email.com", passwordEncoder.encode("password"), "01111111111",rBob, true);
		usersService.save(bob);
		
		ApiUser userApi = new ApiUser("sam@email.com", passwordEncoder.encode("password"), roleApi, true);
		myApiUserService.save(userApi);
		
		Jobs job1 = new Jobs("House Painting", "I like to get house painted.",polk);
		Jobs job2 = new Jobs("Floor needs Polish", "I like someone who gring own Greece and Polish the flooor.",polk);
		job1.setJobDaysLeft(0);
		jobsService.save(job1);
		jobsService.save(new Jobs("Room Painting", "I like to get room painted",bob));
		jobsService.save(new Jobs("Computer Cleaning", "I like to wipe my hard-drive dry.",polk));
		jobsService.save(new Jobs("Housekeeping", "I like to move and keep my house.",bob));
		jobsService.save(new Jobs("Grass Cutting", "A garden needs cut. Size is 0.1 acre. Bring yours scisors.",fred));
		jobsService.save(job2);
		
		bidService.saveAndUpdate( new Bids(500, job2, fred));
		bidService.saveAndUpdate( new Bids(800, job1, fred));
		bidService.saveAndUpdate( new Bids(700, job1, polk));
		bidService.saveAndUpdate( new Bids(600, job1, fred));
		
	}

}

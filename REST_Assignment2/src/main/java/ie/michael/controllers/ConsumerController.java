package ie.michael.controllers;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import ie.michael.domain.Bids;
import ie.michael.domain.Jobs;



@Controller
public class ConsumerController {

	
//	@GetMapping("/")
//	public String index(Model model) {
//		return "index";
//	}
//	@GetMapping("/showalljobs")
//	public String showAll(Model model) {
//		return "showallpages";
//	}
//
	@GetMapping("/")
	public String showAllJobs(Model model)
	{
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			String URL = "http://localhost:8080/api/showjobs";
			String username = "sam@email.com";
			String password = "password";
			HttpHeaders headers = createHeaders(username,password);
			
				ResponseEntity<List<Jobs>> responseEntity =
				restTemplate.exchange(
				URL,
				HttpMethod.GET,
				new HttpEntity<>(headers),
				new ParameterizedTypeReference <List<Jobs>>() {});
			List<Jobs> jobs = responseEntity.getBody();
			//System.out.println(responseEntity);
//			String a =jobs.get(0).getJobName();
//			System.out.println(a);
			model.addAttribute("jobs", jobs);
			return "jobs";
			}catch(Exception e) {
				System.err.println("error: "+e.getMessage());
				return "notfounderror";
		}
	}
	
	@GetMapping("/bid/{id}")
	public String showCounties(@PathVariable(name="id") int id, Model model)
	{
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			String URL = "http://localhost:8080/api/bid/"+id;
			String username = "sam@email.com";
			String password = "password";
			HttpHeaders headers = createHeaders(username,password);
			
				ResponseEntity<List<Bids>> responseEntity =
				restTemplate.exchange(
				URL,
				HttpMethod.GET,
				new HttpEntity<>(headers),
				new ParameterizedTypeReference <List<Bids>>() {});
				
				//System.out.println(responseEntity);
			List<Bids> bids = responseEntity.getBody();
			model.addAttribute("bids", bids);
			return "bid";
			}catch(Exception e) {
				System.err.println("error: "+e.getMessage());
				return "notfounderror";
		}
		
	}
	
	private HttpHeaders createHeaders(String username, String password) {

		return new HttpHeaders() {/**
			 * 
			 */
			private static final long serialVersionUID = 2811664856349760994L;

		{
			
			String auth = username+":"+password;
			
			byte[] encodeStringIntoBytes = auth.getBytes(StandardCharsets.UTF_8);
			
			byte[] encodeAuth = Base64.encodeBase64(encodeStringIntoBytes);
			
			String authHeader = "Basic "+new String(encodeAuth);
			//System.out.println("INFO: "+authHeader);
			
			set(HttpHeaders.AUTHORIZATION,authHeader);
		}};
	}
}
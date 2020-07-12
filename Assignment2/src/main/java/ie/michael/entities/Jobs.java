package ie.michael.entities;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Jobs {
	

	@Id
	@GeneratedValue
	private int    	  jobId;
	private String 	  jobName;
	private String 	  jobDescription;
	private String 	  jobBestOffer;
	private String 	  jobBestBidder;
	private LocalDate jobPublished;
	private LocalDate jobValidTo;
	private int		  jobDaysLeft;
	private boolean   jobIsOpen;
	
	@ManyToOne
	private Users addedBy; 
	
	
	public Jobs(String jobName, String jobDescription, Users user) {
		this.jobName = jobName;
		this.jobDescription =jobDescription;
		jobPublished = LocalDate.now();
		jobValidTo = LocalDate.ofYearDay(jobPublished.getYear(), jobPublished.getDayOfYear()+20);
		jobBestOffer = "";
		jobBestBidder = "";
		jobIsOpen = true;
		jobDaysLeft = 20;
		addedBy = user;
	}
}

package ie.michael.domain;

import java.time.LocalDate;


import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Jobs {
	


	private int    	  jobId;
	private String 	  jobName;
	private String 	  jobDescription;
	private String 	  jobBestOffer;
	private String 	  jobBestBidder;
	private LocalDate jobPublished;
	private LocalDate jobValidTo;
	private int		  jobDaysLeft;
	private boolean   jobIsOpen;
	

	private Users addedBy; 
	
	
}

package ie.michael.forms;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;

@Data
public class AddBidForm {

	@PositiveOrZero
	@NotNull
	private int lowestBid;
	
	private int maximumBid;
	//private Jobs jobs;
	private int jobID;
	
}

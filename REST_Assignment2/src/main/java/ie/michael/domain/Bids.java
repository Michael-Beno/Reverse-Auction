package ie.michael.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bids {
	

	private int id;
	private int bidPrice;
	Jobs job;
	Users addedBy;
}

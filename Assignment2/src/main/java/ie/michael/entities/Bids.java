package ie.michael.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bids {
	
	

	@Id
	@GeneratedValue
	private int id;
	private int bidPrice;
	
	public Bids(int bidPrice, Jobs job, Users user) {
		this.bidPrice = bidPrice;
		this.job = job;
		this.addedBy = user;
	}
	@JsonIgnore
	@ManyToOne
	Jobs job;
	
	@ManyToOne
	Users addedBy;

	public void setUser(Users addedBy) {
		this.addedBy = addedBy;
		
	}
}

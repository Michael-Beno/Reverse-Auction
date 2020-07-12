package ie.michael.service;

import java.util.List;

import ie.michael.entities.Bids;


public interface BidService {

	void save(Bids bid);
	
	List<Bids> findAllByJobJobId(int id);

	void saveAndUpdate(Bids bid);

	String findLowestBid(int jobId);

}

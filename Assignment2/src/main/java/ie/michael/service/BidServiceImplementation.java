package ie.michael.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.michael.dao.BidsDao;
import ie.michael.entities.Bids;

@Service
public class BidServiceImplementation implements BidService{

	@Autowired
	BidsDao bidDao;
	
	@Override
	public List<Bids> findAllByJobJobId(int id) {
		return bidDao.findAllByJobJobId(id);
	}

	@Override
	public void save(Bids bid) {
		bidDao.save(bid);
		
	}

	@Override
	public void saveAndUpdate(Bids bid) {
		int jobId = bid.getJob().getJobId();
		int jobBestOffer = bid.getBidPrice();
		String jobBestBidder = bid.getAddedBy().getUserName();
		bidDao.save(bid);
		bidDao.updateLastBidder(jobId,jobBestBidder,jobBestOffer);
	}

	@Override
	public String findLowestBid(int jobId) {
		return bidDao.findLowestBid(jobId);
	}
	

}

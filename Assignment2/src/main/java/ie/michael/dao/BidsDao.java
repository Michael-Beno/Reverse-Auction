package ie.michael.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import ie.michael.entities.Bids;


public interface BidsDao extends JpaRepository<Bids, Integer>{


	List<Bids> findAllByJobJobId(int i);

	@Transactional
	@Query(value="SELECT MIN(b.bid_Price) FROM BIDS b where b.job_Job_Id = :jobId", nativeQuery = true)
	String findLowestBid( @Param("jobId")int jobId);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE jobs SET job_Best_Bidder = :jobBestBidder, job_Best_Offer = :jobBestOffer WHERE job_Id = :jobId", nativeQuery = true)
	void updateLastBidder( @Param("jobId")int jobId, @Param("jobBestBidder") String jobBestBidder, @Param("jobBestOffer") int jobBestOffer);


}

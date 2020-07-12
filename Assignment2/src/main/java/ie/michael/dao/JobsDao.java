package ie.michael.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ie.michael.entities.Jobs;

public interface JobsDao extends JpaRepository<Jobs, Integer>{

	List<Jobs> findAll();
	Jobs findJobsByJobId(int i);

	@Modifying
	@Transactional
	@Query(value="UPDATE jobs SET job_Best_Bidder = :jobBestBidder, job_Best_Offer = :jobBestOffer WHERE job_Id = :jobId", nativeQuery = true)
	void updateLastBidder( @Param("jobId")int jobId, @Param("jobBestBidder") String jobBestBidder, @Param("jobBestOffer") int jobBestOffer);
	
	@Modifying
	@Transactional
	@Query(value="UPDATE jobs SET job_days_left = (job_days_left)-1  WHERE job_days_left > 0;", nativeQuery = true)
	void updateAllDays();

}

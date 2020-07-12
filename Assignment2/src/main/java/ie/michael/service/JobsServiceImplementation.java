package ie.michael.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.michael.dao.BidsDao;
import ie.michael.dao.JobsDao;
import ie.michael.entities.Jobs;


@Service //no need with JPA
public class JobsServiceImplementation implements JobsService{
	
	@Autowired
	JobsDao jobsDao;
	@Autowired
	BidsDao bidsDao;

	@Override
	public List<Jobs> findAll() {
		return jobsDao.findAll();
	}

	@Override
	public void save(Jobs job) {
		jobsDao.save(job);
			
		
	}
	
	@Override
	public boolean existsByJobId(int id) {
		return jobsDao.existsById(id);
		
	}

	@Override
	public Jobs findJobsByJobId(int id) {
		return jobsDao.findJobsByJobId(id);
	}



}

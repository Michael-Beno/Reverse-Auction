package ie.michael.service;

import java.util.List;

import ie.michael.entities.Jobs;

public interface JobsService {
	List<Jobs> findAll();

	void save(Jobs job);

	boolean existsByJobId(int i);

	Jobs findJobsByJobId(int i);


}

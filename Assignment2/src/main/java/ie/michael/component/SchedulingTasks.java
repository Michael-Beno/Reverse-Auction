package ie.michael.component;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ie.michael.dao.JobsDao;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SchedulingTasks {
	
	@Autowired
	private JobsDao jobDao;

	// At 00:00 every midnight....
	@Scheduled(cron = "0 0 0 * * *")
	public void closeProjects()
	{
		jobDao.updateAllDays();
		log.info("Midnight update");
	}
	
	
	// Every 60000ms = every minute or  86400000 every 24h
	@Scheduled(fixedRate = 60000)
	public void listProjects() {
		jobDao.updateAllDays();
		log.info("Update every minute");
	}
}

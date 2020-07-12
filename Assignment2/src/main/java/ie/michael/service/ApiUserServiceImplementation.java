package ie.michael.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.michael.dao.ApiUserDao;
import ie.michael.entities.ApiUser;


@Service
public class ApiUserServiceImplementation implements ApiUserService{

	@Autowired
	private ApiUserDao myApiUserDao;
	
	@Override
	public ApiUser save(ApiUser newApiUser) {
		if (myApiUserDao.existsByUserEmail(newApiUser.getUserEmail()))
				return null;
		return myApiUserDao.save(newApiUser);
	}	
	
}

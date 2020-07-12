package ie.michael.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.michael.dao.UsersDao;
import ie.michael.entities.Users;

@Service //no need with JPA
public class UsersServiceImplementation implements UsersService{
	@Autowired
	UsersDao usersDao;
	
	@Override
	public List<Users> getAllUsers() {
		return usersDao.findAll();
	}

	@Override
	public Users getUserByName(String userName) {
		if(usersDao.existsByUserName(userName))
			return usersDao.findUserByUserName(userName);
		return null;
	}

	@Override
	public void save(Users user) {
		 usersDao.save(user);
	}

	@Override
	public Users findUserByEmailAndPassword(String userEmail, String userPassword) {
		return usersDao.findUsersByUserEmailAndUserPassword(userEmail, userPassword);
	}

	@Override
	public Users findByEmail(String name) {
		return usersDao.findByUserEmail(name);
	}

}

package ie.michael.service;

import java.util.List;

import ie.michael.entities.Users;

public interface UsersService {
	List<Users> getAllUsers();
	Users getUserByName(String userName);
	void save(Users user);
	Users findUserByEmailAndPassword(String userEmail, String userPassword);
	Users findByEmail(String name);

}

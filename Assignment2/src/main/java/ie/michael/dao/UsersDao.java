package ie.michael.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.michael.entities.Users;

public interface UsersDao extends JpaRepository<Users, Integer>{
	boolean existsByUserName(String userName);
	Users findUserByUserName(String userName);
	Users findByUserEmail(String email);
	boolean existsByUserEmailAndUserPassword(String userEmail, String userPassword);
	Users findUsersByUserEmailAndUserPassword(String userEmail, String userPassword);
	
}

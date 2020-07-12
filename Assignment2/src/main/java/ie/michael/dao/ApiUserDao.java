package ie.michael.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.michael.entities.ApiUser;

public interface ApiUserDao extends JpaRepository<ApiUser, Integer> {
	boolean existsByUserEmail(String email);
}

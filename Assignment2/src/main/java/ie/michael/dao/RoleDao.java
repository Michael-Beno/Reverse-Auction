package ie.michael.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import ie.michael.entities.Role;


public interface RoleDao extends JpaRepository<Role, Integer> {
	boolean existsByUserEmail(String email);
}

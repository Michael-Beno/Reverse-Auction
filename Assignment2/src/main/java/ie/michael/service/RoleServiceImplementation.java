package ie.michael.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.michael.dao.RoleDao;
import ie.michael.entities.Role;


@Service
public class RoleServiceImplementation implements RoleService{

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public Role save(Role newRole) {
		if (roleDao.existsByUserEmail(newRole.getUserEmail()))
			return null;
		return roleDao.save(newRole);
	}
	
}

package com.talentica.resam.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talentica.resam.dao.PermissionDAO;
import com.talentica.resam.dao.RoleDAO;
import com.talentica.resam.entity.Permission;
import com.talentica.resam.exceptions.InvalidArgumentException;

@Service
public class UserAuthorizationServiceImpl implements UserAuthorizationService {
	
	@Autowired
	private PermissionDAO permissionDAO;
	
	@Autowired
	private RoleDAO roleDAO;

	public void addPermission(Permission permission) {
		try {
			permissionDAO.addPermission(permission);
		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		}
		
	}

	public void changePermission(Permission permission) {
		
		permissionDAO.updatePermission(permission);
		
	}

}

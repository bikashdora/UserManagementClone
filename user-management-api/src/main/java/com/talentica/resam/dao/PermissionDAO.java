package com.talentica.resam.dao;

import java.util.List;

import com.talentica.resam.base.dao.BaseDAO;
import com.talentica.resam.entity.Permission;
import com.talentica.resam.exceptions.InvalidArgumentException;
import com.talentica.resam.exceptions.PermissionNotFoundException;

public interface PermissionDAO extends BaseDAO<Permission, Long>{
	
	 public List<Permission> getPermissions();

	 public Permission getPermission(String usersPermission) throws PermissionNotFoundException;
	 
	 public void addPermission(Permission permission) throws InvalidArgumentException;
	 
	 public void deletePermission(String permission);
	 
	 public void updatePermission(Permission permission);
}



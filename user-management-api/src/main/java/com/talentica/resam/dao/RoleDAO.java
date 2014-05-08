package com.talentica.resam.dao;

import java.util.List;

import com.talentica.resam.base.dao.BaseDAO;
import com.talentica.resam.entity.Role;
import com.talentica.resam.entity.User;
import com.talentica.resam.exceptions.RoleNotSupportedException;

public interface RoleDAO extends BaseDAO<Role, Long>{

	
	public Role getRoleByName(String roleName) throws RoleNotSupportedException;
	
	
}

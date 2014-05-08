package com.talentica.resam.api;

import com.talentica.resam.entity.Permission;

public interface UserAuthorizationService {

	public void addPermission(Permission permission);

	public void changePermission(Permission permission);
		
}

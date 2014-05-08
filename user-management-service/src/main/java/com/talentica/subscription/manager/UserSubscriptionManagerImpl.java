package com.talentica.subscription.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talentica.resam.api.UserAuthorizationService;
import com.talentica.resam.api.UserManagementService;
import com.talentica.resam.entity.Permission;
import com.talentica.resam.entity.Role;
import com.talentica.resam.entity.User;
import com.talentica.resam.entity.UserDetail;

@Service
public class UserSubscriptionManagerImpl implements UserSubscriptionManager {
	
	@Autowired
	private UserManagementService userManagementService;
	
	@Autowired
	private UserAuthorizationService UserAuthorizationService;

	@Override
	public void signUp(String userName, String email, String password,
			String mobile, String firstName, String lastName) {
		// TODO Auto-generated method stub
		User user=new User();	
		user.setUserName(userName);
		user.setEmail(email);
		user.setPassword(password);	
		
		
		UserDetail userDetail=new UserDetail();
		userDetail.setFirstName(firstName);
		userDetail.setLastName(lastName);
		userDetail.setGender("Male");
		
		Permission perm=new Permission();
		perm.setPermission("Few");
		perm.setPermissionType("Normal");
		
		List<Permission> permissions=new ArrayList<Permission>();
		permissions.add(perm);
		
		Role role=new Role();
		role.setRoleName("USER");
		role.setRoleType("2");
		role.setPermissions(permissions);
		
		List<Role> roles=new ArrayList<Role>();
		roles.add(role);		
		userManagementService.signUpUser(user, roles);		
		
	}

	@Override
	public void changePassword(String userName, String oldPassword,String newpassword) {
			userManagementService.changePassword(userName,oldPassword,newpassword);
		
	}

	@Override
	public User getUser(String userName) {
		return userManagementService.getUserByUsername(userName);
	}
	

	@Override
	public void deleteuser(String userName) {
		User user = userManagementService.getUserByUsername(userName);
		userManagementService.deleteUser(user);
		
	}

	@Override
	public User getUserByEmail(String email) {
		return userManagementService.getUserByEmail(email);
		
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return userManagementService.updateUser(user);
		
	}

	@Override
	public User signInUser(String userName,String password) {
		// TODO Auto-generated method stub
		return userManagementService.signInUser(userName,password);
	}

	@Override
	public void resetPassword(String userEmail) {
		userManagementService.resetPassword(userEmail);
		
	}

	@Override
	public void addPermission(Permission permission) {
		UserAuthorizationService.addPermission(permission);
		
	}

	@Override
	public void changePermission(Permission permission) {
		UserAuthorizationService.changePermission(permission);
		
	}

	
}

package com.talentica.resam.api;

import java.util.List;

import com.talentica.resam.entity.Role;
import com.talentica.resam.entity.User;
import com.talentica.resam.exceptions.InvalidCredentialException;

public interface UserManagementService {

	public void signUpUser( User user,List<Role> roles);

	public User signInUser(String userName,String password);

	public void resetPassword(String userName, String password);
	public void resetPassword(String userName);

	public void changePassword(String userName, String oldPassword,String newPassword);
	
	public User getUser(Long id);
	
	public User createUser(User user, List<Role> roles);
	
	public User getUserByEmail(String email);
	
	public User getUserByUsername(String userName);
	
	public void deleteUser(User user) ;
	
	public User updateUser(User user);

	


}

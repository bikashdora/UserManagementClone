package com.talentica.resam.api;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talentica.resam.dao.UserDAO;
import com.talentica.resam.entity.Role;
import com.talentica.resam.entity.User;
import com.talentica.resam.exceptions.InvalidCredentialException;
import com.talentica.resam.exceptions.SignupTaskException;
import com.talentica.resam.exceptions.UserAlreadyExistsException;
import com.talentica.resam.exceptions.UserNotFoundException;

@Service
public class UserManagementServiceImpl implements UserManagementService {
	
	@Autowired
	private UserDAO userDAO;

	public void signUpUser(User user, List<Role> roles ) {
		try {
			userDAO.signUp(user,roles);
		} catch (SignupTaskException e) {
			e.printStackTrace();
		}
	}

	public User signInUser(String userName,String password)  {

		User user = null;
		try {
			user = userDAO.signIn(userName,password);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}
		catch (InvalidCredentialException e) {
			e.printStackTrace();
		} 
		return user;
	}

	public void resetPassword(String userName, String password) {
		
		try {
			userDAO.resetPassword(userName, password);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void changePassword(String userName, String oldpassword,String newPassword) {
		
		try {
			userDAO.changePassword(userName, oldpassword,newPassword);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidCredentialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public User getUser(Long id) {
		return userDAO.getUser(id);
	}
	
	public User getUserByEmail(String email){
		return userDAO.findUserByEmail(email);
	}
	
	public User getUserByUsername(String userName){
		return userDAO.findUserByUsername(userName);
	}

	public User createUser(User user, List<Role> roles) {

		User newUser = null;
		try {
			newUser = userDAO.createUser(user, roles);
		} catch (UserAlreadyExistsException e) {
			e.printStackTrace();
		}

		return newUser;
	}
	
	public User updateUser(User user) {

	try {
			userDAO.updateUser(user);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
		}
		return user;

	}

	public void deleteUser(User user) {
		
	try {
			userDAO.deleteUser(user);
		} catch (UserNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}

	public void resetPassword(String userEmail) {
		try {
			userDAO.resetPassword(userEmail);
		} catch (UserNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}	
	
}

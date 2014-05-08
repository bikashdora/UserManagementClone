package com.talentica.resam.dao;

import java.util.List;

import com.talentica.resam.base.dao.BaseDAO;
import com.talentica.resam.entity.Role;
import com.talentica.resam.entity.User;
import com.talentica.resam.exceptions.InvalidCredentialException;
import com.talentica.resam.exceptions.SignupTaskException;
import com.talentica.resam.exceptions.UserAlreadyExistsException;
import com.talentica.resam.exceptions.UserNotFoundException;

public interface UserDAO extends BaseDAO<User, Long> {

	public User signIn(String userName,String password) throws  UserNotFoundException, InvalidCredentialException;
	
	public User createUser(User user, List<Role> roles) throws UserAlreadyExistsException;
	
	public User getUser(Long id);

	User signUp(User user, List<Role> roles) throws SignupTaskException;
	
	public void resetPassword(String userName, String password) throws UserNotFoundException;
	
	public void resetPassword(String userEmail) throws UserNotFoundException;
	
	public void changePassword(String userName, String oldPassword,String newPassword) throws UserNotFoundException, InvalidCredentialException;
	
	public User findUserByEmail(String userName);
	
	public User findUserByUsername(String userName);
	
	public void deleteUser(User user) throws UserNotFoundException;
	
	public User updateUser(User updateUser) throws UserNotFoundException;
}

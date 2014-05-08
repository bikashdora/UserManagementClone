package com.talentica.subscription.manager;

import com.talentica.resam.entity.Permission;
import com.talentica.resam.entity.User;


public interface UserSubscriptionManager {
	
public void signUp(String userName,String email,String password,String mobile,String firstName,String lastName);
public void changePassword (String userName,String oldpassword,String newPassword);
public User getUser(String userName);
public void deleteuser(String userName);
public User getUserByEmail(String email);
public User updateUser(User user);
public User signInUser(String userName,String password);
public void resetPassword(String userEmail);
public void addPermission(Permission permission);
public void changePermission(Permission permission);
}

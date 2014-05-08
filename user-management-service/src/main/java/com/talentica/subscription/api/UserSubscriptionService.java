package com.talentica.subscription.api;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talentica.resam.entity.Permission;
import com.talentica.resam.entity.User;
import com.talentica.subscription.manager.UserSubscriptionManager;

@Service
@Path("/restService")
public class UserSubscriptionService {

	@Autowired
	UserSubscriptionManager UserSubscriptionManager;

	@Path("createUser")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public User signUp(@FormParam("userName") String userName,
			@FormParam("email") String email,
			@FormParam("password") String password,
			@FormParam("mobile") String mobile,
			@FormParam("firstName") String firstName,
			@FormParam("lastName") String lastName) {
		// preconditions
		checkNotNull(userName);
		checkNotNull(email);
		checkNotNull(mobile);
		checkNotNull(firstName);
		checkNotNull(lastName);
		checkNotNull(password);
		UserSubscriptionManager.signUp(userName, email, password, mobile,
				firstName, lastName);
		return UserSubscriptionManager.getUser(userName);
	}

	@Path("changePassword")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public void changePassword(@FormParam("userName") String userName,
			@FormParam("oldPassword") String oldPassword,
			@FormParam("newPassword") String newPassword) {

		// preconditions
		checkNotNull(userName);
		checkNotNull(oldPassword);
		checkNotNull(newPassword);

		// fetch user

		UserSubscriptionManager.changePassword(userName, oldPassword,
				newPassword);

	}
	
	@Path("updateUser")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)	
	public User updateUser(User user) {
		// check profile against current user
		/*if (!profile.getName().equals(getUserPrincipal())) {
			throw new SubscriptionError(new UserNotFoundException(
					profile.getName()));
		}*/

		// save profile
		checkNotNull(user.getUserName());
		return UserSubscriptionManager.updateUser(user);
		
	}
	
	
	@Path("signInUser")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)	
	public User signInUser(@FormParam("userName")String userName,@FormParam("password")String password) {
		// check profile against current user
		/*if (!profile.getName().equals(getUserPrincipal())) {
			throw new SubscriptionError(new UserNotFoundException(
					profile.getName()));
		}*/

		// save profile
		checkNotNull(userName);
		checkNotNull(password);
		return UserSubscriptionManager.signInUser(userName,password);
		
	}
	
	
	@Path("resetPassword")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public void resetPassword(@FormParam("userEmail") String userEmail) {
		// preconditions
		checkNotNull(userEmail);
		UserSubscriptionManager.resetPassword(userEmail);
		
	}
	
	@Path("getUser")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@FormParam("userName")String userName) {
		checkNotNull(userName);
		return UserSubscriptionManager.getUser(userName);
	}
	
	@Path("deleteUser")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteUser(@FormParam("userName")String userName){
		checkNotNull(userName);
		 UserSubscriptionManager.deleteuser(userName);
		
	}
	
	@Path("getUserbyEmail")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public User getUserByEmail(@FormParam("email")String email) {
		checkNotNull(email);
		return UserSubscriptionManager.getUser(email);
	}
	
	@Path("addPermission")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void addPermission(Permission permission){
		checkNotNull(permission);
		 UserSubscriptionManager.addPermission(permission);
		
	}
	
	@Path("changePermission")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void changePermission(Permission permission){
		checkNotNull(permission);
		 UserSubscriptionManager.changePermission(permission);
		
	}
	

	
}
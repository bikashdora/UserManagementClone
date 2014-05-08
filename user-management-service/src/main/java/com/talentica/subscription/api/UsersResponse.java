/**
 * 
 */
package com.talentica.subscription.api;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.talentica.resam.entity.User;



/**
 * @author Siva
 *
 */
@XmlRootElement
public class UsersResponse {

	private User user;
	public void setUser(User user) {
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	
	public String toString(String msg) {
		
		return msg;
	}
	
	private List<User> userList;
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	
}

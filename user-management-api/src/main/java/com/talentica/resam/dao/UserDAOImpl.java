package com.talentica.resam.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.talentica.resam.auth.encryption.MD5Generator;
import com.talentica.resam.auth.encryption.RandomPasswordGenerator;
import com.talentica.resam.base.dao.BaseDAOImpl;
import com.talentica.resam.entity.Role;
import com.talentica.resam.entity.User;
import com.talentica.resam.exceptions.InvalidCredentialException;
import com.talentica.resam.exceptions.SignupTaskException;
import com.talentica.resam.exceptions.UserAlreadyExistsException;
import com.talentica.resam.exceptions.UserNotFoundException;

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class UserDAOImpl extends BaseDAOImpl<User, Long> implements UserDAO {

	@PersistenceContext
	private EntityManager em;

	public User signIn(String userName,String password)
			throws UserNotFoundException, InvalidCredentialException{
		User user = null ;
		user = findUserByUsername(userName);
		if (null == user) {
			throw new UserNotFoundException(userName);
		}
		if(!verifyCredentials(userName, password))
			throw new InvalidCredentialException("Wrong user/password");
		return user;
	}

	public User createUser(User user, List<Role> roles)
			throws UserAlreadyExistsException {

		User newUser = new User();

		try {
			if (userExists(user.getUserName())) {
				throw new UserAlreadyExistsException(user.getUserName());
			} else {
				newUser.setCreated(new Date());
				newUser.setActive(true);
				newUser.setUserName(user.getUserName());
				newUser.setIsFirstLogin(true);
				newUser.setEmail(user.getEmail());
				String md5_password = MD5Generator.MD5(user.getPassword());
				newUser.setPassword(md5_password);
				newUser.setRoles(roles);
				newUser.setUserDetail(user.getUserDetail());
				em.persist(newUser);
			}
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return newUser;
	}

	public User getUser(Long id) {

		User user=findById(id);
//		try {
//			TypedQuery<User> query = em.createQuery("from User where id = ?1",
//					User.class);
//			query.setParameter(1, id);
//			user = query.getSingleResult();
//		} catch (NoResultException e) {
//			e.printStackTrace();
//		}

		return user;
	}
	
	
	 public User findUserByUsername(String userName)  {
			TypedQuery<User> query = em.createQuery("from User where userName = ?1",
					User.class);
			query.setParameter(1, userName);
			User user = null;
			try {
				user = query.getSingleResult();
			} catch (NoResultException e) {
				
			}
			return user;
		}
	

	public User findUserByEmail(String email) {
		TypedQuery<User> query = em.createQuery("from User where email = ?1",
				User.class);
		query.setParameter(1, email);
		User user = null;
		try {
			user = query.getSingleResult();
		} catch (NoResultException e) {
		}
		return user;
	}

	public boolean userExists(String userName) throws UserNotFoundException {
		User user = findUserByUsername(userName);
		return user == null ? false : true;
	}

	public User signUp(User user, List<Role> roles) throws SignupTaskException {
		User newUser = null;
		if (null != user) {
			try {
				newUser = createUser(user, roles);
			} catch (UserAlreadyExistsException e) {
				e.printStackTrace();
			}
		}
		return newUser;
	}

	public void resetPassword(String userName, String password)
			throws UserNotFoundException {

		User user = findUserByUsername(userName);
		if (user == null) {
			throw new UserNotFoundException(userName);
		}

		String md5_password = MD5Generator.MD5(password);
		user.setPassword(md5_password);
		user.setPassResetFlag(true);
		save(user);
	}
	
	/*Newly Added-resam*/
	public void resetPassword(String userEmail)
			throws UserNotFoundException {

		User user = findUserByEmail(userEmail);
		if (user == null) {
			throw new UserNotFoundException(userEmail);
		}

		//users.resetPassword(username, passwords.generate(username));
		String randomPassword = new RandomPasswordGenerator().generate(user.getEmail());	
		user.setPassword(randomPassword);
		user.setPassResetFlag(true);
		save(user);
	}

	public void changePassword(String userName, String oldPassword,String newPassword)
			throws UserNotFoundException, InvalidCredentialException {
		User user = findUserByUsername(userName);
		if (user == null) {
			throw new UserNotFoundException(userName);
		}	
			if(verifyCredentials(userName,oldPassword))
			{
				String md5_password = MD5Generator.MD5(newPassword);
				user.setPassword(md5_password);
				user.setPassResetFlag(false);//User Changed password ,so resetting the resetflag.
				save(user);
			}
			else
				throw new InvalidCredentialException(userName);
		

	}
	
	public void deleteUser(User user) throws UserNotFoundException {

		User userToBeDeleted = findUserByUsername(user.getUserName());
		if (userToBeDeleted == null) {
			throw new UserNotFoundException(user.getUserName());
		} else {
			delete(userToBeDeleted);
		}
	}
	
	public User updateUser(User updateUser) throws UserNotFoundException{
		User userToUpdate = findUserByUsername(updateUser.getUserName());
		if (userToUpdate == null) {
			throw new UserNotFoundException(updateUser.getUserName());
		}else {
			save(updateUser);
		}
		
		return updateUser;
	}
	
	public boolean verifyCredentials(String username,String password) throws UserNotFoundException
	{
		User user = findUserByUsername(username);
		if(user==null)
			throw new UserNotFoundException(username);
			if(user.isPassResetFlag())
				return user.getPassword().equals(password);//If resetflag true check against the password in db
			
		return user.getPassword().equals(MD5Generator.MD5(password));
		
	}
	

}

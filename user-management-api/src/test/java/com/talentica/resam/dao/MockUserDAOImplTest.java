package com.talentica.resam.dao;

import org.junit.Test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;

import org.mockito.Mockito;

import com.talentica.resam.entity.User;
import com.talentica.resam.entity.UserDetail;

public class MockUserDAOImplTest {

	private UserDAO userDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		userDAO = Mockito.mock(UserDAOImpl.class);
	}

	@After
	public void tearDown() throws Exception {
		userDAO = null;
	}

	@Test
	public void testSaveUser() throws Exception {
		User user = new User();
		UserDetail usrDetail = new UserDetail();
		usrDetail.setFirstName("Mark");
		user.setUserDetail(usrDetail);

		userDAO.save(user);
		
				// Verify if save user was invoked on userDAO with given 'user' object.
		Mockito.verify(userDAO).save(user);

		// Verify with Argument Matcher
		Mockito.verify(userDAO).save(Mockito.<User> any());
	}

	@Test
	public void testCountNumberOfInteractions() throws Exception {

		userDAO.findById(1L);

		// Verify the number of interactions with mock
		Mockito.verify(userDAO, Mockito.times(1)).findById(1L);

		// There was only one interaction with userDAO
		Mockito.verifyNoMoreInteractions(userDAO);
	}

	@Test(expected = RuntimeException.class)
	public void testThrowException() throws Exception {

		// throw an exception on call to userDAO
		Mockito.doThrow(new RuntimeException()).when(userDAO)
				.save(Mockito.<User> any());

		// this will throw RunTime exception
		userDAO.save(Mockito.mock(User.class));

	}

	@Test
	public void testFindUserById() throws Exception {

		// Stub the value that will returned on call to userDAO.findById
		User stubUser = new User();
		UserDetail usrDetail = new UserDetail();
		usrDetail.setFirstName("Mark");
		stubUser.setUserDetail(usrDetail);

		Mockito.when(userDAO.findById(1L)).thenReturn(stubUser);

		// make the call
		User user = userDAO.findById(1L);

		// Verify if findById method was invoked on userDAO call
		Mockito.verify(userDAO).findById(1L);

		Assert.assertEquals("Mark", user.getUserDetail().getFirstName());
	}

}

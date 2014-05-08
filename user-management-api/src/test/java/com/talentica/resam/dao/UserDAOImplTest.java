package com.talentica.resam.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.citruspay.base.BaseDAOTest;
import com.talentica.resam.entity.User;
import com.talentica.resam.entity.UserAddressDetail;
import com.talentica.resam.entity.UserContactDetail;
import com.talentica.resam.entity.UserDetail;

public class UserDAOImplTest extends BaseDAOTest {

	@Autowired
	private UserDAO userDAO;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {
		User user = new User();
		user.setEmail("john.doe@test.com");
		
		UserDetail usrDetail = new UserDetail();
		usrDetail.setFirstName("John");
		user.setUserDetail(usrDetail);
		
		List<UserAddressDetail> addressDetail = new ArrayList<UserAddressDetail>();
		UserAddressDetail uad = new UserAddressDetail();
		uad.setAddressCity("Florida");
		addressDetail.add(uad);
		usrDetail.setAddressDetail(addressDetail);
		
		List<UserContactDetail> contactDetail = new ArrayList<UserContactDetail>();
		UserContactDetail ucd = new UserContactDetail();
		ucd.setContactNo("0123456789");
		contactDetail.add(ucd);
		usrDetail.setContactDetail(contactDetail);
		
		
		userDAO.save(user);
		User savedUser = userDAO.findById(1L);
		Assert.assertEquals("John", savedUser.getUserDetail().getFirstName());
	}

	@Test
	public void testFindById() {
		User user = userDAO.findById(1L);
		Assert.assertEquals("John", user.getUserDetail().getFirstName());
	}

	@Test
	public void testFindAll() {
		List<User> users = userDAO.findAll();
		Assert.assertTrue(!users.isEmpty());
	}

	@Test
	public void testDelete() {
		User savedUser = userDAO.findById(1L);
		userDAO.delete(savedUser);
		User user = userDAO.findById(1L);
		Assert.assertTrue(user == null);
	}
	
	@Test
	public void testLogin(){
		
		User user = new User();
		user.setEmail("bond@test.com");
		
		UserDetail usrDetail = new UserDetail();
		usrDetail.setFirstName("John");
		user.setUserDetail(usrDetail);
		
		List<UserAddressDetail> addressDetail = new ArrayList<UserAddressDetail>();
		UserAddressDetail uad = new UserAddressDetail();
		uad.setAddressCity("Florida");
		addressDetail.add(uad);
		usrDetail.setAddressDetail(addressDetail);
		
		List<UserContactDetail> contactDetail = new ArrayList<UserContactDetail>();
		UserContactDetail ucd = new UserContactDetail();
		ucd.setContactNo("0123456789");
		contactDetail.add(ucd);
		usrDetail.setContactDetail(contactDetail);
		
		
		userDAO.save(user);
		
		//userDAO.createUser(principal, password, roles)(user);
	}

}

package com.talentica.resam.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.citruspay.base.BaseDAOTest;
import com.talentica.resam.entity.Permission;
import com.talentica.resam.entity.Role;
import com.talentica.resam.entity.User;
import com.talentica.resam.entity.UserAddressDetail;
import com.talentica.resam.entity.UserContactDetail;
import com.talentica.resam.entity.UserDetail;
import com.talentica.resam.exceptions.InvalidArgumentException;

public class UserRoleIntegrationTest extends BaseDAOTest {

	@Autowired
	private RoleDAO roleDAO;

	@Autowired
	private PermissionDAO permissionDAO;

	@Autowired
	private UserDAO userDAO;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSaveUser() throws InvalidArgumentException {

		Permission permission = new Permission();
		permission.setPermission("Test");
		permission.setPermissionType("Test Pemission");
		
		Permission permission2 = new Permission();
		permission.setPermission("Test2");
		permission.setPermissionType("Test Pemission2");
		
		permissionDAO.addPermission(permission);
		//Permission savedPermission = permissionDAO.save(permission);

		Role role = new Role();
		role.setRoleName("Test Role");
		role.setRoleType("User Role");
		Role savedRole = roleDAO.save(role);

		List<Permission> permissions = new ArrayList<Permission>();
		//permissions.add(savedPermission);
		savedRole.setPermissions(permissions);

		roleDAO.save(role);

		Role userRole = roleDAO.findById(1L);

		User user = new User();
		user.setEmail("john.doe@test.com");

		UserDetail usrDetail = new UserDetail();
		usrDetail.setFirstName("John");
		user.setUserDetail(usrDetail);

		List<UserAddressDetail> addressDetail = new ArrayList<UserAddressDetail>();
		UserAddressDetail uad = new UserAddressDetail();
		uad.setUserDetail(usrDetail);
		uad.setAddressCity("Florida");
		addressDetail.add(uad);
		usrDetail.setAddressDetail(addressDetail);

		List<UserContactDetail> contactDetail = new ArrayList<UserContactDetail>();
		UserContactDetail ucd = new UserContactDetail();
		ucd.setUserDetail(usrDetail);
		ucd.setContactNo("0123456789");
		contactDetail.add(ucd);
		usrDetail.setContactDetail(contactDetail);
		User savedUser = userDAO.save(user);

		List<Role> roles = new ArrayList<Role>();
		roles.add(userRole);
		savedUser.setRoles(roles);
		userDAO.save(savedUser);

		User resamUser = userDAO.findById(1L);
		Assert.assertEquals(resamUser.getEmail(), "john.doe@test.com");

	}
	
	@Test
	public void testFindUserById() {
		User resamUser = userDAO.findById(1L);
		Assert.assertEquals(resamUser.getEmail(), "john.doe@test.com");
	}
	
}

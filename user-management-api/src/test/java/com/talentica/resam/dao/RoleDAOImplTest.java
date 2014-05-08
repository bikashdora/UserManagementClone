package com.talentica.resam.dao;

import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.citruspay.base.BaseDAOTest;
import com.talentica.resam.entity.Role;

public class RoleDAOImplTest extends BaseDAOTest {

	@Autowired
	private RoleDAO roleDAO;


	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {
		Role role = new Role();
		role.setRoleName("Test Role");
		role.setRoleType("User Role");
		Role savedRole = roleDAO.save(role);
		Assert.assertEquals("Test Role", savedRole.getRoleName());
	}

	@Test
	public void testFindById() {
		Role savedRole = roleDAO.findById(1L);
		Assert.assertEquals("Test Role", savedRole.getRoleName());
	}
}

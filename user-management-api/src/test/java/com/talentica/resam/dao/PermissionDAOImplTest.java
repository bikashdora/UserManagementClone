package com.talentica.resam.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.citruspay.base.BaseDAOTest;
import com.talentica.resam.entity.Permission;
import com.talentica.resam.exceptions.InvalidArgumentException;

public class PermissionDAOImplTest extends BaseDAOTest {

	@Autowired
	private PermissionDAO permissionDAO;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() throws InvalidArgumentException {
		Permission permission = new Permission();
		permission.setPermission("Test");
		permission.setPermissionType("Test Pemission");
		
		permissionDAO.addPermission(permission);

		Assert.assertEquals("Test", permission.getPermission());
	}

	@Test
	public void testFindById() {
		Permission savedPermission = permissionDAO.findById(1L);
		Assert.assertEquals("Test", savedPermission.getPermission());
	}
	
	

}

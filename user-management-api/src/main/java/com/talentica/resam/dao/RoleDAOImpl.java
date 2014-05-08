package com.talentica.resam.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.talentica.resam.base.dao.BaseDAOImpl;
import com.talentica.resam.entity.Permission;
import com.talentica.resam.entity.Role;
import com.talentica.resam.exceptions.InvalidArgumentException;
import com.talentica.resam.exceptions.RoleNotSupportedException;

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class RoleDAOImpl extends BaseDAOImpl<Role, Long> implements RoleDAO {

	@PersistenceContext
	private EntityManager em;

	public List<Role> getAllRoles() {
		TypedQuery<Role> query = em.createQuery("from Role", Role.class);
		return query.getResultList();
	}

	public Permission findByRole(Role role) {
		TypedQuery<Permission> query = em.createQuery(
				"from RolePermission where role=?1", Permission.class);
		query.setParameter(1, role);
		List<Permission> results = query.getResultList();
		if (results.isEmpty()) {
			return null;
		} else {
			return results.get(0);
		}
	}

	public Role getRoleByName(String roleName)
			throws RoleNotSupportedException {

		TypedQuery<Role> query = em.createQuery(
				" from Role where roleName =?1", Role.class);
		query.setParameter(1, roleName);

		if (query.getResultList().size() == 0) {
			throw new RoleNotSupportedException("Role [" + roleName
					+ "] not found");
		} else {

			Role role = (Role) query.getResultList().get(0);
			return role;
		}
	}

	
	 public void addRole(Role newRole) throws InvalidArgumentException {
	        try {
	            Role role = getRoleByName(newRole.getRoleName());
	            if (null!=role) {
	            	throw new InvalidArgumentException("Role Already Exist");
	            }
	        } catch (RoleNotSupportedException e) {
	            save(newRole);
	        }
	    }

}

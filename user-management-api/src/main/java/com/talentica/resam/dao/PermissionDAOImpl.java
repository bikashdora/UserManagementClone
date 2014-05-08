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
import com.talentica.resam.exceptions.InvalidArgumentException;
import com.talentica.resam.exceptions.PermissionNotFoundException;

@Repository
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class PermissionDAOImpl extends BaseDAOImpl<Permission, Long> implements
		PermissionDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	 public List<Permission> getPermissions() {
		 TypedQuery<Permission> query = em.createQuery("from Permission",
				 Permission.class);
			return query.getResultList();
	    }
	 
	 public Permission getPermission(String usersPermission) throws PermissionNotFoundException {
	       
		 TypedQuery<Permission> query= em.createQuery(" from Permission where permission =?1" , Permission.class);
		 query.setParameter(1, usersPermission);
	
		 if (query.getResultList().size() == 0 ) {
	            throw new PermissionNotFoundException("Permission [" + usersPermission + "] not found");
	        } else {
	            
	            List<Permission> list = (List<Permission>)query.getResultList();
	            Permission permObject = (Permission) list.get(0);
	 
	            return permObject;
	        }
	    }
	 
	 public void addPermission(Permission permission) throws InvalidArgumentException {
	        try {
	            Permission permCheck = getPermission(permission.getPermission());
	            if (null!=permCheck) {
	            	throw new InvalidArgumentException("Permission Already Exist");
	            }
	        } catch (PermissionNotFoundException e) {
	            save(permission);
	        }
	    }
	 
	 public void deletePermission(String permission)  {
	        Permission existingPermission;
			try {
				existingPermission = getPermission(permission);
				 if (permission != null) {
			           delete(existingPermission);
			        }
			} catch (PermissionNotFoundException e) {
				e.printStackTrace();
			}
	    }
	 
	  public void updatePermission(Permission permission)  {
	        Permission permissionToUpdate=null;
			try {
				permissionToUpdate = getPermission(permission.getPermission());
				permissionToUpdate.setPermission(permission.getPermission());
		        permissionToUpdate.setPermissionType(permission.getPermissionType());
		        em.merge(permissionToUpdate);
			} catch (PermissionNotFoundException e) {
				
				e.printStackTrace();
			}
	        
	    }
	 
	 
}

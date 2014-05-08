package com.talentica.resam.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.talentica.resam.base.entity.BaseDomain;

@Entity
@Table(name = "user_role")
public class UserRole extends BaseDomain {

	private static final long serialVersionUID = 5193671310312635636L;

	@EmbeddedId
	private UserRolePK userRolePK;

	public UserRolePK getUserRolePK() {
		return userRolePK;
	}

	public void setUserRolePK(UserRolePK userRolePK) {
		this.userRolePK = userRolePK;
	}

}

package com.talentica.resam.auth.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.talentica.resam.entity.Role;
import com.talentica.resam.entity.User;

public class ResamUserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 4896807613985768963L;
	private User user;
	private String displayName;
	
	public ResamUserDetailsImpl(User user, String displayName) {
		this.user = user;
		this.displayName = displayName;
	}
	
	public ResamUserDetailsImpl() {
		
	}

	public String getPassword() {
		return user.getPassword();
	}

	public String getUsername() {
		return user.getEmail();
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public Collection<GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		for(final Role auth: user.getRoles()) {
			grantedAuthorities.add(new GrantedAuthority(){
				private static final long serialVersionUID = -8792378482280241300L;

				public String getAuthority() {
					return auth.getPermissions().toString();
				}
			});
		}
		return grantedAuthorities;
	}

	public Long getUserId(){
		return user.getId();
	}

	public User getUser() {
		return user;
	}

	public boolean isAccountNonExpired() {
		
		return false;
	}

	public boolean isAccountNonLocked() {
		
		return false;
	}

	public boolean isCredentialsNonExpired() {
		
		return false;
	}

	public boolean isEnabled() {
		
		return false;
	}
	
}

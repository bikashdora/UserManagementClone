package com.talentica.resam.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.talentica.resam.base.entity.BaseDomain;

@Entity
@Table(name = "user_detail")
public class UserDetail extends BaseDomain {

	private static final long serialVersionUID = -7068033368536937190L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;

	private String lastName;
	
	private String gender;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userDetail")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<UserAddressDetail> addressDetail;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userDetail")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<UserContactDetail> contactDetail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<UserAddressDetail> getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(List<UserAddressDetail> addressDetail) {
		this.addressDetail = addressDetail;
	}

	public List<UserContactDetail> getContactDetail() {
		return contactDetail;
	}

	public void setContactDetail(List<UserContactDetail> contactDetail) {
		this.contactDetail = contactDetail;
	}
	
}

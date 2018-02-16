package com.neev.moh.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "User")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int Id;

	@Column(name = "fname")
	private String firstName;

	@Column(name = "lname")
	private String lastName;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "dob")
	private Date dob;

	@Column(name = "mobile_code")
	private Integer mobileCode;

	@Column(name = "mobile", unique = true)
	private String mobile;

	@Column(name = "password")
	private String password;

	@Column(name = "rolename")
	private String roleType;

	@Column(name = "gender")
	private Gender gender;

	@Column(name = "accept_terms_condition")
	private boolean acceptTermsAndCondition;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL , fetch=FetchType.EAGER)
	private Set<Address> Address;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "users")
	private Set<Role> roles;

	/**
	 * derived value not in db. From email
	 */
	@Transient
	private String userNamePlain;

	public User() {
	}

	public User(String firstName, String lastName, String email, Date dob,
			Integer mobileCode, String mobile, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		setEmail(email);
		this.dob = dob;
		this.mobileCode = mobileCode;
		this.mobile = mobile;
		this.password = password;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
		init();
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Integer getMobileCode() {
		return mobileCode;
	}

	public void setMobileCode(Integer mobileCode) {
		this.mobileCode = mobileCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Address> getAddress() {
		return Address;
	}

	public void setAddress(Set<Address> address) {
		this.Address = address;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getFullName() {
		StringBuilder nameBuilder = new StringBuilder();
		if (this.firstName != null && this.lastName != null) {
			nameBuilder.append(this.firstName + " " + this.lastName);
		} else if (this.firstName != null) {
			nameBuilder.append(this.firstName);
		} else if (this.lastName != null) {
			nameBuilder.append(this.lastName);
		}

		return nameBuilder.toString();
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Boolean getAcceptTermsAndCondition() {
		return acceptTermsAndCondition;
	}

	public void setAcceptTermsAndCondition(Boolean acceptTermsAndCondition) {
		this.acceptTermsAndCondition = acceptTermsAndCondition;
	}

	public String getUserNamePlain() {
		return userNamePlain;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@PostConstruct
	public void init() {
		if (email != null) {
			this.userNamePlain = email.replace("@", "_");
			this.userNamePlain = userNamePlain.replace(".", "_");
		}
	}

	@Override
	public String toString() {
		return "User [Id=" + Id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", dob=" + dob
				+ ", mobileCode=" + mobileCode + ", mobile=" + mobile
				+ ", password=" + password + ", roleType=" + roleType
				+ ", gender=" + gender + ", acceptTermsAndCondition="
				+ acceptTermsAndCondition + ", Address=" + Address + ", roles="
				+ roles + ", userNamePlain=" + userNamePlain + "]";
	}
}

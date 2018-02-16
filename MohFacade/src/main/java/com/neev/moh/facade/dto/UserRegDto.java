package com.neev.moh.facade.dto;

import java.util.Date;
import java.util.Set;

import com.neev.moh.model.Address;

public class UserRegDto {
	
	private int Id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String roleType;
	
	private Date dob;
	
	private Integer mobileCode;
	
	private String mobile;
	
	private String password;
	
	private String confirmPassword;
	
	private Set<Address> addresses;
	
	//below attribute are for jackson mapping from (angular model to DTO) in  webservice  
	private String address1Street1;
	private String address1Street2;
	private String address1City;
	private String address1Country;
	private String address1Pincode;
	
	private String address2Street1;
	private String address2Street2;
	private String address2City;
	private String address2Country;
	private String address2Pincode;
	
	public UserRegDto(){}
	
	public UserRegDto(String firstName, String lastName, String email, Date dob, Integer mobileCode, String mobile, String password) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.email=email;
		this.dob=dob;
		this.mobileCode=mobileCode;
		this.mobile=mobile;
		this.password=password;
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

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public String getAddress1Street1() {
		return address1Street1;
	}

	public void setAddress1Street1(String address1Street1) {
		this.address1Street1 = address1Street1;
	}

	public String getAddress1Street2() {
		return address1Street2;
	}

	public void setAddress1Street2(String address1Street2) {
		this.address1Street2 = address1Street2;
	}

	public String getAddress1Country() {
		return address1Country;
	}

	public void setAddress1Country(String address1Country) {
		this.address1Country = address1Country;
	}

	public String getAddress1Pincode() {
		return address1Pincode;
	}

	public void setAddress1Pincode(String address1Pincode) {
		this.address1Pincode = address1Pincode;
	}

	public String getAddress2Street1() {
		return address2Street1;
	}

	public void setAddress2Street1(String address2Street1) {
		this.address2Street1 = address2Street1;
	}

	public String getAddress2Street2() {
		return address2Street2;
	}

	public void setAddress2Street2(String address2Street2) {
		this.address2Street2 = address2Street2;
	}

	public String getAddress2Country() {
		return address2Country;
	}

	public void setAddress2Country(String address2Country) {
		this.address2Country = address2Country;
	}

	public String getAddress2Pincode() {
		return address2Pincode;
	}

	public void setAddress2Pincode(String address2Pincode) {
		this.address2Pincode = address2Pincode;
	}

	public String getAddress1City() {
		return address1City;
	}

	public void setAddress1City(String address1City) {
		this.address1City = address1City;
	}

	public String getAddress2City() {
		return address2City;
	}

	public void setAddress2City(String address2City) {
		this.address2City = address2City;
	}

}

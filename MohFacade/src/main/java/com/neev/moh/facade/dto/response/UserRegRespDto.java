package com.neev.moh.facade.dto.response;

public class UserRegRespDto extends ResponseDto{
	
	private Integer userId;
	private String firstName;
	private String lastName;
	private String mobile;
	private String email;
	
	private boolean isRegistrationSuccess; 
	
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isRegistrationSuccess() {
		return isRegistrationSuccess;
	}
	public void setRegistrationSuccess(boolean isRegistrationSuccess) {
		this.isRegistrationSuccess = isRegistrationSuccess;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}

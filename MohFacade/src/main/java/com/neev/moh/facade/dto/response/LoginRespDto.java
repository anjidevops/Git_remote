package com.neev.moh.facade.dto.response;


public class LoginRespDto extends ResponseDto{
	
	/* loginId can be mobile/email of User*/
	private String loginId;
	private boolean isValidationFailed;
	
	private boolean isLoginSuccess;
	private String userRole;
	private String userFirstName;
	private String userLastName;
	
	private Integer userId;
	
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public boolean isValidationFailed() {
		return isValidationFailed;
	}

	public void setValidationFailed(boolean isValidationFailed) {
		this.isValidationFailed = isValidationFailed;
	}

	public boolean isLoginSuccess() {
		return isLoginSuccess;
	}

	public void setLoginSuccess(boolean isLoginSuccess) {
		this.isLoginSuccess = isLoginSuccess;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

}

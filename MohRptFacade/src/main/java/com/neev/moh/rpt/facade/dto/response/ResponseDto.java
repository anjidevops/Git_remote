package com.neev.moh.rpt.facade.dto.response;

public class ResponseDto {
	
	private int errorNo;
	private String errorMsg;
	private String successMsg;
	
	public int getErrorNo() {
		return errorNo;
	}
	public void setErrorNo(int errorNo) {
		this.errorNo = errorNo;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getSuccessMsg() {
		return successMsg;
	}
	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}
	
}

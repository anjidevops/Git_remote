package com.neev.moh.facade.dto.response;

public class AppSettingVOResponseDto extends ResponseDto{
	
	private String mainId;
	private String subId;
	private String language;
	private String paramName;
	private String value;
	private int indexNo;
	
	public String getMainId() {
		return mainId;
	}
	public void setMainId(String mainId) {
		this.mainId = mainId;
	}
	public String getSubId() {
		return subId;
	}
	public void setSubId(String subId) {
		this.subId = subId;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getIndexNo() {
		return indexNo;
	}
	public void setIndexNo(int indexNo) {
		this.indexNo = indexNo;
	}
	
}

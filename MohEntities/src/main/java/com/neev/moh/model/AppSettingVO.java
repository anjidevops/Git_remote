package com.neev.moh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "app_settings")
public class AppSettingVO implements Serializable {

	private static final long serialVersionUID = 1L;

	String mainId;
	String subId;
	String language;
	String paramName;
	String value;
	int indexNo;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "index_no")
	public int getIndexNo() {
		return indexNo;
	}

	public void setIndexNo(int indexNo) {
		this.indexNo = indexNo;
	}

	@Column(name = "main_id")
	public String getMainId() {
		return mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

	@Column(name = "sub_id")
	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	@Column(name = "language")
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Column(name = "param_name")
	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	@Column(name = "param_value")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "AppSettingVO [mainId=" + mainId + ", subId=" + subId
				+ ", language=" + language + ", paramName=" + paramName
				+ ", value=" + value + ", indexNo=" + indexNo + "]";
	}

}

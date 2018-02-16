package com.neev.moh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Department")
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer dept_id;

	@Column(name = "dept_name", unique = true)
	private String deptName;

	@Column(name = "dept_desc")
	private String deptDesc;

	// list of doctors/ users that belong to this dept. here?

	public Department() {
	}

	public Department(String deptName, String deptDesc) {
		this.deptName = deptName;
		this.deptDesc = deptDesc;
	}

	public Integer getDept_id() {
		return dept_id;
	}

	public void setDept_id(Integer dept_id) {
		this.dept_id = dept_id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptDesc() {
		return deptDesc;
	}

	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}

	@Override
	public String toString() {
		return "Department [dept_id=" + dept_id + ", deptName=" + deptName
				+ ", deptDesc=" + deptDesc + "]";
	}

}

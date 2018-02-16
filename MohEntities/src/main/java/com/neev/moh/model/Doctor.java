package com.neev.moh.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Doctor")
public class Doctor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer doc_id;

	@Column(name = "reg_number", unique = true)
	private Integer regNumber;

	@Column(name = "doc_spec")
	private String doctorSpec;

	@Column(name = "accepting_apts")
	private Boolean acceptingAppts;

	/** max parallel appointments 0 means no apts */
	@Column(name = "lanes")
	private int lanes;// 0, 1, 2 or 3 (max for now).

	public Doctor() {
	}

	public Doctor(Department dept, Integer regNumber, String doctorSpec, String timeSlot) {
		this.dept = dept;
		this.regNumber = regNumber;
		this.doctorSpec = doctorSpec;
	}

	// hibernate mapping many doctors to one dept

	@ManyToOne(cascade = CascadeType.ALL)
	private Department dept;

	// hibernate mapping one doctor to one user

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Integer getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(Integer regNumber) {
		this.regNumber = regNumber;
	}

	public String getDoctorSpec() {
		return doctorSpec;
	}

	public void setDoctorSpec(String doctorSpec) {
		this.doctorSpec = doctorSpec;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public Integer getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(Integer doc_id) {
		this.doc_id = doc_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isAcceptingAppts() {
		if (acceptingAppts == null) // for previous record
			acceptingAppts = false;
		return acceptingAppts;
	}

	public void setAcceptingAppts(boolean acceptingAppts) {
		this.acceptingAppts = acceptingAppts;
	}

	public int getLanes() {
		return lanes;
	}

	public void setLanes(int lanes) {
		this.lanes = lanes;
	}

	@Override
	public String toString() {
		return "Doctor [doc_id=" + doc_id + ", regNumber=" + regNumber
				+ ", doctorSpec=" + doctorSpec + ", acceptingAppts="
				+ acceptingAppts + ", lanes=" + lanes + ", dept=" + dept
				+ ", user=" + user + "]";
	}

}

package com.neev.moh.rpt.model;

public class UserCount {

	private String doctorName;
	private int id;
	private Integer count;

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "UserCount [DoctorName=" + doctorName + ", Id=" + id
				+ ", count=" + count + "]";
	}

}

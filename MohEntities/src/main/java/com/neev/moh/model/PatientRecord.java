package com.neev.moh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PatientRecord")
public class PatientRecord implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int Id;

	@Column(name = "appointment_id")
	private int appopintmentId;

	@Column(name = "summary")
	private String summary;

	@Column(name = "advise_admit")
	private boolean adviseAdmit;

	@Column(name = "prescription")
	private String prescription;

	// string path of uploaded media file,
	// but then need to sync to nodes or shared folder
	@Column(name = "attach1")
	private String attach1;

	@Column(name = "attach2")
	private String attach2;

	public PatientRecord() {
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getAppopintmentId() {
		return appopintmentId;
	}

	public void setAppopintmentId(int appopintmentId) {
		this.appopintmentId = appopintmentId;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPrescription() {
		return prescription;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	public String getAttach1() {
		return attach1;
	}

	public void setAttach1(String attach1) {
		this.attach1 = attach1;
	}

	public String getAttach2() {
		return attach2;
	}

	public void setAttach2(String attach2) {
		this.attach2 = attach2;
	}

	public boolean isAdviseAdmit() {
		return adviseAdmit;
	}

	public void setAdviseAdmit(boolean adviseAdmit) {
		this.adviseAdmit = adviseAdmit;
	}

	@Override
	public String toString() {
		return "PatientRecord [Id=" + Id + ", appopintmentId=" + appopintmentId
				+ ", summary=" + summary + ", adviseAdmit=" + adviseAdmit
				+ ", prescription=" + prescription + ", attach1=" + attach1
				+ ", attach2=" + attach2 + "]";
	}

}

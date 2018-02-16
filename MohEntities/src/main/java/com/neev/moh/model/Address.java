package com.neev.moh.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Address")
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int Id;

	@Column(name = "street1")
	private String street1;

	@Column(name = "street2")
	private String street2;

	@Column(name = "city")
	private String city;

	@Column(name = "country")
	private String country;

	@Column(name = "pincode")
	private Integer pincode;

	public Address() {
	}

	public Address(String street1, String street2, String city, String country, Integer pincode) {
		this.street1 = street1;
		this.street2 = street2;
		this.city = city;
		this.country = country;
		this.pincode = pincode;
	}

	// hibernate mapping many2one

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Address [Id=" + Id + ", street1=" + street1 + ", street2="
				+ street2 + ", city=" + city + ", country=" + country
				+ ", pincode=" + pincode + "]";
	}

	

}

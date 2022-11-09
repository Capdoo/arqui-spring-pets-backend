package com.pets.app.modules.searchs;

import com.pets.app.modules.pets.PetModel;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="searchs")
public class SearchModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String address;
	private String district;
	private Timestamp registerDate;
	private Timestamp lostDate;
	private String phoneA;
	private String phoneB;
	private String message;
	
	private String linkImg;
	
	@ManyToOne
	@JoinColumn(name="pet_id",referencedColumnName = "id", nullable=false)
	private PetModel pet;

	public SearchModel() {
	}

	public SearchModel(long id, String address, String district, Timestamp registerDate, Timestamp lostDate, String phoneA, String phoneB, String message, String linkImg, PetModel pet) {
		this.id = id;
		this.address = address;
		this.district = district;
		this.registerDate = registerDate;
		this.lostDate = lostDate;
		this.phoneA = phoneA;
		this.phoneB = phoneB;
		this.message = message;
		this.linkImg = linkImg;
		this.pet = pet;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Timestamp getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Timestamp registerDate) {
		this.registerDate = registerDate;
	}

	public Timestamp getLostDate() {
		return lostDate;
	}

	public void setLostDate(Timestamp lostDate) {
		this.lostDate = lostDate;
	}

	public String getPhoneA() {
		return phoneA;
	}

	public void setPhoneA(String phoneA) {
		this.phoneA = phoneA;
	}

	public String getPhoneB() {
		return phoneB;
	}

	public void setPhoneB(String phoneB) {
		this.phoneB = phoneB;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLinkImg() {
		return linkImg;
	}

	public void setLinkImg(String linkImg) {
		this.linkImg = linkImg;
	}

	public PetModel getPet() {
		return pet;
	}

	public void setPet(PetModel pet) {
		this.pet = pet;
	}
}

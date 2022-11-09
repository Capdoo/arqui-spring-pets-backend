package com.pets.app.modules.adoptions;

public class AdoptionDTO {
	
	private long id;
	
	private String message;
	//En caso el animal este enfermo
	private String observation;
	
	private String phoneA;
	private String phoneB;
	
	private String address;
	private String district;
	
	private String registerDate;
	private long pet_id;

	public AdoptionDTO() {
		super();
	}


	public AdoptionDTO(long id, String message, String observation, String phoneA, String phoneB, String address, String district, String registerDate, long pet_id) {
		this.id = id;
		this.message = message;
		this.observation = observation;
		this.phoneA = phoneA;
		this.phoneB = phoneB;
		this.address = address;
		this.district = district;
		this.registerDate = registerDate;
		this.pet_id = pet_id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
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

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public long getPet_id() {
		return pet_id;
	}

	public void setPet_id(long pet_id) {
		this.pet_id = pet_id;
	}
}

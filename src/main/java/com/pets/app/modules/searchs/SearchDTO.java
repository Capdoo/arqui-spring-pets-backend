package com.pets.app.modules.searchs;

public class SearchDTO {
	private long id;
	private String address;
	private String district;
	private String phoneA;
	private String phoneB;
	private long pet_id;
	private String namePet;
	private String speciesPet;
	private String breedPet;
	//Fecha de usuario
	private String lostDate;
	private String registerDate;
	//Mensaje
	private String message;
	//Imagen
	private String encoded;

	public SearchDTO() {
		super();
	}

	public SearchDTO(long id, String address, String district, String phoneA, String phoneB, long pet_id, String namePet, String speciesPet, String breedPet, String lostDate, String registerDate, String message, String encoded) {
		this.id = id;
		this.address = address;
		this.district = district;
		this.phoneA = phoneA;
		this.phoneB = phoneB;
		this.pet_id = pet_id;
		this.namePet = namePet;
		this.speciesPet = speciesPet;
		this.breedPet = breedPet;
		this.lostDate = lostDate;
		this.registerDate = registerDate;
		this.message = message;
		this.encoded = encoded;
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

	public long getPet_id() {
		return pet_id;
	}

	public void setPet_id(long pet_id) {
		this.pet_id = pet_id;
	}

	public String getNamePet() {
		return namePet;
	}

	public void setNamePet(String namePet) {
		this.namePet = namePet;
	}

	public String getSpeciesPet() {
		return speciesPet;
	}

	public void setSpeciesPet(String speciesPet) {
		this.speciesPet = speciesPet;
	}

	public String getBreedPet() {
		return breedPet;
	}

	public void setBreedPet(String breedPet) {
		this.breedPet = breedPet;
	}

	public String getLostDate() {
		return lostDate;
	}

	public void setLostDate(String lostDate) {
		this.lostDate = lostDate;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEncoded() {
		return encoded;
	}

	public void setEncoded(String encoded) {
		this.encoded = encoded;
	}
}

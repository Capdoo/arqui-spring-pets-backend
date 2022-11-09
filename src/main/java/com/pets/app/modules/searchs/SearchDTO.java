package com.pets.app.modules.searchs;

public class SearchDTO {

	private long id;
	private String address;
	private String district;
	private String phoneA;
	private String phoneB;
	private long petId;
	
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
	private String urlLink;

	public SearchDTO() {
		super();
	}

	public SearchDTO(long id, String address, String district, String phoneA, String phoneB, long petId, String namePet, String speciesPet, String breedPet, String lostDate, String registerDate, String message, String encoded, String urlLink) {
		this.id = id;
		this.address = address;
		this.district = district;
		this.phoneA = phoneA;
		this.phoneB = phoneB;
		this.petId = petId;
		this.namePet = namePet;
		this.speciesPet = speciesPet;
		this.breedPet = breedPet;
		this.lostDate = lostDate;
		this.registerDate = registerDate;
		this.message = message;
		this.encoded = encoded;
		this.urlLink = urlLink;
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

	public long getPetId() {
		return petId;
	}

	public void setPetId(long petId) {
		this.petId = petId;
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

	public String getUrlLink() {
		return urlLink;
	}

	public void setUrlLink(String urlLink) {
		this.urlLink = urlLink;
	}
}

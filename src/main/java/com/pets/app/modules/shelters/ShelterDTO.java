package com.pets.app.modules.shelters;

public class ShelterDTO {
	private long id;
	private String address;
	private String district;
	private String name;
	private long numberOfPartners;
	private String contactNumber;
	
	private String registerDate;
	
	private long idMainPartner;
	private String dniMainPartner;

	//Para imagen
	private String encoded;
	private String urlLink;

	public ShelterDTO() {
		super();
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getNumberOfPartners() {
		return numberOfPartners;
	}

	public void setNumberOfPartners(long numberOfPartners) {
		this.numberOfPartners = numberOfPartners;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public long getIdMainPartner() {
		return idMainPartner;
	}

	public void setIdMainPartner(long idMainPartner) {
		this.idMainPartner = idMainPartner;
	}

	public String getDniMainPartner() {
		return dniMainPartner;
	}

	public void setDniMainPartner(String dniMainPartner) {
		this.dniMainPartner = dniMainPartner;
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

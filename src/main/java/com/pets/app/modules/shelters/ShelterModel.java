package com.pets.app.modules.shelters;

import com.pets.app.modules.pets.PetModel;
import com.pets.app.security.models.UserModel;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name="shelters")
public class ShelterModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private long numberOfPartners;
	private Timestamp registerDate;
	private String contactNumber;
	private String address;
	private String district;

	//link de imagen
	private String linkImg;

	//Idx from the main partner (OneToOne)
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "SHELTER_FK_USER"))
	private UserModel user;
	
	//For pets
	@OneToMany(mappedBy="shelter")
	private Set<PetModel> pets;
	
	public ShelterModel() {
		super();
	}


	public ShelterModel(long id, String name, long numberOfPartners, Timestamp registerDate, String contactNumber, String address, String district, String linkImg, UserModel user, Set<PetModel> pets) {
		this.id = id;
		this.name = name;
		this.numberOfPartners = numberOfPartners;
		this.registerDate = registerDate;
		this.contactNumber = contactNumber;
		this.address = address;
		this.district = district;
		this.linkImg = linkImg;
		this.user = user;
		this.pets = pets;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Timestamp getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Timestamp registerDate) {
		this.registerDate = registerDate;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
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

	public String getLinkImg() {
		return linkImg;
	}

	public void setLinkImg(String linkImg) {
		this.linkImg = linkImg;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public Set<PetModel> getPets() {
		return pets;
	}

	public void setPets(Set<PetModel> pets) {
		this.pets = pets;
	}
}


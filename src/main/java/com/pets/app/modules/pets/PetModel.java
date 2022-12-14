package com.pets.app.modules.pets;

import com.pets.app.modules.adoptions.AdoptionModel;
import com.pets.app.modules.details.DetailModel;
import com.pets.app.modules.owners.OwnerModel;
import com.pets.app.modules.searchs.SearchModel;
import com.pets.app.modules.shelters.ShelterModel;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name="pets")
public class PetModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String gender;
	private Timestamp birthDate;
	private Timestamp registerDate;
	private String colour;
	//Especificado por el usuario
	private String characteristic;
	private String size;
	//link de imagen

	@Lob
	@Column(length = 16777215)
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] image;

	@ManyToOne
	@JoinColumn(name="owner_id",referencedColumnName = "id", nullable=true)
	private OwnerModel owner;
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "detail_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "PET_FK_DETAIL"))
//	private DetailModel detail;
	@ManyToOne
	@JoinColumn(name="detail_id", referencedColumnName = "id", nullable = true)
	private DetailModel detail;


	//For searchs
	@OneToMany(mappedBy="pet")
	private Set<SearchModel> searchs;
	//For pets
	@OneToMany(mappedBy="pet")
	private Set<AdoptionModel> adoptions;
	//Shelters
	@ManyToOne
	@JoinColumn(name="shelter_id",referencedColumnName = "id", nullable=true)
	private ShelterModel shelter;

	public PetModel() {
	}

	public PetModel(long id, String name, String gender, Timestamp birthDate, Timestamp registerDate, String colour, String characteristic, byte[] image, String linkImg, OwnerModel owner, DetailModel detail, Set<SearchModel> searchs, Set<AdoptionModel> adoptions, ShelterModel shelter) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birthDate = birthDate;
		this.registerDate = registerDate;
		this.colour = colour;

		this.characteristic = characteristic;
		this.size = size;
		this.image = image;
		this.owner = owner;
		this.detail = detail;
		this.searchs = searchs;
		this.adoptions = adoptions;
		this.shelter = shelter;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Timestamp getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Timestamp birthDate) {
		this.birthDate = birthDate;
	}

	public Timestamp getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Timestamp registerDate) {
		this.registerDate = registerDate;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getCharacteristic() {
		return characteristic;
	}

	public void setCharacteristic(String characteristic) {
		this.characteristic = characteristic;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public OwnerModel getOwner() {
		return owner;
	}

	public void setOwner(OwnerModel owner) {
		this.owner = owner;
	}

	public DetailModel getDetail() {
		return detail;
	}

	public void setDetail(DetailModel detail) {
		this.detail = detail;
	}

	public Set<SearchModel> getSearchs() {
		return searchs;
	}

	public void setSearchs(Set<SearchModel> searchs) {
		this.searchs = searchs;
	}

	public Set<AdoptionModel> getAdoptions() {
		return adoptions;
	}

	public void setAdoptions(Set<AdoptionModel> adoptions) {
		this.adoptions = adoptions;
	}

	public ShelterModel getShelter() {
		return shelter;
	}

	public void setShelter(ShelterModel shelter) {
		this.shelter = shelter;
	}
}

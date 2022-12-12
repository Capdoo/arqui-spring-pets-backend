package com.pets.app.modules.owners;

import com.pets.app.modules.pets.PetModel;
import com.pets.app.security.models.UserModel;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="owners")
public class OwnerModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private Timestamp registerDate;
	private int numberOfPets;
	//Secundarios
	private int rate;
	private long historial_id;

	//Idx user (OneToOne)
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "OWNER_FK_USER"))
	private UserModel user;
	
	//Para mascotas
	@OneToMany(mappedBy="owner")
	private List<PetModel> pets;

	public OwnerModel() {
		super();
	}

	public OwnerModel(long id, Timestamp registerDate, int numberOfPets, int rate, long historial_id, UserModel user, List<PetModel> pets) {
		this.id = id;
		this.registerDate = registerDate;
		this.numberOfPets = numberOfPets;
		this.rate = rate;
		this.historial_id = historial_id;
		this.user = user;
		this.pets = pets;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Timestamp registerDate) {
		this.registerDate = registerDate;
	}


	public int getNumberOfPets() {
		return numberOfPets;
	}

	public void setNumberOfPets(int numberOfPets) {
		this.numberOfPets = numberOfPets;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public long getHistorial_id() {
		return historial_id;
	}

	public void setHistorial_id(long historial_id) {
		this.historial_id = historial_id;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public List<PetModel> getPets() {
		return pets;
	}

	public void setPets(List<PetModel> pets) {
		this.pets = pets;
	}
}

package com.pets.app.modules.owners;

public class OwnerDTO {
	private long id;
	private String registerDate;
	private long historial_id;
	private int numberOfPets;
	private int rate;
	private long user_id;

	public OwnerDTO() {
		super();
	}

	public OwnerDTO(int numberOfPets) {
		this.numberOfPets = numberOfPets;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public long getHistorial_id() {
		return historial_id;
	}

	public void setHistorial_id(long historial_id) {
		this.historial_id = historial_id;
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

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
}

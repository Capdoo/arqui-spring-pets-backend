package com.pets.app.modules.details;

public class DetailDTO {

	private long id;
	private String species;
	private String breed;
	
	public DetailDTO() {
		super();
	}

	public DetailDTO(long id, String species, String breed) {
		this.id = id;
		this.species = species;
		this.breed = breed;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}
}

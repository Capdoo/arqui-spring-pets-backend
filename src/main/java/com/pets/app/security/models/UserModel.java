package com.pets.app.security.models;

import com.pets.app.modules.owners.OwnerModel;
import com.pets.app.modules.shelters.ShelterModel;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class UserModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(unique = true)
	private String username;
	@Column(name="dni")
	private String dni;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="sur_name")
	private String surName;
	@Column(name="phone")
	private String phone;
	@Column(name="address")
	private String address;
	private String email;
	private String password;

	@Lob
	@Column(name="image")
	private byte[] image;

	@Column(name="token_password")
	private String tokenPassword;

	//Referenced owner
	@OneToOne(cascade =  CascadeType.ALL,mappedBy = "user")
	private OwnerModel owner;
	//Refugio
	@OneToOne(cascade =  CascadeType.ALL,mappedBy = "user")
	private ShelterModel shelter;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="user_roles", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_role"))
	private Set<RoleModel> roles = new HashSet<>();

	public UserModel() {
		super();
	}

	public UserModel(String name, String username, String email, String password) {
		super();
		this.firstName = name;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public UserModel(long id, String username, String dni, String firstName, String lastName, String surName, String phone, String address, String email, String password, byte[] image, OwnerModel owner, ShelterModel shelter, Set<RoleModel> roles) {
		this.id = id;
		this.username = username;
		this.dni = dni;
		this.firstName = firstName;
		this.lastName = lastName;
		this.surName = surName;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.password = password;
		this.image = image;
		this.owner = owner;
		this.shelter = shelter;
		this.roles = roles;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public ShelterModel getShelter() {
		return shelter;
	}

	public void setShelter(ShelterModel shelter) {
		this.shelter = shelter;
	}

	public Set<RoleModel> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleModel> roles) {
		this.roles = roles;
	}

	public String getTokenPassword() {
		return tokenPassword;
	}

	public void setTokenPassword(String tokenPassword) {
		this.tokenPassword = tokenPassword;
	}
}


























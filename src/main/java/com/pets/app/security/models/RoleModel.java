package com.pets.app.security.models;


import com.pets.app.security.enums.RoleName;

import javax.persistence.*;


@Entity
@Table(name="roles")
public class RoleModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Enumerated(EnumType.STRING)
	private RoleName roleName;

	public RoleModel() {
		super();
	}

	public RoleModel(RoleName roleName) {
		super();
		this.roleName = roleName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public RoleName getRoleName() {
		return roleName;
	}

	public void setRoleName(RoleName roleName) {
		this.roleName = roleName;
	}
}

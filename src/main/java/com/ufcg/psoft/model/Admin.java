package com.ufcg.psoft.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@DiscriminatorValue(value = "ADMIN")
public class Admin extends CargoSistema {
	
	public Admin() {
		super();
	}

	@Override
	public UserRoleName getUserRole() {
		return UserRoleName.ADMIN;
	}
}

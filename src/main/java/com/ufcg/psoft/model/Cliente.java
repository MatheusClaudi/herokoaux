package com.ufcg.psoft.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@DiscriminatorValue(value = "CLIENTE")
public class Cliente extends CargoSistema {
	

	public Cliente() {
		super();
	}
	
	@Override
	public UserRoleName getUserRole() {
		return UserRoleName.CLIENTE;
	}
}

package com.example.algamoney.api.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
public class Address {
	
	@Column(name = "public_place")
	@Size(max = 30)
	private String publicPlace;
	
	@Column
	@Size(max = 30)
	private String number;
	
	@Column
	@Size(max = 30)	
	private String complement;
	
	@Column
	@Size(max = 30)
	private String neighborhood;
	
	@Column
	@Size(max = 30)
	private String CEP;
	
	@Column
	@Size(max = 30)
	private String city;
	
	@Column
	@Size(max = 30)
	private String state;

	public String getPublicPlace() {
		return publicPlace;
	}

	public void setPublicPlace(String publicPlace) {
		this.publicPlace = publicPlace;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cEP) {
		CEP = cEP;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}

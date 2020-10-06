package com.init.products.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Bus")
public class Bus {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="type", length=20)
	private String type;
	
	@Column(name="motor", length=20)
	private String motor;
	
	@Column(name="brakes", length=20)
	private String brakes;
	
	@Column(name="concessionaireId")
	private Long concessionaireId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMotor() {
		return motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	public String getBrakes() {
		return brakes;
	}

	public void setBrakes(String brakes) {
		this.brakes = brakes;
	}

	public Long getConcessionaireId() {
		return concessionaireId;
	}

	public void setConcessionaireId(Long concessionaireId) {
		this.concessionaireId = concessionaireId;
	}

}

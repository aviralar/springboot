package com.siemens.daacathon.lifePrediction.web.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "raw_data")
public class rawData {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;

	@Column
	private BigDecimal pressure;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getPressure() {
		return pressure;
	}

	public void setPressure(BigDecimal pressure) {
		this.pressure = pressure;
	}
	
	
	

	/*@Column(name = "rpm")
	private BigInteger rpm;

	@Column(name = "Temperature")
	private BigInteger temperature;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigInteger getPressure() {
		return pressure;
	}

	public void setPressure(BigInteger pressure) {
		this.pressure = pressure;
	}

	public BigInteger getRpm() {
		return rpm;
	}

	public void setRpm(BigInteger rpm) {
		this.rpm = rpm;
	}

	public BigInteger getTemperature() {
		return temperature;
	}

	public void setTemperature(BigInteger temperature) {
		this.temperature = temperature;
	}*/





}

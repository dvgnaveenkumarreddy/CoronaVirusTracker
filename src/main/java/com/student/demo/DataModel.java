package com.student.demo;

public class DataModel {

	private String country;
	private String state;
	private String totalDeath;
	
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTotalDeath() {
		return totalDeath;
	}
	public void setTotalDeath(String string) {
		this.totalDeath = string;
	}
	@Override
	public String toString() {
		return "DataModel [country=" + country + ", state=" + state + ", totalDeath=" + totalDeath + "]";
	}

	
}

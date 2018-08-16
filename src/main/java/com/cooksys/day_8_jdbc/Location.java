package com.cooksys.day_8_jdbc;

public class Location {

	@Override
	public String toString() {
		return "Location [id=" + id + ", city=" + city + ", state=" + state + ", country=" + country + "]";
	}

	private int id;
	private String city;
	private String state;
	private String country;

	public Location() {

	}

	public Location(String city, String state, String country) {
		this.city = city;
		this.state = state;
		this.country = country;
	}

	public Location(int id, String city, String state, String country) {
		super();
		this.id = id;
		this.city = city;
		this.state = state;
		this.country = country;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}

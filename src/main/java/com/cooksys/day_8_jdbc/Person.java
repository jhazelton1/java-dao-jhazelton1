package com.cooksys.day_8_jdbc;

import java.util.Set;

public class Person {

	private int id;
	private String firstName;
	private String lastName;
	private int age;
	private int location;
	private Location locationObj;
	private Set<String> interests;

	public Person() {

	}

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Person(String firstName, String lastName, int age, int location) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.location = location;
	}

	public Person(int id, String firstName, String lastName, int age, int location) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.location = location;
	}

	public Person(int id, String firstName, String lastName, int age, int location, Location locationObj,
			Set<String> interests) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.location = location;
		this.locationObj = locationObj;
		this.interests = interests;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public Location getLocationObj() {
		return locationObj;
	}

	public void setLocationObj(Location locationObj) {
		this.locationObj = locationObj;
	}

	public Set<String> getInterests() {
		return interests;
	}

	public void setInterests(Set<String> interests) {
		this.interests = interests;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", location=" + location + ", locationObj=" + locationObj + ", interests=" + interests + "]";
	}

}

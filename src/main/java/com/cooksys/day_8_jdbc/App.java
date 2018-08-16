package com.cooksys.day_8_jdbc;

import java.util.HashSet;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		PersonDAO perDAO = new PersonDAO("jdbc:postgresql://localhost:8080", "postgres", "password");
		LocationDAO locDAO = new LocationDAO("jdbc:postgresql://localhost:8080", "postgres", "password");
		InterestDAO intDAO = new InterestDAO("jdbc:postgresql://localhost:8080", "postgres", "password");

	}

}

package com.cooksys.day_8_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.postgresql.Driver;

public class PersonDAO {

	private Driver d = new Driver();
	private String url;
	private String user;
	private String password;

	public PersonDAO(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Person> getAll() {
		List<Person> persons = new ArrayList<Person>();
		try (Connection connection = DriverManager.getConnection(this.url, this.user, this.password)) {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM \"Assignment-6\".person");
			while (rs.next()) {
				persons.add(new Person(rs.getInt("Id"), rs.getString("First_Name"), rs.getString("Last_Name"),
						rs.getInt("Age"), rs.getInt("Location")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return persons;
	}

	public Person get(int id) throws Exception {
		Person person = null;
		try (Connection connection = DriverManager.getConnection(this.url, this.user, this.password)) {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM \"Assignment-6\".person WHERE \"Id\"= '" + id + "'");
			while (rs.next()) {
				person = new Person(rs.getInt("Id"), rs.getString("First_Name"), rs.getString("Last_Name"),
						rs.getInt("Age"), rs.getInt("Location"), getLocation(rs.getInt("Location")), getInterests(id));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (person == null) {
			throw new Exception("This ID wasn't found in the database.");
		}
		return person;
	}

	public Location getLocation(int id) throws Exception {
		Location location = null;
		try (Connection connection = DriverManager.getConnection(this.url, this.user, this.password)) {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM \"Assignment-6\".location " + "WHERE \"Assignment-6\".location.\"Id\" = "
					+ id;
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				location = new Location(rs.getInt("Id"), rs.getString("City"), rs.getString("State"),
						rs.getString("Country"));
			}
			return location;
		}
	}

	public Set<String> getInterests(int id) throws Exception {
		Set<String> interests = new HashSet<String>();
		try (Connection connection = DriverManager.getConnection(this.url, this.user, this.password)) {
			Statement statement = connection.createStatement();
			String query = "SELECT \"Assignment-6\".interest.\"Title\" FROM \"Assignment-6\".interest "
					+ "JOIN \"Assignment-6\".person_interest ON \"Assignment-6\".interest.\"Id\" = \"Assignment-6\".person_interest.\"interest_id\" "
					+ "JOIN \"Assignment-6\".person ON \"Assignment-6\".person_interest.person_id = \"Assignment-6\".person.\"Id\" "
					+ "WHERE \"Assignment-6\".person.\"Id\" = " + id;
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				interests.add(rs.getString("Title"));
			}
			return interests;
		}

	}

	public Person save(Person person) throws Exception {
		if (person.getLocationObj() != null) {
			int newLocation = person.getLocation();
			try (Connection connection = DriverManager.getConnection(this.url, this.user, this.password)) {
				Statement statement = connection.createStatement();
				String query = "" + "SELECT * FROM \"Assignment-6\".location " + "WHERE \"City\" = '"
						+ person.getLocationObj().getCity() + "' " + "AND \"State\" = '"
						+ person.getLocationObj().getState() + "' " + "AND \"Country\" = '"
						+ person.getLocationObj().getCountry() + "'";
				ResultSet rs = statement.executeQuery(query);
				if (!rs.next()) { // If no results, insert
					String update = "INSERT INTO \"Assignment-6\".location (\"City\", \"State\", \"Country\") "
							+ "VALUES ('" + person.getLocationObj().getCity() + "', '"
							+ person.getLocationObj().getState() + "', '" + person.getLocationObj().getCountry() + "')";
					statement.executeUpdate(update);
					ResultSet rs2 = statement.executeQuery(query);
					if (rs2.next()) {
						newLocation = rs2.getInt("Id");
					}
				} else {
					ResultSet rs3 = statement.executeQuery(query);
					if (rs3.next()) {
						newLocation = rs3.getInt("Id");
					}
				}
				person.setLocation(newLocation);
			}
		}

		if (person.getInterests() != null) {
			try (Connection connection = DriverManager.getConnection(this.url, this.user, this.password)) {
				Statement statement = connection.createStatement();
				for (String i : person.getInterests()) {
					String query = "SELECT * FROM \"Assignment-6\".interest WHERE \"Title\" = '" + i + "'";
					ResultSet rs = statement.executeQuery(query);
					if (!rs.next()) {
						String update = "INSERT INTO \"Assignment-6\".interest (\"Title\") VALUES ('" + i + "')";
					}
				}
			}
		}

		if (person.getId() == 0) {
			try (Connection connection = DriverManager.getConnection(this.url, this.user, this.password)) {
				Statement statement = connection.createStatement();
				statement.executeUpdate(
						"INSERT INTO \"Assignment-6\".person (\"First_Name\", \"Last_Name\", \"Age\", \"Location\") "
								+ "VALUES ('" + person.getFirstName() + "', '" + person.getLastName() + "', "
								+ person.getAge() + ", " + person.getLocation() + ")");
				ResultSet rs = statement.executeQuery(
						"SELECT * FROM \"Assignment-6\".person WHERE \"First_Name\"= '" + person.getFirstName() + "'");
				if (rs.next()) {
					return new Person(rs.getInt("Id"), rs.getString("First_Name"), rs.getString("Last_Name"),
							rs.getInt("Age"), rs.getInt("Location"), getLocation(rs.getInt("Location")),
							getInterests(person.getId()));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (get(person.getId()) == null) {
			throw new Exception("ID is not null and it doesn't exist in database");
		} else {
			try (Connection connection = DriverManager.getConnection(this.url, this.user, this.password)) {
				Statement statement = connection.createStatement();
				statement.executeUpdate("UPDATE \"Assignment-6\".person SET \"First_Name\" = '" + person.getFirstName()
						+ "',  \"Last_Name\" = '" + person.getLastName() + "', \"Age\" = " + person.getAge()
						+ ", \"Location\" = " + person.getLocation() + " WHERE \"Id\" = " + person.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return person;
		}
		return person;

	}

	public Set<Person> findInterestGroup(Interest interest, Location location) {
		Set<Person> interestGroup = new HashSet<>();
		try (Connection connection = DriverManager.getConnection(this.url, this.user, this.password)) {
			Statement statement = connection.createStatement();
			String query = "SELECT \"Assignment-6\".interest.\"Title\", \"Assignment-6\".person.\"First_Name\", \"Assignment-6\".person.\"Last_Name\", \"Assignment-6\".location.\"City\" "
					+ "FROM \"Assignment-6\".interest "
					+ "JOIN \"Assignment-6\".person_interest ON \"Assignment-6\".person_interest.interest_id = \"Assignment-6\".interest.\"Id\" "
					+ "JOIN \"Assignment-6\".person ON \"Assignment-6\".person_interest.person_id = \"Assignment-6\".person.\"Id\" "
					+ "JOIN \"Assignment-6\".location ON \"Assignment-6\".person.\"Location\" = \"Assignment-6\".location.\"Id\" "
					+ "WHERE \"Assignment-6\".location.\"City\" = '" + location.getCity() + "'"
					+ "AND \"Assignment-6\".interest.\"Title\" = '" + interest.getTitle() + "'";
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				interestGroup.add(new Person(rs.getString("First_Name"), rs.getString("Last_Name")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return interestGroup;
	}

}

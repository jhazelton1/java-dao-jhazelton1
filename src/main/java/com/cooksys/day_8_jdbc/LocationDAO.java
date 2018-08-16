package com.cooksys.day_8_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.postgresql.Driver;

public class LocationDAO {

	private Driver d = new Driver();
	private String url;
	private String user;
	private String password;

	public LocationDAO(String url, String user, String password) {
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

	public Location get(int id) throws Exception {
		Location location = null;
		try (Connection connection = DriverManager.getConnection(this.url, this.user, this.password)) {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM \"Assignment-6\".location WHERE \"Id\"= '" + id + "'");
			while (rs.next()) {
				location = new Location(rs.getInt("Id"), rs.getString("City"), rs.getString("State"),
						rs.getString("Country"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (location == null) {
			throw new Exception("This ID wasn't found in the database.");
		}

		return location;
	}

	public Location save(Location location) throws Exception {
		if (location.getId() == 0) {
			try (Connection connection = DriverManager.getConnection(this.url, this.user, this.password)) {
				Statement statement = connection.createStatement();
				statement.executeUpdate("INSERT INTO \"Assignment-6\".location (\"City\", \"State\", \"Country\") "
						+ "VALUES ('" + location.getCity() + "', '" + location.getState() + "', '"
						+ location.getCountry() + "')");
				ResultSet rs = statement.executeQuery(
						"SELECT * FROM \"Assignment-6\".location WHERE \"City\"= '" + location.getCity() + "'");
				if (rs.next()) {
					return new Location(rs.getInt("Id"), rs.getString("City"), rs.getString("State"),
							rs.getString("Country"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (get(location.getId()) == null) {
			throw new Exception("ID is not null and it doesn't exist in database");
		} else {
			try (Connection connection = DriverManager.getConnection(this.url, this.user, this.password)) {
				Statement statement = connection.createStatement();
				statement.executeUpdate("UPDATE \"Assignment-6\".location SET \"City\" = '" + location.getCity()
						+ "',  \"State\" = '" + location.getState() + "', \"Country\" = '" + location.getCountry()
						+ "' WHERE \"Id\" = " + location.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return location;
		}
		return location;
	}

}

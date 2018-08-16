package com.cooksys.day_8_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.postgresql.Driver;

public class InterestDAO {

	private Driver d = new Driver();
	private String url;
	private String user;
	private String password;

	public InterestDAO(String url, String user, String password) {
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

	public Interest get(int id) throws Exception {
		Interest interest = null;
		try (Connection connection = DriverManager.getConnection(this.url, this.user, this.password)) {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM \"Assignment-6\".interest WHERE \"Id\"= '" + id + "'");
			while (rs.next()) {
				interest = new Interest(rs.getInt("Id"), rs.getString("Title"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (interest == null) {
			throw new Exception("This ID wasn't found in the database.");
		}

		return interest;
	}

	public Interest save(Interest interest) throws Exception {
		if (interest.getId() == 0) {
			try (Connection connection = DriverManager.getConnection(this.url, this.user, this.password)) {
				Statement statement = connection.createStatement();
				statement.executeUpdate("INSERT INTO \"Assignment-6\".interest (\"Title\") " + "VALUES ('"
						+ interest.getTitle() + "')");
				ResultSet rs = statement.executeQuery(
						"SELECT * FROM \"Assignment-6\".location WHERE \"Title\"= '" + interest.getTitle() + "'");
				if (rs.next()) {
					return new Interest(rs.getInt("Id"), rs.getString("Title"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (get(interest.getId()) == null) {
			throw new Exception("ID is not null and it doesn't exist in database");
		} else {
			try (Connection connection = DriverManager.getConnection(this.url, this.user, this.password)) {
				Statement statement = connection.createStatement();
				statement.executeUpdate("UPDATE \"Assignment-6\".location SET \"Title\" = '" + interest.getTitle()
						+ "' WHERE \"Id\" = " + interest.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return interest;
		}
		return interest;
	}
}

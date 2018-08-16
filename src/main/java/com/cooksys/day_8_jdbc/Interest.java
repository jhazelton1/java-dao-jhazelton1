package com.cooksys.day_8_jdbc;

public class Interest {

	private int id;
	private String title;

	public Interest() {

	}

	public Interest(String title) {
		this.title = title;
	}

	public Interest(int id, String title) {
		this.id = id;
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Interest [id=" + id + ", title=" + title + "]";
	}

}

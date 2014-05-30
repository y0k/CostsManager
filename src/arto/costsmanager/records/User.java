package arto.costsmanager.records;

public class User {
	
	private String name;
	private int id;
	
	public User(String name) {
		this.name = name;
	}
	
	public User(String name, int id) {
		this.id = id;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}
	
}

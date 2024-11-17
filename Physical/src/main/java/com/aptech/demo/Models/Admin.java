package com.aptech.demo.Models;

public class Admin {

	private int ID;
	private String Username;
	private String Password;
    
    public int getID() {
		return ID;
	}
	public void setID(int ID) {
		this.ID = ID;
	}
	public Admin() {
    }
    public Admin(int ID, String Username, String Password) {
		super();
		this.ID = ID;
		this.Username = Username;
		this.Password = Password;
	}
    
    public String getUsername() {
    	return Username;
    }
    public void setUsername(String Username) {
    	this.Username = Username;
    }
    
    public String getPassword() {
    	return Password;
    }
    public void setPassword(String Password) {
    	this.Password = Password;
    }
    
}

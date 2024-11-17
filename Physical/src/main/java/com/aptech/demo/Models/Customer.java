package com.aptech.demo.Models;

public class Customer {

    private int Stt;
    private Long ID;
    private String Name;
    private String Birthday;
    private String Address;
    private String Phone;
    private String Email;
    private String Sex;
    private String Username;
    private String Password;
    private String CreateAt;

    public Customer() {
    }

	public Customer(int stt, Long iD, String name, String birthday, String address, String phone, String email,
			String sex, String username, String password, String createAt) {
		super();
		this.Stt = stt;
		ID = iD;
		Name = name;
		Birthday = birthday;
		Address = address;
		Phone = phone;
		Email = email;
		Sex = sex;
		Username = username;
		Password = password;
		CreateAt = createAt;
	}

	public int getStt() {
		return Stt;
	}

	public void setStt(int stt) {
		this.Stt = stt;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getBirthday() {
		return Birthday;
	}

	public void setBirthday(String birthday) {
		Birthday = birthday;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getCreateAt() {
		return CreateAt;
	}

	public void setCreateAt(String createAt) {
		CreateAt = createAt;
	}
    
}

package com.masai.dto;

public class UserImpl implements User{

	private int userId;
	private String username;
	private String password;
	private String fName;
	private String lName;
	private String address;
	private String mobNo;
	
	public UserImpl(String username, String password, String fName, String lName, String address, String mobNo) {
		super();
		this.username = username;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
		this.address = address;
		this.mobNo = mobNo;
	}

	public UserImpl() {
		// TODO Auto-generated constructor stub
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobNo() {
		return mobNo;
	}

	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}

	@Override
	public String toString() {
		return "UserImpl [userId=" + userId + ", username=" + username + ", password=" + password + ", fName=" + fName
				+ ", lName=" + lName + ", address=" + address + ", mobNo=" + mobNo + "]";
	}
	
}

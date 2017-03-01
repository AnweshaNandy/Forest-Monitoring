package com.forestmonitoring.model;

import java.io.Serializable;

public class UserClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name = "";
	private String userId = "";
	private String id = "";
	private String databaseId = "";
	private String phone = "";
	private String email = "";
	private String userType= "";
	private String password = "";
	private boolean isRemember = false;
	private boolean isLoggedin = false;
	private boolean isAllDataFetched = false;

	
	
	

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDatabaseId() {
		return databaseId;
	}

	public void setDatabaseId(String databaseId) {
		this.databaseId = databaseId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getIsRemember() {
		return isRemember;
	}

	public void setIsRemember(boolean isRemember) {
		this.isRemember = isRemember;
	}

	public boolean getIsLoggedin() {
		return isLoggedin;
	}

	public void setIsLoggedin(boolean isLoggedin) {
		this.isLoggedin = isLoggedin;
	}

	public boolean getIsAllDataFetched() {
		return isAllDataFetched;
	}

	public void setIsAllDataFetched(boolean isAllDataFetched) {
		this.isAllDataFetched = isAllDataFetched;
	}

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}

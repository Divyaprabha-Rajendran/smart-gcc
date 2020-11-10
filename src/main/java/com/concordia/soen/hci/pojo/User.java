package com.concordia.soen.hci.pojo;

import java.util.ArrayList;

public class User {
	
	String userName;
	String userType;
	String password;
	ArrayList<String> freq_options = new ArrayList<String>();
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList<String> getFreq_options() {
		return freq_options;
	}
	public void setFreq_options(String option) {
		this.freq_options.add(option);
	}
	
	

}

package kr.re.ec.bibim.vo;

import java.io.Serializable;

public class UserData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userid;
	private String name;
	private String password;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
}

package ru.community.database.units;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//Свой класс пользователя, который включает в себя больше полей, чем простой пользователь спринг секурити
//My custom user class, with more fields than simple springsecurity user
@Entity
@Table(name="users")
public class customUser{
	
	public customUser(String username, String password, boolean enabled, String visibleName) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.visibleName = visibleName;
	}
	public customUser(){}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int ID;
	private String username;
	private String password;
	private boolean enabled;
	private String visibleName;
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getVisibleName() {
		return visibleName;
	}
	public void setVisibleName(String visibleName) {
		this.visibleName = visibleName;
	}
}
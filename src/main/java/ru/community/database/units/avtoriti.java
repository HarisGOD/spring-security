package ru.community.database.units;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//Для меня бесполезная таблица, но лишь потому, что проект недостаточно развит
//table with authorities - not a lot used
@Entity
@Table(name="authorities")
public class avtoriti {
	public avtoriti() {
		super();
	}
	public avtoriti(String username, String authority) {
		super();
		this.username = username;
		this.authority = authority;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int ID;
	
	private String username;
	private String authority;
	public int getID() {
		return ID;
	}
	public String getUsername() {
		return username;
	}
	public String getAuthority() {
		return authority;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
}
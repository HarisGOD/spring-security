package ru.community.database.units;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="nametags")
public class nametag{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String ownername;
	public nametag() {
		super();
	}
	public nametag(int id, String name, String ownername) {
		super();
		this.id = id;
		this.name = name;
		this.ownername = ownername;
	}
	public int getId() {
		return id;
	}
	public nametag(String name, String ownername) {
		super();
		this.name = name;
		this.ownername = ownername;
	}
	public String getName() {
		return name;
	}
	public String getOwnername() {
		return ownername;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}
	
}
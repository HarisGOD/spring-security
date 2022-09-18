package ru.community.database.units;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="attachment")
public class attached {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String url;
	private String ownername;
	public int getId() {
		return id;
	}
	public String getUrl() {
		return url;
	}
	public String getOwnername() {
		return ownername;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}
	public attached(int id, String url, String ownername) {
		super();
		this.id = id;
		this.url = url;
		this.ownername = ownername;
	}
	public attached() {
		super();
	}
	
}
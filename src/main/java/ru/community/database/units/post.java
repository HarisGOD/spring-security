package ru.community.database.units;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ru.community.PostingController;

@Entity
@Table(name="posts")
public class post {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int ID;  //Unique
	private String name;
	private String context;
	private String ownername;
	public int getID() {
		return ID;
	}
	public String getName() {
		return name;
	}
	public post(String name, String context, String ownername) {
		super();
		this.name = name;
		this.context = context;
		this.ownername = ownername;
	}
	public String getOwnername() {
		return ownername;
	}
	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}
	public String getContext() {
		return context;
	}
	public String getShortContext() {
		if (context.length()>30) { 
		return context.substring(30)+"...";}
		else {return context;}
	}
	
	public void setID(int iD) {
		ID = iD;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public post() {
		super();
	}
	//Просто не могу доделать метод, по хорошему нужно выдавать какую-то другую ошибку
	public String getVisibleNameBySelectedID() {
		String a;
		try {
			
		a = PostingController.customUser_repo().findByUsername(this.ownername).getVisibleName();//.getById(i).getVisibleName();
		} catch( Exception e) { a = "wtfidk";}
		return a;
	}
}
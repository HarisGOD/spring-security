package ru.community.database.units;

import java.io.File;
import java.io.IOException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;
//класс изображения, который включает в себя сохранение картинок, пока-что просто на мой жд
//image class, with saving image logic, on my winchester
@Entity
@Table(name="gallery")
public class Image{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String parentName;
	private String urlToPicture;
	public Image() {
		super();
	}
	public Image(String parentId, String urlToPicture) {
		super();
		
		this.parentName = parentId;
		this.urlToPicture = urlToPicture;
	}
	public int getId() {
		return id;
	}
	public String getParentName() {
		return parentName;
	}
	public String getUrlToPicture() {
		return urlToPicture;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setParentName(String parentId) {
		this.parentName = parentId;
	}
	public void setUrlToPicture(String urlToPicture) {
		this.urlToPicture = urlToPicture;
	}
	public void saveImage(String suffix,MultipartFile file) {
		final String repoway="C:\\Users\\Ахмедьяновы\\eclipse-workspace\\blender-community\\src\\main\\resources\\static\\image\\";
		try {
		    // retrieve image
			File outputfile = new File(repoway+suffix+file.getOriginalFilename());
		    file.transferTo(outputfile);
		} catch (IOException e) {
			System.out.println(file.toString());
		e.printStackTrace();
		}
	}
}
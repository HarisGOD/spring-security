package ru.community.database.repos;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.community.database.units.Image;

public interface Gallery_repo extends JpaRepository<Image, Long>{
	Collection<Image> findAllByParentName(String Parentname);
	void deleteAllByParentName(String ParentName);
}
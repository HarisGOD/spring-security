package ru.community.database.repos;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.community.database.units.nametag;

public interface nametags extends JpaRepository<nametag,Long>{
	Collection<nametag> findAllByOwnername(String Ownername);
	void deleteByNameAndOwnername(String name,String Ownername);
}
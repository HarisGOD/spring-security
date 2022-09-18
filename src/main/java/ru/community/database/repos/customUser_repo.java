package ru.community.database.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.community.database.units.customUser;

public interface customUser_repo extends JpaRepository<customUser,Long>{
	customUser findByUsername(String username);
	
}
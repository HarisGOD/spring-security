package ru.community.database.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.community.database.units.attached;

public interface attached_repo extends JpaRepository<attached,Long>{
	
}
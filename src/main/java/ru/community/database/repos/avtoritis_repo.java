package ru.community.database.repos;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.community.database.units.avtoriti;

public interface avtoritis_repo extends JpaRepository<avtoriti,Long>{
	avtoriti getAvtoritiByUsername(String Username);
	Collection<avtoriti> findAllAvtoritiByUsername(String Username);
}
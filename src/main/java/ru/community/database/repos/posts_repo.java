package ru.community.database.repos;

import org.springframework.data.repository.CrudRepository;

import ru.community.database.units.post;

public interface posts_repo extends CrudRepository<post,Long>{
	post findByName(String Name);
	post findByOwnername(String name);
	void deleteByName(String Name);
}
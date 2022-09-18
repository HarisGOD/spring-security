package ru.community.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.community.database.repos.avtoritis_repo;
import ru.community.database.repos.customUser_repo;
import ru.community.database.units.avtoriti;
import ru.community.database.units.customUser;

//Приязывание своей бд для пользователей спринг секурити
//Adding my Table of users for spring security
@Service
public class customUserDetailsService implements UserDetailsService{
	private customUser_repo myrepo;
	//Autowired над каждым репозиторием, чтобы всё могло работать корректно
	//@Autowired on every repository, for it work correct
	@Autowired
	avtoritis_repo avtrepo;
	
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		customUser user = findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("user not found "+username);
		}
		Collection<GrantedAuthority> roles = mapRolesToGA(avtrepo.findAllAvtoritiByUsername(username) );
		
		
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), roles);
	}
	private customUser findByUsername(String username) {
		
		return myrepo.findByUsername(username);
	}

	@Autowired
	public void setMyrepo(customUser_repo myrepo) {
		this.myrepo = myrepo;
	}
	private Collection<GrantedAuthority> mapRolesToGA(Collection<avtoriti> roles){
		return roles.stream().map(r -> new SimpleGrantedAuthority(r.getAuthority())).collect(Collectors.toList());
		
	}
}
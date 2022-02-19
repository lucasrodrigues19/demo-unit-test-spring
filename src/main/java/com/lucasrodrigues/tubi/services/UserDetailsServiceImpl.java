package com.lucasrodrigues.tubi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lucasrodrigues.tubi.entitys.User;
import com.lucasrodrigues.tubi.repositorys.UserRepository;
import com.lucasrodrigues.tubi.services.main.MainService;
import com.lucasrodrigues.tubi.utils.PasswordEncoderUtil;

@Service
public class UserDetailsServiceImpl extends MainService<User> implements UserDetailsService{
	
	private UserRepository repository;
	
	@Autowired
	public UserDetailsServiceImpl(UserRepository repository) {
		this.repository = repository;
		setRepository(repository);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return Optional.ofNullable(this.repository.findByUsername(username))
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

	@Override
	public User save(User entity) {
		if(entity == null)
			throw new RuntimeException("user is null");
		
		entity.setPassword(PasswordEncoderUtil.getInstance().encode(entity.getPassword()));
		return super.save(entity);
	}
	
	

}

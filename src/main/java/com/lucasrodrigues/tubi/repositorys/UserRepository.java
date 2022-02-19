package com.lucasrodrigues.tubi.repositorys;

import org.springframework.stereotype.Repository;

import com.lucasrodrigues.tubi.entitys.User;
import com.lucasrodrigues.tubi.repositorys.main.MainRepository;

/**
 * @author Lucas Rodrigues
 * @since 2022/02/17
 */
@Repository
public interface UserRepository extends MainRepository<User> {
	
	User findByUsername(String username);
}

package com.lucasrodrigues.tubi.repositorys;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lucasrodrigues.tubi.entitys.Anime;
import com.lucasrodrigues.tubi.repositorys.main.MainRepository;

/**
 * @author Lucas Rodrigues
 * @since 2022/02/17
 */
@Repository
public interface AnimeRepository extends MainRepository<Anime> {

	List<Anime> findByName(String name);
	
}

package com.lucasrodrigues.tubi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasrodrigues.tubi.entitys.Anime;
import com.lucasrodrigues.tubi.mapper.AnimeMapper;
import com.lucasrodrigues.tubi.repositorys.AnimeRepository;
import com.lucasrodrigues.tubi.requests.AnimePostRequestBody;
import com.lucasrodrigues.tubi.requests.AnimePutRequestBody;
import com.lucasrodrigues.tubi.services.main.MainService;

@Service
public class AnimeService extends MainService<Anime>{

	private AnimeRepository repository;
	
	@Autowired
	public AnimeService(AnimeRepository repository) {
		this.repository = repository;
		setRepository(repository);
	}
	
	public Anime save(AnimePostRequestBody animePostRequestBody) {
		Anime anime = AnimeMapper.INSTANCE.toAnime(animePostRequestBody);
		return super.save(anime);
	}

	public void updateName(AnimePutRequestBody animePutRequestBody) {
		super.updateName(animePutRequestBody.getName(), animePutRequestBody.getId());
	}
	
}

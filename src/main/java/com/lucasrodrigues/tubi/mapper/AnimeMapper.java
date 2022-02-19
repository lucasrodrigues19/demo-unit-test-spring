package com.lucasrodrigues.tubi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.lucasrodrigues.tubi.entitys.Anime;
import com.lucasrodrigues.tubi.requests.AnimePostRequestBody;
import com.lucasrodrigues.tubi.requests.AnimePutRequestBody;

@Mapper
public interface AnimeMapper {

	AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);
	
	public Anime toAnime(AnimePostRequestBody animePostRequestBody);
	
	public Anime toAnime(AnimePutRequestBody animePostRequestBody);
}

package com.lucasrodrigues.tubi.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.lucasrodrigues.tubi.entitys.Anime;
import com.lucasrodrigues.tubi.repositorys.AnimeRepository;
import com.lucasrodrigues.tubi.services.AnimeService;

@ExtendWith(SpringExtension.class)
public class AnimeServiceTests {

	@InjectMocks
	private AnimeService animeService;
	
	@Mock
	private AnimeRepository repository;
	
	
	@BeforeEach
	void setup() {
		
		Anime anime1 = createAnime("One Piece");
		Anime anime2 = createAnime("Naruto");
		
		BDDMockito.when(repository.findAll()).thenReturn(Arrays.asList(anime1,anime2));
		
		
		
	}
	
   @Test
   @DisplayName("findAll Retorne Lista De Anime QuandoForSucesso")
   void findAll_RetorneListaDeAnime_QuandoForSucesso() {
	   List<Anime> listAnimes = animeService.findAll();
	   
	   Assertions.assertThat(listAnimes).isNotNull().isNotEmpty();
	   
	   Assertions.assertThat(listAnimes).hasSize(2);
   }
	
	private Anime createAnime(String name) {
		return Anime.builder()
				.name(name)
				.dtInsert(LocalDateTime.now())
				.dtUpdate(LocalDateTime.now())
				.build();
	}
	
}

package com.lucasrodrigues.tubi.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.lucasrodrigues.tubi.entitys.Anime;
import com.lucasrodrigues.tubi.repositorys.AnimeRepository;

@DataJpaTest
@DisplayName(" Teste para Repositorio do Anime")
public class AnimeRepostoryTests {
	
	@Autowired
	private AnimeRepository animeRepository;
	
	
	@Test
	@DisplayName("save retorne um anime quando for sucesso")
	void save_ReturnAnime_WhenSuccess() {
		Anime animeSaved = createAnime("Boruto");
		
		Anime anime = animeRepository.save(animeSaved);
		Assertions.assertThat(anime).isNotNull();
		
	}


	
	@Test
	@DisplayName("findByName retorne uma lista de anime quando for sucesso")
	void findByName_ReturnListAnime_WhenSuccess() {
		Anime animeSaved = createAnime("Boruto");
		Anime animeSaved1 = createAnime("Boruto");
		
		animeRepository.save(animeSaved);
		animeRepository.save(animeSaved1);
		
		List<Anime> listAnime = animeRepository.findByName("Boruto");
		
		Assertions.assertThat(listAnime).isNotEmpty();
	}
	
	@Test
	@DisplayName("findByName retorna uma vazia quando o anime não é encontrado")
	void findByName_ReturnEmptyList_WhenSuccess() {
		Anime animeSaved = createAnime("Boruto");
		Anime animeSaved1 = createAnime("One piece");
		
		animeRepository.save(animeSaved);
		animeRepository.save(animeSaved1);
		
		List<Anime> listAnime = animeRepository.findByName("Dbz");
		
		Assertions.assertThat(listAnime).isEmpty();
	}
	
	private Anime createAnime(String name) {
		return Anime.builder()
				.name(name)
				.dtInsert(LocalDateTime.now())
				.dtUpdate(LocalDateTime.now())
				.build();
	}
	
}

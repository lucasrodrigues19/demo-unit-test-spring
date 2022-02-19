package com.lucasrodrigues.tubi.config;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.lucasrodrigues.tubi.entitys.Anime;
import com.lucasrodrigues.tubi.entitys.User;
import com.lucasrodrigues.tubi.requests.AnimePostRequestBody;
import com.lucasrodrigues.tubi.services.AnimeService;
import com.lucasrodrigues.tubi.services.UserDetailsServiceImpl;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Profile("test")
@Configuration
public class ConfigTest implements CommandLineRunner{

	@Autowired
	private AnimeService animeService;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Override
	public void run(String... args) throws Exception {
		try {
			
			User userAdmin = userDetailsServiceImpl.save(createUser("Admin", "ROLE_ADMIN,ROLE_USER", "test", "_admin"));
			User userUser = userDetailsServiceImpl.save(createUser("Padrão", "ROLE_USER", "test", "_padrao"));
	
			
			Anime animeSaved1 = animeService.save(createAnimePostRequestBody( "Boruto"));
			Anime animeSaved2 = animeService.save(createAnimePostRequestBody( "Naruto clássico"));
			Anime animeSaved3 = animeService.save(createAnimePostRequestBody( "Dbz classico"));
			Anime animeSaved4 = animeService.save(createAnimePostRequestBody( "One Piece"));
			
			System.out.println("-------------------------------");
			System.out.println();
			
			if(animeSaved1 != null) 
				log.info(animeSaved1.getId() + " - " + animeSaved1.toString());
			
			System.out.println();
			System.out.println("-------------------------------");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	private AnimePostRequestBody createAnimePostRequestBody(String name) {
		return AnimePostRequestBody.builder().name(name).build();
	}
	
	private Anime createAnime(UUID id, String name) {
		return Anime.builder().id(id).name(name).build();
	}
	

	
	private User createUser(String name, String authorities, String password, String username) {
		return User.builder().username(username).name(name).authorities(authorities).password(password).build();
	}

}

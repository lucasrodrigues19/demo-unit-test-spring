package com.lucasrodrigues.tubi.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucasrodrigues.tubi.entitys.Anime;
import com.lucasrodrigues.tubi.requests.AnimePostRequestBody;
import com.lucasrodrigues.tubi.requests.AnimePutRequestBody;
import com.lucasrodrigues.tubi.services.AnimeService;

@RestController
@RequestMapping("/anime")
public class AnimeController {

	@Autowired
	private AnimeService service;
	
	
	@GetMapping
	public ResponseEntity<List<Anime>> findAll(){
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Anime> findById(@PathParam("id") UUID id){
		return ResponseEntity.ok().body(service.findById(id));
	}
	
	@PostMapping("/admin/save")
	public ResponseEntity<Anime> save(@Valid @RequestBody AnimePostRequestBody animePostRequestBody){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(animePostRequestBody));
	}
	
	@PutMapping("/admin/update")
	public ResponseEntity<Anime> update(@Valid @RequestBody AnimePutRequestBody animePutRequestBody){
		service.updateName(animePutRequestBody);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
	
	@DeleteMapping("/admin/delete/{id}")
	public ResponseEntity<Anime> delete(@PathParam("id") UUID id){
		service.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
	
	
}

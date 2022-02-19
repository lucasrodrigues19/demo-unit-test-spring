package com.lucasrodrigues.tubi.services.main;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.MappedSuperclass;

import com.lucasrodrigues.tubi.entitys.main.MainEntity;
import com.lucasrodrigues.tubi.exception.BadRequestException;
import com.lucasrodrigues.tubi.repositorys.main.MainRepository;

/**
 * @author Lucas Rodrigues
 * @since 2022/02/17
 */
@MappedSuperclass
public abstract class MainService<E extends MainEntity> {

	private MainRepository<E> repository;
	
	public List<E> findAll(){
		return this.repository.findAll();
	}
	
	public E findById(UUID id){
		return this.repository.findById(id).orElseThrow(() -> new BadRequestException("anime not found"));
	}
	
	public void deleteById(UUID id){
		this.repository.deleteById(id);
	}
	
	public void updateName(String name, UUID id){
		this.repository.updateNameById(name, id);
	}
	
	public E save(E entity) {
		E entityExisting = null;
		
		if(entity == null)
			throw new RuntimeException("entity is null");
		
		if(entity.getId() != null)
			entityExisting = findById(entity.getId());
		
		if(entityExisting == null)
			entity.setDtInsert(LocalDateTime.now());
		
		entity.setDtUpdate(LocalDateTime.now());
		
		return this.repository.save(entity);
	}
	
	public void setRepository(MainRepository<E> repository) {
		this.repository = repository;
	}
}

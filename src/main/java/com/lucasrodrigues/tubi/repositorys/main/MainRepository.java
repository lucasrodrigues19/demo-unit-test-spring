package com.lucasrodrigues.tubi.repositorys.main;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import com.lucasrodrigues.tubi.entitys.main.MainEntity;

/**
 * @author Lucas Rodrigues
 * @since 2022/02/17
 */
@NoRepositoryBean
public interface MainRepository<E extends MainEntity> extends JpaRepository<E, UUID> {

	@Modifying
	@Query(nativeQuery = true,value = "UPDATE #{#entityName} SET #{#entityName}.name = :name where #{#entityName}.id = :id")
	public void updateNameById(@Param("name") String name, @Param("id")UUID id);
}

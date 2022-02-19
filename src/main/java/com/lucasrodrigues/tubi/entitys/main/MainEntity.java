package com.lucasrodrigues.tubi.entitys.main;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author Lucas Rodrigues
 * @since 2022/02/17
 */
@SuperBuilder
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class MainEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Type(type = "uuid-char")
	private UUID id;
	
	@Column(length = 255, nullable = false)
	private String name;
	
	@Column(nullable = false)
	private LocalDateTime dtInsert;
	
	@Column(nullable = false)
	private LocalDateTime dtUpdate;
}

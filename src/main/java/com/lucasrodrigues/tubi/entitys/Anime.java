package com.lucasrodrigues.tubi.entitys;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.lucasrodrigues.tubi.entitys.main.MainEntity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Lucas Rodrigues
 * @since 2022/02/17
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "tbanime")
@SuperBuilder
public class Anime extends MainEntity {

	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "Anime [Id=" + getId() + ", Name="+ getName() + "]";
	}
}

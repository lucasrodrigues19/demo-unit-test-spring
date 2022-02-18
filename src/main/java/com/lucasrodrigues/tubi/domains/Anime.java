package com.lucasrodrigues.tubi.domains;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.lucasrodrigues.tubi.domains.main.MainEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tbanime")
public class Anime extends MainEntity {

	@Override
	public String toString() {
		return "Anime [Id=" + getId() + ", Name="+ getName() + "]";
	}
}

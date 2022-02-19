package com.lucasrodrigues.tubi.requests;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnimePutRequestBody {

	@NotNull
	private UUID id;
	
	@NotBlank
	private String name;
}

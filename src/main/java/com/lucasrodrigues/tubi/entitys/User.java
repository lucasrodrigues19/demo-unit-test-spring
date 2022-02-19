package com.lucasrodrigues.tubi.entitys;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
@Table(name = "tbuser")
@SuperBuilder
public class User extends MainEntity implements UserDetails{
	
	private static final long serialVersionUID = 1L;

	@Column(name = "username",nullable = false)
	private String username;
	
	@Column(name = "password",nullable = false)
	private String password;
	
	@Column(name = "authorities",nullable = false)
	private String authorities;

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.stream(this.authorities.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	
}

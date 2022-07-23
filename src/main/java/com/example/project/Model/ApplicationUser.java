package com.example.project.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class ApplicationUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	public String user_email;
	@Id
	@JsonProperty("user_name")
	public String username;
	public String password;
	public String user_mobile;
	public String location;

	public void setUsername(String user_name) {
		username = user_name;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_mobile() {
		return user_mobile;
	}

	public void setUser_mobile(String user_mobile) {
		this.user_mobile = user_mobile;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public ApplicationUser(String user_name, String user_email, String password, String user_mobile, String location) {
		super();
		username = user_name;
		this.user_email = user_email;
		this.password = password;
		this.user_mobile = user_mobile;
		this.location = location;
	}

	public ApplicationUser(String user_name, String password) {
		super();
		username = user_name;
		this.password = password;
	}

	public ApplicationUser() {
	}

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority("admin"));
		return roles;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String toString() {
		return "ApplicationUser [user_email=" + user_email + ", username=" + username + ", password=" + password
				+ ", user_mobile=" + user_mobile + ", location=" + location + "]";
	}

}

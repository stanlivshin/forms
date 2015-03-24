package com.usbzoso.forms.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.codehaus.jackson.map.annotate.JsonView;
import org.hibernate.validator.constraints.NotBlank;

import com.usbzoso.forms.domain.user.UserRole;

@Entity
public class User extends BaseEntity {

	public interface WithoutPasswordView {};
	
	@Column(nullable = false, unique = true)
	@NotBlank
	private String username;

	@Column(nullable = false)
	private String passwordHash;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private UserRole role;

	@JsonView(WithoutPasswordView.class)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", username='"
				+ username.replaceFirst("@.*", "@***") + ", passwordHash='"
				+ passwordHash.substring(0, 10) + ", role=" + role + '}';
	}
}

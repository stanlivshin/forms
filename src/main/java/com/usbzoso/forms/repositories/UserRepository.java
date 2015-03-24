package com.usbzoso.forms.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usbzoso.forms.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findOneByUsername(String username);
}

package com.ufcg.psoft.model.repositories;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import com.ufcg.psoft.model.User;


@Repository
public interface UserDAO extends JpaRepository<User, Long> {
	
	public Optional<User> findByEmail(String email);
}

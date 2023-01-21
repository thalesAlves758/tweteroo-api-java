package com.tweteroo.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tweteroo.api.model.TweterooUser;

public interface UserRepository extends JpaRepository<TweterooUser, Long> {
  public Optional<TweterooUser> findByUsername(String username);
}

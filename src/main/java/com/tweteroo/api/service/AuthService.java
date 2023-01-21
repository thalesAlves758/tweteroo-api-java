package com.tweteroo.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweteroo.api.exception.UserAlreadyExistsException;
import com.tweteroo.api.model.TweterooUser;
import com.tweteroo.api.repository.UserRepository;

@Service
public class AuthService {
  @Autowired
  private UserRepository userRepository;

  public TweterooUser signUp(TweterooUser user) throws UserAlreadyExistsException {
    Optional<TweterooUser> foundUser = userRepository.findByUsername(user.getUsername());

    if (foundUser.isPresent()) {
      throw new UserAlreadyExistsException();
    }

    return userRepository.save(user);
  }
}

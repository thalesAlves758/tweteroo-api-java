package com.tweteroo.api.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweteroo.api.dto.SignUpDto;
import com.tweteroo.api.exception.UserAlreadyExistsException;
import com.tweteroo.api.model.TweterooUser;
import com.tweteroo.api.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth/sign-up")
@CrossOrigin(origins = "*")
public class AuthController {
  @Autowired
  private AuthService authService;

  @Autowired
  private ModelMapper modelMapper;

  @PostMapping()
  public ResponseEntity signUp(@RequestBody @Valid SignUpDto body) {
    TweterooUser user = modelMapper.map(body, TweterooUser.class);

    try {
      authService.signUp(user);
      return new ResponseEntity<>("OK", HttpStatus.CREATED);
    } catch (UserAlreadyExistsException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
  }
}

package com.tweteroo.api.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweteroo.api.dto.TweetDTO;
import com.tweteroo.api.exception.UserNotExists;
import com.tweteroo.api.model.Tweet;
import com.tweteroo.api.service.TweetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tweets")
public class TweetController {
  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private TweetService tweetService;

  @PostMapping()
  public ResponseEntity createTweet(@RequestBody @Valid TweetDTO body) {
    Tweet tweet = modelMapper.map(body, Tweet.class);

    try {
      tweetService.saveTweet(tweet);
      return new ResponseEntity<>("OK", HttpStatus.CREATED);
    } catch (UserNotExists e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
  }
}

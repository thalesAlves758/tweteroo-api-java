package com.tweteroo.api.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweteroo.api.dto.ResponseTweetDTO;
import com.tweteroo.api.dto.TweetDTO;
import com.tweteroo.api.exception.UserNotExists;
import com.tweteroo.api.model.Tweet;
import com.tweteroo.api.service.TweetService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tweets")
@CrossOrigin(origins = "*")
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

  @GetMapping()
  public List<ResponseTweetDTO> getTweets(
      @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 5) Pageable page) {
    List<Tweet> tweets = tweetService.getTweets(page);

    List<ResponseTweetDTO> response = tweets.stream().map((tweet) -> modelMapper.map(tweet, ResponseTweetDTO.class))
        .toList();

    return response;
  }

  @GetMapping("/{username}")
  public List<ResponseTweetDTO> getTweetsFromUser(@PathVariable String username) {
    List<Tweet> tweets = tweetService.getTweetsByUsername(username);

    List<ResponseTweetDTO> response = tweets.stream().map((tweet) -> modelMapper.map(tweet, ResponseTweetDTO.class))
        .toList();

    return response;
  }
}

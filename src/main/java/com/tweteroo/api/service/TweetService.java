package com.tweteroo.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweteroo.api.exception.UserNotExists;
import com.tweteroo.api.model.Tweet;
import com.tweteroo.api.model.TweterooUser;
import com.tweteroo.api.repository.TweetRepository;
import com.tweteroo.api.repository.UserRepository;

@Service
public class TweetService {
  @Autowired
  private TweetRepository tweetRepository;

  @Autowired
  private UserRepository userRepository;

  public Tweet saveTweet(Tweet tweet) throws UserNotExists {
    Optional<TweterooUser> user = userRepository.findByUsername(tweet.getUsername());

    if (user.isEmpty()) {
      throw new UserNotExists();
    }

    tweet.setAvatar(user.get().getAvatar());

    return tweetRepository.save(tweet);
  }
}

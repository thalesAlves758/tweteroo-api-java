package com.tweteroo.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TweetDTO {
  @NotBlank
  private String username;
  @NotBlank
  private String text;
}

package com.tweteroo.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseTweetDTO {
  @NotBlank
  private String username;
  @NotBlank
  private String avatar;
  @NotBlank
  private String text;
}

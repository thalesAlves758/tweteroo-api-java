package com.tweteroo.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpDto {
  @NotBlank
  private String username;
  @NotBlank
  private String avatar;
}

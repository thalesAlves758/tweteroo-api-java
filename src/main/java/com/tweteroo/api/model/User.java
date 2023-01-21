package com.tweteroo.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class User {
  @Column(unique = true)
  private String username;

  private String avatar;
}

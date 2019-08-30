package com.altigee.needoff.graphqlapi.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GqlAccount {

  private Long id;
  private String email;
  private String status;
  private List<String> roles;
  private LocalDateTime createdTime;
  private LocalDateTime updatedTime;
}

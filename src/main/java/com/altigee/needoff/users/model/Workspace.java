package com.altigee.needoff.users.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity @Data
public class Workspace {
  // todo
  @Id
  private Long id;
}

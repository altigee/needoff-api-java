package com.altigee.needoff.shared.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data @Builder @JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorDetails {
  private Integer code;
  private String message;
}

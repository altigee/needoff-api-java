package com.altigee.needoff.graphqlapi.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ImmutableMap;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;
import java.util.Map;

public abstract class GqlAbstractError extends RuntimeException implements GraphQLError {
  private String message;
  private long errorCode;

  public GqlAbstractError(String message) {
    this.message = message;
  }

  public GqlAbstractError(String message, long errorCode) {
    this.message = message;
    this.errorCode = errorCode;
  }

  @Override
  public String getMessage() {
    return this.message;
  }

  @Override
  public List<SourceLocation> getLocations() {
    return null;
  }

  @Override
  public ErrorType getErrorType() {
    return null;
  }

  @Override
  @JsonIgnore
  public StackTraceElement[] getStackTrace() {
    return super.getStackTrace();
  }

  @Override
  public Map<String, Object> getExtensions() {
    return ImmutableMap.of("errorCode", this.errorCode);
  }
}

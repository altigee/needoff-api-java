package com.altigee.needoff.graphqlapi.error;

public class GqlProfileNotFoundError extends GqlAbstractError {
  public GqlProfileNotFoundError() {
    super("User Profile not found");
  }
}
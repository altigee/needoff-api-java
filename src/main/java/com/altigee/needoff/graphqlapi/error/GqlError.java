package com.altigee.needoff.graphqlapi.error;

public class GqlError extends GqlAbstractError {

    public GqlError(String message) {
        super(message);
    }

    public GqlError(String message, long errorCode) {
        super(message, errorCode);
    }
}

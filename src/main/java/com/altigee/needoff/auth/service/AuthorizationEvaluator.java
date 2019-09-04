package com.altigee.needoff.auth.service;

public interface AuthorizationEvaluator {

    boolean hasPermission(Long resourceId);
}

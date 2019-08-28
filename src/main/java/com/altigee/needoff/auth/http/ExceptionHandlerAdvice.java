package com.altigee.needoff.auth.http;

import com.altigee.needoff.shared.dto.ErrorDetails;
import com.altigee.needoff.auth.exception.ExpiredTokenException;
import com.altigee.needoff.auth.exception.InvalidTokenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandlerAdvice {
  private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

  @ExceptionHandler(InvalidTokenException.class)
  public ResponseEntity<ErrorDetails> handleInvalidToken(InvalidTokenException ex, WebRequest request) {
    return ResponseEntity.status(401).body(ErrorDetails.builder().message(ex.getMessage()).build());
  }

  @ExceptionHandler(ExpiredTokenException.class)
  public ResponseEntity<ErrorDetails> handleExpiredToken(ExpiredTokenException ex, WebRequest request) {
    return ResponseEntity.status(401).body(ErrorDetails.builder().message(ex.getMessage()).build());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorDetails> handleValidationError(MethodArgumentNotValidException ex, WebRequest request) {
    // var bindingResult = ex.getBindingResult(); todo send all errors
    return ResponseEntity.status(400).body(ErrorDetails.builder().message(ex.getMessage()).build());
  }

  @ExceptionHandler(Throwable.class)
  public ResponseEntity<ErrorDetails> handleGeneric(Throwable ex, WebRequest request) {
    LOG.error("Unknown exception", ex);
    return ResponseEntity.status(500).body(ErrorDetails.builder().message(ex.getMessage()).build());
  }
}

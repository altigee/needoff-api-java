package com.altigee.needoff.shared.http;

import com.altigee.needoff.shared.dto.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestController
public class NotFoundController {

  @ExceptionHandler
  @ResponseStatus(value= HttpStatus.NOT_FOUND)
  @ResponseBody
  public ErrorDetails requestHandlingNoHandlerFound(NoHandlerFoundException ex) {
    return ErrorDetails.builder().message("This path was not found on the server").build();
  }

}

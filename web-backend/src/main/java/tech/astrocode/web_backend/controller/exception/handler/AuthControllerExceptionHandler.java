package tech.astrocode.web_backend.controller.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tech.astrocode.web_backend.controller.exception.handler.body.ExceptionResponseBody;
import tech.astrocode.web_backend.domain.utils.InstantUtils;
import tech.astrocode.web_backend.service.auth.exception.AuthException;
import tech.astrocode.web_backend.service.auth.exception.LoginException;

@ControllerAdvice("tech.astrocode.web_backend.controller.authentication")
public class AuthControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { LoginException.class })
    protected ResponseEntity<ExceptionResponseBody> handleLoginException(final LoginException anException,
                                                                         final HttpServletRequest aRequest){
        final var body = new ExceptionResponseBody(
                InstantUtils.now(),
                HttpStatus.BAD_REQUEST.value(),
                anException.getMessage(),
                aRequest.getRequestURI());

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(value = { AuthException.class })
    protected ResponseEntity<ExceptionResponseBody> handleAuthException(final AuthException anException,
                                                                         final HttpServletRequest aRequest){
        final var body = new ExceptionResponseBody(
                InstantUtils.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                anException.getMessage(),
                aRequest.getRequestURI());

        return ResponseEntity.internalServerError().body(body);
    }

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<ExceptionResponseBody> handleAnotherException(final Exception anException,
                                                                        final HttpServletRequest aRequest){
        final var body = new ExceptionResponseBody(
                InstantUtils.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                anException.getMessage(),
                aRequest.getRequestURI());

        return ResponseEntity.internalServerError().body(body);
    }

}

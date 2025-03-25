package tech.astrocode.web_backend.controller.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tech.astrocode.web_backend.controller.exception.handler.body.ExceptionResponseBody;
import tech.astrocode.web_backend.domain.exception.DomainException;
import tech.astrocode.web_backend.domain.utils.InstantUtils;
import tech.astrocode.web_backend.repository.activity.exception.PersistenceException;
import tech.astrocode.web_backend.service.exception.ServiceException;

@ControllerAdvice
public class ActivityControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { DomainException.class })
    protected ResponseEntity<ExceptionResponseBody> handleDomainException(final DomainException anException,
                                                                          final HttpServletRequest aRequest){
        final var body = new ExceptionResponseBody(
                InstantUtils.now(),
                HttpStatus.BAD_REQUEST.value(),
                anException.getMessage(),
                aRequest.getRequestURI());

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(value = { PersistenceException.class })
    protected ResponseEntity<ExceptionResponseBody> handlePersistenceException(final PersistenceException anException,
                                                          final HttpServletRequest aRequest){
        final var body = new ExceptionResponseBody(
                InstantUtils.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                anException.getMessage(),
                aRequest.getRequestURI());

        return ResponseEntity.internalServerError().body(body);
    }

    @ExceptionHandler(value = { ServiceException.class })
    protected ResponseEntity<ExceptionResponseBody> handleServiceException(final ServiceException anException,
                                                                           final HttpServletRequest aRequest){
        final var body = new ExceptionResponseBody(
                InstantUtils.now(),
                HttpStatus.BAD_REQUEST.value(),
                anException.getMessage(),
                aRequest.getRequestURI());

        return ResponseEntity.badRequest().body(body);
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

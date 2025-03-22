package tech.astrocode.web_backend.domain.exception;

public class DomainException extends RuntimeException {
    public DomainException(String aMessage){
        super(aMessage);
    }
}

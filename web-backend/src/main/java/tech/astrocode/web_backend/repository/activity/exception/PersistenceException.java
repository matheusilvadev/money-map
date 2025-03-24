package tech.astrocode.web_backend.repository.activity.exception;

public class PersistenceException extends RuntimeException {
    public PersistenceException(final String aMessage){
        super(aMessage);
    }
}

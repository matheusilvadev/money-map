package tech.astrocode.web_backend.service.auth.exception;

public class LoginException extends RuntimeException {
    public LoginException(final String aMessage){
        super(aMessage);
    }
}

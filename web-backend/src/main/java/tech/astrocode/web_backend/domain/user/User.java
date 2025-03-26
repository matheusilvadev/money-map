package tech.astrocode.web_backend.domain.user;

public class User {

    private String email;
    private String password;

    private User(final String anEmail, final String aPassword){
        this.email = anEmail;
        this.password = aPassword;
    }

    public static User with(final String anEmail, final String aPassword){
        return new User(anEmail, aPassword);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


}

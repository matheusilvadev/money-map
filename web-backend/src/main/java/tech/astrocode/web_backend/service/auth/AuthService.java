package tech.astrocode.web_backend.service.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.astrocode.web_backend.domain.user.User;
import tech.astrocode.web_backend.domain.utils.InstantUtils;
import tech.astrocode.web_backend.service.auth.dtos.LoginServiceInputDTO;
import tech.astrocode.web_backend.service.auth.dtos.LoginServiceOutputDTO;
import tech.astrocode.web_backend.service.auth.exception.AuthException;
import tech.astrocode.web_backend.service.auth.exception.LoginException;

@Service
public class AuthService implements UserDetailsService {
    //Unique User
    final User uniqueUser = User.with("user@example.com", "12345");

    private final String TOKEN_SECRET = "13579";
    private final String TOKEN_ISSUER = "MY_BACKEND";

    public LoginServiceOutputDTO login(final LoginServiceInputDTO input){
        final var anUser = User.with(input.email(), input.password());

        if (!uniqueUser.getEmail().equals(anUser.getEmail())
                || !uniqueUser.getPassword().equals(anUser.getPassword())){

            throw new LoginException("User or password not found");
        }

        final var aToken = this.createToken(anUser);

        return new LoginServiceOutputDTO(aToken);

    }

    private String createToken(User anUser) {

        try{
            final var algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            final var token = JWT.create()
                    .withIssuer(TOKEN_ISSUER)
                    .withSubject(anUser.getEmail())
                    .withExpiresAt(InstantUtils.now().plusSeconds(60 * 60 * 4)) // 4 hours
                    .sign(algorithm);

            return token;
        } catch(IllegalArgumentException | JWTCreationException e){
            throw new AuthException(e.getMessage());
        }
    }

    public String validateToken(final String token){
        try {
            final var algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //verify
            final var verifier = JWT.require(algorithm)
                    .withIssuer(TOKEN_ISSUER)
                    .build();
            //decoder
            final var decodedToken = verifier.verify(token);
            //get subject
            final var subject = decodedToken.getSubject();

            return subject;
        } catch (Exception e){
            return "";
        }
    }

    public boolean isTokenValid(final String token){
        return !validateToken(token).isEmpty();
    }


    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        if(username.equals(this.uniqueUser.getUsername())){
            return this.uniqueUser;
        } else {
            throw new UsernameNotFoundException("User nor found");
        }
    }
}

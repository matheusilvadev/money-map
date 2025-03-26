package tech.astrocode.web_backend.controller.authentication;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.astrocode.web_backend.controller.authentication.dto.LoginRequestDTO;
import tech.astrocode.web_backend.controller.authentication.dto.ValidateRequestDTO;
import tech.astrocode.web_backend.controller.authentication.dto.ValidateResponseDTO;
import tech.astrocode.web_backend.controller.authentication.dto.mapper.LoginRequestToLoginServiceInputMapper;
import tech.astrocode.web_backend.controller.authentication.dto.LoginResponseDTO;
import tech.astrocode.web_backend.service.auth.AuthService;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthService service;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid final LoginRequestDTO input){

        final var serviceInput = LoginRequestToLoginServiceInputMapper.build().apply(input);
        final var token = service.login(serviceInput).token();
        final var response = new LoginResponseDTO(token);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/validate")
    public ResponseEntity<ValidateResponseDTO> validate(@RequestBody @Valid final ValidateRequestDTO input){

        final boolean isValid = this.service.isTokenValid(input.token());
        final var response = new ValidateResponseDTO(isValid);

        return ResponseEntity.ok().body(response);
    }

}

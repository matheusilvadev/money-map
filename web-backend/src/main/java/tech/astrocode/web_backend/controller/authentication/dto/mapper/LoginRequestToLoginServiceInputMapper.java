package tech.astrocode.web_backend.controller.authentication.dto.mapper;

import tech.astrocode.web_backend.controller.authentication.dto.LoginRequestDTO;
import tech.astrocode.web_backend.service.auth.dtos.LoginServiceInputDTO;

import java.util.function.Function;

public class LoginRequestToLoginServiceInputMapper implements Function<LoginRequestDTO, LoginServiceInputDTO> {

    public static LoginRequestToLoginServiceInputMapper build(){
        return new LoginRequestToLoginServiceInputMapper();
    }

    @Override
    public LoginServiceInputDTO apply(final LoginRequestDTO input) {
        return new LoginServiceInputDTO(
                input.email(),
                input.password());
    }
}

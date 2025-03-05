package danisbagus.fleet_tracking_api.controller;

import danisbagus.fleet_tracking_api.domain.dto.*;
import danisbagus.fleet_tracking_api.service.AuthService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/register")
    public WebResponse<RegisterResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        RegisterResponse registerResponse = authService.register(registerRequest);

        return new WebResponse<>(registerResponse, "SUCCESS", HttpStatus.CREATED);
    }

    @PostMapping(path = "/login")
    public WebResponse<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = authService.login(loginRequest);

        return new WebResponse<>(loginResponse, "SUCCESS", HttpStatus.OK);
    }
}

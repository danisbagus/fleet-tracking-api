package danisbagus.fleet_tracking_api.service;

import danisbagus.fleet_tracking_api.domain.dto.*;
import danisbagus.fleet_tracking_api.domain.entity.UserEntity;
import danisbagus.fleet_tracking_api.exception.BadRequestException;
import danisbagus.fleet_tracking_api.repository.UserRepository;
import danisbagus.fleet_tracking_api.util.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public RegisterResponse register(RegisterRequest registerRequest) {
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new BadRequestException("Email already used");
        }

        String hashedPassword = passwordEncoder.encode(registerRequest.getPassword());

        UserEntity user = toUserEntity(registerRequest, hashedPassword);
        UserEntity newUser = userRepository.save(user);

        user.setId(newUser.getId());
        return toRegisterResponse(user);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        String jwtToken = jwtUtil.generateToken(loginRequest.getEmail());
        return new LoginResponse(jwtToken);
    }

    private UserEntity toUserEntity(RegisterRequest registerRequest, String hashedPassword) {
        Timestamp currentTime = Timestamp.from(Instant.now());
        return new UserEntity(
                registerRequest.getEmail(),
                hashedPassword,
                registerRequest.getRole(),
                currentTime,
                currentTime);
    }

    private RegisterResponse toRegisterResponse(UserEntity user) {
        return new RegisterResponse(
                user.getId(),
                user.getEmail(),
                user.getRole());
    }
}

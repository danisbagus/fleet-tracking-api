package danisbagus.fleet_tracking_api.service;

import danisbagus.fleet_tracking_api.domain.dto.*;
import danisbagus.fleet_tracking_api.domain.entity.Fleet;
import danisbagus.fleet_tracking_api.domain.entity.User;
import danisbagus.fleet_tracking_api.exception.BadRequestException;
import danisbagus.fleet_tracking_api.exception.NotFoundException;
import danisbagus.fleet_tracking_api.repository.UserRepository;
import danisbagus.fleet_tracking_api.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public RegisterResponse register(RegisterRequest registerRequest) {
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new BadRequestException("Email already used");
        }

        User user = toUser(registerRequest);
        User newUser = userRepository.save(user);

        user.setId(newUser.getId());
        return toRegisterResponse(user);
    }

    public LoginResponse login(LoginRequest loginRequest) {
       userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new NotFoundException("User not found"));

       try {
           authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
                           loginRequest.getEmail(),
                           loginRequest.getPassword()
                   )
           );
       } catch (Exception ex) {
           throw
       }

        if (!authentication.isAuthenticated()) {
            throw   new NotFoundException("Invalid user request");
        }

       String jwtToken = jwtUtil.generateToken(loginRequest.getEmail());
       return  new LoginResponse(jwtToken);
    }

    private User toUser(RegisterRequest registerRequest) {
        Timestamp currentTime = Timestamp.from(Instant.now());
        return  new User(
                registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword()), // todo: encode or hash password
                registerRequest.getRole(),
                currentTime,
                currentTime
        );
    }

    private RegisterResponse toRegisterResponse(User user) {
        return new RegisterResponse(
                user.getId(),
                user.getUsername(),
                user.getRole()
        );
    }
}




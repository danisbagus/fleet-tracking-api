package danisbagus.fleet_tracking_api.service;

import org.springframework.stereotype.Service;

import danisbagus.fleet_tracking_api.domain.dto.ProfileResponse;
import danisbagus.fleet_tracking_api.domain.entity.UserEntity;
import danisbagus.fleet_tracking_api.exception.BadRequestException;
import danisbagus.fleet_tracking_api.repository.UserRepository;
import danisbagus.fleet_tracking_api.util.JwtUtil;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public ProfileResponse getProfile(String token) {
        String email = jwtUtil.extractEmail(token);

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("User not found"));

        return toProfileResponse(user);
    }

    private ProfileResponse toProfileResponse(UserEntity user) {
        return new ProfileResponse(
                user.getId(),
                user.getEmail(),
                user.getRole());
    }

}

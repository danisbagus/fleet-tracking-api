package danisbagus.fleet_tracking_api.controller;

import danisbagus.fleet_tracking_api.domain.dto.ProfileResponse;
import danisbagus.fleet_tracking_api.domain.dto.WebResponse;
import danisbagus.fleet_tracking_api.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/profile")
    public WebResponse<ProfileResponse> profile(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        ProfileResponse profileResponse = userService.getProfile(token);

        return new WebResponse<>(profileResponse, "SUCCESS", 200);
    }
}

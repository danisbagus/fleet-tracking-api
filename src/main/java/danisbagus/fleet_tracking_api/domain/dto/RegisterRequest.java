package danisbagus.fleet_tracking_api.domain.dto;

import danisbagus.fleet_tracking_api.domain.enums.RoleType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RegisterRequest {
    @NotBlank(message = "email cannot be blank")
    @Email(message = "invalid email format")
    private String email;

    @NotBlank(message = "password cannot be blank")
    private String password;

    @NotNull(message = "role cannot be null")
    private RoleType role;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public RoleType getRole() {
        return role;
    }
}

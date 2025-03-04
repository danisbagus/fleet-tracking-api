package danisbagus.fleet_tracking_api.domain.dto;

import danisbagus.fleet_tracking_api.domain.enums.RoleType;

public class RegisterResponse {
    private Integer id;

    private String email;

    private RoleType role;

    public RegisterResponse(Integer id, String email, RoleType role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }
}

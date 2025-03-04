package danisbagus.fleet_tracking_api.domain.dto;

import danisbagus.fleet_tracking_api.domain.enums.RoleType;

public class UserResponse {
    private Integer id;

    private String email;

    private String password;

    private RoleType role;
}

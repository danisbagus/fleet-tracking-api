package danisbagus.fleet_tracking_api.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import danisbagus.fleet_tracking_api.domain.enums.VehicleType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FleetRequest {
    @NotBlank(message = "Vehicle number cannot be blank")
    @Size(min = 4, message = "Vehicle number must be at least 4 characters long")
    @JsonProperty("vehicle_number")
    private String vehicleNumber;

    @NotNull(message = "Vehicle type cannot be null")
    @JsonProperty("vehicle_type")
    private VehicleType vehicleType;

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}

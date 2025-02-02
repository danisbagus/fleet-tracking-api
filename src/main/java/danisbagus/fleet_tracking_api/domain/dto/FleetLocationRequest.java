package danisbagus.fleet_tracking_api.domain.dto;

import jakarta.validation.constraints.*;

public class FleetLocationRequest {
    @NotNull(message = "Longitude cannot be blank")
    @Min(value = -180, message = "Minimum longitude -180")
    @Max(value = 180, message = "Maximum longitude 180")
    private Float longitude;

    @NotNull(message = "Latitude cannot be blank")
    @Min(value = -90, message = "Minimum latitude -90")
    @Max(value = 90, message = "Maximum latitude 90")
    private Float latitude;

    public Float getLongitude() {
        return longitude;
    }

    public Float getLatitude() {
        return latitude;
    }
}

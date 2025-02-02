package danisbagus.fleet_tracking_api.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class FleetLocationResponse {
    @JsonProperty("longitude")
    private Float longitude;

    @JsonProperty("latitude")
    private Float latitude;

    @JsonProperty("fleet_id")
    private Integer fleetId;

    @JsonProperty("created_at")
    private Timestamp createdAt;

    public FleetLocationResponse(Float longitude, Float latitude, Integer fleetId, Timestamp createdAt) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.fleetId = fleetId;
        this.createdAt = createdAt;
    }
}

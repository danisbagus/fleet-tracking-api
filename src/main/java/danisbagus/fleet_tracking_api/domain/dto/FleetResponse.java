package danisbagus.fleet_tracking_api.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import danisbagus.fleet_tracking_api.domain.enums.VehicleType;

public class FleetResponse {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("vehicle_number")
    private String vehicleNumber;

    @JsonProperty("vehicle_type")
    private VehicleType vehicleType;

    public FleetResponse(Integer id, String vehicleNumber, VehicleType vehicleType) {
        this.id = id;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}

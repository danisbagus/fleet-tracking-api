package danisbagus.fleet_tracking_api.domain.entity;

import danisbagus.fleet_tracking_api.domain.enums.VehicleType;
import java.sql.Timestamp;

public class Fleet {
    private Integer id;

    private String vehicleNumber;

    private VehicleType vehicleType;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    public Fleet() {}

    public Fleet(Integer id, String vehicleNumber, VehicleType vehicleType) {
        this.id = id;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }

    public Fleet(String vehicleNumber, VehicleType vehicleType) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }

    public Fleet(String vehicleNumber, VehicleType vehicleType, Timestamp createdAt, Timestamp updatedAt) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}

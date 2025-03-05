package danisbagus.fleet_tracking_api.domain.entity;

import danisbagus.fleet_tracking_api.domain.dto.FleetRequest;
import danisbagus.fleet_tracking_api.domain.enums.VehicleType;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "ms_fleet")
@SequenceGenerator(name = "fleet_seq_generator", sequenceName = "fleet_id_seq", allocationSize = 1)
public class FleetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fleet_seq_generator")
    private Integer id;

    @Column(name = "vehicle_number")
    private String vehicleNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type")
    private VehicleType vehicleType;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public FleetEntity() {
    }

    public FleetEntity(Integer id, String vehicleNumber, VehicleType vehicleType) {
        this.id = id;
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }

    public FleetEntity(String vehicleNumber, VehicleType vehicleType) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }

    public FleetEntity(String vehicleNumber, VehicleType vehicleType, Timestamp createdAt, Timestamp updatedAt) {
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

    public void setWithFleetRequest(FleetRequest request) {
        if (request == null) {
            return;
        }

        this.vehicleNumber = request.getVehicleNumber();
        this.vehicleType = request.getVehicleType();
        this.updatedAt = Timestamp.from(Instant.now());
    }
}

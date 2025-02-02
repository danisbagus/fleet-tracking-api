package danisbagus.fleet_tracking_api.domain.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
public class FleetLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "fleet_id")
    private Integer fleetId;

    @Column(name = "latitude")
    private Float latitude;

    @Column(name = "longitude")
    private Float longitude;

    @Column(name = "created_at")
    private Timestamp createdAt;

    public FleetLocation() {
    }

    public FleetLocation(Integer fleetId, Float latitude, Float longitude, Timestamp createdAt) {
        this.fleetId = fleetId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createdAt = createdAt;
    }

    public Integer getFleetId() {
        return fleetId;
    }

    public Float getLatitude() {
        return latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}

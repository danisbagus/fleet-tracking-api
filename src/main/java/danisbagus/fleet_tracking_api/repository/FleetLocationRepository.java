package danisbagus.fleet_tracking_api.repository;

import danisbagus.fleet_tracking_api.domain.entity.FleetLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FleetLocationRepository extends JpaRepository<FleetLocation, Integer> {
    List<FleetLocation> findByFleetIdOrderByCreatedAtDesc(Integer fleetId);
}

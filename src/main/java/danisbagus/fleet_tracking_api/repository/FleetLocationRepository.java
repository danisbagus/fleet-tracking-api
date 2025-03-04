package danisbagus.fleet_tracking_api.repository;

import danisbagus.fleet_tracking_api.domain.entity.FleetLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FleetLocationRepository extends JpaRepository<FleetLocationEntity, Integer> {
    List<FleetLocationEntity> findByFleetIdOrderByCreatedAtDesc(Integer fleetId);
}

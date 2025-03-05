package danisbagus.fleet_tracking_api.repository;

import danisbagus.fleet_tracking_api.domain.entity.FleetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FleetRepository extends JpaRepository<FleetEntity, Integer> {
}

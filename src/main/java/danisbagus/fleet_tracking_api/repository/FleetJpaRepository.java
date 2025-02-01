package danisbagus.fleet_tracking_api.repository;

import danisbagus.fleet_tracking_api.domain.entity.Fleet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FleetJpaRepository extends JpaRepository<Fleet, Integer> {
}


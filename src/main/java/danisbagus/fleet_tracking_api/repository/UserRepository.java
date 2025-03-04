package danisbagus.fleet_tracking_api.repository;

import danisbagus.fleet_tracking_api.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
   Optional<User> findByEmail(String email);
}

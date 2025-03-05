package danisbagus.fleet_tracking_api.repository;

import danisbagus.fleet_tracking_api.domain.entity.FleetEntity;
import danisbagus.fleet_tracking_api.domain.enums.VehicleType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Repository
public class FleetStubRepository {
    private static List<FleetEntity> fleets = new ArrayList<>();

    private static int increment = 1;

    static {
        fleets.add(new FleetEntity(increment++, "AG0001ZB", VehicleType.BUS));
        fleets.add(new FleetEntity(increment++, "AG0002ZB", VehicleType.TRUCK));
        fleets.add(new FleetEntity(increment++, "AG0003ZB", VehicleType.CAR));
    }

    public List<FleetEntity> findAll() {
        return fleets;
    }

    public Integer insert(FleetEntity fleet) {
        Integer id = increment++;
        fleet.setId(id);
        fleets.add(fleet);
        return id;
    }

    public Optional<FleetEntity> findByID(Integer id) {
        return fleets.stream().filter(fleet -> fleet.getId() == id).findFirst();
    }

    public Boolean deleteByID(Integer id) {
        return fleets.removeIf(fleet -> fleet.getId().equals(id));
    }

    public Boolean updateByID(Integer id, FleetEntity payload) {
        Predicate<? super FleetEntity> predicate = fleet -> fleet.getId().equals(id);

        Optional<FleetEntity> fleetOptional = fleets.stream().filter(predicate).findFirst();

        if (fleetOptional.isPresent()) {
            FleetEntity fleet = fleetOptional.get();
            fleet.setVehicleNumber(payload.getVehicleNumber());
            fleet.setVehicleType(payload.getVehicleType());
            return true;
        }

        return false;
    }
}

package danisbagus.fleet_tracking_api.service;

import danisbagus.fleet_tracking_api.domain.dto.FleetRequest;
import danisbagus.fleet_tracking_api.domain.dto.FleetResponse;
import danisbagus.fleet_tracking_api.domain.entity.Fleet;
import danisbagus.fleet_tracking_api.exception.BadRequestException;
import danisbagus.fleet_tracking_api.repository.FleetJdbcRepository;
import danisbagus.fleet_tracking_api.repository.FleetJpaRepository;
import danisbagus.fleet_tracking_api.repository.FleetStubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class FleetService {
    @Autowired
    private FleetStubRepository fleetStubRepository;

    @Autowired
    private FleetJdbcRepository fleetJdbcRepository;

    @Autowired
    private FleetJpaRepository fleetJpaRepository;

    public List<FleetResponse> list() {
        //List<Fleet> fleets = fleetStubRepository.findAll();
        // List<Fleet> fleets = fleetJdbcRepository.findAll();
        List<Fleet> fleets = fleetJpaRepository.findAll();

        return  fleets.stream().map(this::toFleetResponse).toList();
    }

    public FleetResponse create(FleetRequest fleetRequest) {
        Fleet fleet = toFleet(fleetRequest);

        // Integer id = fleetStubRepository.insert(fleet);
        // Integer id = fleetJdbcRepository.insert(fleet);
        // fleet.setId(id);

        Fleet newFleet = fleetJpaRepository.save(fleet);
        fleet.setId(newFleet.getId());

        return toFleetResponse(fleet);
    }

    public FleetResponse get(Integer id) {
        // Fleet fleet = fleetStubRepository.findByID(id).orElseThrow(() -> new BadRequestException("Fleet not found"));
        // Fleet fleet = fleetJdbcRepository.findByID(id).orElseThrow(() -> new BadRequestException("Fleet not found"));

        Fleet fleet = fleetJpaRepository.findById(id).orElseThrow(() -> new BadRequestException("Fleet not found"));
        return toFleetResponse(fleet);
    }

    public Boolean delete(Integer id) {
        // fleetStubRepository.findByID(id).orElseThrow(() -> new BadRequestException("Fleet not found"));
        //fleetJdbcRepository.findByID(id).orElseThrow(() -> new BadRequestException("Fleet not found"));
        fleetJpaRepository.findById(id).orElseThrow(() -> new BadRequestException("Fleet not found"));

        // return fleetStubRepository.deleteByID(id);
        // return fleetJdbcRepository.deleteByID(id);

        fleetJpaRepository.deleteById(id);
        return true;
    }

    public Boolean update(Integer id, FleetRequest fleetRequest) {
        // fleetStubRepository.findByID(id).orElseThrow(() -> new BadRequestException("Fleet not found"));
        // fleetJdbcRepository.findByID(id).orElseThrow(() -> new BadRequestException("Fleet not found"));

        // Fleet fleet = toFleet(fleetRequest);

        // return  fleetStubRepository.updateByID(id, fleet);
        // return  fleetJdbcRepository.updateByID(id, fleet);

        Fleet fleet = fleetJdbcRepository.findByID(id).orElseThrow(() -> new BadRequestException("Fleet not found"));
        fleet.setWithFleetRequest(fleetRequest);

        fleetJpaRepository.save(fleet);
        return  true;
    }

    private FleetResponse toFleetResponse(Fleet fleet) {
        return new FleetResponse(
                fleet.getId(),
                fleet.getVehicleNumber(),
                fleet.getVehicleType()
        );
    }

    private Fleet toFleet(FleetRequest fleetRequest) {
        Timestamp currentTime = Timestamp.from(Instant.now());
        return  new Fleet(
                fleetRequest.getVehicleNumber(),
                fleetRequest.getVehicleType(),
                currentTime,
                currentTime
        );
    }
}

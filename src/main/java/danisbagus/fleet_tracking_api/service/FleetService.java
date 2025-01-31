package danisbagus.fleet_tracking_api.service;

import danisbagus.fleet_tracking_api.domain.dto.FleetRequest;
import danisbagus.fleet_tracking_api.domain.dto.FleetResponse;
import danisbagus.fleet_tracking_api.domain.entity.Fleet;
import danisbagus.fleet_tracking_api.exception.BadRequestException;
import danisbagus.fleet_tracking_api.repository.FleetJdbcRepository;
import danisbagus.fleet_tracking_api.repository.FleetStubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class FleetService {
    @Autowired
    private FleetStubRepository fleetStubRepository;

    @Autowired
    private FleetJdbcRepository fleetJdbcRepository;

    public List<FleetResponse> list() {
        //List<Fleet> fleets = fleetStubRepository.findAll();
        List<Fleet> fleets = fleetJdbcRepository.findAll();

        return  fleets.stream().map(this::toFleetResponse).toList();
    }

    public FleetResponse create(FleetRequest fleetRequest) {
        Fleet fleet = toFleet(fleetRequest);

        // Integer id = fleetStubRepository.insert(fleet);
        Integer id = fleetJdbcRepository.insert(fleet);

        fleet.setId(id);

        return toFleetResponse(fleet);
    }

    public FleetResponse get(Integer id) {
        // Fleet fleet = fleetStubRepository.findByID(id).orElseThrow(() -> new BadRequestException("Fleet not found"));
        Fleet fleet = fleetJdbcRepository.findByID(id).orElseThrow(() -> new BadRequestException("Fleet not found"));

        return toFleetResponse(fleet);
    }

    public Boolean delete(Integer id) {
        // fleetStubRepository.findByID(id).orElseThrow(() -> new BadRequestException("Fleet not found"));
        fleetJdbcRepository.findByID(id).orElseThrow(() -> new BadRequestException("Fleet not found"));

        // return fleetStubRepository.deleteByID(id);
        return fleetJdbcRepository.deleteByID(id);
    }

    public Boolean update(Integer id, FleetRequest fleetRequest) {
        // fleetStubRepository.findByID(id).orElseThrow(() -> new BadRequestException("Fleet not found"));
        fleetJdbcRepository.findByID(id).orElseThrow(() -> new BadRequestException("Fleet not found"));

        Fleet fleet = toFleet(fleetRequest);

        // return  fleetStubRepository.updateByID(id, fleet);
        return  fleetJdbcRepository.updateByID(id, fleet);
    }

    private FleetResponse toFleetResponse(Fleet fleet) {
        return new FleetResponse(
                fleet.getId(),
                fleet.getVehicleNumber(),
                fleet.getVehicleType()
        );
    }

    private Fleet toFleet(FleetRequest fleetRequest) {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        return  new Fleet(
                fleetRequest.getVehicleNumber(),
                fleetRequest.getVehicleType(),
                currentTime,
                currentTime
        );
    }
}

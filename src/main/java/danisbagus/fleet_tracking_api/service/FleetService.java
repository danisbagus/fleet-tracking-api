package danisbagus.fleet_tracking_api.service;

import danisbagus.fleet_tracking_api.domain.dto.FleetRequest;
import danisbagus.fleet_tracking_api.domain.dto.FleetResponse;
import danisbagus.fleet_tracking_api.domain.entity.Fleet;
import danisbagus.fleet_tracking_api.exception.BadRequestException;
import danisbagus.fleet_tracking_api.repository.FleetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class FleetService {
    @Autowired
    private FleetRepository fleetRepository;

    public List<FleetResponse> list() {
        List<Fleet> fleets = fleetRepository.findAll();

        return  fleets.stream().map(this::toFleetResponse).toList();
    }

    public FleetResponse create(FleetRequest fleetRequest) {
        Fleet fleet = toFleet(fleetRequest);
        Fleet newFleet = fleetRepository.save(fleet);

        fleet.setId(newFleet.getId());
        return toFleetResponse(fleet);
    }

    public FleetResponse get(Integer id) {
        Fleet fleet = fleetRepository.findById(id).orElseThrow(() -> new BadRequestException("Fleet not found"));

        return toFleetResponse(fleet);
    }

    public void delete(Integer id) {
        fleetRepository.findById(id).orElseThrow(() -> new BadRequestException("Fleet not found"));

        fleetRepository.deleteById(id);
    }

    public void update(Integer id, FleetRequest fleetRequest) {
        Fleet fleet = fleetRepository.findById(id).orElseThrow(() -> new BadRequestException("Fleet not found"));

        fleet.setWithFleetRequest(fleetRequest);
        fleetRepository.save(fleet);
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

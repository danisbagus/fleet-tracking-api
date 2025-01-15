package danisbagus.fleet_tracking_api.service;

import danisbagus.fleet_tracking_api.domain.dto.FleetRequest;
import danisbagus.fleet_tracking_api.domain.dto.FleetResponse;
import danisbagus.fleet_tracking_api.domain.entity.Fleet;
import danisbagus.fleet_tracking_api.repository.FleetStubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FleetService {
    @Autowired
    private FleetStubRepository fleetStubRepository;

    public List<FleetResponse> list() {
        List<Fleet> fleets = fleetStubRepository.findAll();
        return  fleets.stream().map(this::toFleetResponse).toList();
    }

    public FleetResponse create(FleetRequest fleetRequest) {
        Fleet fleet = toFleet(fleetRequest);
        Integer id = fleetStubRepository.insert(fleet);
        fleet.setId(id);
        return toFleetResponse(fleet);
    }

    public FleetResponse get(Integer id) {
        Fleet fleet = fleetStubRepository.findByID(id);
        return toFleetResponse(fleet);
    }

    public Boolean delete(Integer id) {
        return fleetStubRepository.deleteByID(id);
    }

    public Boolean update(Integer id, FleetRequest fleetRequest) {
        Fleet fleet = toFleet(fleetRequest);
        return  fleetStubRepository.updateByID(id, fleet);
    }

    private FleetResponse toFleetResponse(Fleet fleet) {
        return new FleetResponse(
                fleet.getId(),
                fleet.getVehicleNumber(),
                fleet.getVehicleType()
        );
    }

    private Fleet toFleet(FleetRequest fleetRequest) {
        return  new Fleet(
                fleetRequest.getVehicleNumber(),
                fleetRequest.getVehicleType()
        );
    }
}

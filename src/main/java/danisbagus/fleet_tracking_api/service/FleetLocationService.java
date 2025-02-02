package danisbagus.fleet_tracking_api.service;

import danisbagus.fleet_tracking_api.domain.dto.FleetLocationRequest;
import danisbagus.fleet_tracking_api.domain.dto.FleetLocationResponse;
import danisbagus.fleet_tracking_api.domain.entity.FleetLocation;
import danisbagus.fleet_tracking_api.exception.BadRequestException;
import danisbagus.fleet_tracking_api.repository.FleetLocationRepository;
import danisbagus.fleet_tracking_api.repository.FleetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class FleetLocationService {
    @Autowired
    private FleetLocationRepository fleetLocationRepository;

    @Autowired
    private FleetRepository fleetRepository;

    public List<FleetLocationResponse> list(Integer fleetId) {
        fleetRepository.findById(fleetId).orElseThrow(() -> new BadRequestException("Fleet not found"));

        List<FleetLocation> fleetLocations = fleetLocationRepository.findByFleetIdOrderByCreatedAtDesc(fleetId);

        return fleetLocations.stream().map(this::toFleetLocationResponse).toList();
    }

    public FleetLocationResponse create(Integer fleetId, FleetLocationRequest fleetLocationRequest) {
        fleetRepository.findById(fleetId).orElseThrow(() -> new BadRequestException("Fleet not found"));

        FleetLocation fleetLocation = toFleetLocation(fleetId, fleetLocationRequest);
        fleetLocationRepository.save(fleetLocation);

        return toFleetLocationResponse(fleetLocation);
    }

    private FleetLocation toFleetLocation(Integer fleetID, FleetLocationRequest fleetLocationRequest) {
        return new FleetLocation(
                fleetID,
                fleetLocationRequest.getLongitude(),
                fleetLocationRequest.getLatitude(),
                Timestamp.from(Instant.now())
        );
    }

    private FleetLocationResponse toFleetLocationResponse(FleetLocation fleetLocation) {
        return new FleetLocationResponse(
                fleetLocation.getLongitude(),
                fleetLocation.getLatitude(),
                fleetLocation.getFleetId(),
                fleetLocation.getCreatedAt()
        );
    }
}

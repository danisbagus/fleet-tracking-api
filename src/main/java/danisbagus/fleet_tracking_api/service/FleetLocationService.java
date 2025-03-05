package danisbagus.fleet_tracking_api.service;

import danisbagus.fleet_tracking_api.domain.dto.FleetLocationRequest;
import danisbagus.fleet_tracking_api.domain.dto.FleetLocationResponse;
import danisbagus.fleet_tracking_api.domain.entity.FleetLocationEntity;
import danisbagus.fleet_tracking_api.exception.BadRequestException;
import danisbagus.fleet_tracking_api.repository.FleetLocationRepository;
import danisbagus.fleet_tracking_api.repository.FleetRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class FleetLocationService {
    private final FleetLocationRepository fleetLocationRepository;
    private final FleetRepository fleetRepository;

    public FleetLocationService(FleetLocationRepository fleetLocationRepository, FleetRepository fleetRepository) {
        this.fleetLocationRepository = fleetLocationRepository;
        this.fleetRepository = fleetRepository;
    }

    public List<FleetLocationResponse> list(Integer fleetId) {
        fleetRepository.findById(fleetId).orElseThrow(() -> new BadRequestException("Fleet not found"));

        List<FleetLocationEntity> fleetLocations = fleetLocationRepository.findByFleetIdOrderByCreatedAtDesc(fleetId);

        return fleetLocations.stream().map(this::toFleetLocationResponse).toList();
    }

    public FleetLocationResponse create(Integer fleetId, FleetLocationRequest fleetLocationRequest) {
        fleetRepository.findById(fleetId).orElseThrow(() -> new BadRequestException("Fleet not found"));

        FleetLocationEntity fleetLocation = toFleetLocation(fleetId, fleetLocationRequest);
        fleetLocationRepository.save(fleetLocation);

        return toFleetLocationResponse(fleetLocation);
    }

    private FleetLocationEntity toFleetLocation(Integer fleetID, FleetLocationRequest fleetLocationRequest) {
        return new FleetLocationEntity(
                fleetID,
                fleetLocationRequest.getLongitude(),
                fleetLocationRequest.getLatitude(),
                Timestamp.from(Instant.now()));
    }

    private FleetLocationResponse toFleetLocationResponse(FleetLocationEntity fleetLocation) {
        return new FleetLocationResponse(
                fleetLocation.getLongitude(),
                fleetLocation.getLatitude(),
                fleetLocation.getFleetId(),
                fleetLocation.getCreatedAt());
    }
}

package danisbagus.fleet_tracking_api.controller;

import danisbagus.fleet_tracking_api.domain.dto.*;
import danisbagus.fleet_tracking_api.service.FleetLocationService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fleets/{fleetId}/locations")
public class FleetLocationController {
    private final FleetLocationService fleetLocationService;

    public FleetLocationController(FleetLocationService fleetLocationService) {
        this.fleetLocationService = fleetLocationService;
    }

    @GetMapping
    public WebResponse<List<FleetLocationResponse>> list(
            @PathVariable Integer fleetId) {
        List<FleetLocationResponse> fleetLocationResponses = fleetLocationService.list(fleetId);

        return new WebResponse<>(fleetLocationResponses, "SUCCESS", HttpStatus.OK);
    }

    @PostMapping
    public WebResponse<FleetLocationResponse> create(
            @PathVariable Integer fleetId,
            @Valid @RequestBody FleetLocationRequest fleetLocationRequest) {
        FleetLocationResponse fleetLocationResponse = fleetLocationService.create(fleetId, fleetLocationRequest);

        return new WebResponse<>(fleetLocationResponse, "SUCCESS", HttpStatus.CREATED);
    }
}

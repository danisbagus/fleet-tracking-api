package danisbagus.fleet_tracking_api.controller;

import danisbagus.fleet_tracking_api.domain.dto.*;
import danisbagus.fleet_tracking_api.service.FleetLocationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fleets/{fleetId}/locations")
public class FleetLocationController {
    @Autowired
    private FleetLocationService fleetLocationService;

    @GetMapping
    public WebResponse<List<FleetLocationResponse>> list(
            @PathVariable Integer fleetId) {
        List<FleetLocationResponse> fleetLocationResponses = fleetLocationService.list(fleetId);

        return new WebResponse<>(fleetLocationResponses, "SUCCESS", 200);
    }

    @PostMapping
    public WebResponse<FleetLocationResponse> create(
            @PathVariable Integer fleetId,
            @Valid @RequestBody FleetLocationRequest fleetLocationRequest) {
        FleetLocationResponse fleetLocationResponse = fleetLocationService.create(fleetId, fleetLocationRequest);

        return new WebResponse<>(fleetLocationResponse, "SUCCESS", 201);
    }
}

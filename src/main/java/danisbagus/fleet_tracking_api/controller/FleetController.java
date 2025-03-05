package danisbagus.fleet_tracking_api.controller;

import danisbagus.fleet_tracking_api.domain.dto.FleetRequest;
import danisbagus.fleet_tracking_api.domain.dto.FleetResponse;
import danisbagus.fleet_tracking_api.domain.dto.WebResponse;
import danisbagus.fleet_tracking_api.service.FleetService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/fleets")
public class FleetController {
    private final FleetService fleetService;

    public FleetController(FleetService fleetService) {
        this.fleetService = fleetService;
    }

    @GetMapping
    public WebResponse<List<FleetResponse>> list() {
        List<FleetResponse> fleetResponses = fleetService.list();
        return new WebResponse<>(fleetResponses, "SUCCESS", HttpStatus.OK);
    }

    @PostMapping
    public WebResponse<FleetResponse> create(@Valid @RequestBody FleetRequest fleetRequest) {
        FleetResponse fleetResponse = fleetService.create(fleetRequest);
        return new WebResponse<>(fleetResponse, "SUCCESS", HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public WebResponse<FleetResponse> get(@PathVariable Integer id) {
        FleetResponse fleetResponse = fleetService.get(id);
        return new WebResponse<>(fleetResponse, "SUCCESS", HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public WebResponse<Boolean> delete(@PathVariable Integer id) {
        fleetService.delete(id);
        return new WebResponse<>(null, "SUCCESS", HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public WebResponse<Boolean> update(@Valid @RequestBody FleetRequest fleetRequest, @PathVariable Integer id) {
        fleetService.update(id, fleetRequest);
        return new WebResponse<>(null, "SUCCESS", HttpStatus.OK);
    }
}
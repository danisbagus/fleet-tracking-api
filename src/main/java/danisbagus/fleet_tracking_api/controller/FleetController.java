package danisbagus.fleet_tracking_api.controller;

import danisbagus.fleet_tracking_api.domain.dto.FleetRequest;
import danisbagus.fleet_tracking_api.domain.dto.FleetResponse;
import danisbagus.fleet_tracking_api.domain.dto.WebResponse;
import danisbagus.fleet_tracking_api.service.FleetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/fleets")
public class FleetController {

    @Autowired
    private FleetService fleetService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public WebResponse<List<FleetResponse>> list() {
        List<FleetResponse> fleetResponses = fleetService.list();
        return new WebResponse<>(fleetResponses, "SUCCESS", 200);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public WebResponse<FleetResponse> create(@Valid @RequestBody FleetRequest fleetRequest) {
        FleetResponse fleetResponse = fleetService.create(fleetRequest);
        return new WebResponse<>(fleetResponse, "SUCCESS", 201);
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public WebResponse<FleetResponse> get(@PathVariable Integer id) {
        FleetResponse fleetResponse = fleetService.get(id);
        return new WebResponse<>(fleetResponse, "SUCCESS", 200);
    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public WebResponse<Boolean> delete(@PathVariable Integer id) {
        fleetService.delete(id);
        return  new WebResponse<>(null, "SUCCESS", 200);
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public WebResponse<Boolean> update(@Valid @RequestBody FleetRequest fleetRequest, @PathVariable Integer id) {
        fleetService.update(id, fleetRequest);
        return  new WebResponse<>(null, "SUCCESS", 200);
    }
}
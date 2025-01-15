package danisbagus.fleet_tracking_api.controller;

import danisbagus.fleet_tracking_api.domain.dto.FleetRequest;
import danisbagus.fleet_tracking_api.domain.dto.FleetResponse;
import danisbagus.fleet_tracking_api.domain.dto.WebResponse;
import danisbagus.fleet_tracking_api.service.FleetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.validation.Valid;

@RestController
public class FleetController {

    @Autowired
    private FleetService fleetService;

    @GetMapping(path = "/api/fleets")
    public WebResponse<List<FleetResponse>> list() {
        List<FleetResponse> fleetResponses = fleetService.list();
        return new WebResponse<>(fleetResponses, "SUCCESS", 200);
    }

    @PostMapping(path = "/api/fleets")
    public WebResponse<FleetResponse> create(@Valid @RequestBody FleetRequest fleetRequest) {
        FleetResponse fleetResponse = fleetService.create(fleetRequest);
        return new WebResponse<>(fleetResponse, "SUCCESS", 201);
    }

    @GetMapping(path = "/api/fleets/{id}")
    public WebResponse<FleetResponse> get(@PathVariable Integer id) {
        FleetResponse fleetResponse = fleetService.get(id);
        return new WebResponse<>(fleetResponse, "SUCCESS", 200);
    }

    @DeleteMapping(path = "/api/fleets/{id}")
    public WebResponse<Boolean> delete(@PathVariable Integer id) {
        Boolean isSuccess = fleetService.delete(id);
        if (isSuccess) {
            return  new WebResponse<>(true, "SUCCESS", 200);
        }
        return new WebResponse<>(false, "FAILED", 500);
    }

    @PutMapping(path = "/api/fleets/{id}")
    public WebResponse<Boolean> update(@Valid @RequestBody FleetRequest fleetRequest, @PathVariable Integer id) {
        Boolean isSuccess = fleetService.update(id, fleetRequest);
        if (isSuccess) {
            return  new WebResponse<>(true, "SUCCESS", 200);
        }
        return new WebResponse<>(false, "FAILED", 500);
    }
}
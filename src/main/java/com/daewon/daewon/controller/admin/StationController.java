package com.daewon.daewon.controller.admin;

import com.daewon.daewon.domain.station.dto.*;
import com.daewon.daewon.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class StationController {

    private final StationService stationService;

    @PostMapping("/api/station")
    public CreateStationResponseDto createStation(@RequestBody StationRequestDto stationRequestDto) {
        return stationService.createStation(stationRequestDto.getName());
    }

    @GetMapping("/api/station")
    public ReadStationListResponseDto readStationList() {
        return stationService.readStation();
    }

    @PutMapping("/api/station")
    public UpdateStationResponseDto updateStation(@RequestBody StationRequestDto stationRequestDto) {
        return stationService.updateStation(stationRequestDto.getId(), stationRequestDto.getName());
    }

    @DeleteMapping("/api/station")
    public DeleteStationResponseDto deleteStation(@RequestBody StationRequestDto stationRequestDto) {
        return stationService.deleteStation(stationRequestDto.getId());
    }
}

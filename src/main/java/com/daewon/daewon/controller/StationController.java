package com.daewon.daewon.controller;

import com.daewon.daewon.domain.station.dto.*;
import com.daewon.daewon.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class StationController {

    private final StationService stationService;

    @PostMapping("/station")
    public CreateStationResponseDto createStation(@RequestBody StationRequestDto stationRequestDto) {
        return stationService.createStation(stationRequestDto.getName());
    }

    @GetMapping("/station")
    public ResponseEntity<ReadStationListResponseDto> readStationList() {
        ReadStationListResponseDto readStationListResponseDto = stationService.readStation();
//        return ResponseEntity.status(HttpStatus.OK).body(readStationListResponseDto);
        return new ResponseEntity(readStationListResponseDto, HttpStatus.OK);
    }

    @PutMapping("/station")
    public UpdateStationResponseDto updateStation(@RequestBody StationRequestDto stationRequestDto) {
        return stationService.updateStation(stationRequestDto.getId(), stationRequestDto.getName());
    }

    @DeleteMapping("/station")
    public DeleteStationResponseDto deleteStation(@RequestBody StationRequestDto stationRequestDto) {
        return stationService.deleteStation(stationRequestDto.getId());
    }
}

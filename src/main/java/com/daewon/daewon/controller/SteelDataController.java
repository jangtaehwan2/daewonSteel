package com.daewon.daewon.controller;

import com.daewon.daewon.domain.steeldata.dto.*;
import com.daewon.daewon.service.SteelDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class SteelDataController {

    private final SteelDataService steelDataService;

    @PostMapping("/data")
    public CreateSteelDataResponseDto createSteelData(@RequestBody SteelDataRequestDto steelDataRequestDto) {
        return steelDataService.createSteelData(steelDataRequestDto.getStationName(), steelDataRequestDto.getWeight(), steelDataRequestDto.getDate());
    }

    @GetMapping("/data/year")
    public ReadSteelDataResponseDto readSteelDataForYear(@RequestBody SteelDataRequestDto steelDataRequestDto) {
        return steelDataService.readSteelDataForYear(steelDataRequestDto.getYear());
    }

    @GetMapping("/data/month")
    public ReadSteelDataResponseDto readSteelDataForMonth(@RequestBody SteelDataRequestDto steelDataRequestDto) {
        return steelDataService.readSteelDataForMonth(steelDataRequestDto.getYear(), steelDataRequestDto.getMonth());
    }

    @GetMapping("/data/day")
    public ReadSteelDataResponseDto readSteelDataForDay(@RequestBody SteelDataRequestDto steelDataRequestDto) {
        return steelDataService.readSteelDataForDay(steelDataRequestDto.getYear(), steelDataRequestDto.getMonth(), steelDataRequestDto.getDay());
    }

    @PutMapping("/data")
    public UpdateSteelDataResponseDto updateSteelData(@RequestBody SteelDataRequestDto steelDataRequestDto) {
        return steelDataService.updateSteelData(steelDataRequestDto.getId(), steelDataRequestDto.getStationName(), steelDataRequestDto.getWeight(), steelDataRequestDto.getDate());
    }

    @DeleteMapping("/data")
    public DeleteSteelDataResponseDto deleteSteelData(@RequestBody SteelDataRequestDto steelDataRequestDto) {
        return steelDataService.deleteSteelData(steelDataRequestDto.getId());
    }

}

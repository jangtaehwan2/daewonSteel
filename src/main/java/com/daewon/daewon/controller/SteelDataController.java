package com.daewon.daewon.controller;

import com.daewon.daewon.domain.steeldata.dto.*;
import com.daewon.daewon.service.AuthorizationManager;
import com.daewon.daewon.service.SteelDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class SteelDataController {

    private final SteelDataService steelDataService;
    private final AuthorizationManager authorizationManager;

    @PostMapping("/data")
    public ResponseEntity<CreateSteelDataResponseDto> createSteelData(@RequestBody SteelDataRequestDto steelDataRequestDto, @RequestHeader(value = "Authorization")String token) {
        try {
            if(authorizationManager.isUser(token)) {
                return ResponseEntity.ok().body(steelDataService.createSteelData(steelDataRequestDto.getStationName(), steelDataRequestDto.getWeight(), steelDataRequestDto.getDate()));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/data")
    public ResponseEntity<ReadSteelDataResponseDto> readSteelDataForYear(@RequestHeader(value = "Authorization")String token) {
        try {
            if(authorizationManager.isUser(token)) {
                return ResponseEntity.ok().body(steelDataService.readSteelDataAll());
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/data/{year}")
    public ResponseEntity<ReadSteelDataResponseDto> readSteelDataForYear(@PathVariable int year, @RequestHeader(value = "Authorization")String token) {
        try {
            if(authorizationManager.isUser(token)) {
                return ResponseEntity.ok().body(steelDataService.readSteelDataForYear(year));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/data/{year}/{month}")
    public ResponseEntity<ReadSteelDataResponseDto> readSteelDataForMonth(@PathVariable int year, @PathVariable int month, @RequestHeader(value = "Authorization")String token) {
        try {
            if(authorizationManager.isUser(token)) {
                return ResponseEntity.ok().body(steelDataService.readSteelDataForMonth(year, month));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/data/{year}/{month}/{day}")
    public ResponseEntity<ReadSteelDataResponseDto> readSteelDataForDay(@PathVariable int year, @PathVariable int month, @PathVariable int day, @RequestHeader(value = "Authorization")String token) {
        try {
            if(authorizationManager.isUser(token)) {

                return ResponseEntity.ok().body(steelDataService.readSteelDataForDay(year, month, day));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/data")
    public ResponseEntity<UpdateSteelDataResponseDto> updateSteelData(@RequestBody SteelDataRequestDto steelDataRequestDto, @RequestHeader(value = "Authorization")String token) {
        try {
            if(authorizationManager.isAdmin(token)) {
                return ResponseEntity.ok().body(steelDataService.updateSteelData(steelDataRequestDto.getId(), steelDataRequestDto.getStationName(), steelDataRequestDto.getWeight(), steelDataRequestDto.getDate()));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping("/data")
    public ResponseEntity<DeleteSteelDataResponseDto> deleteSteelData(@RequestBody SteelDataRequestDto steelDataRequestDto, @RequestHeader(value = "Authorization")String token) {
        try {
            if(authorizationManager.isAdmin(token)) {
                return ResponseEntity.ok().body(steelDataService.deleteSteelData(steelDataRequestDto.getId()));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

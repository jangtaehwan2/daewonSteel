package com.daewon.daewon.controller;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.daewon.daewon.domain.station.dto.*;
import com.daewon.daewon.service.AuthorizationManager;
import com.daewon.daewon.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class StationController {

    private final StationService stationService;
    private final AuthorizationManager authorizationManager;

    @PostMapping("/station")
    public ResponseEntity<CreateStationResponseDto> createStation(@Valid @RequestBody StationRequestDto stationRequestDto, @RequestHeader(value = "Authorization") String token) {
        try {
            if(authorizationManager.isAdmin(token)) {
                return ResponseEntity.ok().body(stationService.createStation(stationRequestDto.getName()));
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/station")
    public ResponseEntity<ReadStationListResponseDto> readStationList(@RequestHeader(value = "Authorization") String token) {
        try {
            if(authorizationManager.isUser(token)) {
                return ResponseEntity.ok().body(stationService.readStation());
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/station")
    public ResponseEntity<UpdateStationResponseDto> updateStation(@RequestBody StationRequestDto stationRequestDto, @RequestHeader(value = "Authorization") String token) {
        try {
            if(authorizationManager.isAdmin(token)) {
                return ResponseEntity.ok().body(stationService.updateStation(stationRequestDto.getId(), stationRequestDto.getName()));
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping("/station")
    public ResponseEntity<DeleteStationResponseDto> deleteStation(@RequestBody StationRequestDto stationRequestDto,  @RequestHeader(value = "Authorization") String token) {
        try {
            if(authorizationManager.isAdmin(token)) {
                return ResponseEntity.ok().body(stationService.deleteStation(stationRequestDto.getId()));
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

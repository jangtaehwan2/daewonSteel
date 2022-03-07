package com.daewon.daewon.service;

import com.daewon.daewon.domain.station.dto.CreateStationResponseDto;
import com.daewon.daewon.domain.station.dto.DeleteStationResponseDto;
import com.daewon.daewon.domain.station.dto.ReadStationListResponseDto;
import com.daewon.daewon.domain.station.dto.UpdateStationResponseDto;

public interface StationService {


    CreateStationResponseDto createStation(String name);
    ReadStationListResponseDto readStation();
    UpdateStationResponseDto updateStation(long id, String name);
    DeleteStationResponseDto deleteStation(long id);
}

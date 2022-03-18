package com.daewon.daewon.service;

import com.daewon.daewon.domain.station.Station;
import com.daewon.daewon.domain.station.dto.CreateStationResponseDto;
import com.daewon.daewon.domain.station.dto.DeleteStationResponseDto;
import com.daewon.daewon.domain.station.dto.ReadStationListResponseDto;
import com.daewon.daewon.domain.station.dto.UpdateStationResponseDto;
import com.daewon.daewon.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StationServiceImpl implements StationService{

    private final StationRepository stationRepository;

    @Override
    public CreateStationResponseDto createStation(String name) {
        Station station = new Station(name);
        stationRepository.save(station);
        CreateStationResponseDto stationResponseDto = new CreateStationResponseDto(station.getId(), station.getName());
        return stationResponseDto;
    }

    @Override
    public ReadStationListResponseDto readStation() {
        return new ReadStationListResponseDto(stationRepository.findAll());
    }

    @Override
    public UpdateStationResponseDto updateStation(long id, String name) {
        Station station = stationRepository.findById(id).get();
        station.updateName(name);
        stationRepository.save(station);
        return new UpdateStationResponseDto(station.getId(), station.getName());
    }

    @Override
    public DeleteStationResponseDto deleteStation(long id) {
        Station station = stationRepository.findById(id).get();
        stationRepository.delete(station);
        return new DeleteStationResponseDto("1");
    }
}

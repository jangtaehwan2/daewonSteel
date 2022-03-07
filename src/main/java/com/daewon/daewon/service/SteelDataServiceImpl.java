package com.daewon.daewon.service;

import com.daewon.daewon.domain.station.dto.ReadStationListResponseDto;
import com.daewon.daewon.domain.steeldata.dto.*;
import com.daewon.daewon.repository.SteelDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class SteelDataServiceImpl implements SteelDataService {
    private final SteelDataRepository steelDataRepository;

    @Override
    public ReadStationListResponseDto readStationList() {
        return null;
    }

    @Override
    public CreateSteelDataResponseDto createSteelData(long stationId, long weight, LocalDate date) {
        return null;
    }

    @Override
    public ReadSteelDataForYearResponseDto readSteelDataForYear(int year) {
        return null;
    }

    @Override
    public ReadSteelDataForMonthResponseDto readSteelDataForMonth(int year, int month) {
        return null;
    }

    @Override
    public ReadSteelDataForDayResponseDto readSteelDataForDay(int year, int month, int day) {
        return null;
    }

    @Override
    public UpdateSteelDataResponseDto updateSteelData(long id, long newStationId, long newWeight, LocalDate newDate) {
        return null;
    }

    @Override
    public DeleteSteelDataResponseDto deleteSteelData(long id) {
        return null;
    }
}

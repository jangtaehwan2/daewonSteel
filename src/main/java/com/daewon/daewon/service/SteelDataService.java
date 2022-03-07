package com.daewon.daewon.service;

import com.daewon.daewon.domain.station.dto.ReadStationListResponseDto;
import com.daewon.daewon.domain.steeldata.dto.*;

import java.time.LocalDate;

public interface SteelDataService {
    ReadStationListResponseDto readStationList();
    CreateSteelDataResponseDto createSteelData(long stationId, long weight, LocalDate date);
    ReadSteelDataForYearResponseDto readSteelDataForYear(int year);
    ReadSteelDataForMonthResponseDto readSteelDataForMonth(int year, int month);
    ReadSteelDataForDayResponseDto readSteelDataForDay(int year, int month, int day);
    UpdateSteelDataResponseDto updateSteelData(long id, long newStationId, long newWeight, LocalDate newDate);
    DeleteSteelDataResponseDto deleteSteelData(long id);
}

package com.daewon.daewon.service;

import com.daewon.daewon.domain.station.dto.ReadStationListResponseDto;
import com.daewon.daewon.domain.steeldata.dto.*;

import java.time.LocalDate;

public interface SteelDataService {
    CreateSteelDataResponseDto createSteelData(String stationName, long weight, LocalDate date);
    ReadSteelDataResponseDto readSteelDataForYear(int year);
    ReadSteelDataResponseDto readSteelDataForMonth(int year, int month);
    ReadSteelDataResponseDto readSteelDataForDay(int year, int month, int day);
    UpdateSteelDataResponseDto updateSteelData(long id, String newStationName, long newWeight, LocalDate newDate);
    DeleteSteelDataResponseDto deleteSteelData(long id);
}

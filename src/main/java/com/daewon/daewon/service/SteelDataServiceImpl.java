package com.daewon.daewon.service;

import com.daewon.daewon.domain.station.dto.ReadStationListResponseDto;
import com.daewon.daewon.domain.steeldata.SteelData;
import com.daewon.daewon.domain.steeldata.dto.*;
import com.daewon.daewon.repository.SteelDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.*;

@RequiredArgsConstructor
@Service
public class SteelDataServiceImpl implements SteelDataService {
    private final SteelDataRepository steelDataRepository;

    @Override
    public CreateSteelDataResponseDto createSteelData(String stationName, long weight, LocalDate date) {
        SteelData steelData = new SteelData(stationName, weight, date);
        steelDataRepository.save(steelData);
        return new CreateSteelDataResponseDto(steelData.getId(), steelData.getStationName(), steelData.getWeight(), steelData.getDate());
    }

    @Override
    public ReadSteelDataResponseDto readSteelDataAll() {
        List<SteelData> steelDataList = steelDataRepository.findByDeletedOrderByDateAsc(false);
        return new ReadSteelDataResponseDto(steelDataList);
    }

    @Override
    public ReadSteelDataResponseDto readSteelDataForYear(int year) {
        LocalDate localDate = LocalDate.of(year, 1, 1);
        LocalDate startDate = localDate.with(firstDayOfYear());
        LocalDate endDate = localDate.with(lastDayOfYear());
        List<SteelData> steelDataList = steelDataRepository.findByDateBetweenAndDeletedOrderByDateAsc(startDate, endDate, false);
        return new ReadSteelDataResponseDto(steelDataList);
    }

    @Override
    public ReadSteelDataResponseDto readSteelDataForMonth(int year, int month) {
        LocalDate localDate = LocalDate.of(year, month, 1);
        LocalDate startDate = localDate.with(firstDayOfMonth());
        LocalDate endDate = localDate.with(lastDayOfMonth());
        List<SteelData> steelDataList = steelDataRepository.findByDateBetweenAndDeletedOrderByDateAsc(startDate, endDate, false);
        return new ReadSteelDataResponseDto(steelDataList);
    }

    @Override
    public ReadSteelDataResponseDto readSteelDataForDay(int year, int month, int day) {
        LocalDate localDate = LocalDate.of(year, month, day);
        List<SteelData> steelDataList = steelDataRepository.findByDateBetweenAndDeletedOrderByDateAsc(localDate, localDate, false);
        return new ReadSteelDataResponseDto(steelDataList);
    }

    @Override
    public UpdateSteelDataResponseDto updateSteelData(long id, String newStationName, long newWeight, LocalDate newDate) {
        SteelData steelData = steelDataRepository.findById(id).get();
        steelData.updateSteelData(newStationName, newWeight, newDate);
        steelDataRepository.save(steelData);
        return new UpdateSteelDataResponseDto(steelData.getId(), steelData.getStationName(), steelData.getWeight(), steelData.getDate());

    }

    @Override
    public DeleteSteelDataResponseDto deleteSteelData(long id) {
        SteelData steelData = steelDataRepository.findById(id).get();
        steelDataRepository.delete(steelData);
        return new DeleteSteelDataResponseDto("1");
    }
}

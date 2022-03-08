package com.daewon.daewon.service;

import com.daewon.daewon.domain.steeldata.SteelData;
import com.daewon.daewon.domain.steeldata.dto.CreateSteelDataResponseDto;
import com.daewon.daewon.domain.steeldata.dto.DeleteSteelDataResponseDto;
import com.daewon.daewon.domain.steeldata.dto.ReadSteelDataResponseDto;
import com.daewon.daewon.repository.SteelDataRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class SteelDataServiceTest {

    @Autowired
    private SteelDataService steelDataService;
    @Autowired
    private SteelDataRepository steelDataRepository;

    @Test
    @DisplayName("Create Test")
    public void createSteelDataTest() {
        //given
        String name = "햄토리공장";
        long weight = 50;
        LocalDate date = LocalDate.of(2022, 3, 5);

        //when
        CreateSteelDataResponseDto steelData = steelDataService.createSteelData(name, weight, date);

        //then
        System.out.println(steelData.toString());
        Assertions.assertThat(steelData.getStationName()).isEqualTo(name);
        Assertions.assertThat(steelData.getWeight()).isEqualTo(weight);
        Assertions.assertThat(steelData.getDate()).isEqualTo(date);
    }

    @Test
    @DisplayName("Read Test")
    public void readSteelDataTest() {
        //given
        steelDataService.createSteelData("햄토리공장", 50, LocalDate.of(2022, 1, 3));
        steelDataService.createSteelData("햄토리공장", 100, LocalDate.of(2022, 3, 3));
        steelDataService.createSteelData("햄토리공장", 250, LocalDate.of(2022, 1, 8));
        //when
        ReadSteelDataResponseDto yearData = steelDataService.readSteelDataForYear(2022);
        ReadSteelDataResponseDto monthData = steelDataService.readSteelDataForMonth(2022, 1);
        ReadSteelDataResponseDto dayData = steelDataService.readSteelDataForDay(2022, 3, 3);

        //then
        System.out.println(yearData.toString());
        System.out.println("####################");
        System.out.println(monthData.toString());
        System.out.println("####################");
        System.out.println(dayData.toString());
        System.out.println("####################");
        Assertions.assertThat(yearData.toString()).isNotEqualTo(monthData.toString()).isNotEqualTo(dayData.toString());

    }

    @Test
    @DisplayName("Update Test")
    public void updateSteelDataTest() {
        //given
        String name = "햄토리공장";
        long weight = 50L;
        LocalDate date = LocalDate.of(2022, 1, 1);
        CreateSteelDataResponseDto steelData = steelDataService.createSteelData(name, weight, date);
        System.out.println(steelData.toString());

        //when
        String newName = "Second Factory";
        long newWeight = 30L;
        LocalDate newDate = LocalDate.of(2000, 1, 3);
        steelDataService.updateSteelData(steelData.getSteelDataId(), newName, newWeight, newDate);
        SteelData updatedSteelData = steelDataRepository.findById(steelData.getSteelDataId()).get();

        //then
        System.out.println(updatedSteelData.toString());
        Assertions.assertThat(updatedSteelData.getStationName()).isEqualTo(newName);
        Assertions.assertThat(updatedSteelData.getWeight()).isEqualTo(newWeight);
        Assertions.assertThat(updatedSteelData.getDate()).isEqualTo(newDate);
    }


    @Test
    @DisplayName("Delete Test")
    public void deleteSteelDataTest() {
        //given
        String name = "햄토리공장";
        long weight = 50L;
        LocalDate date = LocalDate.of(2022, 3, 5);
        CreateSteelDataResponseDto steelData = steelDataService.createSteelData(name, weight, date);
        System.out.println(steelData.toString());

        //when
        DeleteSteelDataResponseDto deleteSteelDataResponseDto = steelDataService.deleteSteelData(steelData.getSteelDataId());
        System.out.println(deleteSteelDataResponseDto.toString());

        //then
        ReadSteelDataResponseDto readSteelDataResponseDto = steelDataService.readSteelDataForDay(2022, 3, 5);
        System.out.println(readSteelDataResponseDto.toString());
        Assertions.assertThat(readSteelDataResponseDto.getData().isEmpty()).isEqualTo(true);
    }

}

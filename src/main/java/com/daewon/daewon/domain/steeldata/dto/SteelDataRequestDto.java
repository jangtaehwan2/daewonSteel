package com.daewon.daewon.domain.steeldata.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class SteelDataRequestDto {
    private long id;
    private long weight;
    private LocalDate date;
    private String stationName;
    private int year;
    private int month;
    private int day;
}

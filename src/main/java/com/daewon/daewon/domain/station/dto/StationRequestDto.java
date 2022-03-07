package com.daewon.daewon.domain.station.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class StationRequestDto {
    private long id;
    private String name;
}

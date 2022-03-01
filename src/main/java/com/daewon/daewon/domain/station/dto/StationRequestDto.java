package com.daewon.daewon.domain.station.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StationRequestDto {
    private long id;
    private String name;

    @Override
    public String toString() {
        return "StationRequestDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

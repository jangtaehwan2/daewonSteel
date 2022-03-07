package com.daewon.daewon.domain.station.dto;

import com.daewon.daewon.domain.station.Station;
import lombok.Value;

import java.util.List;

@Value
public class ReadStationListResponseDto {
    List<Station> stationList;
}

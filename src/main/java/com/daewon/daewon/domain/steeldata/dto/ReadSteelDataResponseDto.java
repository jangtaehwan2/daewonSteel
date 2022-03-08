package com.daewon.daewon.domain.steeldata.dto;

import com.daewon.daewon.domain.steeldata.SteelData;
import lombok.Value;

import java.util.List;

@Value
public class ReadSteelDataResponseDto {
    private List<SteelData> data;
}

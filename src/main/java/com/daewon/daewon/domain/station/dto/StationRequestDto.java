package com.daewon.daewon.domain.station.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ToString
@Getter
@NoArgsConstructor
public class StationRequestDto {
    private long id;
    @Size(min = 1, max = 10)
    @Pattern(regexp = "^[a-zA-Z가-힣0-9]*$")
    private String name;
}

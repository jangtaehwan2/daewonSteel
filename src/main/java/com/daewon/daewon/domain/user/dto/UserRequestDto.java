package com.daewon.daewon.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UserRequestDto {
    private long id;
    @Size(min = 1, max = 20)
    @Pattern(regexp = "^[a-zA-Z가-힣0-9]*$")
    private String userName;
    @NotBlank
    private String userPassword;
}

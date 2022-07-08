package com.example.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UnivercityDto {
    @NotBlank(message = "Please provide a Univercity name")
    private String name;
    @NotNull(message = "Please provide a openYear")
    private Integer openYear;
    @NotNull(message = "Please provide a city")
    private String city;
    @NotNull(message = "Please provide a district")
    private String district;
    @NotNull(message = "Please provide a street")
    private String street;
}

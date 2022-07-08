package com.example.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class GroupDto {
    @NotBlank(message = "Please provide a Group name")
    private String name;
    @NotNull(message = "Please provide a year")
    private Integer year;
    @NotNull(message = "Please provide a faculty")
    private Integer facultyId;
}

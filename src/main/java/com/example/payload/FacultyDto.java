package com.example.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class FacultyDto {
    @NotBlank(message = "Please provide a Faculty name")
    private String name;
    @NotNull(message = "Please provide a Unovercity")
    private Integer univercityId;
}

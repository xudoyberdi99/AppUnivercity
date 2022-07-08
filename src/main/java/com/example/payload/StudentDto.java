package com.example.payload;

import com.example.entity.Address;
import com.example.entity.Group;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class StudentDto {
    @NotBlank(message = "Please provide a student fullName")
    private String fullName;
    @NotBlank(message = "Please provide a city")
    private String city;
    @NotBlank(message = "Please provide a district")
    private String district;
    @NotBlank(message = "Please provide a street")
    private String street;
    @NotNull(message = "Please provide a group")
    private Integer groupId;
}

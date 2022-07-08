package com.example.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class SubjectDto {
    @NotBlank(message = "Please provide a Subject name")
    private String name;
    private List<Integer> groupList;
}

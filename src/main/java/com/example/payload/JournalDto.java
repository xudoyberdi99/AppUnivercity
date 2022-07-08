package com.example.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class JournalDto {
    @NotNull(message = "Please provide a Journal name")
    private String name;
    private Integer groupId;
    private Set<Integer> subjectsList;
}

package com.example.payload;

import com.example.entity.Journal;
import com.example.entity.Student;
import lombok.Data;

import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class MarkDto {
    @NotNull(message = "Please provide a Mark")
    private Integer ball;
    private List<Integer> studentList;
    private List<Integer> journalList;
}

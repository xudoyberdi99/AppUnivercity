package com.example.entity;



import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "groups")
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name", "faculty_id"}))
public class Group  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable =false)
    private Integer year;
    @ManyToOne(optional = false)
    private Faculty faculty;

}

package io.github.muhittinpalamutcu.schoolmanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String courseCode;
    private int creditScore;

    @JsonBackReference
    @ManyToMany
    private List<Student> students = new ArrayList<>();

    @JsonBackReference
    @ManyToOne
    private Instructor instructor;
}
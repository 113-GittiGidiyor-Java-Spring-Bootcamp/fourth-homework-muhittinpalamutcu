package io.github.muhittinpalamutcu.schoolmanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

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
    @ToString.Exclude
    @ManyToMany
    private List<Student> students;

    @JsonBackReference
    @ManyToOne
    private Instructor instructor;

}
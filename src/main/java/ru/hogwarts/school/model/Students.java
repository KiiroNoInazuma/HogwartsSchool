package ru.hogwarts.school.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "students")
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int age;
    @Column(name = "faculty_id")
    private int facultyId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id", insertable = false, updatable = false, nullable = false)
    @JsonIgnore
    private Faculties faculty;

}

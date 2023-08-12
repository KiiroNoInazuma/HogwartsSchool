package ru.hogwarts.school.model;


import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "faculties")
public class Faculties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String color;
}

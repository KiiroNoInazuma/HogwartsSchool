package ru.hogwarts.school.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Data
@Entity
@Table(name = "faculties")
public class Faculties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String color;
    @OneToMany(mappedBy = "faculty", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @Schema(hidden = true)
    private Collection<Students> students;
}


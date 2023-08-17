package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Faculties;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculties, Long> {
    List<Faculties> getFacultiesByColor(String color);
    Faculties findByNameIgnoreCase(String name);
    Faculties findFacultiesByStudentsId(long id);
}

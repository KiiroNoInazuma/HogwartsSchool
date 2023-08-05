package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculties;
import ru.hogwarts.school.service.MetCrud;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    @Resource
    private MetCrud<Faculties> facultyMetCrud;

    @PostMapping("creation_faculty/{nameFaculty}/{color}")
    public ResponseEntity<Faculties> creation(@PathVariable String nameFaculty, String color, @Parameter(hidden = true) Faculties faculties) {
        faculties.setFacultyParam(nameFaculty, color);
        facultyMetCrud.creat(faculties);
        return ResponseEntity.ok(faculties);
    }
    @GetMapping("allFaculties")
    public ResponseEntity<StringBuilder> all() {
        StringBuilder allFaculties = facultyMetCrud.getAll();
        if (allFaculties.isEmpty())
            return ResponseEntity.status(404).body(allFaculties.append("Список пуст, добавьте учеников."));
        return ResponseEntity.ok(allFaculties);
    }
}

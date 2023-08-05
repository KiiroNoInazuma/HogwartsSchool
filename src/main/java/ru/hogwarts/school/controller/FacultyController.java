package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculties;
import ru.hogwarts.school.service.FacultyService;
import ru.hogwarts.school.service.MetCrud;

import java.util.List;
@Tag(name = "Создание факультетов и распределение учеников")
@RestController
@RequestMapping("faculty")
public class FacultyController {
    @Resource
    private MetCrud<Faculties> facultyMetCrud;
    @Operation(summary = "СОЗДАТЬ ФАКУЛЬТЕТ")
    @PostMapping("creation_faculty/{nameFaculty}/{color}")
    public ResponseEntity<Faculties> creation(@PathVariable String nameFaculty, String color, @Parameter(hidden = true) Faculties faculties) {
        faculties.setFacultyParam(nameFaculty, color);
        facultyMetCrud.creat(faculties);
        return ResponseEntity.ok(faculties);
    }
    @Operation(summary = "ПОКАЗАТЬ ВСЕ ФАКУЛЬТЕТЫ В ШКОЛЕ")
    @GetMapping("allFaculties")
    public ResponseEntity<StringBuilder> all() {
        StringBuilder allFaculties = facultyMetCrud.getAll();
        if (allFaculties.isEmpty())
            return ResponseEntity.status(404).body(allFaculties.append("Список пуст, факультеты не созданы."));
        return ResponseEntity.ok(allFaculties);
    }
    @Operation(summary = "НАЙТИ ФАКУЛЬТЕТ ПО ЕГО ПОРЯДКОВОМУ НОМЕРУ")
    @GetMapping("search/{id}")
    public ResponseEntity<Faculties> search(@PathVariable int id) {
        Faculties faculties = facultyMetCrud.find(id);
        if (faculties == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(faculties);
    }
    @Operation(summary = "РАСПУСТИТЬ УЧЕНИКОВ И УПРАЗДНИТЬ ФАКУЛЬТЕТ")
    @DeleteMapping("abolish/{id}")
    ResponseEntity<String> exclude(@PathVariable long id) {
        String delete = facultyMetCrud.delete(id);
        return ResponseEntity.ok().body(delete);
    }
    @Operation(summary = "ИЗМЕНИТЬ ДАННЫЕ ПО ФАКУЛЬТЕТУ(название, цвет, список учеников)")
    @PutMapping("change/{id}/{nameFaculty}/{color}")
    public ResponseEntity<Faculties> changeFaculty(@PathVariable long id, @PathVariable String nameFaculty, String color, @Parameter(hidden = true) Faculties faculties) {
        facultyMetCrud.edit(id, faculties);
        return creation(nameFaculty, color, faculties);
    }
    @Operation(summary = "НАЙТИ ФАКУЛЬТЕТ ПО ЕГО ЦВЕТУ")
    @GetMapping("color/{color}")
    public ResponseEntity<List<Faculties>> getAgeStudents(@PathVariable String color) {
        List<Faculties> facultyService = new FacultyService().getColorFaculty(color);
        if (facultyService == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(facultyService);
    }

}

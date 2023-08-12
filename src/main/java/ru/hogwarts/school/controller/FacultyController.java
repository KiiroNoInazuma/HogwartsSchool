package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final MetCrud<Faculties> facultyMetCrud;
    private final FacultyService facultyService;

    @Autowired
    public FacultyController(MetCrud<Faculties> facultyMetCrud, FacultyService facultyService) {
        this.facultyMetCrud = facultyMetCrud;
        this.facultyService = facultyService;
    }


    @Operation(summary = "СОЗДАТЬ ФАКУЛЬТЕТ")
    @PostMapping("creation_faculty")
    public ResponseEntity<Faculties> creation(@RequestBody Faculties faculties) {
        Faculties creat = facultyMetCrud.creat(faculties);
        return ResponseEntity.ok(creat);
    }

    @Operation(summary = "ПОКАЗАТЬ ВСЕ ФАКУЛЬТЕТЫ В ШКОЛЕ")
    @GetMapping("allFaculties")
    public ResponseEntity<List<Faculties>> all() {
        List<Faculties> all = facultyMetCrud.getAll();
        if (all.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(all);
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
        if (delete == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(delete);
    }

    @Operation(summary = "ИЗМЕНИТЬ ДАННЫЕ ПО ФАКУЛЬТЕТУ(название, цвет, список учеников)")
    @PutMapping("change")
    public ResponseEntity<Faculties> changeFaculty(@RequestBody Faculties faculties) {
        Faculties edit = facultyMetCrud.edit(faculties);
        return creation(edit);
    }

    @Operation(summary = "НАЙТИ ФАКУЛЬТЕТ ПО ЕГО ЦВЕТУ")
    @GetMapping("color/{color}")
    public ResponseEntity<List<Faculties>> getAgeStudents(@PathVariable String color) {
        List<Faculties> colorFaculty = facultyService.getColorFaculty(color);
        if (colorFaculty == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(colorFaculty);
    }

}

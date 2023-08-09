package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Students;
import ru.hogwarts.school.service.MetCrud;
import ru.hogwarts.school.service.StudentService;
@Tag(name = "Добавить учеников в список школы")
@RestController
@RequestMapping("student")
public class StudentController {
    @Resource
    private MetCrud<Students> studentsMetCrud;

    @Operation(summary = "ДОБАВИТЬ СТУДЕНТА В СПИСОК НА РАСПРЕДЕЛЕНИЕ")
    @PostMapping("enrolled")
    public ResponseEntity<Students> enrolled(@RequestBody Students students) {
        return ResponseEntity.ok(studentsMetCrud.creat(students));
    }

    @Operation(summary = "ИЗМЕНИТЬ ДАННЫЕ СТУДЕНТА В СПИСКЕ")
    @PutMapping("replace/{id}")
    public ResponseEntity<Students> replaceStudent(@PathVariable long id, @RequestBody Students students) {
        Students edit = studentsMetCrud.edit(id, students);
        if (edit == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(edit);
    }
    @Operation(summary = "ИСКЛЮЧИТЬ СТУДЕНТА ИЗ СПИСКА")
    @DeleteMapping("exclude/{id}")
    ResponseEntity<String> exclude(@PathVariable long id) {
        String delete = studentsMetCrud.delete(id);
        return ResponseEntity.ok().body(delete);
    }
    @Operation(summary = "НАЙТИ СТУДЕНТА ПО ЕГО УЧЕТНОМУ НОМЕРУ")
    @GetMapping("find/{id}")
    public ResponseEntity<Students> search(@PathVariable int id) {
        Students students = studentsMetCrud.find(id);
        if (students == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(students);
    }
    @Operation(summary = "ПОКАЗАТЬ ВЕСЬ СПИСОК СТУДЕНТОВ")
    @GetMapping("allStudents")
    public ResponseEntity<StringBuilder> all() {
        StringBuilder allStudents = studentsMetCrud.getAll();
        if (allStudents.isEmpty())
            return ResponseEntity.status(404).body(allStudents.append("Список пуст, добавьте учеников."));
        return ResponseEntity.ok(allStudents);
    }
    @Operation(summary = "НАЙТИ СТУДЕНТОВ ПО ВОЗРАСТУ")
    @GetMapping("age/{age}")
    public ResponseEntity<StringBuilder> getAgeStudents(@PathVariable int age) {
        StringBuilder allStudents = new StudentService().getAgeStudents(age);
        if (allStudents.isEmpty())
            return ResponseEntity.status(404).body(allStudents.append("Учеников с таким возрастом в школе нет."));
        return ResponseEntity.ok(allStudents);
    }

}

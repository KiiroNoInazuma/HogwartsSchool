package ru.hogwarts.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Students;
import ru.hogwarts.school.service.MetCrud;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;

@Tag(name = "Добавить учеников в список школы")
@RestController
@RequestMapping("student")
public class StudentController {
    @Resource
    private MetCrud<Students> studentsMetCrud;
    @Resource
    private StudentService studentService;


    @Operation(summary = "ДОБАВИТЬ СТУДЕНТА В СПИСОК НА РАСПРЕДЕЛЕНИЕ")
    @PostMapping("enrolled")
    public ResponseEntity<Students> enrolled(@RequestBody Students students) {
        return ResponseEntity.ok(studentsMetCrud.creat(students));
    }

    @Operation(summary = "ИЗМЕНИТЬ ДАННЫЕ СТУДЕНТА В СПИСКЕ")
    @PutMapping("replace")
    public ResponseEntity<Students> replaceStudent(@RequestBody Students students) {
        Students edit = studentsMetCrud.edit(students);
        if (edit == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(edit);
    }

    @Operation(summary = "ИСКЛЮЧИТЬ СТУДЕНТА ИЗ СПИСКА")
    @DeleteMapping("exclude")
    ResponseEntity<String> exclude(@RequestParam long id) {
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
    public ResponseEntity<?> all() {
        List<Students> all = studentsMetCrud.getAll();
        if (all.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(all);
    }

    @Operation(summary = "НАЙТИ СТУДЕНТОВ ПО ВОЗРАСТУ")
    @GetMapping("age/{age}")
    public ResponseEntity<List<Students>> getAgeStudents(@PathVariable int age) {
        List<Students> ageStudents = studentService.getAgeStudents(age);
        if (ageStudents.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ageStudents);
    }

    @Operation(summary = "НАЙТИ ВСЕХ СТУДЕНТОВ, ВОЗРАСТ КОТОРЫХ НАХОДИТСЯ" +
            " В ПРОМЕЖУТКЕ, ПРИШЕДШИМ В ЗАПРОСЕ")
    @GetMapping("age/between")
    public ResponseEntity<List<Students>> findByAgeBetween(@RequestParam int begin,
                                                           @RequestParam int end) {
        List<Students> byAgeBetween = studentService.findByAgeBetween(begin, end);
        if (byAgeBetween == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(byAgeBetween);
    }
    @Operation(summary = "НАЙТИ СТУДЕНТОВ ФАКУЛЬТЕТА ПО ID ФАКУЛЬТЕТА")
    @GetMapping("findStudentsByFacultyId")
    public ResponseEntity<Collection<Students>> findStudentsByFacultyId(@RequestParam int id){
        Collection<Students> studentsByFacultyId = studentService.findStudentsByFacultyId(id);
        if (studentsByFacultyId == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(studentsByFacultyId);
    }

}

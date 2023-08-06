package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Students;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService implements MetCrud<Students> {
    private static long idStudent;


    private static Map<Long, Students> STUDENTS_MAP = new HashMap<>();

    public static Map<Long, Students> getStudentsMap() {
        return STUDENTS_MAP;
    }

    public static void setStudentsMap(Map<Long, Students> studentsMap) {
        STUDENTS_MAP = studentsMap;
    }

    public static void setIdStudent(long idStudent) {
        StudentService.idStudent = idStudent;
    }

    @Override
    public Students creat(Students students) {
        STUDENTS_MAP.put(++idStudent, students);
        return students;
    }

    @Override
    public Students find(long id) {
        return STUDENTS_MAP.getOrDefault(id, null);
    }

    @Override
    public Students edit(long id, Students students) {
        return (STUDENTS_MAP.containsKey(id) ? STUDENTS_MAP.put(id, students) : null);
    }

    @Override
    public String delete(long id) {
        Students students = find(id);
        if (STUDENTS_MAP.containsKey(id)) {
            STUDENTS_MAP.remove(id);
            idStudent--;
            return students.name() + " отчислен.";
        } else {
            return "Такой ученик в школе не числиться, либо был отчислен ранее.";
        }
    }

    @Override
    public StringBuilder getAll() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Long, Students> map : STUDENTS_MAP.entrySet()) {
            result.append(map.getKey()).append(": ").append(map.getValue()).append("\n");
        }
        return result;
    }

    public StringBuilder getAgeStudents(int age) {
        StringBuilder result = new StringBuilder();
        STUDENTS_MAP
                .values()
                .stream()
                .filter(students -> students.age() == age)
                .forEach(students -> result.append(students).append("\n"));
        return result;
    }
}

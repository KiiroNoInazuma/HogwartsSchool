package ru.hogwarts.school.model;


import lombok.Getter;
import ru.hogwarts.school.service.StudentService;

import java.util.Map;

@Getter
public class Faculties {
    private String name, color;
    private Map<Long, Students> studentsMap;

    public void setFacultyParam(String name, String color) {
        this.name = name;
        this.color = color;
        studentsMap = StudentService.getStudentsMap();
    }

    public StringBuilder convert() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Long, Students> map : studentsMap.entrySet()) {
            result.append(map.getKey()).append(": ").append(map.getValue()).append("\n");
        }
        return result;
    }
    @Override
    public String toString() {
        return "Факультет: " + name + "; Цвет: " + color + "\nУченики:\n" + convert();
    }


}

package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Students;

import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService implements MetCrud<Students> {
    Logger logger = LoggerFactory.getLogger(StudentService.class);
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
        try {
            logger
                    .info("Вызван метод для добавления студента в список на распределение: Метод -> [{}], Класс -> [{}]",
                            StudentService.class.getMethod("creat", Students.class).getName(), StudentService.class.getSimpleName());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        STUDENTS_MAP.put(++idStudent, students);
        logger.debug("Добавлен ученик: {}", students.toString());
        return students;
    }

    @Override
    public Students find(long id) {
        try {
            logger
                    .info("Вызван метод поиска студента по его учетному номеру: Метод -> [{}], Класс -> [{}]",
                            StudentService.class.getMethod("find", long.class).getName(), StudentService.class.getSimpleName());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return STUDENTS_MAP.getOrDefault(id, null);
    }

    @Override
    public Students edit(long id, Students students) {
        try {
            logger
                    .info("Вызван метод для изменения данных студента: Метод -> [{}], Класс -> [{}]",
                            StudentService.class.getMethod("edit", long.class, Students.class).getName(), StudentService.class.getSimpleName());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return (STUDENTS_MAP.containsKey(id)) ? STUDENTS_MAP.put(id, students) : null;
    }

    @Override
    public String delete(long id) {
        Students students = find(id);
        try {
            logger
                    .info("Вызван метод для исключения студента из списка на распределение: Метод -> [{}], Класс -> [{}]",
                            StudentService.class.getMethod("delete", long.class).getName(), StudentService.class.getSimpleName());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
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
        try {
            logger
                    .info("Вызван метод для отображения всего списка учеников: Метод -> [{}], Класс -> [{}]",
                            StudentService.class.getMethod("getAll").getName(), StudentService.class.getSimpleName());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Long, Students> map : STUDENTS_MAP.entrySet()) {
            result.append(map.getKey()).append(": ").append(map.getValue()).append("\n");
        }
        return result;
    }

    public StringBuilder getAgeStudents(int age) {
        try {
            logger
                    .info("Вызван метод для поиска студента по его возрасту: Метод -> [{}], Класс -> [{}]",
                            StudentService.class.getMethod("getAgeStudents", int.class).getName(), StudentService.class.getSimpleName());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        logger.debug("Для поиска выбран возраст {} лет", age);
        StringBuilder result = new StringBuilder();
        STUDENTS_MAP
                .values()
                .stream()
                .filter(students -> students.age() == age)
                .forEach(students -> result.append(students).append("\n"));
        return result;
    }
}

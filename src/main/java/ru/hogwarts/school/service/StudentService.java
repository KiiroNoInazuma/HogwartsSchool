package ru.hogwarts.school.service;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Students;
import ru.hogwarts.school.repositories.StudentRepository;

import java.util.List;

@Service
public class StudentService implements MetCrud<Students> {
    Logger logger = LoggerFactory.getLogger(StudentService.class);
    @Resource
    private StudentRepository studentRepository;

    @Override
    public Students creat(Students students) {
        studentRepository.save(students);
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
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Students edit(Students students) {
        try {
            logger
                    .info("Вызван метод для изменения данных студента: Метод -> [{}], Класс -> [{}]",
                            StudentService.class.getMethod("edit", Students.class).getName(), StudentService.class.getSimpleName());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return studentRepository.save(students);
    }

    @Override
    public String delete(long id) {
        Students students = find(id);
        if (students != null) {
            studentRepository.deleteById(id);
            return students + " отчислен.";
        } else {
            return "Такой ученик в школе не числиться, либо был отчислен ранее.";
        }
    }

    @Override
    public List<Students> getAll() {
        try {
            logger
                    .info("Вызван метод для отображения всего списка учеников: Метод -> [{}], Класс -> [{}]",
                            StudentService.class.getMethod("getAll").getName(), StudentService.class.getSimpleName());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return studentRepository.findAll();
    }


    public List<Students> getAgeStudents(int age) {
        try {
            logger
                    .info("Вызван метод для поиска студента по его возрасту: Метод -> [{}], Класс -> [{}]",
                            StudentService.class.getMethod("getAgeStudents", int.class).getName(), StudentService.class.getSimpleName());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        logger.debug("Для поиска выбран возраст {} лет", age);
        return studentRepository.findByAge(age);
    }
}

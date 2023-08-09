package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class FacultyService implements MetCrud<Faculties> {
    Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private static final List<Faculties> FACULTIES_LIST = new ArrayList<>();

    @Override
    public Faculties creat(Faculties faculties) {
        try {
            logger
                    .info("Вызван метод для создания факультета: Метод -> [{}], Класс -> [{}]",
                            FacultyService.class
                                    .getMethod("creat", Faculties.class)
                                    .getName(), FacultyService.class
                                    .getSimpleName());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        FACULTIES_LIST.add(faculties);
        StudentService.setStudentsMap(new HashMap<>());
        StudentService.setIdStudent(0);
        return faculties;
    }


    @Override
    public Faculties find(long id) {
        try {
            logger
                    .info("Вызван метод для поиска факультета: Метод -> [{}], Класс -> [{}]",
                            FacultyService.class.getMethod("find", long.class)
                                    .getName(), FacultyService.class
                                    .getSimpleName());
            return FACULTIES_LIST.get((int) id - 1);
        } catch (IndexOutOfBoundsException e) {
            logger.error("Нет такого факультета с id: " + id, e);
            return null;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Faculties edit(long id, Faculties faculties) {
        try {
            logger
                    .info("Вызван метод для редактирования данных факультета: Метод -> [{}], Класс -> [{}]",
                            FacultyService.class.getMethod("edit", long.class, Faculties.class)
                                    .getName(), FacultyService.class
                                    .getSimpleName());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        delete(id);
        return faculties;
    }

    @Override
    public String delete(long id) {
        try {
            logger
                    .info("Вызван метод для расформирования факультета: Метод -> [{}], Класс -> [{}]",
                            FacultyService.class.getMethod("delete", long.class)
                                    .getName(), FacultyService.class
                                    .getSimpleName());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        Faculties faculties = find(id);
        if (faculties != null) {
            String clear = faculties.getName();
            FACULTIES_LIST.remove(faculties);
            return clear + " упразднен.";
        } else {
            return "Такого факультета  в школе нет, либо он был упразднен ранее.";
        }
    }

    @Override
    public StringBuilder getAll() {
        try {
            logger
                    .info("Вызван метод для отображения всех факультетов: Метод -> [{}], Класс -> [{}]",
                            FacultyService.class.getMethod("getAll")
                                    .getName(), FacultyService.class
                                    .getSimpleName());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        StringBuilder result = new StringBuilder();
        for (Faculties faculties : FACULTIES_LIST) {
            result.append(faculties).append("\n");
            if (faculties.getStudentsMap().isEmpty()) result.append("\n");
        }
        return result;
    }

    public List<Faculties> getColorFaculty(String color) {
        try {
            logger
                    .info("Вызван метод для поиска факультета по его цвету: Метод -> [{}], Класс -> [{}]",
                            FacultyService.class.getMethod("getColorFaculty", String.class)
                                    .getName(), FacultyService.class
                                    .getSimpleName());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        logger.debug("Выбран {} цвет факультета", color);
        List<Faculties> list = FACULTIES_LIST.stream().filter(faculties -> faculties.getColor().equals(color)).toList();
        if (list.isEmpty()) return null;
        return list;
    }
}

package ru.hogwarts.school.service;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculties;
import ru.hogwarts.school.repositories.FacultyRepository;

import java.util.List;

@Service
public class FacultyService implements MetCrud<Faculties> {
    Logger logger = LoggerFactory.getLogger(FacultyService.class);
    @Resource
    private FacultyRepository facultiesRepository;

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
        Faculties save = facultiesRepository.save(faculties);
        logger.debug("Создан факультет: {}", save);
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
            return facultiesRepository.findById(id).orElse(null);
        } catch (IndexOutOfBoundsException e) {
            logger.error("Нет такого факультета с id: " + id, e);
            return null;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Faculties edit(Faculties faculties) {
        try {
            logger
                    .info("Вызван метод для редактирования данных факультета: Метод -> [{}], Класс -> [{}]",
                            FacultyService.class.getMethod("edit", Faculties.class)
                                    .getName(), FacultyService.class
                                    .getSimpleName());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return facultiesRepository.save(faculties);
    }

    @Override
    public String delete(long id) {
        Faculties faculties = find(id);
        try {
            logger
                    .info("Вызван метод для расформирования факультета: Метод -> [{}], Класс -> [{}]",
                            FacultyService.class.getMethod("delete", long.class)
                                    .getName(), FacultyService.class
                                    .getSimpleName());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        if (faculties != null) {
            facultiesRepository.deleteById(id);
            return faculties + " упразднен.";
        }else {
            return null;
        }
    }

    @Override
    public List<Faculties> getAll() {
        try {
            logger
                    .info("Вызван метод для отображения всех факультетов: Метод -> [{}], Класс -> [{}]",
                            FacultyService.class.getMethod("getAll")
                                    .getName(), FacultyService.class
                                    .getSimpleName());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return facultiesRepository.findAll();
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
        List<Faculties> list = facultiesRepository.getFacultiesByColor(color);
        if (list.isEmpty()) return null;
        return list;
    }
}

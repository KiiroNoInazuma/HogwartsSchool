package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class FacultyService implements MetCrud<Faculties> {
    private static final List<Faculties> FACULTIES_LIST = new ArrayList<>();

    @Override
    public Faculties creat(Faculties faculties) {
        FACULTIES_LIST.add(faculties);
        StudentService.setStudentsMap(new HashMap<>());
        StudentService.setIdStudent(0);
        return faculties;
    }


    @Override
    public Faculties find(long id) { //следующая часть
        return null;
    }

    @Override
    public Faculties edit(long id, Faculties faculties) {
        return null;
    }

    @Override
    public String delete(long id) {
        return null;
    }

    @Override
    public StringBuilder getAll() {
        StringBuilder result = new StringBuilder();
        for (Faculties faculties : FACULTIES_LIST) {
            result.append(faculties).append("\n");
        }
        return result;
    }


}

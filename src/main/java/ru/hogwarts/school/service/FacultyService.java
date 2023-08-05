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
    public Faculties find(long id) {
        try {
            return FACULTIES_LIST.get((int) id - 1);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public Faculties edit(long id, Faculties faculties) {
        delete(id);
        return faculties;
    }

    @Override
    public String delete(long id) {
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
        StringBuilder result = new StringBuilder();
        for (Faculties faculties : FACULTIES_LIST) {
            result.append(faculties).append("\n");
            if (faculties.getStudentsMap().isEmpty()) result.append("\n");
        }
        return result;
    }

    public List<Faculties> getColorFaculty(String color) {
        List<Faculties> list = FACULTIES_LIST.stream().filter(faculties -> faculties.getColor().equals(color)).toList();
        if (list.isEmpty()) return null;
        return list;
    }
}

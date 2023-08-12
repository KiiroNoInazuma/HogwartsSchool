package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school.model.Students;

import java.util.List;

public interface StudentRepository extends JpaRepository<Students,Long> {
        List<Students> findByAge(int age);
}

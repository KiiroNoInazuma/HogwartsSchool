package ru.hogwarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school.model.Students;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends JpaRepository<Students, Long> {
    List<Students> findByAge(int age);

    List<Students> findByAgeBetween(int begin, int end);
    Collection<Students>findStudentsByFacultyId(long facultyId);
    @Query(value = "SELECT COUNT (1) FROM students", nativeQuery = true)
    int countStudent();
    @Query(value = "SELECT avg(age) FROM students", nativeQuery = true)
    float averageAge();
    @Query(value = "SELECT * FROM students order by age limit 5", nativeQuery = true)
    List<Students> findAllStudents();


}
